package jp.co.biglobe.isp.mobile.voiceoption.domain.mnpin;

import jp.co.biglobe.isp.mobile.extension.domain.CommonEntity;
import jp.co.biglobe.isp.mobile.voiceoption.domain.policy.MsisdnDoubleRegistrationCheckStatus;
import jp.co.biglobe.isp.mobile.voiceoption.domain.voiceengagement.VoiceEngagement;

public interface MnpIn extends CommonEntity {

    public boolean isIdentificationUploadDeadlineOver(VoiceEngagement voiceEngagement);

    public boolean isNotExist();

    public MsisdnDoubleRegistrationCheckStatus verifyMsisdnDoubleRegistration();
}
