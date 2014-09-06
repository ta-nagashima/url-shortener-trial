package jp.co.biglobe.isp.mobile.voiceoption.datasource.mnpin;

import jp.co.biglobe.isp.mobile.biglobedenwa.domain.linkage.BiglobeDenwaMsisdn;
import jp.co.biglobe.isp.mobile.extension.datasource.BaseRepositoryDb;
import jp.co.biglobe.isp.mobile.extension.exception.SystemCheckException;
import jp.co.biglobe.isp.mobile.voiceoption.datasource.mnpin.db.MnpInQueryMapper;
import jp.co.biglobe.isp.mobile.voiceoption.domain.alarm.VoiceOptionAlarmIdentifier;
import jp.co.biglobe.isp.mobile.voiceoption.domain.identification.IdentificationReceiptNumber;
import jp.co.biglobe.isp.mobile.voiceoption.domain.mnpin.*;
import jp.co.biglobe.isp.mobile.voiceoption.domain.voiceengagement.*;
import jp.co.biglobe.lib.plugin.event.RequestEventTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MnpInRepositoryDb extends BaseRepositoryDb<MnpIn, ValidMnpIn> implements MnpInRepository {

    @Autowired
    private MnpInActivationDueDateRepositoryDb mnpInActivationDueDateRepositoryDb;

    @Autowired
    private MnpInPersonalInfoRepositoryDb mnpInPersonalInfoRepositoryDb;

    @Autowired
    private RequestEventTime requestEventTime;

    /**
     * 子エンティティを追加する
     */
    @Override
    protected void childInsert(ValidMnpIn after) {
        mnpInPersonalInfoRepositoryDb.insert(after.getMnpInPersonalInfo());
        mnpInActivationDueDateRepositoryDb.insert(after.getMnpInActivation());
    }

    /**
     * 子エンティティを削除する
     */
    @Override
    protected void childDelete(ValidMnpIn before) {
        mnpInActivationDueDateRepositoryDb.delete(before.getMnpInActivation());
        mnpInPersonalInfoRepositoryDb.delete(before.getMnpInPersonalInfo());
    }

    /**
     * 子エンティティを削除する（削除された場合は true を返す）
     */
    @Override
    protected boolean childDelete(ValidMnpIn before, ValidMnpIn after) {
        boolean deleted = mnpInActivationDueDateRepositoryDb.delete(before.getMnpInActivation(), after.getMnpInActivation());
        if (mnpInPersonalInfoRepositoryDb.delete(before.getMnpInPersonalInfo(), after.getMnpInPersonalInfo())) {
            deleted = true;
        }
        return deleted;
    }

    /**
     * 子エンティティを追加・更新する
     */
    @Override
    protected void childInsertOrUpdate(ValidMnpIn before, ValidMnpIn after) {
        mnpInPersonalInfoRepositoryDb.insertOrUpdate(before.getMnpInPersonalInfo(), after.getMnpInPersonalInfo());
        mnpInActivationDueDateRepositoryDb.insertOrUpdate(before.getMnpInActivation(), after.getMnpInActivation());
    }

    @Autowired
    private MnpInQueryMapper mnpInQueryMapper;

    @Override
    public void delete(VoiceEngagementNumber voiceEngagementNumber) {
        ValidMnpIn before = mnpInQueryMapper.findByVoiceEngagementNumber(voiceEngagementNumber);

        if (before != null) {
            delete(before);
        }
    }

    @Override
    public MnpIn findByVoiceEngagementNumber(VoiceEngagementNumber voiceEngagementNumber) {

        ValidMnpIn validMnpIn = mnpInQueryMapper.findByVoiceEngagementNumber(voiceEngagementNumber);

        if (validMnpIn == null) {
            return new NotExistMnpIn();
        }

        return validMnpIn;
    }

    @Override
    public MnpIn findByVoiceEngagementNumber(VoiceEngagement voiceEngagement) {

        if (voiceEngagement.isNotExist()) {
            return new NotExistMnpIn();
        }

        ValidVoiceEngagement validVoiceEngagement = (ValidVoiceEngagement) voiceEngagement;
        return findByVoiceEngagementNumber(validVoiceEngagement.getVoiceEngagementNumber());
    }

    @Override
    public MnpIn findByIdentificationReceiptNumber(IdentificationReceiptNumber identificationReceiptNumber) {

        ValidMnpIn validMnpIn = mnpInQueryMapper.findByIdentificationReceiptNumber(identificationReceiptNumber);

        if (validMnpIn == null) {
            return new NotExistMnpIn();
        }

        return validMnpIn;
    }

    @Override
    public MnpIn findByVoiceMsisdnUnderValid(VoiceMsisdn voiceMsisdn) {

        java.util.Date date = requestEventTime.getDate();

        List<ValidMnpIn> validMnpInList = mnpInQueryMapper.findByVoiceMsisdnUnderValidForAll(
                voiceMsisdn,
                new VoiceEngagementEndDateTime(date),
                VoiceEngagementStatus.ORDERED,
                VoiceEngagementStatus.ENGAGED,
                VoiceEngagementStatus.DISENGAGED);

        if (validMnpInList == null || validMnpInList.size() == 0) {
            return new NotExistMnpIn();
        }

        return validMnpInList.get(0);
    }

    @Override
    public MnpIn findByBiglobeDenwaMsisdnUnderValid(BiglobeDenwaMsisdn biglobeDenwaMsisdn) {

        java.util.Date date = requestEventTime.getDate();

        List<ValidMnpIn> validMnpInList = mnpInQueryMapper.findByBiglobeDenwaMsisdnUnderValidForAll(
                biglobeDenwaMsisdn,
                new VoiceEngagementEndDateTime(date),
                VoiceEngagementStatus.ORDERED,
                VoiceEngagementStatus.ENGAGED,
                VoiceEngagementStatus.DISENGAGED);

        if (validMnpInList == null || validMnpInList.size() == 0) {
            return new NotExistMnpIn();
        }

        return validMnpInList.get(0);
    }

    @Override
    public ValidMnpIn findByVoiceEngagementNumberForValid(VoiceEngagementNumber voiceEngagementNumber) {

        ValidMnpIn validMnpIn = mnpInQueryMapper.findByVoiceEngagementNumber(voiceEngagementNumber);

        if (validMnpIn == null) {
            throw new SystemCheckException(
                    "データ不整合が発生しています。MNP転入情報が存在しませんでした。", VoiceOptionAlarmIdentifier.DEFAULT
            );
        }

        return validMnpIn;
    }

    @Override
    public ValidMnpIn findByVoiceEngagementNumberForUpdate(VoiceEngagementNumber voiceEngagementNumber) {

        VoiceEngagementNumber voiceEngagementNumberLocked = mnpInQueryMapper.lockByVoiceEngagementNumber(voiceEngagementNumber);
        if (voiceEngagementNumberLocked == null) {
            throw new SystemCheckException(
                    "データ不整合が発生しています。MNP転入情報が存在しませんでした。", VoiceOptionAlarmIdentifier.DEFAULT
            );
        }

        ValidMnpIn validMnpIn = mnpInQueryMapper.findByVoiceEngagementNumber(voiceEngagementNumber);

        return validMnpIn;
    }

}
