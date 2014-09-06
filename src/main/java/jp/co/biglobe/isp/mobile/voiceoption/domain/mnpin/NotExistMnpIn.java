package jp.co.biglobe.isp.mobile.voiceoption.domain.mnpin;

import jp.co.biglobe.isp.mobile.voiceoption.domain.mnpout.MnpOut;
import jp.co.biglobe.isp.mobile.voiceoption.domain.policy.MsisdnDoubleRegistrationCheckStatus;
import jp.co.biglobe.isp.mobile.voiceoption.domain.policy.SimChangeCheckStatus;
import jp.co.biglobe.isp.mobile.voiceoption.domain.voiceengagement.VoiceEngagement;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@ToString(includeFieldNames=false)
@EqualsAndHashCode
public class NotExistMnpIn implements MnpIn {

    @Override
    public boolean isIdentificationUploadDeadlineOver(VoiceEngagement voiceEngagement) {
        return false;
    }

    @Override
    public boolean isExist() {
        return false;
    }

    @Override
    public boolean isNotExist() {
        return true;
    }

    @Override
    public MsisdnDoubleRegistrationCheckStatus verifyMsisdnDoubleRegistration() {
        return MsisdnDoubleRegistrationCheckStatus.UNREGISTERED;
    }
}
