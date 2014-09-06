package jp.co.biglobe.isp.mobile.ltethreeg.service.charge;

import jp.co.biglobe.isp.mobile.ltethreeg.domain.connectperformance.AfterConnectPerformanceEntity;
import jp.co.biglobe.isp.mobile.ltethreeg.domain.connectperformance.BeforeConnectPerformanceEntity;
import jp.co.biglobe.isp.mobile.ltethreeg.domain.connectperformance.ConnectPerformanceEvent;
import jp.co.biglobe.isp.mobile.ltethreeg.domain.connectperformance.ConnectPerformanceRepository;
import jp.co.biglobe.isp.mobile.ltethreeg.domain.ltethreegengagement.LteThreeGEngagementNumber;
import jp.co.biglobe.isp.mobile.ltethreeg.domain.ltethreegengagement.LteThreeGEngagementRepository;
import jp.co.biglobe.isp.mobile.ltethreeg.domain.ltethreegengagement.ValidLteThreeGEngagementEntity;
import jp.co.biglobe.isp.mobile.ltethreeg.option.charge.service.speedcharge.SpeedChargeCompleteService;
import jp.co.biglobe.isp.mobile.ltethreeg.service.ConnectAuthLocalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SpeedChargeEngagementCompleteService {

    @Autowired
    private ConnectPerformanceRepository connectPerformanceRepository;

    @Autowired
    private LteThreeGEngagementRepository lteThreeGEngagementRepository;

    @Autowired
    private ConnectAuthLocalService connectAuthService;

    @Autowired
    private SpeedChargeCompleteService speedChargeCompleteService;

    public void complete(LteThreeGEngagementNumber lteThreeGEngagementNumber){

        speedChargeCompleteService.complete(lteThreeGEngagementNumber);

        ValidLteThreeGEngagementEntity validLteThreeGEngagement = lteThreeGEngagementRepository.findByLteThreeGEngagementNumberForValid(lteThreeGEngagementNumber);

        BeforeConnectPerformanceEntity beforeConnectPerformanceEntity = connectPerformanceRepository.findByLteThreeGEngagementNumberForValid(lteThreeGEngagementNumber);

        AfterConnectPerformanceEntity afterConnectPerformanceEntity = beforeConnectPerformanceEntity.speedChargeComplete(validLteThreeGEngagement);

        connectPerformanceRepository.update(beforeConnectPerformanceEntity, afterConnectPerformanceEntity, ConnectPerformanceEvent.SPEED_CHARGE_COMPLETE);

        connectAuthService.updateAllMaybe
                (beforeConnectPerformanceEntity, afterConnectPerformanceEntity, validLteThreeGEngagement);

    }



}
