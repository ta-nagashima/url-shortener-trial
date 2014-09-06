package jp.co.biglobe.isp.mobile.ltethreeg.domain.connectperformance;

import jp.co.biglobe.isp.mobile.ltethreeg.domain.connectperformance.connectcontrolpolicy.*;
import jp.co.biglobe.isp.mobile.ltethreeg.domain.connectperformance.speedstatus.*;
import jp.co.biglobe.isp.mobile.ltethreeg.domain.ltethreegengagement.ValidLteThreeGEngagementEntity;

public class ConnectPerformanceEntityFactory {

    public ValidConnectPerformanceEntity create(ValidLteThreeGEngagementEntity validLteThreeGEngagementEntity) {

        return new ValidConnectPerformanceEntity(
                validLteThreeGEngagementEntity.getLteThreeGEngagementNumber(),
                createSpeedStatus(),
                DestinationStatus.NO_LIMIT,
                createConnectControlPolicyEntity(validLteThreeGEngagementEntity.getConnectControlNoLimitPolicy()));
    }

    public ValidConnectPerformanceEntity createDestinationLimited(ValidLteThreeGEngagementEntity validLteThreeGEngagementEntity) {

        return new ValidConnectPerformanceEntity(
                validLteThreeGEngagementEntity.getLteThreeGEngagementNumber(),
                createSpeedStatus(),
                DestinationStatus.LIMITED_BIGLOBE_ONLY,
                createConnectControlDestinationLimitPolicyEntity(ConnectControlDestinationLimitPolicy.BIGLOBE_ONLY_LIMIT));
    }

    private SpeedStatus createSpeedStatus(){
        return new SpeedStatus(
                LimitStatus1Month.NO_LIMIT,
                LimitStatus72Hour.NO_LIMIT,
                VolumeChargeStatus.UN_PURCHASE,
                SpeedChargeStatus.UN_PURCHASE);
    }

    private ConnectControlPolicyEntity createConnectControlPolicyEntity(ConnectControlSpeedNoLimitPolicy connectControlSpeedNoLimitPolicy){
        return new ConnectControlPolicyEntity(
                connectControlSpeedNoLimitPolicy,
                ApplicationDateTime.create());
    }

    private ConnectControlPolicyEntity createConnectControlDestinationLimitPolicyEntity(ConnectControlDestinationLimitPolicy connectControlDestinationLimitPolicy){
        return new ConnectControlPolicyEntity(
                connectControlDestinationLimitPolicy,
                ApplicationDateTime.create());
    }

}