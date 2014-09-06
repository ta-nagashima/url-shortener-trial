package jp.co.biglobe.isp.mobile.voiceoption.domain.policy;

import jp.co.biglobe.lib.publication.enumeration.businessstatus.BusinessStatusForApi;
import jp.co.biglobe.lib.publication.enumeration.checkable.CheckResult;
import jp.co.biglobe.lib.publication.enumeration.checkable.Checkable;
import jp.co.biglobe.lib.publication.enumeration.valuetype.ApiValue;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@ToString(includeFieldNames=false)
@AllArgsConstructor
public enum UploadCheckStatus implements BusinessStatusForApi {

    AVAILABLE(CheckResult.OK, "アップロード可能", UploadCheckApiValue.AVAILABLE),
    LTE_THREE_G_NOT_ENGAGEMENT(CheckResult.NG, "LTE・3G契約がない", UploadCheckApiValue.LTE_THREE_G_NOT_ENGAGEMENT),
    ENGAGEMENT_NOT_ORDERED    (CheckResult.NG, "音声通話オプション契約状態がアップロードを受け付けられない状態", UploadCheckApiValue.ENGAGEMENT_NOT_ORDERED),
    ENGAGEMENT_IS_PROVISIONAL (CheckResult.NG, "音声通話オプション契約状態が仮受付中", UploadCheckApiValue.ENGAGEMENT_IS_PROVISIONAL),
    UPLOAD_DISABLE_STATUS     (CheckResult.NG, "本人確認状態がアップロードを受け付けられない状態", UploadCheckApiValue.UPLOAD_DISABLE_STATUS),
    RECEIPT_LIMIT_TIME_OVER   (CheckResult.NG, "MNP転入有りかつ契約受付日からの制限時間を経過", UploadCheckApiValue.RECEIPT_LIMIT_TIME_OVER),
    UPLOAD_LIMIT_COUNT_OVER   (CheckResult.NG, "アップロード回数が制限回数を超過", UploadCheckApiValue.UPLOAD_LIMIT_COUNT_OVER);

    @Getter
    private final Checkable checkable;

    @Getter
    private final String message;

    private final UploadCheckApiValue uploadCheckApiValue;

    @Override
    public String getApiValue() {
        return this.uploadCheckApiValue.getNoRefactoringValue();
    }

    /**
     * APIとして公開される値（文字列リテラル）の定義
     */
    @AllArgsConstructor
    private enum UploadCheckApiValue implements ApiValue {
        AVAILABLE("ok_update_check_available" /* 文字列リテラルの変更禁止 */),
        LTE_THREE_G_NOT_ENGAGEMENT("ng_update_check_lte_three_g_not_engagement" /* 文字列リテラルの変更禁止 */),
        ENGAGEMENT_NOT_ORDERED("ng_update_check_engagement_not_ordered" /* 文字列リテラルの変更禁止 */),
        ENGAGEMENT_IS_PROVISIONAL("ng_update_check_engagement_is_provisional" /* 文字列リテラルの変更禁止 */),
        UPLOAD_DISABLE_STATUS("ng_update_check_upload_disable_status" /* 文字列リテラルの変更禁止 */),
        RECEIPT_LIMIT_TIME_OVER("ng_update_check_receipt_limit_time_over" /* 文字列リテラルの変更禁止 */),
        UPLOAD_LIMIT_COUNT_OVER("ng_update_check_upload_limit_count_over" /* 文字列リテラルの変更禁止 */);

        @Getter
        private final String noRefactoringValue;
    }

}
