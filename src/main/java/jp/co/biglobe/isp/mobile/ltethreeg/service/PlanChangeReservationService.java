package jp.co.biglobe.isp.mobile.ltethreeg.service;

import jp.co.biglobe.isp.mobile.ltethreeg.domain.connectperformance.AfterConnectPerformanceEntity;
import jp.co.biglobe.isp.mobile.ltethreeg.domain.connectperformance.ConnectPerformanceRepository;
import jp.co.biglobe.isp.mobile.ltethreeg.domain.ltethreegengagement.LteThreeGEngagementNumber;
import jp.co.biglobe.isp.mobile.ltethreeg.domain.ltethreegengagement.LteThreeGEngagementRepository;
import jp.co.biglobe.isp.mobile.ltethreeg.domain.ltethreegengagement.ValidLteThreeGEngagementEntity;
import jp.co.biglobe.isp.mobile.ltethreeg.domain.ltethreegengagement.serviceplan.LteThreeGServicePlan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlanChangeReservationService {

    @Autowired
    private ConnectPerformanceRepository connectPerformanceRepository;

    @Autowired
    private LteThreeGEngagementRepository lteThreeGEngagementRepository;

    @Autowired
    private ConnectAuthLocalService connectAuthService;

    public void planChange(LteThreeGEngagementNumber lteThreeGEngagementNumber, LteThreeGServicePlan lteThreeGServicePlan){

        ValidLteThreeGEngagementEntity validLteThreeGEngagement = createLteThreeGEngagementForPlanChanged(lteThreeGEngagementNumber, lteThreeGServicePlan);

        AfterConnectPerformanceEntity afterConnectPerformanceEntity = connectPerformanceRepository.findByLteThreeGEngagementNumberForValid(lteThreeGEngagementNumber);

        connectAuthService.updateAll(afterConnectPerformanceEntity, validLteThreeGEngagement);

    }

    private ValidLteThreeGEngagementEntity createLteThreeGEngagementForPlanChanged(LteThreeGEngagementNumber lteThreeGEngagementNumber, LteThreeGServicePlan lteThreeGServicePlan){
        ValidLteThreeGEngagementEntity validLteThreeGEngagement = lteThreeGEngagementRepository.findByLteThreeGEngagementNumberForValid(lteThreeGEngagementNumber);
        return validLteThreeGEngagement.planChange(lteThreeGServicePlan);
    }


}
