package jp.co.biglobe.isp.mobile.ltethreeg.option.charge.datasource.volumechargeengagement;

import jp.co.biglobe.isp.mobile.extension.exception.SystemCheckException;
import jp.co.biglobe.isp.mobile.ltethreeg.domain.alarm.LteThreegAlarmIdentifier;
import jp.co.biglobe.isp.mobile.ltethreeg.domain.ltethreegengagement.LteThreeGEngagementNumber;
import jp.co.biglobe.isp.mobile.ltethreeg.option.charge.datasource.volumechargeengagement.db.VolumeChargeEngagementRepositoryQueryMapper;
import jp.co.biglobe.isp.mobile.ltethreeg.option.charge.datasource.volumechargeengagement.volumechargeengagementnumber.VolumeChargeEngagementNumberQueryMapper;
import jp.co.biglobe.isp.mobile.ltethreeg.option.charge.domain.volumechargeengagement.*;
import jp.co.biglobe.lib.plugin.event.RequestEventProcess;
import jp.co.biglobe.lib.plugin.event.RequestEventTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class VolumeChargeEngagementRepositoryDb implements VolumeChargeEngagementRepository {

    @Autowired
    VolumeChargeEngagementRepositoryQueryMapper volumeChargeEngagementRepositoryQueryMapper;

    @Autowired
    private RequestEventTime requestEventTime;

    @Autowired
    private RequestEventProcess requestEventProcess;


    @Autowired
    private VolumeChargeEngagementNumberQueryMapper volumeChargeEngagementNumberQueryMapper;

    @Override
    public ValidVolumeChargeEngagementEntity create(LteThreeGEngagementNumber lteThreeGEngagementNumber,
                                               VolumeChargePurchasedVolumeNumber volumeChargePurchasedVolumeNumber) {

        return ValidVolumeChargeEngagementEntity.purchase(
                volumeChargeEngagementNumberQueryMapper.create(),
                lteThreeGEngagementNumber,
                volumeChargePurchasedVolumeNumber
        );

    }

    @Override
    public void purchase(ValidVolumeChargeEngagementEntity validVolumeChargeEngagementEntity) {

        volumeChargeEngagementRepositoryQueryMapper.insertPurchaseEvent(
                requestEventTime.getDate(),
                requestEventProcess.getValue(),
                validVolumeChargeEngagementEntity);

        insertOrUpdateState(validVolumeChargeEngagementEntity);

    }

    private void insertOrUpdateState(ValidVolumeChargeEngagementEntity validVolumeChargeEngagementEntity){

        ValidVolumeChargeEngagementEntity beforeValidVolumeChargeEngagementEntity =
                volumeChargeEngagementRepositoryQueryMapper.findByLteThreeGEngagementNumber(validVolumeChargeEngagementEntity.getLteThreeGEngagementNumber());

        if(beforeValidVolumeChargeEngagementEntity == null){
            volumeChargeEngagementRepositoryQueryMapper.insertVolumeState(
                    requestEventTime.getDate(),
                    validVolumeChargeEngagementEntity);

            return;
        }

        volumeChargeEngagementRepositoryQueryMapper.updateStateForPurchase(
                requestEventTime.getDate(),
                validVolumeChargeEngagementEntity);

    }

    @Override
    public void complete(AfterVolumeChargeEngagementEntity afterVolumeChargeEngagementEntity) {

        volumeChargeEngagementRepositoryQueryMapper.insertCompleteEvent(
                requestEventTime.getDate(),
                requestEventProcess.getValue(),
                afterVolumeChargeEngagementEntity);

        volumeChargeEngagementRepositoryQueryMapper.updateCompleteState(
                requestEventTime.getDate(),
                afterVolumeChargeEngagementEntity);

    }

    @Override
    public void completePlanChange(AfterVolumeChargeEngagementEntity afterVolumeChargeEngagementEntity) {

        volumeChargeEngagementRepositoryQueryMapper.insertCompletePlanChangeEvent(
                requestEventTime.getDate(),
                requestEventProcess.getValue(),
                afterVolumeChargeEngagementEntity);

        volumeChargeEngagementRepositoryQueryMapper.updateCompleteState(
                requestEventTime.getDate(),
                afterVolumeChargeEngagementEntity);

    }

    @Override
    public VolumeChargeEngagementEntity findByLteThreeGEngagementNumber(LteThreeGEngagementNumber lteThreeGEngagementNumber){

        ValidVolumeChargeEngagementEntity validVolumeChargeEngagementEntity =
                volumeChargeEngagementRepositoryQueryMapper.findByLteThreeGEngagementNumber(lteThreeGEngagementNumber);

        if(validVolumeChargeEngagementEntity == null){
            return new NotExistVolumeChargeEngagementEntity();
        }

        return validVolumeChargeEngagementEntity;
    }

    @Override
    public ValidVolumeChargeEngagementEntity findByLteThreeGEngagementNumberForValid(LteThreeGEngagementNumber lteThreeGEngagementNumber){

        ValidVolumeChargeEngagementEntity validVolumeChargeEngagementEntity =
                volumeChargeEngagementRepositoryQueryMapper.findByLteThreeGEngagementNumber(lteThreeGEngagementNumber);

        if(validVolumeChargeEngagementEntity == null){
            throw new SystemCheckException(
                    "データ不整合が発生しています。ボリュームチャージ契約が存在しませんでした。", LteThreegAlarmIdentifier.DEFAULT
            );
        }

        return validVolumeChargeEngagementEntity;
    }

}
