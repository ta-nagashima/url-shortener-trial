package jp.co.biglobe.isp.mobile.ltethreeg.option.charge.service.volumecharge;

import jp.co.biglobe.isp.mobile.ltethreeg.domain.ltethreegengagement.LteThreeGEngagementNumber;
import jp.co.biglobe.isp.mobile.ltethreeg.option.charge.domain.volumechargeengagement.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VolumeChargeCompleteService {

    @Autowired
    private VolumeChargeEngagementRepository volumeChargeEngagementRepository;


    public void completePlanChange(LteThreeGEngagementNumber lteThreeGEngagementNumber){

        BeforeVolumeChargeEngagementEntity beforeVolumeChargeEngagementEntity =
                volumeChargeEngagementRepository.findByLteThreeGEngagementNumberForValid(lteThreeGEngagementNumber);

        AfterVolumeChargeEngagementEntity afterVolumeChargeEngagementEntity = beforeVolumeChargeEngagementEntity.planChange();

        volumeChargeEngagementRepository.completePlanChange(afterVolumeChargeEngagementEntity);

    }

    public void complete(LteThreeGEngagementNumber lteThreeGEngagementNumber){

        BeforeVolumeChargeEngagementEntity beforeVolumeChargeEngagementEntity =
                volumeChargeEngagementRepository.findByLteThreeGEngagementNumberForValid(lteThreeGEngagementNumber);

        AfterVolumeChargeEngagementEntity afterVolumeChargeEngagementEntity = beforeVolumeChargeEngagementEntity.complete();

        volumeChargeEngagementRepository.complete(afterVolumeChargeEngagementEntity);
    }

}
