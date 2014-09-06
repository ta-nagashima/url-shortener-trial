package jp.co.biglobe.isp.mobile.ltethreeg.option.charge.domain.volumechargeengagement;

import jp.co.biglobe.isp.auth.domain.user.UserId;

public interface VolumeChargeSendMailTransfer {
    public void send(ValidVolumeChargeEngagementEntity validVolumeChargeEngagementEntity,UserId userId,VolumeChargePurchasedVolumeNumber volumeChargePurchasedVolumeNumber);
}
