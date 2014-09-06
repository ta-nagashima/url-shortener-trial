package jp.co.biglobe.isp.mobile.ltethreeg.option.charge.service.speedcharge;

import jp.co.biglobe.isp.mobile.ltethreeg.domain.ltethreegengagement.LteThreeGEngagementNumber;
import jp.co.biglobe.isp.mobile.ltethreeg.option.charge.domain.speedchargeengagement.SpeedChargeEngagementEntity;
import jp.co.biglobe.isp.mobile.ltethreeg.option.charge.domain.speedchargeengagement.SpeedChargeEngagementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SpeedChargeEngagementCheckService {

    @Autowired
    private SpeedChargeEngagementRepository speedChargeEngagementRepository;


    public boolean unEngaged(LteThreeGEngagementNumber lteThreeGEngagementNumber){

        SpeedChargeEngagementEntity speedChargeEngagementEntity =
                speedChargeEngagementRepository.findByLteThreeGEngagementNumber(lteThreeGEngagementNumber);

        return speedChargeEngagementEntity.isNotExist();
    }

}
