package jp.co.biglobe.isp.mobile.ltethreeg.domain.ltethreegengagement.status;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@ToString(includeFieldNames = false)
@EqualsAndHashCode
@AllArgsConstructor
public class LteThreeGEngagementStatus {

    private final LteThreeGEngagementStatusCode lteThreeGEngagementStatusCode;

    private final LteThreeGEngagementEndDateTime lteThreeGEngagementEndDateTime;

    public boolean isConnectControlPolicyExemption(){

        return lteThreeGEngagementStatusCode.isConnectControlPolicyExemption(lteThreeGEngagementEndDateTime);
    }

    public boolean isOrdered(){
        return lteThreeGEngagementStatusCode.isOrdered();
    }

    public boolean isDisEngaged(){
        return lteThreeGEngagementStatusCode.isDisEngaged(lteThreeGEngagementEndDateTime);
    }
}
