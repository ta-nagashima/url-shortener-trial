package jp.co.biglobe.isp.mobile.voiceoption.domain.voiceengagement;

import jp.co.biglobe.isp.mobile.voiceoption.domain.identification.Identification;
import jp.co.biglobe.isp.mobile.voiceoption.domain.mnpin.MnpIn;
import jp.co.biglobe.isp.mobile.voiceoption.domain.mnpout.MnpOut;
import jp.co.biglobe.isp.mobile.voiceoption.domain.policy.*;
import jp.co.biglobe.isp.mobile.voiceoption.domain.voiceengagement.cancel.NotExistVoiceEngagementCancel;
import jp.co.biglobe.isp.mobile.voiceoption.domain.voiceengagement.cancel.VoiceEngagementCancel;
import jp.co.biglobe.isp.mobile.voiceoption.domain.voiceengagement.disengage.NotExistVoiceEngagementDisengage;
import jp.co.biglobe.isp.mobile.voiceoption.domain.voiceengagement.disengage.VoiceEngagementDisengage;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@ToString(includeFieldNames = false)
@EqualsAndHashCode
public class NotExistVoiceEngagement implements VoiceEngagement {

    @Override
    public boolean isExist() {
        return false;
    }

    @Override
    public boolean isNotExist() { return true; }

    @Override
    public boolean notCanIdentificationUploadStatus() {
        return true;
    }

    @Override
    public boolean isIdentificationUploadDeadlineOverForMnpIn() {
        return true;
    }

    @Override
    public SimChangeCheckStatus verifySimChange(MnpOut mnpOut) {
        return SimChangeCheckStatus.NO_VOICE_OPTION;
    }

    @Override
    public DisengagementCheckStatus verifyDisengagementCheckStatus(MnpOut mnpOut) {
        return DisengagementCheckStatus.NO_VOICE_OPTION;
    }

    @Override
    public MnpOutAndDisengagementCheckStatus verifyMnpOutAndDisengagementCheckStatus(MnpOut mnpOut) {
        return MnpOutAndDisengagementCheckStatus.NO_VOICE_OPTION;
    }

    @Override
    public NewOrderCancelCheckStatus verifyNewOrderCancelCheckStatus(MnpIn mnpIn, MnpOut mnpOut) {
        return NewOrderCancelCheckStatus.NO_VOICE_OPTION;
    }

    @Override
    public MnpOutAndNewOrderCancelCheckStatus verifyNewOrderMnpOutAndCancelCheckStatus(MnpIn mnpIn, MnpOut mnpOut) {
        return MnpOutAndNewOrderCancelCheckStatus.NO_VOICE_OPTION;
    }

    @Override
    public MnpOutApplyCheckStatus verifyMnpOutApplyCheckStatus(Identification identification, MnpIn mnpIn, MnpOut mnpOut) {
        return MnpOutApplyCheckStatus.NO_VOICE_OPTION;
    }

    @Override
    public MnpOutApplyCancelCheckStatus verifyMnpOutApplyCancelCheckStatus(Identification identification, MnpIn mnpIn, MnpOut mnpOut) {
        return MnpOutApplyCancelCheckStatus.NO_VOICE_OPTION;
    }

    @Override
    public boolean isNecessaryToMnpOutIntentionCheck(MnpIn mnpIn, MnpOut mnpOut) {
        return false;
    }
}
