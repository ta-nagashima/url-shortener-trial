package jp.co.biglobe.isp.mobile.ltethreeg.option.charge.datasource.volumechargeengagement;

import jp.co.biglobe.isp.auth.domain.user.UserId;
import jp.co.biglobe.isp.mobile.ltethreeg.option.charge.datasource.volumechargeengagement.scenario.VolumeChargeSendMailScenario;
import jp.co.biglobe.isp.mobile.ltethreeg.option.charge.domain.volumechargeengagement.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class VolumeChargeSendMailTransferScenario implements VolumeChargeSendMailTransfer {

    @Autowired
    VolumeChargeSendMailScenario volumeChargeSendMailScenario;

    @Override
    public void send(ValidVolumeChargeEngagementEntity validVolumeChargeEngagementEntity,UserId userId,VolumeChargePurchasedVolumeNumber volumeChargePurchasedVolumeNumber) {
        volumeChargeSendMailScenario.send(validVolumeChargeEngagementEntity,userId, volumeChargePurchasedVolumeNumber);
    }
}
