package jp.co.biglobe.isp.mobile.voiceoption.domain.mnpout;

import jp.co.biglobe.isp.mobile.voiceoption.domain.mnpin.ValidMnpIn;
import jp.co.biglobe.isp.mobile.voiceoption.domain.voiceengagement.ValidVoiceEngagement;

public interface MnpOutFactory {

    public ValidMnpOut create(ValidVoiceEngagement validVoiceEngagement, MnpOutMsisdn mnpOutMsisdn);

    public ValidMnpOut createByValidMnpIn(ValidVoiceEngagement validVoiceEngagement, ValidMnpIn mnpIn);
}
