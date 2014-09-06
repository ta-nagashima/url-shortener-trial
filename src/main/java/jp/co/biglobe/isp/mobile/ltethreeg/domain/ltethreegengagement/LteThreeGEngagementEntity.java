package jp.co.biglobe.isp.mobile.ltethreeg.domain.ltethreegengagement;

import jp.co.biglobe.isp.mobile.ltethreeg.option.charge.domain.volumechargeengagement.VolumeChargePurchaseCheckStatus;
import jp.co.biglobe.isp.mobile.ltethreeg.option.charge.domain.speedchargeengagement.SpeedChargeEngagementEntity;
import jp.co.biglobe.isp.mobile.ltethreeg.option.charge.domain.speedchargeengagement.SpeedChargePurchaseCheckStatus;
import jp.co.biglobe.isp.mobile.ltethreeg.option.charge.domain.volumechargeengagement.VolumeChargeEngagementEntity;

public interface LteThreeGEngagementEntity {
    public boolean isValid();

    public boolean isInvalid();

    public VolumeChargePurchaseCheckStatus verifyVolumeChargePurchase(VolumeChargeEngagementEntity volumeChargeEngagementEntity);

    public SpeedChargePurchaseCheckStatus verifySpeedChargePurchase(SpeedChargeEngagementEntity speedChargeEngagementEntity);
}
