package jp.co.biglobe.isp.mobile.ltethreeg.domain.connectperformance.connectcontrolpolicy;

import jp.co.biglobe.isp.mobile.ltethreeg.domain.ltethreegengagement.ValidLteThreeGEngagementEntity;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@ToString(includeFieldNames=false)
@EqualsAndHashCode
@AllArgsConstructor
public class ConnectControlPolicyEntity {

    @Getter
    private final ConnectControlPolicy connectControlPolicy;

    @Getter
    private final ApplicationDateTime applicationDateTime;

    public ConnectControlPolicyEntity limitDestination(){
        return create(ConnectControlDestinationLimitPolicy.BIGLOBE_ONLY_LIMIT);
    }

    public ConnectControlPolicyEntity limitReleaseSpeed(ValidLteThreeGEngagementEntity validLteThreeGEngagementEntity){
        return create(validLteThreeGEngagementEntity.getConnectControlNoLimitPolicy());
    }


    public ConnectControlPolicyEntity limitSpeed(ValidLteThreeGEngagementEntity validLteThreeGEngagementEntity){
        return create(validLteThreeGEngagementEntity.getConnectControlLimitPolicy());
    }

    public ConnectControlPolicyEntity speedChargePurchase(){
        return create(ConnectControlSpeedNoLimitPolicy.SPEED_CHARGE_NO_LIMIT);
    }

    private ConnectControlPolicyEntity create(ConnectControlPolicy connectControlPolicy) {
        return new ConnectControlPolicyEntity(connectControlPolicy, ApplicationDateTime.create());
    }
}
