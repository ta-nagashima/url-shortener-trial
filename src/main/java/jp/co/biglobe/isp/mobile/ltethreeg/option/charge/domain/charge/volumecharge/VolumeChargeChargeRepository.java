package jp.co.biglobe.isp.mobile.ltethreeg.option.charge.domain.charge.volumecharge;

import jp.co.biglobe.isp.auth.domain.user.UserId;
import jp.co.biglobe.isp.mobile.ltethreeg.option.charge.domain.volumechargeengagement.VolumeChargePurchasedVolumeNumber;
import jp.co.biglobe.isp.mobile.ltethreeg.option.charge.domain.volumechargeengagement.ValidVolumeChargeEngagementEntity;

public interface VolumeChargeChargeRepository {
    public void register(ValidVolumeChargeEngagementEntity validVolumeChargeEngagementEntity,UserId userId,VolumeChargePurchasedVolumeNumber volumeChargePurchasedVolumeNumber);
}
