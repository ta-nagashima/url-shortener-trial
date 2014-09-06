package jp.co.biglobe.isp.mobile.voiceoption.domain.identification.identificationresult.identificationdocuments;

import jp.co.biglobe.lib.publication.enumeration.convert.EnumConvertForApi;
import jp.co.biglobe.lib.publication.enumeration.convert.EnumConvertForDb;
import jp.co.biglobe.lib.publication.enumeration.valuetype.ApiValue;
import jp.co.biglobe.lib.publication.enumeration.valuetype.DbValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum IdentificationDocumentType implements EnumConvertForDb, EnumConvertForApi {
    LICENSE(
            "運転免許証",
            DocumentTypeDbValue.LICENSE,
            DocumentTypeApiValue.LICENSE
    ),
    PASSPORT("パスポート",
            DocumentTypeDbValue.PASSPORT,
            DocumentTypeApiValue.PASSPORT
    ),
    FOREIGN_REGISTRATION_CARD(
            "外国人登録証明書",
            DocumentTypeDbValue.FOREIGN_REGISTRATION_CARD,
            DocumentTypeApiValue.FOREIGN_REGISTRATION_CARD
    ),
    RESIDENCE_CARD(
            "在留カード",
            DocumentTypeDbValue.RESIDENCE_CARD,
            DocumentTypeApiValue.RESIDENCE_CARD
    ),
    SPECIAL_PERMANENT_RESIDENT_CERTIFICATE(
            "特別永住者証明書",
            DocumentTypeDbValue.SPECIAL_PERMANENT_RESIDENT_CERTIFICATE,
            DocumentTypeApiValue.SPECIAL_PERMANENT_RESIDENT_CERTIFICATE
    ),
    BASIC_RESIDENT_REGISTER_CARD(
            "住民基本台帳カード",
            DocumentTypeDbValue.BASIC_RESIDENT_REGISTER_CARD,
            DocumentTypeApiValue.BASIC_RESIDENT_REGISTER_CARD
    ),
    HEALTH_INSURANCE_CARD(
            "健康保険証",
            DocumentTypeDbValue.HEALTH_INSURANCE_CARD,
            DocumentTypeApiValue.HEALTH_INSURANCE_CARD
    ),
    OTHER(
            "その他",
            DocumentTypeDbValue.OTHER,
            DocumentTypeApiValue.OTHER
    ),
    INVALID(
            "本人確認書類無効",
            DocumentTypeDbValue.INVALID,
            DocumentTypeApiValue.INVALID
    );

    private final String message;

    private final DbValue dbValue;

    private final ApiValue apiValue;

    @Override
    public String getApiValue() {
        return apiValue.getNoRefactoringValue();
    }


    @Override
    public String getDbValue() {
        return dbValue.getNoRefactoringValue();
    }

    /**
     * APIとして提示される値の定義
     */
    @AllArgsConstructor
    private enum DocumentTypeApiValue implements ApiValue {
        LICENSE("license" /* 文字列リテラルの変更禁止 */),
        PASSPORT("passport" /* 文字列リテラルの変更禁止 */),
        FOREIGN_REGISTRATION_CARD("foreign_registration_card" /* 文字列リテラルの変更禁止 */),
        RESIDENCE_CARD("residence_card" /* 文字列リテラルの変更禁止 */),
        SPECIAL_PERMANENT_RESIDENT_CERTIFICATE("special_permanent_resident_certificate" /* 文字列リテラルの変更禁止 */),
        BASIC_RESIDENT_REGISTER_CARD("basic_resident_register_card" /* 文字列リテラルの変更禁止 */),
        HEALTH_INSURANCE_CARD("health_insurance_card" /* 文字列リテラルの変更禁止 */),
        OTHER("other" /* 文字列リテラルの変更禁止 */),
        INVALID("invalid" /* 文字列リテラルの変更禁止 */);

        @Getter
        private final String noRefactoringValue;
    }


    /**
     * DBに格納される値（文字列リテラル）の定義
     */
    @AllArgsConstructor
    private enum DocumentTypeDbValue implements DbValue {
        LICENSE("license" /* 文字列リテラルの変更禁止 */),
        PASSPORT("passport" /* 文字列リテラルの変更禁止 */),
        FOREIGN_REGISTRATION_CARD("foreign_registration_card" /* 文字列リテラルの変更禁止 */),
        RESIDENCE_CARD("residence_card" /* 文字列リテラルの変更禁止 */),
        SPECIAL_PERMANENT_RESIDENT_CERTIFICATE("special_permanent_resident_certificate" /* 文字列リテラルの変更禁止 */),
        BASIC_RESIDENT_REGISTER_CARD("basic_resident_register_card" /* 文字列リテラルの変更禁止 */),
        HEALTH_INSURANCE_CARD("health_insurance_card" /* 文字列リテラルの変更禁止 */),
        OTHER("other" /* 文字列リテラルの変更禁止 */),
        INVALID("invalid" /* 文字列リテラルの変更禁止 */);

        @Getter
        private final String noRefactoringValue;
    }

}
