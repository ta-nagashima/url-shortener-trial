package jp.co.biglobe.isp.mobile.voiceoption.domain.voiceengagement;

import jp.co.biglobe.isp.mobile.extension.domain.CommonEntity;
import jp.co.biglobe.isp.mobile.voiceoption.domain.identification.Identification;
import jp.co.biglobe.isp.mobile.voiceoption.domain.mnpin.MnpIn;
import jp.co.biglobe.isp.mobile.voiceoption.domain.mnpout.MnpOut;
import jp.co.biglobe.isp.mobile.voiceoption.domain.policy.*;
import jp.co.biglobe.isp.mobile.voiceoption.domain.voiceengagement.cancel.VoiceEngagementCancel;
import jp.co.biglobe.isp.mobile.voiceoption.domain.voiceengagement.disengage.VoiceEngagementDisengage;

public interface VoiceEngagement extends CommonEntity {

    public boolean isNotExist();

    public boolean notCanIdentificationUploadStatus();

    public boolean isIdentificationUploadDeadlineOverForMnpIn();

    public SimChangeCheckStatus verifySimChange(MnpOut mnpOut);

    public DisengagementCheckStatus verifyDisengagementCheckStatus(MnpOut mnpOut);

    public MnpOutAndDisengagementCheckStatus verifyMnpOutAndDisengagementCheckStatus(MnpOut mnpOut);

    public NewOrderCancelCheckStatus verifyNewOrderCancelCheckStatus(MnpIn mnpIn, MnpOut mnpOut);

    public MnpOutAndNewOrderCancelCheckStatus verifyNewOrderMnpOutAndCancelCheckStatus(MnpIn mnpIn, MnpOut mnpOut);

    public MnpOutApplyCheckStatus verifyMnpOutApplyCheckStatus(Identification identification, MnpIn mnpIn, MnpOut mnpOut);

    public MnpOutApplyCancelCheckStatus verifyMnpOutApplyCancelCheckStatus(Identification identification, MnpIn mnpIn, MnpOut mnpOut);

    public boolean isNecessaryToMnpOutIntentionCheck(MnpIn mnpIn, MnpOut mnpOut);
}
