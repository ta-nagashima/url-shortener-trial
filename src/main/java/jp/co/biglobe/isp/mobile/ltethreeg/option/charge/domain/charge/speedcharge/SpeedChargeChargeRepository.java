package jp.co.biglobe.isp.mobile.ltethreeg.option.charge.domain.charge.speedcharge;

import jp.co.biglobe.isp.auth.domain.user.UserId;
import jp.co.biglobe.isp.mobile.ltethreeg.option.charge.domain.speedchargeengagement.SpeedChargePurchasedVolumeNumber;
import jp.co.biglobe.isp.mobile.ltethreeg.option.charge.domain.speedchargeengagement.ValidSpeedChargeEngagementEntity;

public interface SpeedChargeChargeRepository {
    public void register(ValidSpeedChargeEngagementEntity validSpeedChargeEngagementEntity, UserId userId, SpeedChargePurchasedVolumeNumber speedChargePurchasedVolumeNumber);
}
