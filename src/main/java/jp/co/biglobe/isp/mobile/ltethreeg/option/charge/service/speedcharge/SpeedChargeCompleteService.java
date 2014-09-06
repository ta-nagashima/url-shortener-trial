package jp.co.biglobe.isp.mobile.ltethreeg.option.charge.service.speedcharge;

import jp.co.biglobe.isp.mobile.ltethreeg.domain.ltethreegengagement.LteThreeGEngagementNumber;
import jp.co.biglobe.isp.mobile.ltethreeg.option.charge.domain.speedchargeengagement.AfterSpeedChargeEngagementEntity;
import jp.co.biglobe.isp.mobile.ltethreeg.option.charge.domain.speedchargeengagement.BeforeSpeedChargeEngagementEntity;
import jp.co.biglobe.isp.mobile.ltethreeg.option.charge.domain.speedchargeengagement.SpeedChargeEngagementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SpeedChargeCompleteService {

    @Autowired
    private SpeedChargeEngagementRepository speedChargeEngagementRepository;


    public void completePlanChange(LteThreeGEngagementNumber lteThreeGEngagementNumber){

        BeforeSpeedChargeEngagementEntity beforeSpeedChargeEngagementEntity =
                speedChargeEngagementRepository.findByLteThreeGEngagementNumberForValid(lteThreeGEngagementNumber);

        AfterSpeedChargeEngagementEntity afterSpeedChargeEngagementEntity = beforeSpeedChargeEngagementEntity.planChange();

        speedChargeEngagementRepository.planChange(afterSpeedChargeEngagementEntity);

    }

    public void complete(LteThreeGEngagementNumber lteThreeGEngagementNumber){

        BeforeSpeedChargeEngagementEntity beforeSpeedChargeEngagementEntity =
                speedChargeEngagementRepository.findByLteThreeGEngagementNumberForValid(lteThreeGEngagementNumber);

        AfterSpeedChargeEngagementEntity afterSpeedChargeEngagementEntity = beforeSpeedChargeEngagementEntity.complete();

        speedChargeEngagementRepository.complete(afterSpeedChargeEngagementEntity);

    }

}
