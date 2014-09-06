package jp.co.biglobe.isp.mobile.voiceoption.domain.mnpin;

import jp.co.biglobe.isp.mobile.voiceoption.domain.voiceengagement.VoiceEngagementNumber;

public interface MnpInFactory {

    public ValidMnpIn create(VoiceEngagementNumber voiceEngagementNumber,
                             MnpInInitialRequestData mnpInInitialRequestData);

}
