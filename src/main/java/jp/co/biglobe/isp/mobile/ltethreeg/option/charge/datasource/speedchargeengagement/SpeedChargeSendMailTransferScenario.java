package jp.co.biglobe.isp.mobile.ltethreeg.option.charge.datasource.speedchargeengagement;

import jp.co.biglobe.isp.auth.domain.user.UserId;
import jp.co.biglobe.isp.mobile.ltethreeg.option.charge.datasource.speedchargeengagement.scenario.SpeedChargeSendMailScenario;
import jp.co.biglobe.isp.mobile.ltethreeg.option.charge.domain.speedchargeengagement.SpeedChargePurchasedVolumeNumber;
import jp.co.biglobe.isp.mobile.ltethreeg.option.charge.domain.speedchargeengagement.SpeedChargeSendMailTransfer;
import jp.co.biglobe.isp.mobile.ltethreeg.option.charge.domain.speedchargeengagement.ValidSpeedChargeEngagementEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class SpeedChargeSendMailTransferScenario implements SpeedChargeSendMailTransfer {

    @Autowired
    SpeedChargeSendMailScenario speedChargeSendMailScenario;

    @Override
    public void purchase(
            ValidSpeedChargeEngagementEntity validSpeedChargeEngagementEntity,
            UserId userId,
            SpeedChargePurchasedVolumeNumber speedChargePurchasedVolumeNumber){

        speedChargeSendMailScenario.send(validSpeedChargeEngagementEntity, userId, speedChargePurchasedVolumeNumber);
    }
}
