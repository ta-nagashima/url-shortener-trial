package jp.co.biglobe.isp.mobile.voiceoption.datasource.voiceengagement;

import jp.co.biglobe.isp.mobile.voiceoption.datasource.voiceengagement.voiceengagementnumber.VoiceEngagementNumberQueryMapper;
import jp.co.biglobe.isp.mobile.voiceoption.domain.voiceengagement.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class VoiceEngagementFactoryDb implements VoiceEngagementFactory {

    @Autowired
    VoiceEngagementNumberQueryMapper voiceEngagementNumberQueryMapper;

    @Override
    public ValidVoiceEngagement createForOrderAndJoin(VoiceEngagementInitialRequestData voiceEngagementInitialRequestData) {

        return new ValidVoiceEngagement(
                voiceEngagementNumberQueryMapper.create(),
                voiceEngagementInitialRequestData,
                VoiceOrderType.ORDER_WITH_LTE
        );
    }

    @Override
    public ValidVoiceEngagement createForChangeDataToVoice(VoiceEngagementInitialRequestData voiceEngagementInitialRequestData) {

        return new ValidVoiceEngagement(
                voiceEngagementNumberQueryMapper.create(),
                voiceEngagementInitialRequestData,
                VoiceOrderType.CHANGE_DATA_TO_VOICE
        );
    }

}
