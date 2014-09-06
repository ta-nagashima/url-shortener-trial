package jp.co.biglobe.isp.mobile.ltethreeg.service;

import jp.co.biglobe.isp.mobile.ltethreeg.domain.connectperformance.ValidConnectPerformanceEntity;
import jp.co.biglobe.isp.mobile.ltethreeg.domain.connectperformance.ConnectPerformanceEntityFactory;
import jp.co.biglobe.isp.mobile.ltethreeg.domain.connectperformance.ConnectPerformanceRepository;
import jp.co.biglobe.isp.mobile.ltethreeg.domain.ltethreegengagement.LteThreeGEngagementNumber;
import jp.co.biglobe.isp.mobile.ltethreeg.domain.ltethreegengagement.LteThreeGEngagementRepository;
import jp.co.biglobe.isp.mobile.ltethreeg.domain.ltethreegengagement.ValidLteThreeGEngagementEntity;
import jp.co.biglobe.isp.mobile.ltethreeg.domain.sim.ValidMsisdn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConnectAuthRegisterAndConnectLimitRegisterService {

    @Autowired
    private ConnectPerformanceRepository connectPerformanceRepository;

    @Autowired
    private LteThreeGEngagementRepository lteThreeGEngagementRepository;

    @Autowired
    private ConnectAuthLocalService connectAuthService;

    public void init(LteThreeGEngagementNumber lteThreeGEngagementNumber,ValidMsisdn msisdn){

        ValidLteThreeGEngagementEntity validLteThreeGEngagementEntity =
                lteThreeGEngagementRepository.findByLteThreeGEngagementNumberForValid(lteThreeGEngagementNumber);

        ValidConnectPerformanceEntity validConnectPerformanceEntity = new ConnectPerformanceEntityFactory().create(
                    validLteThreeGEngagementEntity);

        connectPerformanceRepository.insertMaybe(validConnectPerformanceEntity);

        connectAuthService.contract(validLteThreeGEngagementEntity, validConnectPerformanceEntity, msisdn);

    }

}
