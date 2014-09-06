package jp.co.biglobe.isp.mobile.voiceoption.domain.policy;

import jp.co.biglobe.lib.publication.enumeration.businessstatus.BusinessStatusForApi;
import jp.co.biglobe.lib.publication.enumeration.checkable.CheckResult;
import jp.co.biglobe.lib.publication.enumeration.checkable.Checkable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@ToString(includeFieldNames = false)
@AllArgsConstructor
public enum MnpOutAndNewOrderCancelCheckStatus implements BusinessStatusForApi {
    NO_MNP_IN(CheckResult.NG, "MNP転入の新規申し込みではない", MnpOutDetailType.NO_MNP_IN),
    NO_MNP_OUT(CheckResult.NG, "MNP転出キャンセル/MNP転出レコードなし", MnpOutDetailType.NO_MNP_OUT),
    REQUEST_WAITING(CheckResult.NG, "MNP予約番号発行手続依頼待ち", MnpOutDetailType.REQUEST_WAITING),
    MNP_RESERVATION_NUMBER_WAITING(CheckResult.NG, "MNP予約番号発行待ち", MnpOutDetailType.MNP_RESERVATION_NUMBER_WAITING),
    MNP_OUT_WAITING(CheckResult.OK, "MNP予約番号発行済で転出待ち", MnpOutDetailType.MNP_OUT_WAITING),
    MNP_OUT_DONE(CheckResult.NG, "MNP転出済", MnpOutDetailType.MNP_OUT_DONE),
    NO_VOICE_OPTION(CheckResult.NG, "音声オプション契約なし", MnpOutDetailType.NO_VOICE_OPTION);

    @Getter
    private final Checkable checkable;

    @Getter
    private final String message;

    @Getter
    private final MnpOutDetailType mnpOutDetailType;

    @Override
    public String getApiValue() {
        return mnpOutDetailType.getNoRefactoringValue();
    }

}
