package jp.co.biglobe.isp.mobile.voiceoption.domain.voicesendmail;

import jp.co.biglobe.lib.publication.enumeration.convert.EnumConvertForApi;
import jp.co.biglobe.lib.publication.enumeration.valuetype.ApiValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum VoiceSendMailStatus implements EnumConvertForApi {
    OK(
            "メール送信成功",
            IdentificationResultMailStatusApiValue.OK
    ),
    UNNECESSARY(
            "不要",
            IdentificationResultMailStatusApiValue.UNNECESSARY
    ),
    EXPIRATION_OF_SERVICE(
            "サービス期限切れ（会員情報の契約が終了）",
            IdentificationResultMailStatusApiValue.EXPIRATION_OF_SERVICE
    ),
    ERROR(
            "送信エラー",
            IdentificationResultMailStatusApiValue.ERROR
    );

    private final String message;
    private final ApiValue apiValue;

    @Override
    public String getApiValue() {
        return apiValue.getNoRefactoringValue();
    }

    /**
     * APIに提示される値（文字列リテラル）の定義
     */
    @AllArgsConstructor
    private enum IdentificationResultMailStatusApiValue implements ApiValue {
        OK("ok" /* 文字列リテラルの変更禁止 */),
        UNNECESSARY("unnecessary" /* 文字列リテラルの変更禁止 */),
        EXPIRATION_OF_SERVICE("expiration_of_service" /* 文字列リテラルの変更禁止 */),
        ERROR("error" /* 文字列リテラルの変更禁止 */);

        @Getter
        private final String noRefactoringValue;
    }

}