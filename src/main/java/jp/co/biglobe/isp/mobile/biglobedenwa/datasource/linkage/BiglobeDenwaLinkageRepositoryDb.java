package jp.co.biglobe.isp.mobile.biglobedenwa.datasource.linkage;

import jp.co.biglobe.isp.mobile.biglobedenwa.datasource.linkage.db.LinkageMapper;
import jp.co.biglobe.isp.mobile.biglobedenwa.domain.linkage.*;
import jp.co.biglobe.isp.mobile.extension.exception.SystemCheckException;
import jp.co.biglobe.isp.mobile.voiceoption.domain.alarm.VoiceOptionAlarmIdentifier;
import jp.co.biglobe.lib.plugin.event.RequestEventProcess;
import jp.co.biglobe.lib.plugin.event.RequestEventTime;
import jp.co.biglobe.lib.publication.datasource.EventType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class BiglobeDenwaLinkageRepositoryDb implements BiglobeDenwaLinkageRepository {

    @Autowired
    private LinkageMapper linkageMapper;

    @Autowired
    private RequestEventTime requestEventTime;

    @Autowired
    private RequestEventProcess requestEventProcess;


    private void insert(ValidBiglobeDenwaLinkage validLinkage) {

        linkageMapper.insertEvent(
                EventType.INSERT,
                requestEventTime.getDate(),
                requestEventProcess.getValue(),
                validLinkage
        );

        linkageMapper.insertState(
                validLinkage
        );

    }

    private void update(ValidBiglobeDenwaLinkage validLinkage) {
        linkageMapper.insertEvent(
                EventType.UPDATE,
                requestEventTime.getDate(),
                requestEventProcess.getValue(),
                validLinkage
        );

        linkageMapper.updateState(validLinkage);
    }

    private void delete(ValidBiglobeDenwaLinkage validLinkage) {

        linkageMapper.insertEvent(
                EventType.DELETE,
                requestEventTime.getDate(),
                requestEventProcess.getValue(),
                validLinkage
        );

        linkageMapper.deleteState(validLinkage);
    }

    private void verifyForValid(ValidBiglobeDenwaLinkage validLinkage) {

        Optional.ofNullable(validLinkage).orElseThrow(
                () -> new SystemCheckException("該当するでんわ連携なし", VoiceOptionAlarmIdentifier.DEFAULT)
        );

    }

    private void verifyForValid(BiglobeDenwaMsisdn msisdn) {

        Optional.ofNullable(msisdn).orElseThrow(
                () -> new SystemCheckException("該当するでんわ連携なし", VoiceOptionAlarmIdentifier.DEFAULT)
        );
    }


    @Override
    public BiglobeDenwaLinkage findByMsisdn(BiglobeDenwaMsisdn msisdn) {

        ValidBiglobeDenwaLinkage validLinkage = linkageMapper.findByMsisdn(msisdn);

        if (validLinkage == null) {
            return new NotExistBiglobeDenwaLinkage();
        }

        return validLinkage;
    }

    private ValidBiglobeDenwaLinkage findByMsisdnForValid(BiglobeDenwaMsisdn biglobeDenwaMsisdn) {
        ValidBiglobeDenwaLinkage validLinkage = linkageMapper.findByMsisdn(biglobeDenwaMsisdn);

        verifyForValid(validLinkage);

        return validLinkage;
    }

    @Override
    public BiglobeDenwaLinkage findByMsisdnForUpdate(BiglobeDenwaMsisdn biglobeDenwaMsisdn) {

        ValidBiglobeDenwaLinkage linkage = linkageMapper.findByMsisdn(biglobeDenwaMsisdn);

        if (linkage == null) {
            return new NotExistBiglobeDenwaLinkage();
        } else {
            linkageMapper.lockByMsisdn(biglobeDenwaMsisdn);
            return linkage;
        }
    }

    @Override
    public void applyFromApp(BiglobeDenwaLinkage before, ValidBiglobeDenwaLinkage after) {
        if (before.isNotExist()) {
            insert(after);
        } else {
            update(after);
        }
    }

    @Override
    public void applyFromBatch(ValidBiglobeDenwaLinkage validLinkage) {
        insert(validLinkage);
    }

    @Override
    public void remove(ValidBiglobeDenwaLinkage validLinkage) {
        delete(validLinkage);
    }

    @Override
    public void updateRegistered(ValidBiglobeDenwaLinkage validLinkage) {
        update(validLinkage);
    }

    @Override
    public void updateWaitingRegisterResult(ValidBiglobeDenwaLinkage validLinkage) {
        update(validLinkage);
    }

    @Override
    public void updateWaitingReregister(ValidBiglobeDenwaLinkage validLinkage) {
        update(validLinkage);
    }

    @Override
    public void updateWaitingReregisterResult(ValidBiglobeDenwaLinkage validLinkage) {
        update(validLinkage);
    }

    @Override
    public void updateWaitingRemove(ValidBiglobeDenwaLinkage validLinkage) {
        update(validLinkage);
    }

    @Override
    public void updateWaitingRemoveResult(ValidBiglobeDenwaLinkage validLinkage) {
        update(validLinkage);
    }

    @Override
    public void updateUnableToRegister(ValidBiglobeDenwaLinkage validLinkage) {
        update(validLinkage);
    }

    @Override
    public void updateRegisteredInOther(ValidBiglobeDenwaLinkage validLinkage) {
        update(validLinkage);
    }
}
