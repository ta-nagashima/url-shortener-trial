package jp.co.biglobe.isp.mobile.callhistory.domain.chargeitem;


import jp.co.biglobe.lib.publication.enumeration.convert.EnumConvertForApi;
import jp.co.biglobe.lib.publication.enumeration.convert.EnumConvertForDb;
import jp.co.biglobe.lib.publication.enumeration.valuetype.ApiValue;
import jp.co.biglobe.lib.publication.enumeration.valuetype.DbValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum CallKind implements EnumConvertForDb,EnumConvertForApi {
    DOMESTIC(
            InterNationalCallKindApiValue.DOMESTIC,
            InternationalCallKindDbValue.DOMESTIC
    ),
    INTERNATIONAL(
            InterNationalCallKindApiValue.INTERNATIONAL,
            InternationalCallKindDbValue.INTERNATIONAL
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
    private enum InterNationalCallKindApiValue implements ApiValue {
        DOMESTIC("1" /* 文字列リテラルの変更禁止 */),
        INTERNATIONAL("2" /* 文字列リテラルの変更禁止 */);

        @Getter
        private final String noRefactoringValue;
    }

    @AllArgsConstructor
    private enum InternationalCallKindDbValue implements DbValue {
        DOMESTIC("domestic" /* 文字列リテラルの変更禁止 */),
        INTERNATIONAL("international" /* 文字列リテラルの変更禁止 */);

        @Getter
        private final String noRefactoringValue;
    }



}
