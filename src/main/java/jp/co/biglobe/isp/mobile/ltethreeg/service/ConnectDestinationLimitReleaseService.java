package jp.co.biglobe.isp.mobile.ltethreeg.service;

import jp.co.biglobe.isp.mobile.ltethreeg.domain.connectperformance.AfterConnectPerformanceEntity;
import jp.co.biglobe.isp.mobile.ltethreeg.domain.connectperformance.BeforeConnectPerformanceEntity;
import jp.co.biglobe.isp.mobile.ltethreeg.domain.connectperformance.ConnectPerformanceRepository;
import jp.co.biglobe.isp.mobile.ltethreeg.domain.connectperformance.ConnectPerformanceEvent;
import jp.co.biglobe.isp.mobile.ltethreeg.domain.ltethreegengagement.LteThreeGEngagementNumber;
import jp.co.biglobe.isp.mobile.ltethreeg.domain.ltethreegengagement.LteThreeGEngagementRepository;
import jp.co.biglobe.isp.mobile.ltethreeg.domain.ltethreegengagement.ValidLteThreeGEngagementEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConnectDestinationLimitReleaseService {

    @Autowired
    ConnectPerformanceRepository connectPerformanceRepository;

    @Autowired
    LteThreeGEngagementRepository lteThreeGEngagementRepository;

    @Autowired
    private ConnectAuthLocalService connectAuthService;

    public void limitRelease(LteThreeGEngagementNumber lteThreeGEngagementNumber){

        ValidLteThreeGEngagementEntity validLteThreeGEngagement = lteThreeGEngagementRepository.findByLteThreeGEngagementNumberForValid(lteThreeGEngagementNumber);

        BeforeConnectPerformanceEntity beforeConnectPerformanceEntity = connectPerformanceRepository.findByLteThreeGEngagementNumberForValid(lteThreeGEngagementNumber);

        AfterConnectPerformanceEntity afterConnectPerformanceEntity = beforeConnectPerformanceEntity.limitReleaseDestination(validLteThreeGEngagement);

        connectPerformanceRepository.update(
                beforeConnectPerformanceEntity, afterConnectPerformanceEntity, ConnectPerformanceEvent.LIMIT_RELEASE_DESTINATION);

        connectAuthService.updateAllMaybe
                (beforeConnectPerformanceEntity, afterConnectPerformanceEntity, validLteThreeGEngagement);

    }

}
