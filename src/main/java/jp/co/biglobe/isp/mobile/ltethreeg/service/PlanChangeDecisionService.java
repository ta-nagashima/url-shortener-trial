package jp.co.biglobe.isp.mobile.ltethreeg.service;

import jp.co.biglobe.isp.mobile.ltethreeg.domain.connectperformance.AfterConnectPerformanceEntity;
import jp.co.biglobe.isp.mobile.ltethreeg.domain.connectperformance.BeforeConnectPerformanceEntity;
import jp.co.biglobe.isp.mobile.ltethreeg.domain.connectperformance.ConnectPerformanceEvent;
import jp.co.biglobe.isp.mobile.ltethreeg.domain.connectperformance.ConnectPerformanceRepository;
import jp.co.biglobe.isp.mobile.ltethreeg.domain.ltethreegengagement.LteThreeGEngagementNumber;
import jp.co.biglobe.isp.mobile.ltethreeg.domain.ltethreegengagement.LteThreeGEngagementRepository;
import jp.co.biglobe.isp.mobile.ltethreeg.domain.ltethreegengagement.ValidLteThreeGEngagementEntity;
import jp.co.biglobe.isp.mobile.ltethreeg.domain.ltethreegengagement.serviceplan.LteThreeGServicePlan;
import jp.co.biglobe.isp.mobile.ltethreeg.option.charge.service.speedcharge.SpeedChargeCompleteService;
import jp.co.biglobe.isp.mobile.ltethreeg.option.charge.service.speedcharge.SpeedChargeEngagementCheckService;
import jp.co.biglobe.isp.mobile.ltethreeg.option.charge.service.volumecharge.VolumeChargeCompleteService;
import jp.co.biglobe.isp.mobile.ltethreeg.option.charge.service.volumecharge.VolumeChargeEngagementCheckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlanChangeDecisionService {

    @Autowired
    private ConnectPerformanceRepository connectPerformanceRepository;

    @Autowired
    private LteThreeGEngagementRepository lteThreeGEngagementRepository;

    @Autowired
    private ConnectAuthLocalService connectAuthService;

    @Autowired
    private SpeedChargeCompleteService speedChargeCompleteService;

    @Autowired
    private SpeedChargeEngagementCheckService speedChargeEngagementCheckService;

    @Autowired
    private VolumeChargeCompleteService volumeChargeCompleteService;

    @Autowired
    private VolumeChargeEngagementCheckService volumeChargeEngagementCheckService;

    public void planChange(LteThreeGEngagementNumber lteThreeGEngagementNumber, LteThreeGServicePlan lteThreeGServicePlan) {

        ValidLteThreeGEngagementEntity beforeValidLteThreeGEngagement = lteThreeGEngagementRepository.findByLteThreeGEngagementNumberForValid(lteThreeGEngagementNumber);

        ValidLteThreeGEngagementEntity afterValidLteThreeGEngagement = beforeValidLteThreeGEngagement.planChange(lteThreeGServicePlan);

        speedChargeDisengageMaybe(beforeValidLteThreeGEngagement, afterValidLteThreeGEngagement);

        volumeChargeDisengageMaybe(beforeValidLteThreeGEngagement, afterValidLteThreeGEngagement);

        BeforeConnectPerformanceEntity beforeConnectPerformanceEntity = connectPerformanceRepository.findByLteThreeGEngagementNumberForValid(lteThreeGEngagementNumber);

        AfterConnectPerformanceEntity afterConnectPerformanceEntity = beforeConnectPerformanceEntity.planChange(afterValidLteThreeGEngagement);

        connectPerformanceRepository.update(beforeConnectPerformanceEntity, afterConnectPerformanceEntity, ConnectPerformanceEvent.PLAN_CHANGE);

        connectAuthService.updateAll(afterConnectPerformanceEntity, afterValidLteThreeGEngagement);

    }

    private void speedChargeDisengageMaybe(
            ValidLteThreeGEngagementEntity beforeValidLteThreeGEngagement,
            ValidLteThreeGEngagementEntity afterValidLteThreeGEngagement) {

        if (afterValidLteThreeGEngagement.isSpeedChargeValidPlan()) {
            return;
        }

        if (speedChargeEngagementCheckService.unEngaged(beforeValidLteThreeGEngagement.getLteThreeGEngagementNumber())) {
            return;
        }

        BeforeConnectPerformanceEntity beforeConnectPerformanceEntity = connectPerformanceRepository.findByLteThreeGEngagementNumberForValid(beforeValidLteThreeGEngagement.getLteThreeGEngagementNumber());

        AfterConnectPerformanceEntity afterConnectPerformanceEntity = beforeConnectPerformanceEntity.speedChargeComplete(beforeValidLteThreeGEngagement);

        connectPerformanceRepository.update(beforeConnectPerformanceEntity, afterConnectPerformanceEntity, ConnectPerformanceEvent.SPEED_CHARGE_COMPLETE);

        speedChargeCompleteService.completePlanChange(beforeValidLteThreeGEngagement.getLteThreeGEngagementNumber());

    }

    private void volumeChargeDisengageMaybe(
            ValidLteThreeGEngagementEntity beforeValidLteThreeGEngagement,
            ValidLteThreeGEngagementEntity afterValidLteThreeGEngagement) {

        if (afterValidLteThreeGEngagement.isVolumeChargeValidPlan()) {
            return;
        }

        if (volumeChargeEngagementCheckService.unEngaged(beforeValidLteThreeGEngagement.getLteThreeGEngagementNumber())) {
            return;
        }

        BeforeConnectPerformanceEntity beforeConnectPerformanceEntity = connectPerformanceRepository.findByLteThreeGEngagementNumberForValid(beforeValidLteThreeGEngagement.getLteThreeGEngagementNumber());

        AfterConnectPerformanceEntity afterConnectPerformanceEntity = beforeConnectPerformanceEntity.volumeChargeComplete(beforeValidLteThreeGEngagement);

        connectPerformanceRepository.update(beforeConnectPerformanceEntity, afterConnectPerformanceEntity, ConnectPerformanceEvent.VOLUME_CHARGE_COMPLETE);

        volumeChargeCompleteService.completePlanChange(beforeValidLteThreeGEngagement.getLteThreeGEngagementNumber());
    }


}
