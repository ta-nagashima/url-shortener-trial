package jp.co.biglobe.isp.mobile.voiceoption.domain.identification.identificationresult.ngreasons;


import jp.co.biglobe.lib.publication.enumeration.convert.EnumConvertForApi;
import jp.co.biglobe.lib.publication.enumeration.convert.EnumConvertForDb;
import jp.co.biglobe.lib.publication.enumeration.valuetype.ApiValue;
import jp.co.biglobe.lib.publication.enumeration.valuetype.DbValue;
import lombok.AllArgsConstructor;
import lombok.Getter;


@AllArgsConstructor
public enum NgReason implements EnumConvertForDb, EnumConvertForApi {
    INCOMPLETE_DOCUMENT(
            "本人確認書類不備",
            NgReasonDbValue.INCOMPLETE_DOCUMENT,
            NgReasonApiValue.INCOMPLETE_DOCUMENT
    ),
    INCONSISTENT_INFORMATION(
            "会員情報と本人確認書類の不一致",
            NgReasonDbValue.INCONSISTENT_INFORMATION,
            NgReasonApiValue.INCONSISTENT_INFORMATION
    ),
    INCOMPLETE_APPLICATION_DOCUMENT(
            "MNP転入情報不備",
            NgReasonDbValue.INCOMPLETE_APPLICATION_DOCUMENT,
            NgReasonApiValue.INCOMPLETE_APPLICATION_DOCUMENT),
    INCONSISTENT_MNP_INFORMATION(
            "MNP転入情報不備",
            NgReasonDbValue.INCONSISTENT_MNP_INFORMATION,
            NgReasonApiValue.INCONSISTENT_MNP_INFORMATION),
    ETC(
            "申込書不備",
            NgReasonDbValue.ETC,
            NgReasonApiValue.ETC
    );

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
     */
    @AllArgsConstructor
    private enum NgReasonDbValue implements DbValue {
        INCOMPLETE_DOCUMENT("incomplete_document" /* 文字列リテラルの変更禁止 */),
        INCONSISTENT_INFORMATION("inconsistent_information" /* 文字列リテラルの変更禁止 */),
        INCONSISTENT_MNP_INFORMATION("inconsistent_mnp_information" /* 文字列リテラルの変更禁止 */),
        INCOMPLETE_APPLICATION_DOCUMENT("incomplete_application_document" /* 文字列リテラルの変更禁止 */),
        ETC("etc" /* 文字列リテラルの変更禁止 */);

        @Getter
        private final String noRefactoringValue;
    }

    /**
     * APIとして公開される値（文字列リテラル）の定義
     */
    @AllArgsConstructor
    public enum NgReasonApiValue implements ApiValue {
        INCOMPLETE_DOCUMENT("incomplete_document" /* 文字列リテラルの変更禁止 */),
        INCONSISTENT_INFORMATION("inconsistent_information" /* 文字列リテラルの変更禁止 */),
        INCONSISTENT_MNP_INFORMATION("inconsistent_mnp_information" /* 文字列リテラルの変更禁止 */),
        INCOMPLETE_APPLICATION_DOCUMENT("incomplete_application_document" /* 文字列リテラルの変更禁止 */),
        ETC("etc" /* 文字列リテラルの変更禁止 */);

        @Getter
        private final String noRefactoringValue;
    }

}
