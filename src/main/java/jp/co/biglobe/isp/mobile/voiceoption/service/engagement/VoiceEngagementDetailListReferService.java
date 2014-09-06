package jp.co.biglobe.isp.mobile.voiceoption.service.engagement;


import jp.co.biglobe.isp.mobile.ltethreeg.domain.ltethreegengagement.LteThreeGEngagementNumber;
import jp.co.biglobe.isp.mobile.voiceoption.domain.view.voiceengagementdetailcontainer.VoiceEngagementDetailContainerList;
import jp.co.biglobe.isp.mobile.voiceoption.domain.view.voiceengagementdetailcontainer.VoiceEngagementDetailContainerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VoiceEngagementDetailListReferService {

    @Autowired
    VoiceEngagementDetailContainerRepository voiceEngagementDetailContainerRepository;

    public VoiceEngagementDetailContainerList refer(LteThreeGEngagementNumber lteThreeGEngagementNumber){

        return voiceEngagementDetailContainerRepository.findAllByLteThreeGEngagementNumber(lteThreeGEngagementNumber);

    }
}
