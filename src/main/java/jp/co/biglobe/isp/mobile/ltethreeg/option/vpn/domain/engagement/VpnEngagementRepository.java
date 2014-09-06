package jp.co.biglobe.isp.mobile.ltethreeg.option.vpn.domain.engagement;

import jp.co.biglobe.isp.mobile.ltethreeg.domain.ltethreegengagement.LteThreeGEngagementNumber;
import jp.co.biglobe.isp.mobile.ltethreeg.domain.ltethreegengagement.ValidLteThreeGEngagementEntity;

public interface VpnEngagementRepository {

    public VpnEngagementEntity findByLteThreeGEngagementNumber(LteThreeGEngagementNumber lteThreeGEngagementNumber);

}
