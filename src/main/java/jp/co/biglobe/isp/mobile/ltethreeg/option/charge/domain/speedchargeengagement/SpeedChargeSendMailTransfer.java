package jp.co.biglobe.isp.mobile.ltethreeg.option.charge.domain.speedchargeengagement;

import jp.co.biglobe.isp.auth.domain.user.UserId;

public interface SpeedChargeSendMailTransfer {

    public void purchase(
            ValidSpeedChargeEngagementEntity validSpeedChargeEngagementEntity,
            UserId userId,
            SpeedChargePurchasedVolumeNumber speedChargePurchasedVolumeNumber);
}
