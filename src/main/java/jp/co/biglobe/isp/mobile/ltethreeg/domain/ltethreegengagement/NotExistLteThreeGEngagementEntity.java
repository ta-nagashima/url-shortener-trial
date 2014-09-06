package jp.co.biglobe.isp.mobile.ltethreeg.domain.ltethreegengagement;

import jp.co.biglobe.isp.mobile.ltethreeg.option.charge.domain.volumechargeengagement.VolumeChargePurchaseCheckStatus;
import jp.co.biglobe.isp.mobile.ltethreeg.option.charge.domain.speedchargeengagement.SpeedChargeEngagementEntity;
import jp.co.biglobe.isp.mobile.ltethreeg.option.charge.domain.speedchargeengagement.SpeedChargePurchaseCheckStatus;
import jp.co.biglobe.isp.mobile.ltethreeg.option.charge.domain.volumechargeengagement.VolumeChargeEngagementEntity;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@ToString(includeFieldNames=false)
@EqualsAndHashCode
@AllArgsConstructor
public class NotExistLteThreeGEngagementEntity implements LteThreeGEngagementEntity {

    @Override
    public boolean isValid() { return false; }

    @Override
    public boolean isInvalid() { return true; }

    @Override
    public VolumeChargePurchaseCheckStatus verifyVolumeChargePurchase(VolumeChargeEngagementEntity volumeChargeEngagementEntity) {
        return VolumeChargePurchaseCheckStatus.NOT_EXIST;
    }

    @Override
    public SpeedChargePurchaseCheckStatus verifySpeedChargePurchase(SpeedChargeEngagementEntity speedChargeEngagementEntity){
        return SpeedChargePurchaseCheckStatus.NOT_EXIST;
    }

}
