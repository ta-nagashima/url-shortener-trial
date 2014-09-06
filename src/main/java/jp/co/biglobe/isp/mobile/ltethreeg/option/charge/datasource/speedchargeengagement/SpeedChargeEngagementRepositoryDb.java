package jp.co.biglobe.isp.mobile.ltethreeg.option.charge.datasource.speedchargeengagement;


import jp.co.biglobe.isp.mobile.extension.exception.SystemCheckException;
import jp.co.biglobe.isp.mobile.ltethreeg.domain.alarm.LteThreegAlarmIdentifier;
import jp.co.biglobe.isp.mobile.ltethreeg.domain.ltethreegengagement.LteThreeGEngagementNumber;
import jp.co.biglobe.isp.mobile.ltethreeg.option.charge.datasource.speedchargeengagement.db.SpeedChargeEngagementRepositoryQueryMapper;
import jp.co.biglobe.isp.mobile.ltethreeg.option.charge.datasource.speedchargeengagement.speedchargeengagementnumber.SpeedChargeEngagementNumberQueryMapper;
import jp.co.biglobe.isp.mobile.ltethreeg.option.charge.domain.speedchargeengagement.*;
import jp.co.biglobe.lib.plugin.event.RequestEventProcess;
import jp.co.biglobe.lib.plugin.event.RequestEventTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class SpeedChargeEngagementRepositoryDb implements SpeedChargeEngagementRepository {

    @Autowired
    SpeedChargeEngagementRepositoryQueryMapper speedChargeEngagementRepositoryQueryMapper;

    @Autowired
    SpeedChargeEngagementNumberQueryMapper speedChargeEngagementNumberQueryMapper;

    @Autowired
    private RequestEventTime requestEventTime;

    @Autowired
    private RequestEventProcess requestEventProcess;

    @Override
    public SpeedChargeEngagementEntity findByLteThreeGEngagementNumber(LteThreeGEngagementNumber lteThreeGEngagementNumber) {


        ValidSpeedChargeEngagementEntity validSpeedChargeEngagementEntity =
                speedChargeEngagementRepositoryQueryMapper.findByLteThreeGEngagementNumber(lteThreeGEngagementNumber);


        if (validSpeedChargeEngagementEntity == null) {
            return new NotExistSpeedChargeEngagementEntity();
        }

        return validSpeedChargeEngagementEntity;
    }

    @Override
    public ValidSpeedChargeEngagementEntity findByLteThreeGEngagementNumberForValid(LteThreeGEngagementNumber lteThreeGEngagementNumber) {


        ValidSpeedChargeEngagementEntity validSpeedChargeEngagementEntity =
                speedChargeEngagementRepositoryQueryMapper.findByLteThreeGEngagementNumber(lteThreeGEngagementNumber);


        if (validSpeedChargeEngagementEntity == null) {
            throw new SystemCheckException(
                    "データ不整合が発生しています。スピードチャージ契約が存在しませんでした。", LteThreegAlarmIdentifier.DEFAULT
            );
        }

        return validSpeedChargeEngagementEntity;
    }


    @Override
    public ValidSpeedChargeEngagementEntity create(LteThreeGEngagementNumber lteThreeGEngagementNumber, SpeedChargePurchasedVolumeNumber speedChargePurchasedVolumeNumber) {

        return ValidSpeedChargeEngagementEntity.purchase(
                speedChargeEngagementNumberQueryMapper.create(),
                lteThreeGEngagementNumber,
                speedChargePurchasedVolumeNumber
        );

    }

    @Override
    public void insert(ValidSpeedChargeEngagementEntity validSpeedChargeEngagementEntity) {


        speedChargeEngagementRepositoryQueryMapper.insertPurchaseEvent(
                requestEventTime.getDate(),
                requestEventProcess.getValue(),
                validSpeedChargeEngagementEntity
        );

        insertOrUpdateState(validSpeedChargeEngagementEntity);


    }

    private void insertOrUpdateState(ValidSpeedChargeEngagementEntity validSpeedChargeEngagementEntity){
        ValidSpeedChargeEngagementEntity beforeSpeedChargeEngagementEntity =
                speedChargeEngagementRepositoryQueryMapper.findByLteThreeGEngagementNumber(validSpeedChargeEngagementEntity.getLteThreeGEngagementNumber());

        if(beforeSpeedChargeEngagementEntity == null){
            speedChargeEngagementRepositoryQueryMapper.insertState(
                    requestEventTime.getDate(),
                    validSpeedChargeEngagementEntity
            );

            return;
        }

        speedChargeEngagementRepositoryQueryMapper.updateStateForPurchase(
                requestEventTime.getDate(),
                validSpeedChargeEngagementEntity
        );
    }


    @Override
    public void planChange(AfterSpeedChargeEngagementEntity afterSpeedChargeEngagementEntity) {
        update(afterSpeedChargeEngagementEntity);
    }

    @Override
    public void complete(AfterSpeedChargeEngagementEntity afterSpeedChargeEngagementEntity) {
        update(afterSpeedChargeEngagementEntity);
    }

    private void update(AfterSpeedChargeEngagementEntity afterSpeedChargeEngagementEntity) {

        speedChargeEngagementRepositoryQueryMapper.insertCompleteEvent(
                requestEventTime.getDate(),
                requestEventProcess.getValue(),
                afterSpeedChargeEngagementEntity
        );

        speedChargeEngagementRepositoryQueryMapper.updateState(
                requestEventTime.getDate(),
                afterSpeedChargeEngagementEntity
        );

    }
}
