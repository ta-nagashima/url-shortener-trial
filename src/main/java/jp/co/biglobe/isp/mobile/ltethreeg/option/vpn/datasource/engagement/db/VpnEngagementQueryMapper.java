package jp.co.biglobe.isp.mobile.ltethreeg.option.vpn.datasource.engagement.db;

import jp.co.biglobe.isp.mobile.ltethreeg.domain.ltethreegengagement.LteThreeGEngagementNumber;
import jp.co.biglobe.isp.mobile.ltethreeg.domain.ltethreegengagement.ValidLteThreeGEngagementEntity;
import jp.co.biglobe.isp.mobile.ltethreeg.option.vpn.domain.engagement.VpnOptionCode;
import org.apache.ibatis.annotations.Param;

public interface VpnEngagementQueryMapper {

    public int findByLteThreeGEngagementNumber(
            @Param("lteThreeGEngagementNumber") LteThreeGEngagementNumber lteThreeGEngagementNumber,
            @Param("vpnOptionCode") VpnOptionCode vpnOptionCode
    );
}
