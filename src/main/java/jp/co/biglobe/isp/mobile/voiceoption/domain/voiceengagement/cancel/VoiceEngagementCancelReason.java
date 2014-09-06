package jp.co.biglobe.isp.mobile.voiceoption.domain.voiceengagement.cancel;

import jp.co.biglobe.lib.publication.enumeration.convert.EnumConvertForApi;
import jp.co.biglobe.lib.publication.enumeration.convert.EnumConvertForDb;
import jp.co.biglobe.lib.publication.enumeration.valuetype.ApiValue;
import jp.co.biglobe.lib.publication.enumeration.valuetype.DbValue;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@ToString(includeFieldNames = false)
@AllArgsConstructor
public enum VoiceEngagementCancelReason implements EnumConvertForDb, EnumConvertForApi {
    USER_REQUEST("ユーザからのキャンセル依頼", MnpOutCancelReasonValue.USER_REQUEST, MnpOutCancelReasonValue.USER_REQUEST),
    DELIVERY_RECEIPT_REFUSAL("配送受取拒否", MnpOutCancelReasonValue.DELIVERY_RECEIPT_REFUSAL, MnpOutCancelReasonValue.DELIVERY_RECEIPT_REFUSAL),
    WITHDRAW_OR_DE_REGISTER("退会・登録抹消・強制退会", MnpOutCancelReasonValue.WITHDRAW_OR_DE_REGISTER, MnpOutCancelReasonValue.WITHDRAW_OR_DE_REGISTER),
    COURSE_CHANGE("コース変更", MnpOutCancelReasonValue.COURSE_CHANGE, MnpOutCancelReasonValue.COURSE_CHANGE),
    ADJOURN_OR_SERVICE_STOP("休会・サービス停止", MnpOutCancelReasonValue.ADJOURN_OR_SERVICE_STOP, MnpOutCancelReasonValue.ADJOURN_OR_SERVICE_STOP);

    private final String message;
    private final DbValue dbValue;
    private final ApiValue apiValue;

    @Override
    public String getDbValue() {
        return dbValue.getNoRefactoringValue();
    }

    @Override
    public String getApiValue() {
        return apiValue.getNoRefactoringValue();
    }

    /**
     * DBに格納される値（文字列リテラル）の定義
     * APIとして公開される値（文字列リテラル）の定義
     */
    @AllArgsConstructor
    private enum MnpOutCancelReasonValue implements DbValue, ApiValue {
        USER_REQUEST("user_request" /* 文字列リテラルの変更禁止 */),
        DELIVERY_RECEIPT_REFUSAL("delivery_receipt_refusal" /* 文字列リテラルの変更禁止 */),
        WITHDRAW_OR_DE_REGISTER("withdraw_or_de_register" /* 文字列リテラルの変更禁止 */),
        COURSE_CHANGE("course_change" /* 文字列リテラルの変更禁止 */),
        ADJOURN_OR_SERVICE_STOP("adjourn_or_service_stop" /* 文字列リテラルの変更禁止 */),;

        @Getter
        private final String noRefactoringValue;
    }

}
