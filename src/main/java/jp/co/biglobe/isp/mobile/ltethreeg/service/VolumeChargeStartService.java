package jp.co.biglobe.isp.mobile.ltethreeg.service;

import jp.co.biglobe.isp.mobile.ltethreeg.domain.connectperformance.*;
import jp.co.biglobe.isp.mobile.ltethreeg.domain.ltethreegengagement.LteThreeGEngagementNumber;
import jp.co.biglobe.isp.mobile.ltethreeg.domain.ltethreegengagement.LteThreeGEngagementRepository;
import jp.co.biglobe.isp.mobile.ltethreeg.domain.ltethreegengagement.ValidLteThreeGEngagementEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VolumeChargeStartService {

    @Autowired
    private LteThreeGEngagementRepository lteThreeGEngagementRepository;

    @Autowired
    private ConnectPerformanceRepository connectPerformanceRepository;

    @Autowired
    private ConnectAuthLocalService connectAuthService;


    public void start(LteThreeGEngagementNumber lteThreeGEngagementNumber){

        ValidLteThreeGEngagementEntity validLteThreeGEngagementEntity = lteThreeGEngagementRepository.findByLteThreeGEngagementNumberForValid(lteThreeGEngagementNumber);

        BeforeConnectPerformanceEntity beforeConnectPerformanceEntity = connectPerformanceRepository.findByLteThreeGEngagementNumberForValid(lteThreeGEngagementNumber);

        AfterConnectPerformanceEntity afterConnectPerformanceEntity = beforeConnectPerformanceEntity.volumeChargeStart(validLteThreeGEngagementEntity);

        connectPerformanceRepository.update(beforeConnectPerformanceEntity,afterConnectPerformanceEntity, ConnectPerformanceEvent.VOLUME_CHARGE_START);
        connectAuthService.updateAll(afterConnectPerformanceEntity,validLteThreeGEngagementEntity);

        //ボリュームチャージ契約のエンティティは、ボリュームチャージ開始時には何もしない

    }
}
