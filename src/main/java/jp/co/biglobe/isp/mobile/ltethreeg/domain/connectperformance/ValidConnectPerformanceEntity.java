package jp.co.biglobe.isp.mobile.ltethreeg.domain.connectperformance;

import jp.co.biglobe.isp.mobile.ltethreeg.domain.connectperformance.connectcontrolpolicy.ConnectControlPolicyEntity;
import jp.co.biglobe.isp.mobile.ltethreeg.domain.connectperformance.speedstatus.LimitStatus;
import jp.co.biglobe.isp.mobile.ltethreeg.domain.connectperformance.speedstatus.SpeedStatus;
import jp.co.biglobe.isp.mobile.ltethreeg.domain.ltethreegengagement.LteThreeGEngagementNumber;
import jp.co.biglobe.isp.mobile.ltethreeg.domain.ltethreegengagement.ValidLteThreeGEngagementEntity;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@ToString(includeFieldNames=false)
@EqualsAndHashCode
@AllArgsConstructor
public class ValidConnectPerformanceEntity implements ConnectPerformanceEntity, BeforeConnectPerformanceEntity, AfterConnectPerformanceEntity{

    @Getter
    private final LteThreeGEngagementNumber lteThreeGEngagementNumber;

    @Getter
    private final SpeedStatus speedStatus;

    @Getter
    private final DestinationStatus destinationStatus;

    @Getter
    private final ConnectControlPolicyEntity connectControlPolicyEntity;

    private ValidConnectPerformanceEntity create(
            SpeedStatus speedStatus,
            DestinationStatus destinationStatus,
            ConnectControlPolicyEntity connectControlPolicyEntity){

        return new ValidConnectPerformanceEntity(
                lteThreeGEngagementNumber,
                speedStatus,
                destinationStatus,
                connectControlPolicyEntity
        );
    }

    @Override
    public AfterConnectPerformanceEntity limit72Hour(ValidLteThreeGEngagementEntity validLteThreeGEngagementEntity){
        if(canChangeConnectControlPolicyForLimit72Hour(validLteThreeGEngagementEntity)){
            return create(speedStatus.limit72Hour(), destinationStatus, connectControlPolicyEntity.limitSpeed(validLteThreeGEngagementEntity));
        }

        return create(speedStatus.limit72Hour(), destinationStatus, connectControlPolicyEntity);
    }

    @Override
    public AfterConnectPerformanceEntity limitRelease72Hour(ValidLteThreeGEngagementEntity validLteThreeGEngagementEntity){
        if(canChangeConnectControlPolicyForLimitRelease72Hour(validLteThreeGEngagementEntity)){
            return create(speedStatus.limitRelease72Hour(), destinationStatus, connectControlPolicyEntity.limitReleaseSpeed(validLteThreeGEngagementEntity));
        }

        return create(speedStatus.limitRelease72Hour(), destinationStatus, connectControlPolicyEntity);
    }

    @Override
    public AfterConnectPerformanceEntity limit1Month(ValidLteThreeGEngagementEntity validLteThreeGEngagementEntity){
        if(isChangeConnectControlPolicyForSpeed(validLteThreeGEngagementEntity)){
            return create(speedStatus.limit1Month(), destinationStatus, connectControlPolicyEntity.limitSpeed(validLteThreeGEngagementEntity));
        }

        return create(speedStatus.limit1Month(), destinationStatus, connectControlPolicyEntity);
    }

    @Override
    public AfterConnectPerformanceEntity limitRelease1Month(ValidLteThreeGEngagementEntity validLteThreeGEngagementEntity){
        if(canChangeConnectControlPolicyForLimitRelease1Month(validLteThreeGEngagementEntity)){
            return create(speedStatus.limitRelease1Month(), destinationStatus, connectControlPolicyEntity.limitReleaseSpeed(validLteThreeGEngagementEntity));
        }

        return create(speedStatus.limitRelease1Month(), destinationStatus, connectControlPolicyEntity);
    }

    @Override
    public AfterConnectPerformanceEntity limitDestination(ValidLteThreeGEngagementEntity validLteThreeGEngagementEntity){
        if(isChangeConnectControlPolicyForDestination(validLteThreeGEngagementEntity)){
            return create(speedStatus, DestinationStatus.LIMITED_BIGLOBE_ONLY, connectControlPolicyEntity.limitDestination());
        }

        return create(speedStatus, DestinationStatus.LIMITED_BIGLOBE_ONLY, connectControlPolicyEntity);
    }

    @Override
    public AfterConnectPerformanceEntity limitReleaseDestination(ValidLteThreeGEngagementEntity validLteThreeGEngagementEntity){

        if(speedStatus.canLimitedConnectControlPolicy()){
            return create(speedStatus, DestinationStatus.NO_LIMIT, connectControlPolicyEntity.limitSpeed(validLteThreeGEngagementEntity));
        }

        return create(speedStatus, DestinationStatus.NO_LIMIT, connectControlPolicyEntity.limitReleaseSpeed(validLteThreeGEngagementEntity));
    }

    @Override
    public AfterConnectPerformanceEntity planChange(ValidLteThreeGEngagementEntity validLteThreeGEngagementEntity){

        // 通信先の制限状態を考慮せず、更新後の通信制御ポリシーを決めている。
        // 理由として、今の通信先の制限のサービス仕様だと、後からOSSで申込するまでの間、通信先を制限したいという内容なので、
        // プラン変更ができる状態の契約中には通信先制限という状態が当てはまらないため。
        // 未成年のサイト閲覧制限等のサービス提供があれば、更新後の通信制御ポリシーに通信先を考慮する必要あり。
        if(speedStatus.isAlreadyLimited()){
            return create(speedStatus, destinationStatus, connectControlPolicyEntity.limitSpeed(validLteThreeGEngagementEntity));
        }

        return create(speedStatus, destinationStatus, connectControlPolicyEntity.limitReleaseSpeed(validLteThreeGEngagementEntity));
    }

    @Override
    public AfterConnectPerformanceEntity volumeChargePurchase(ValidLteThreeGEngagementEntity validLteThreeGEngagementEntity) {

        if(speedStatus.isAlreadyLimitedForVolume()){
            return create(speedStatus.volumeChargeCharging(), destinationStatus, connectControlPolicyEntity.limitReleaseSpeed(validLteThreeGEngagementEntity));
        }
        // 72時間制限の有無を判断していないため、ポリシーは一意に決まらない。
        return create(speedStatus.volumeChargeUnCharge(), destinationStatus, connectControlPolicyEntity );
    }

    @Override
    public AfterConnectPerformanceEntity volumeChargeStopped(ValidLteThreeGEngagementEntity validLteThreeGEngagementEntity) {

        if (speedStatus.isAlreadyLimited()) {
            return create(speedStatus.volumeChargeStopped(), destinationStatus, connectControlPolicyEntity.limitSpeed(validLteThreeGEngagementEntity));
        }

        return create(speedStatus.volumeChargeStopped(), destinationStatus, connectControlPolicyEntity.limitReleaseSpeed(validLteThreeGEngagementEntity));
    }

    @Override
    public AfterConnectPerformanceEntity volumeChargeStart(ValidLteThreeGEngagementEntity validLteThreeGEngagementEntity) {

        return create(speedStatus.volumeChargeCharging(), destinationStatus, connectControlPolicyEntity.limitReleaseSpeed(validLteThreeGEngagementEntity));
    }

    @Override
    public AfterConnectPerformanceEntity volumeChargeComplete(ValidLteThreeGEngagementEntity validLteThreeGEngagementEntity){

        // プラン変更時と同じ考え方
        if(speedStatus.isAlreadyLimited()){
            return create(speedStatus.volumeChargeComplete(), destinationStatus, connectControlPolicyEntity.limitSpeed(validLteThreeGEngagementEntity));
        }

        return create(speedStatus.volumeChargeComplete(), destinationStatus, connectControlPolicyEntity.limitReleaseSpeed(validLteThreeGEngagementEntity));
    }

    @Override
    public AfterConnectPerformanceEntity speedChargePurchase(){

        return create(speedStatus.speedChargePurchase(), destinationStatus, connectControlPolicyEntity.speedChargePurchase());
    }

    @Override
    public AfterConnectPerformanceEntity speedChargeComplete(ValidLteThreeGEngagementEntity validLteThreeGEngagementEntity){

        // プラン変更時と同じ考え方
        if(speedStatus.isAlreadyLimited()){
            return create(speedStatus.speedChargeComplete(), destinationStatus, connectControlPolicyEntity.limitSpeed(validLteThreeGEngagementEntity));
        }

        return create(speedStatus.speedChargeComplete(), destinationStatus, connectControlPolicyEntity.limitReleaseSpeed(validLteThreeGEngagementEntity));
    }

    @Override
    public boolean isSameConnectControlPolicy(BeforeConnectPerformanceEntity before){
        return connectControlPolicyEntity.equals(before.getConnectControlPolicyEntity());
    }

    @Override
    public LimitStatus getSpeedLimitStatus(){
        return speedStatus.getSpeedLimitStatusForHistory();
    }

    @Override
    public LimitStatus getDestinationLimitStatusForHistory(){
        return destinationStatus.getDestinationLimitStatus();
    }
//    public LimitStatus getDestinationLimitStatusForHistory(){
//        return connectControlPolicyEntity.getDestinationLimitStatus();
//    }

    public boolean isAlreadyLimitedForVolume(){
        return speedStatus.isAlreadyLimitedForVolume();
    }

    public boolean isNoLimitedForVolume(){
        return speedStatus.isNoLimitedForVolume();
    }

    @Override
    public boolean isNotExist() {
        return false;
    }

    private boolean isChangeConnectControlPolicyForSpeed(ValidLteThreeGEngagementEntity validLteThreeGEngagementEntity){
        if ( validLteThreeGEngagementEntity.isConnectControlPolicyExemptionForLimit() ){
            return false;
        }

        if( destinationStatus.isLimited()){
            return false;
        }

        return speedStatus.isChangeConnectControlPolicyForSpeed();

    }

    private boolean canChangeConnectControlPolicyForLimit72Hour(ValidLteThreeGEngagementEntity validLteThreeGEngagementEntity){
        if ( validLteThreeGEngagementEntity.isConnectControlPolicyExemptionForLimit() ){
            return false;
        }

        if( destinationStatus.isLimited()){
            return false;
        }

        return speedStatus.canChangeConnectControlPolicyForLimit72Hour();

    }

    private boolean canChangeConnectControlPolicyForLimitRelease1Month(ValidLteThreeGEngagementEntity validLteThreeGEngagementEntity){
        if ( validLteThreeGEngagementEntity.isConnectControlPolicyExemptionForLimitRelease() ){
            return false;
        }

        if( destinationStatus.isLimited()){
            return false;
        }

        return speedStatus.canChangeConnectControlPolicyForLimitRelease1Month();
    }

    private boolean canChangeConnectControlPolicyForLimitRelease72Hour(ValidLteThreeGEngagementEntity validLteThreeGEngagementEntity){
        if ( validLteThreeGEngagementEntity.isConnectControlPolicyExemptionForLimitRelease() ){
            return false;
        }

        if( destinationStatus.isLimited()){
            return false;
        }

        return speedStatus.canChangeConnectControlPolicyForLimitRelease72Hour();
    }

    private boolean isChangeConnectControlPolicyForDestination(ValidLteThreeGEngagementEntity validLteThreeGEngagementEntity){
        if ( validLteThreeGEngagementEntity.isConnectControlPolicyExemptionForLimit() ){
            return false;
        }

        if( destinationStatus.isLimited()){
            return false;
        }

        return true;
    }

}
