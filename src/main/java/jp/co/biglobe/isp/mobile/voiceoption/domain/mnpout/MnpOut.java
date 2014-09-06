package jp.co.biglobe.isp.mobile.voiceoption.domain.mnpout;

import jp.co.biglobe.isp.mobile.extension.domain.CommonEntity;
import jp.co.biglobe.isp.mobile.voiceoption.domain.policy.*;

public interface MnpOut extends CommonEntity {

    public boolean isNotExist();

    // SIMサイズ変更可否チェック
    public SimChangeCheckStatus verifySimSizeChangeCheckStatus();

    public MnpOutStatus getMnpOutStatus();

    public void verifyApply();

    public boolean isCompletion();

    public DisengagementCheckStatus verifyDisengagementCheckStatus();

    public MnpOutAndDisengagementCheckStatus verifyMnpOutAndDisengagementCheckStatus();

    public NewOrderCancelCheckStatus verifyNewOrderCancelCheckStatus();

    public MnpOutAndNewOrderCancelCheckStatus verifyNewOrderMnpOutAndCancelCheckStatus();

    public MnpOutApplyCheckStatus verifyMnpOutApplyCheckStatus();

    public MnpOutApplyCancelCheckStatus verifyMnpOutApplyCancelCheckStatus();

    public boolean canNotVoiceEngageCancel();

    public ValidMnpOut changeStatusRequested(MnpOut mnpOut);

    public boolean isValidMnpOut();
}
