package jp.co.biglobe.isp.mobile.voiceoption.domain.voiceengagement;

public interface VoiceEngagementFactory {

    public ValidVoiceEngagement createForOrderAndJoin(VoiceEngagementInitialRequestData voiceEngagementInitialRequestData);

    public ValidVoiceEngagement createForChangeDataToVoice(VoiceEngagementInitialRequestData voiceEngagementInitialRequestData);

}
