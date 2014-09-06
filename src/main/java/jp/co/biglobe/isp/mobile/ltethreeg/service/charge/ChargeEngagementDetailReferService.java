package jp.co.biglobe.isp.mobile.ltethreeg.service.charge;

import jp.co.biglobe.isp.mobile.ltethreeg.domain.connectperformance.ConnectPerformanceEntity;
import jp.co.biglobe.isp.mobile.ltethreeg.domain.connectperformance.ConnectPerformanceRepository;
import jp.co.biglobe.isp.mobile.ltethreeg.domain.ltethreegengagement.LteThreeGEngagementEntity;
import jp.co.biglobe.isp.mobile.ltethreeg.domain.ltethreegengagement.LteThreeGEngagementNumber;
import jp.co.biglobe.isp.mobile.ltethreeg.domain.ltethreegengagement.LteThreeGEngagementRepository;
import jp.co.biglobe.isp.mobile.ltethreeg.option.charge.domain.speedchargeengagement.SpeedChargeEngagementEntity;
import jp.co.biglobe.isp.mobile.ltethreeg.option.charge.domain.speedchargeengagement.SpeedChargeEngagementRepository;
import jp.co.biglobe.isp.mobile.ltethreeg.option.charge.domain.volumechargeengagement.VolumeChargeEngagementEntity;
import jp.co.biglobe.isp.mobile.ltethreeg.option.charge.domain.volumechargeengagement.VolumeChargeEngagementRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChargeEngagementDetailReferService {

    @Autowired
    private LteThreeGEngagementRepository lteThreeGEngagementRepository;

    @Autowired
    private SpeedChargeEngagementRepository speedChargeEngagementRepository;

    @Autowired
    private VolumeChargeEngagementRepository volumeChargeEngagementRepository;

    @Autowired
    private ConnectPerformanceRepository connectPerformanceRepository;

    public ChargeEngagementDetailReferContainer refer(LteThreeGEngagementNumber lteThreeGEngagementNumber){

        LteThreeGEngagementEntity lteThreeGEngagementEntity = lteThreeGEngagementRepository.findByLteThreeGEngagementNumber(lteThreeGEngagementNumber);

        ConnectPerformanceEntity connectPerformanceEntity = connectPerformanceRepository.findByLteThreeGEngagementNumber(lteThreeGEngagementNumber);

        SpeedChargeEngagementEntity speedChargeEngagementEntity = speedChargeEngagementRepository.findByLteThreeGEngagementNumber(lteThreeGEngagementNumber);

        VolumeChargeEngagementEntity volumeChargeEngagementEntity = volumeChargeEngagementRepository.findByLteThreeGEngagementNumber(lteThreeGEngagementNumber);

        return new ChargeEngagementDetailReferContainer(
                lteThreeGEngagementEntity, connectPerformanceEntity, speedChargeEngagementEntity, volumeChargeEngagementEntity);
    }

    @AllArgsConstructor
    public class ChargeEngagementDetailReferContainer {

        @Getter
        private final LteThreeGEngagementEntity lteThreeGEngagementEntity;

        @Getter
        private final ConnectPerformanceEntity connectPerformanceEntity;

        @Getter
        private final SpeedChargeEngagementEntity speedChargeEngagementEntity;

        @Getter
        private final VolumeChargeEngagementEntity volumeChargeEngagementEntity;

    }

}
