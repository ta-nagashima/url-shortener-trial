package jp.co.biglobe.isp.mobile.voiceoption.domain.mnpout;

import jp.co.biglobe.isp.mobile.voiceoption.domain.policy.*;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@ToString(includeFieldNames = false)
@EqualsAndHashCode
public class NotExistMnpOut implements MnpOut {

    @Override
    public boolean isExist(){
        return false;
    }

    @Override
    public boolean isNotExist(){
        return true;
    }

    // SIMサイズ変更可否チェック
    @Override
    public SimChangeCheckStatus verifySimSizeChangeCheckStatus() {

        return MnpOutStatus.NO_REQUEST.getSimChangeCheckStatus();

    }

    @Override
    public MnpOutStatus getMnpOutStatus() {
        return MnpOutStatus.NO_REQUEST;
    }

    @Override
    public void verifyApply() {
        // エンティティがない場合は申し込み可のため、そのままreturn
        return;
    }

    @Override
    public boolean isCompletion() {
        return false;
    }

    @Override
    public DisengagementCheckStatus verifyDisengagementCheckStatus() {
        return DisengagementCheckStatus.NO_MNP_OUT;
    }

    @Override
    public MnpOutAndDisengagementCheckStatus verifyMnpOutAndDisengagementCheckStatus() {
        return MnpOutAndDisengagementCheckStatus.NO_MNP_OUT;
    }

    @Override
    public NewOrderCancelCheckStatus verifyNewOrderCancelCheckStatus() {
        return NewOrderCancelCheckStatus.NO_MNP_OUT;
    }

    @Override
    public MnpOutAndNewOrderCancelCheckStatus verifyNewOrderMnpOutAndCancelCheckStatus() {
        return MnpOutAndNewOrderCancelCheckStatus.NO_MNP_OUT;
    }

    @Override
    public MnpOutApplyCheckStatus verifyMnpOutApplyCheckStatus() {
        return MnpOutApplyCheckStatus.NO_MNP_OUT;
    }

    @Override
    public MnpOutApplyCancelCheckStatus verifyMnpOutApplyCancelCheckStatus() {
        return MnpOutApplyCancelCheckStatus.NO_MNP_OUT;
    }

    @Override
    public boolean canNotVoiceEngageCancel() {
        return false;
    }

    @Override
    public ValidMnpOut changeStatusRequested(MnpOut mnpOut) {
        return null;
    }

    @Override
    public boolean isValidMnpOut() {
        return false;
    }

}
