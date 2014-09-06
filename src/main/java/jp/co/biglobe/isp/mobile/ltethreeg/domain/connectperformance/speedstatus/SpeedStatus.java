package jp.co.biglobe.isp.mobile.ltethreeg.domain.connectperformance.speedstatus;

import jp.co.biglobe.isp.mobile.ltethreeg.domain.ltethreegengagement.ValidLteThreeGEngagementEntity;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@ToString(includeFieldNames=false)
@EqualsAndHashCode
@AllArgsConstructor
public class SpeedStatus {

    @Getter
    private final LimitStatus1Month limitStatus1Month;

    @Getter
    private final LimitStatus72Hour limitStatus72Hour;

    @Getter
    private final VolumeChargeStatus volumeChargeStatus;

    @Getter
    private final SpeedChargeStatus speedChargeStatus;

    public SpeedStatus limit72Hour(){
        return new SpeedStatus(
                limitStatus1Month,
                limitStatus72Hour.LIMITED,
                volumeChargeStatus,
                speedChargeStatus
        );
    }

    public SpeedStatus limitRelease72Hour() {
        return new SpeedStatus(
                limitStatus1Month,
                limitStatus72Hour.NO_LIMIT,
                volumeChargeStatus,
                speedChargeStatus
        );
    }

    public SpeedStatus limit1Month(){
        return new SpeedStatus(
                LimitStatus1Month.LIMITED,
                limitStatus72Hour,
                volumeChargeStatus,
                speedChargeStatus
        );
    }

    public SpeedStatus limitRelease1Month() {
        return new SpeedStatus(
                LimitStatus1Month.NO_LIMIT,
                limitStatus72Hour,
                volumeChargeStatus,
                speedChargeStatus
        );
    }

    public SpeedStatus volumeChargeUnCharge(){
        return new SpeedStatus(
                limitStatus1Month,
                limitStatus72Hour,
                VolumeChargeStatus.UN_CHARGE,
                speedChargeStatus
        );
    }

    public SpeedStatus volumeChargeCharging(){
        return new SpeedStatus(
                limitStatus1Month,
                limitStatus72Hour,
                VolumeChargeStatus.CHARGING,
                speedChargeStatus
        );
    }

    public SpeedStatus volumeChargeStopped(){
        return new SpeedStatus(
                limitStatus1Month,
                limitStatus72Hour,
                VolumeChargeStatus.STOPPED,
                speedChargeStatus
        );
    }

    public SpeedStatus volumeChargeComplete(){
        return new SpeedStatus(
                limitStatus1Month,
                limitStatus72Hour,
                VolumeChargeStatus.COMPLETE,
                speedChargeStatus
        );
    }

    public SpeedStatus speedChargePurchase(){
        return new SpeedStatus(
                limitStatus1Month,
                limitStatus72Hour,
                volumeChargeStatus,
                SpeedChargeStatus.CHARGING
        );
    }

    public SpeedStatus speedChargeComplete(){
        return new SpeedStatus(
                limitStatus1Month,
                limitStatus72Hour,
                volumeChargeStatus,
                SpeedChargeStatus.COMPLETE
        );
    }

    public boolean isChangeConnectControlPolicyForSpeed(){

        if(volumeChargeStatus.isLimitExclude()){
            return false;
        }

        if(isAlreadyLimited()){
            return false;
        }

        return true;

    }

    public boolean canLimitedConnectControlPolicy(){

        if(volumeChargeStatus.isLimitExclude()){
            return false;
        }

        if(isNoLimited()){
            return false;
        }

        return true;
    }

    public boolean isAlreadyLimited(){
        if(limitStatus1Month.isAlreadyLimited()){
            return true;
        }

        if(limitStatus72Hour.isAlreadyLimited()){
            return true;
        }

        return false;
    }

    public boolean isNoLimited(){

        return !isAlreadyLimited();
    }

    public boolean canChangeConnectControlPolicyForLimit72Hour() {
        if(isChargeOptionCharging()){
            return false;
        }

        if(isAlreadyLimited()){
            return false;
        }

        return true;
    }

    public boolean canChangeConnectControlPolicyForLimitRelease1Month(){

        // １ヶ月制限解除の場合は、ボリュームチャージが適用中かだけ判断すれば良い
        // スピードチャージ適用中（＝ライトSSプラン契約中）の場合、１ヶ月制限の速度制限が無いため
        if(volumeChargeStatus.isCharging()){
            return false;
        }

        if(limitStatus72Hour.isAlreadyLimited()){
            return false;
        }

        return limitStatus1Month.isAlreadyLimited();
    }

    public boolean canChangeConnectControlPolicyForLimitRelease72Hour(){

        // １ヶ月制限解除と異なり、
        // 72時間制限解除の場合はスピードチャージとボリュームチャージ両方のステータスを判断する必要がある
        if(isChargeOptionCharging()){
            return false;
        }

        if(limitStatus1Month.isAlreadyLimited()){
            return false;
        }

        return limitStatus72Hour.isAlreadyLimited();
    }

    public LimitStatus getSpeedLimitStatusForHistory(){
        if(isChargeOptionCharging()){
            return LimitStatus.NO_LIMIT;
        }
        if(isNoLimited()){
            return LimitStatus.NO_LIMIT;
        }
        return LimitStatus.LIMITED;
    }

    public boolean isAlreadyLimitedForVolume(){
        return limitStatus1Month.isAlreadyLimited();
    }

    public boolean isNoLimitedForVolume(){
        return !isAlreadyLimitedForVolume();
    }

    private boolean isChargeOptionCharging() {
        return volumeChargeStatus.isCharging() || speedChargeStatus.isCharging();
    }

}
