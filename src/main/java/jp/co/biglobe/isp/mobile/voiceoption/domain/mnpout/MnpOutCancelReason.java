package jp.co.biglobe.isp.mobile.voiceoption.domain.mnpout;

import jp.co.biglobe.lib.publication.enumeration.convert.EnumConvertForApi;
import jp.co.biglobe.lib.publication.enumeration.convert.EnumConvertForDb;
import jp.co.biglobe.lib.publication.enumeration.valuetype.ApiValue;
import jp.co.biglobe.lib.publication.enumeration.valuetype.DbValue;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@ToString(includeFieldNames=false)
@AllArgsConstructor
public enum MnpOutCancelReason implements EnumConvertForDb,EnumConvertForApi {
    NOT_CANCEL(
            "キャンセルなし",
            MnpOutCancelReasonDbValue.NOT_CANCEL,
            MnpOutCancelReasonApiValue.NOT_CANCEL
    ),
    EXPIRED(
            "MNP予約番号の有効期限切れ",
            MnpOutCancelReasonDbValue.EXPIRED,
            MnpOutCancelReasonApiValue.EXPIRED
    ),
    USER_REQUEST(
            "ユーザからの転出キャンセル依頼",
            MnpOutCancelReasonDbValue.USER_REQUEST,
            MnpOutCancelReasonApiValue.USER_REQUEST
    );

    private final String message;
    private final DbValue dbValue;
    private final MnpOutCancelReasonApiValue mnpOutCancelReasonApiValue;

    @Override
    public String getDbValue() {
        return dbValue.getNoRefactoringValue();
    }

    @Override
    public String getApiValue() { return mnpOutCancelReasonApiValue.getNoRefactoringValue(); }

    /**
     * DBに格納される値（文字列リテラル）の定義
     */
    @AllArgsConstructor
    private enum MnpOutCancelReasonDbValue implements DbValue {
        NOT_CANCEL("not_cancel" /* 文字列リテラルの変更禁止 */),
        EXPIRED("expired" /* 文字列リテラルの変更禁止 */),
        USER_REQUEST("user_request" /* 文字列リテラルの変更禁止 */);

        @Getter
        private final String noRefactoringValue;
    }

    /**
     * APIとして公開される値（文字列リテラル）の定義
     */
    @AllArgsConstructor
    public enum MnpOutCancelReasonApiValue implements ApiValue {
        NOT_CANCEL("not_cancel" /* 文字列リテラルの変更禁止 */),
        EXPIRED("expired" /* 文字列リテラルの変更禁止 */),
        USER_REQUEST("user_request" /* 文字列リテラルの変更禁止 */);

        @Getter
        private final String noRefactoringValue;
    }

}
