package jp.co.biglobe.isp.mobile.voiceoption.domain.identification.identificationresult.identificationdocuments;

import jp.co.biglobe.lib.publication.enumeration.convert.EnumConvertForApi;
import jp.co.biglobe.lib.publication.enumeration.convert.EnumConvertForDb;
import jp.co.biglobe.lib.publication.enumeration.valuetype.ApiValue;
import jp.co.biglobe.lib.publication.enumeration.valuetype.DbValue;
import lombok.AllArgsConstructor;
import lombok.Getter;


/**
 * 補助書類
 */
@AllArgsConstructor
public enum IdentificationSubDocumentType implements EnumConvertForDb, EnumConvertForApi{
    UTILITY_BILL_RECEIPT(
            "公共料金領収書",
            AdditionalDocumentTypeDbValue.UTILITY_BILL_RECEIPT,
            AdditionalDocumentTypeApiValue.UTILITY_BILL_RECEIPT
    ),
    RESIDENT_CARD(
            "住民票",
            AdditionalDocumentTypeDbValue.RESIDENT_CARD,
            AdditionalDocumentTypeApiValue.RESIDENT_CARD
    ),
    FAMILY_REGISTER(
            "戸籍謄本",
            AdditionalDocumentTypeDbValue.FAMILY_REGISTER,
            AdditionalDocumentTypeApiValue.FAMILY_REGISTER
    ),
    OTHER(
            "その他",
            AdditionalDocumentTypeDbValue.OTHER,
            AdditionalDocumentTypeApiValue.OTHER
    ),
    NOTHING(
            "補助書類なし",
            AdditionalDocumentTypeDbValue.NOTHING,
            AdditionalDocumentTypeApiValue.NOTHING
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
     * APIに提示される値（文字列リテラル）の定義
     */
    @AllArgsConstructor
    private enum AdditionalDocumentTypeApiValue implements ApiValue {
        UTILITY_BILL_RECEIPT("utility_bill_receipt" /* 文字列リテラルの変更禁止 */),
        RESIDENT_CARD("resident_card" /* 文字列リテラルの変更禁止 */),
        FAMILY_REGISTER("family_register" /* 文字列リテラルの変更禁止 */),
        OTHER("other" /* 文字列リテラルの変更禁止 */),
        NOTHING("nothing" /* 文字列リテラルの変更禁止 */);

        @Getter
        private final String noRefactoringValue;
    }



    /**
     * DBに格納される値（文字列リテラル）の定義
     */
    @AllArgsConstructor
    private enum AdditionalDocumentTypeDbValue implements DbValue {
        UTILITY_BILL_RECEIPT("utility_bill_receipt" /* 文字列リテラルの変更禁止 */),
        RESIDENT_CARD("resident_card" /* 文字列リテラルの変更禁止 */),
        FAMILY_REGISTER("family_register" /* 文字列リテラルの変更禁止 */),
        OTHER("other" /* 文字列リテラルの変更禁止 */),
        NOTHING("nothing" /* 文字列リテラルの変更禁止 */);

        @Getter
        private final String noRefactoringValue;
    }

}
