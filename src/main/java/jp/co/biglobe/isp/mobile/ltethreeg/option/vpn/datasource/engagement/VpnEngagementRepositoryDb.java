package jp.co.biglobe.isp.mobile.ltethreeg.option.vpn.datasource.engagement;

import jp.co.biglobe.isp.mobile.ltethreeg.domain.ltethreegengagement.LteThreeGEngagementNumber;
import jp.co.biglobe.isp.mobile.ltethreeg.option.vpn.datasource.engagement.db.VpnEngagementQueryMapper;
import jp.co.biglobe.isp.mobile.ltethreeg.option.vpn.domain.engagement.VpnEngagementEntity;
import jp.co.biglobe.isp.mobile.ltethreeg.option.vpn.domain.engagement.VpnEngagementRepository;
import jp.co.biglobe.isp.mobile.ltethreeg.option.vpn.domain.engagement.VpnOptionCode;
import jp.co.biglobe.isp.mobile.ltethreeg.option.vpn.domain.engagement.VpnStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class VpnEngagementRepositoryDb implements VpnEngagementRepository {

    @Autowired
    private VpnEngagementQueryMapper vpnEngagementQueryMapper;

    @Override
    public VpnEngagementEntity findByLteThreeGEngagementNumber(LteThreeGEngagementNumber lteThreeGEngagementNumber) {

        int count = vpnEngagementQueryMapper.findByLteThreeGEngagementNumber(lteThreeGEngagementNumber, new VpnOptionCode());

        if(isVpnEngagementOrdered(count)){
            return new VpnEngagementEntity(lteThreeGEngagementNumber, VpnStatus.ORDERED);
        }

        return new VpnEngagementEntity(lteThreeGEngagementNumber, VpnStatus.NOT_ORDERED);

    }

    private boolean isVpnEngagementOrdered(int count){
        return count > 0 ? true : false;
    }
}
