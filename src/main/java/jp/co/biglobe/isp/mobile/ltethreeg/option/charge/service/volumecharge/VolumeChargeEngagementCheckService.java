package jp.co.biglobe.isp.mobile.ltethreeg.option.charge.service.volumecharge;

import jp.co.biglobe.isp.mobile.ltethreeg.domain.ltethreegengagement.LteThreeGEngagementNumber;
import jp.co.biglobe.isp.mobile.ltethreeg.option.charge.domain.volumechargeengagement.VolumeChargeEngagementEntity;
import jp.co.biglobe.isp.mobile.ltethreeg.option.charge.domain.volumechargeengagement.VolumeChargeEngagementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VolumeChargeEngagementCheckService {

    @Autowired
    private VolumeChargeEngagementRepository volumeChargeEngagementRepository;


    public boolean unEngaged(LteThreeGEngagementNumber lteThreeGEngagementNumber){

        VolumeChargeEngagementEntity volumeChargeEngagementEntity =
                volumeChargeEngagementRepository.findByLteThreeGEngagementNumber(lteThreeGEngagementNumber);

        return volumeChargeEngagementEntity.isNotExist();
    }


}
