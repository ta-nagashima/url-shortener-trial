package jp.co.biglobe.isp.mobile.voiceoption.domain.charge.mnpoutcharge;

import jp.co.biglobe.isp.mobile.voiceoption.domain.voiceengagement.ValidVoiceEngagement;

public interface MnpOutChargeRepository {

    public void register(ValidVoiceEngagement validVoiceEngagement);
}
