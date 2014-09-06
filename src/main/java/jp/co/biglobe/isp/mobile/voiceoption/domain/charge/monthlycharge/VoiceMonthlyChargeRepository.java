package jp.co.biglobe.isp.mobile.voiceoption.domain.charge.monthlycharge;

import jp.co.biglobe.isp.mobile.voiceoption.domain.voiceengagement.ValidVoiceEngagement;
import jp.co.biglobe.isp.mobile.voiceoption.domain.voiceengagement.VoiceEngagementEndDateTime;

public interface VoiceMonthlyChargeRepository {

    public void register(ValidVoiceEngagement validVoiceEngagement);

    public void delete(
            VoiceEngagementEndDateTime voiceEngagementEndDateTime,
            ValidVoiceEngagement validVoiceEngagement);

    public void update(ValidVoiceEngagement validVoiceEngagement);

}
