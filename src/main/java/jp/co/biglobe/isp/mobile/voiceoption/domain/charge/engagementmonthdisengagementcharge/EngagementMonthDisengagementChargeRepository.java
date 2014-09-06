package jp.co.biglobe.isp.mobile.voiceoption.domain.charge.engagementmonthdisengagementcharge;

import jp.co.biglobe.isp.mobile.voiceoption.domain.voiceengagement.ValidVoiceEngagement;
import jp.co.biglobe.isp.mobile.voiceoption.domain.voiceengagement.VoiceEngagement;
import jp.co.biglobe.isp.mobile.voiceoption.domain.voiceengagement.VoiceEngagementNumber;

public interface EngagementMonthDisengagementChargeRepository {

    public void register(ValidVoiceEngagement validVoiceEngagement);

    public EngagementMonthDisengagementCharge findByVoiceEngagement(VoiceEngagement voiceEngagement);

    public EngagementMonthDisengagementCharge findByVoiceEngagement(VoiceEngagementNumber voiceEngagementNumber);

}
