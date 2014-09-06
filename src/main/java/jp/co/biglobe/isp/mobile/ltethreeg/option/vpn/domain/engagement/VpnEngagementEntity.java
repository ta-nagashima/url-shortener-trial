package jp.co.biglobe.isp.mobile.ltethreeg.option.vpn.domain.engagement;


import jp.co.biglobe.isp.mobile.ltethreeg.domain.ltethreegengagement.LteThreeGEngagementNumber;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@ToString(includeFieldNames = false)
@EqualsAndHashCode
@AllArgsConstructor
public class VpnEngagementEntity {


    private final LteThreeGEngagementNumber lteThreeGEngagementNumber;

    private final VpnStatus vpnStatus;

    public boolean isVpnOrdered(){
        return vpnStatus.isOrdered();
    }
}
