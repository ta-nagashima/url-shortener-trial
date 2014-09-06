package jp.co.biglobe.isp.mobile.ltethreeg.option.charge.domain.speedchargeengagement;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@ToString(includeFieldNames = false)
@EqualsAndHashCode
@AllArgsConstructor
public class NotExistSpeedChargeEngagementEntity implements SpeedChargeEngagementEntity{

    @Override
    public boolean isNotExist(){
        return true;
    }

    @Override
    public SpeedChargePurchaseCheckStatus verifySpeedChargePurchase(){
        return SpeedChargePurchaseCheckStatus.UN_PURCHASE;
    }
}
