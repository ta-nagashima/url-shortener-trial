package jp.co.biglobe.isp.mobile.voiceoption.service.identification;

import jp.co.biglobe.isp.mobile.ltethreeg.domain.ltethreegengagement.LteThreeGEngagementNumber;
import jp.co.biglobe.isp.mobile.voiceoption.domain.identification.Identification;
import jp.co.biglobe.isp.mobile.voiceoption.domain.identification.IdentificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IdentificationDetailReferService {

    @Autowired
    private IdentificationRepository identificationRepository;


    public Identification refer(LteThreeGEngagementNumber lteThreeGEngagementNumber){

        Identification identification = identificationRepository.findByLteThreeGEngagementNumber(lteThreeGEngagementNumber);

        return identification;
    }
}
