
package jp.co.biglobe.isp.mobile.ltethreeg.service;

import jp.co.biglobe.isp.mobile.ltethreeg.domain.ltethreegengagement.LteThreeGEngagementNumber;
import jp.co.biglobe.isp.mobile.ltethreeg.domain.sim.ValidMsisdn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConnectAuthChangeAndDestinationLimitReleaseService {

    @Autowired
    private ConnectAuthChangeService connectAuthChangeService;

    @Autowired
    private ConnectDestinationLimitReleaseService connectDestinationLimitReleaseService;

    public void changeAndRelease(ValidMsisdn validMsisdn,LteThreeGEngagementNumber lteThreeGEngagementNumber){

        connectAuthChangeService.changeChargeFreeToUser(validMsisdn,lteThreeGEngagementNumber);

        connectDestinationLimitReleaseService.limitRelease(lteThreeGEngagementNumber);
    }

}

