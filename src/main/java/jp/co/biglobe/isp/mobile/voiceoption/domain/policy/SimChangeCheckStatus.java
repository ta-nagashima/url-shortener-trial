package jp.co.biglobe.isp.mobile.voiceoption.domain.policy;

import jp.co.biglobe.lib.publication.enumeration.businessstatus.BusinessStatusForApi;
import jp.co.biglobe.lib.publication.enumeration.checkable.CheckResult;
import jp.co.biglobe.lib.publication.enumeration.checkable.Checkable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@ToString(includeFieldNames=false)
@AllArgsConstructor
public enum SimChangeCheckStatus implements BusinessStatusForApi {
    MNP_NEVER_ORDERED(CheckResult.OK, "SIMサイズ変更可能", MnpOutDetailType.NO_MNP_OUT),
    MNP_CANCELLED(CheckResult.OK, "SIMサイズ変更可能", MnpOutDetailType.NO_MNP_OUT),
    MNP_REQUEST_WAITING(CheckResult.NG, "SIMサイズ変更不可（MNP予約番号発行手続き開始待ち）", MnpOutDetailType.REQUEST_WAITING),
    MNP_NUMBERING_WAITING(CheckResult.NG, "SIMサイズ変更不可(MNP予約番号発行待ち)", MnpOutDetailType.MNP_RESERVATION_NUMBER_WAITING),
    MNP_NUMBERING(CheckResult.NG, "SIMサイズ変更不可(MNP予約番号発行済で転出待ち)", MnpOutDetailType.MNP_OUT_WAITING),
    MNP_PORT_OUT(CheckResult.NG, "SIMサイズ変更不可(MNP転出済)", MnpOutDetailType.MNP_OUT_DONE),
    NO_VOICE_OPTION(CheckResult.NG, "不正な呼び出し(音声通話オプション契約なし)", MnpOutDetailType.NO_VOICE_OPTION);

    @Getter
    private final Checkable checkable;

    @Getter
    private final String message;

    private final MnpOutDetailType mnpOutDetailType;

    @Override
    public String getApiValue(){
        return mnpOutDetailType.getNoRefactoringValue();
    }

}
