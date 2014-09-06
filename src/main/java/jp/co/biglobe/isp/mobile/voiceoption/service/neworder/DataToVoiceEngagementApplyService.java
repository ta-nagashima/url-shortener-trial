package jp.co.biglobe.isp.mobile.voiceoption.service.neworder;

import jp.co.biglobe.isp.mobile.voiceoption.domain.voiceengagement.ValidVoiceEngagement;
import jp.co.biglobe.isp.mobile.voiceoption.domain.voiceengagement.VoiceEngagementFactory;
import jp.co.biglobe.isp.mobile.voiceoption.domain.voiceengagement.VoiceEngagementInitialRequestData;
import jp.co.biglobe.isp.mobile.voiceoption.domain.voiceengagement.VoiceEngagementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
class DataToVoiceEngagementApplyService {

    @Autowired
    private VoiceEngagementRepository voiceEngagementRepository;

    @Autowired
    private VoiceEngagementFactory voiceEngagementFactory;

    ValidVoiceEngagement newApply(VoiceEngagementInitialRequestData voiceEngagementInitialRequestData) {

        ValidVoiceEngagement validVoiceEngagement = voiceEngagementFactory.createForChangeDataToVoice(voiceEngagementInitialRequestData);

        voiceEngagementRepository.insert(validVoiceEngagement);

        return validVoiceEngagement;
    }
}
