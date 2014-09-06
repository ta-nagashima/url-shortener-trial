package jp.co.biglobe.isp.mobile.callhistory.domain.chargeitem;


import jp.co.biglobe.lib.publication.enumeration.convert.EnumConvertForApi;
import jp.co.biglobe.lib.publication.enumeration.convert.EnumConvertForDb;
import jp.co.biglobe.lib.publication.enumeration.valuetype.ApiValue;
import jp.co.biglobe.lib.publication.enumeration.valuetype.DbValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum TaxStatus implements EnumConvertForDb,EnumConvertForApi {
    TAX_EXEMPT(
            TaxStatusApiValue.TAX_EXEMPT,
            TaxStatusDbValue.TAX_EXEMPT
    ),
    TAX_FREE(
            TaxStatusApiValue.TAX_FREE,
            TaxStatusDbValue.TAX_FREE
    );

    private final ApiValue apiValue;

    private final DbValue dbValue;

    public String getApiValue(){
        return apiValue.getNoRefactoringValue();
    }

    public String getDbValue(){
        return dbValue.getNoRefactoringValue();
    }

    @AllArgsConstructor
    private enum TaxStatusApiValue implements ApiValue {
        TAX_EXEMPT("tax_exempt" /* 文字列リテラルの変更禁止 */),
        TAX_FREE("tax_free" /* 文字列リテラルの変更禁止 */);

        @Getter
        private final String noRefactoringValue;
    }

    @AllArgsConstructor
    private enum TaxStatusDbValue implements DbValue {
        TAX_EXEMPT("tax_exempt" /* 文字列リテラルの変更禁止 */),
        TAX_FREE("tax_free" /* 文字列リテラルの変更禁止 */);

        @Getter
        private final String noRefactoringValue;
    }



}
