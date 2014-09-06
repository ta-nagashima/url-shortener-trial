package jp.co.biglobe.isp.mobile.ltethreeg.option.charge.domain.volumechargeengagement;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@ToString(includeFieldNames = false)
@EqualsAndHashCode
@AllArgsConstructor
public class NotExistVolumeChargeEngagementEntity implements VolumeChargeEngagementEntity{

    @Override
    public boolean isNotExist(){
        return true;
    }

    @Override
    public VolumeChargePurchaseCheckStatus verifyVolumeChargePurchase() {
        return VolumeChargePurchaseCheckStatus.UN_PURCHASE;
    }
}
