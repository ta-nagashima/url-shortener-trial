package jp.co.biglobe.isp.mobile.ltethreeg.domain.ltethreegengagement;

import jp.co.biglobe.isp.auth.domain.user.UserId;
import jp.co.biglobe.isp.mobile.ltethreeg.domain.connectperformance.connectcontrolpolicy.ConnectControlSpeedLimitPolicy;
import jp.co.biglobe.isp.mobile.ltethreeg.domain.connectperformance.connectcontrolpolicy.ConnectControlSpeedNoLimitPolicy;
import jp.co.biglobe.isp.mobile.ltethreeg.domain.ltethreegengagement.serviceplan.LteThreeGServicePlan;
import jp.co.biglobe.isp.mobile.ltethreeg.domain.ltethreegengagement.status.LteThreeGEngagementStatus;
import jp.co.biglobe.isp.mobile.ltethreeg.option.charge.domain.volumechargeengagement.VolumeChargePurchaseCheckStatus;
import jp.co.biglobe.isp.mobile.ltethreeg.option.charge.domain.speedchargeengagement.SpeedChargeEngagementEntity;
import jp.co.biglobe.isp.mobile.ltethreeg.option.charge.domain.speedchargeengagement.SpeedChargePurchaseCheckStatus;
import jp.co.biglobe.isp.mobile.ltethreeg.option.charge.domain.volumechargeengagement.VolumeChargeEngagementEntity;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@ToString(includeFieldNames = false)
@EqualsAndHashCode
@AllArgsConstructor
public class ValidLteThreeGEngagementEntity implements LteThreeGEngagementEntity {

    @Getter
    private final LteThreeGEngagementNumber lteThreeGEngagementNumber;

    private final LteThreeGEngagementStatus lteThreeGEngagementStatus;

    @Getter
    private final LteThreeGOrderType lteThreeGOrderType;

    @Getter
    private final UserId userId;

    @Getter
    private final LteThreeGServicePlan lteThreeGServicePlan;

    @Getter
    private final ConnectControlExemption connectControlExemption;

    @Override
    public boolean isValid() { return true; }

    @Override
    public boolean isInvalid() { return false; }

    private ValidLteThreeGEngagementEntity create(LteThreeGServicePlan lteThreeGServicePlan){

        return new ValidLteThreeGEngagementEntity(
                lteThreeGEngagementNumber,
                lteThreeGEngagementStatus,
                lteThreeGOrderType,
                userId,
                lteThreeGServicePlan,
                connectControlExemption
        );
    }

    public ConnectControlSpeedLimitPolicy getConnectControlLimitPolicy(){
        return lteThreeGServicePlan.getConnectControlLimitPolicy();
    }

    public ConnectControlSpeedNoLimitPolicy getConnectControlNoLimitPolicy(){
        return lteThreeGServicePlan.getConnectControlNoLimitPolicy();
    }

    public boolean isConnectControlPolicyExemptionForLimit(){

        if(lteThreeGEngagementStatus.isConnectControlPolicyExemption()){
            return true;
        }

        return connectControlExemption.isExemption();

    }

    public boolean isConnectControlPolicyExemptionForLimitRelease(){
        return lteThreeGEngagementStatus.isConnectControlPolicyExemption();
    }

    public ValidLteThreeGEngagementEntity planChange(LteThreeGServicePlan lteThreeGServicePlan){
        return create(lteThreeGServicePlan);
    }

    public boolean isSpeedChargeValidPlan(){
        return lteThreeGServicePlan.isSpeedChargeValidPlan();
    }

    public boolean isVolumeChargeValidPlan(){
        return lteThreeGServicePlan.isVolumeChargeValidPlan();
    }


    @Override
    public VolumeChargePurchaseCheckStatus verifyVolumeChargePurchase(VolumeChargeEngagementEntity volumeChargeEngagementEntity) {
        if(lteThreeGEngagementStatus.isOrdered()){
            return VolumeChargePurchaseCheckStatus.ORDERED;
        }

        if(lteThreeGEngagementStatus.isDisEngaged()){
            return VolumeChargePurchaseCheckStatus.NOT_EXIST;
        }

        if(lteThreeGServicePlan.isVolumeChargeInValidPlan()){
            return VolumeChargePurchaseCheckStatus.INVALID_SERVICE_PLAN;
        }

        return volumeChargeEngagementEntity.verifyVolumeChargePurchase();

    }

    @Override
    public SpeedChargePurchaseCheckStatus verifySpeedChargePurchase(SpeedChargeEngagementEntity speedChargeEngagementEntity){

        if(lteThreeGEngagementStatus.isOrdered()){
            return SpeedChargePurchaseCheckStatus.ORDERED;
        }

        if(lteThreeGEngagementStatus.isDisEngaged()){
            return SpeedChargePurchaseCheckStatus.NOT_EXIST;
        }

        if(lteThreeGServicePlan.isSpeedChargeInValidPlan()){
            return SpeedChargePurchaseCheckStatus.INVALID_SERVICE_PLAN;
        }

        return speedChargeEngagementEntity.verifySpeedChargePurchase();

    }

}
