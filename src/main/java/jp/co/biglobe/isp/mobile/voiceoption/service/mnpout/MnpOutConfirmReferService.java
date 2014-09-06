package jp.co.biglobe.isp.mobile.voiceoption.service.mnpout;


import jp.co.biglobe.isp.mobile.ltethreeg.domain.ltethreegengagement.LteThreeGEngagementNumber;
import jp.co.biglobe.isp.mobile.voiceoption.domain.container.VoiceEngagementDetailReferContainer;
import jp.co.biglobe.isp.mobile.voiceoption.domain.voiceengagement.VoiceEngagement;
import jp.co.biglobe.isp.mobile.voiceoption.domain.voiceengagement.VoiceEngagementRepository;
import jp.co.biglobe.isp.mobile.voiceoption.service.engagement.VoiceEngagementDetailReferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MnpOutConfirmReferService {

    @Autowired
    private VoiceEngagementRepository voiceEngagementRepository;

    @Autowired
    private VoiceEngagementDetailReferService voiceEngagementDetailReferService;


    public VoiceEngagementDetailReferContainer refer(LteThreeGEngagementNumber lteThreeGEngagementNumber){

        VoiceEngagement voiceEngagement = voiceEngagementRepository.findByLteThreeGEngagementNumber(lteThreeGEngagementNumber);

        return voiceEngagementDetailReferService.getVoiceEngagementDetailReferContainerByVoiceEngagement(voiceEngagement);
    }

}
