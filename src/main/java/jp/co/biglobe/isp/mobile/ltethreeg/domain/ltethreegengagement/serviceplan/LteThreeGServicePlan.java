package jp.co.biglobe.isp.mobile.ltethreeg.domain.ltethreegengagement.serviceplan;

import jp.co.biglobe.isp.mobile.ltethreeg.domain.connectperformance.connectcontrolpolicy.ConnectControlSpeedLimitPolicy;
import jp.co.biglobe.isp.mobile.ltethreeg.domain.connectperformance.connectcontrolpolicy.ConnectControlSpeedNoLimitPolicy;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@ToString(includeFieldNames = false)
@EqualsAndHashCode
@AllArgsConstructor
public class LteThreeGServicePlan {

    @Getter
    private final LteThreeGServicePlanChange lteThreeGServicePlanChange;

    @Getter
    private final LteThreeGServicePlanCode afterLteThreeGServicePlanCode;

    public LteThreeGServicePlan(
            ValidLteThreeGServicePlanChange validLteThreeGServicePlanChange,
            LteThreeGServicePlanCode afterLteThreeGServicePlanCode){

        this.lteThreeGServicePlanChange = validLteThreeGServicePlanChange == null ?
                new NotExistLteThreeGServicePlanChange() : validLteThreeGServicePlanChange;

        this.afterLteThreeGServicePlanCode = afterLteThreeGServicePlanCode;
    }

    public LteThreeGServicePlanCode getCurrentServicePlanCode(){

        if(lteThreeGServicePlanChange.isChangeReservation()){
            return getValidLteThreeGServicePlanChange().getBeforeLteThreeGServicePlanCode();
        }

        return afterLteThreeGServicePlanCode;
    }

    private ValidLteThreeGServicePlanChange getValidLteThreeGServicePlanChange(){
        return (ValidLteThreeGServicePlanChange)lteThreeGServicePlanChange;
    }

    public ConnectControlSpeedLimitPolicy getConnectControlLimitPolicy(){
        return getCurrentServicePlanCode().getConnectControlSpeedLimitPolicy();
    }

    public ConnectControlSpeedNoLimitPolicy getConnectControlNoLimitPolicy(){
        return getCurrentServicePlanCode().getConnectControlSpeedNoLimitPolicy();
    }

    public boolean isSpeedChargeValidPlan(){
        return getCurrentServicePlanCode().isSpeedChargeValid();
    }

    public boolean isSpeedChargeInValidPlan(){
        return !isSpeedChargeValidPlan();
    }

    public boolean isVolumeChargeValidPlan(){
        return getCurrentServicePlanCode().isVolumeChargeValid();
    }

    public boolean isVolumeChargeInValidPlan(){
        return !isVolumeChargeValidPlan();
    }

}
