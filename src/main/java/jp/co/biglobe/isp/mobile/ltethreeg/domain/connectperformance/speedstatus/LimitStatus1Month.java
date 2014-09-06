package jp.co.biglobe.isp.mobile.ltethreeg.domain.connectperformance.speedstatus;

import jp.co.biglobe.lib.publication.enumeration.convert.EnumConvertForApi;
import jp.co.biglobe.lib.publication.enumeration.convert.EnumConvertForDb;
import jp.co.biglobe.lib.publication.enumeration.valuetype.ApiValue;
import jp.co.biglobe.lib.publication.enumeration.valuetype.DbValue;
import jp.co.biglobe.lib.publication.validation.EnumForApiValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum LimitStatus1Month implements EnumConvertForDb, EnumConvertForApi {
    LIMITED(
            LimitStatus1MonthValue.LIMITED,
            LimitStatus1MonthApiValue.LIMITED
    ),
    NO_LIMIT(
            LimitStatus1MonthValue.NO_LIMIT,
            LimitStatus1MonthApiValue.NO_LIMIT
    );

    private final DbValue dbValue;

    private final LimitStatus1MonthApiValue limitStatus1MonthApiValue;

    @Override
    public String getDbValue() {
        return dbValue.getNoRefactoringValue();
    }

    @Override
    public String getApiValue(){
        return limitStatus1MonthApiValue.getNoRefactoringValue();
    }

    public boolean isAlreadyLimited(){
        return this.equals(LimitStatus1Month.LIMITED);
    }

    @AllArgsConstructor
    private enum LimitStatus1MonthValue implements DbValue {
        LIMITED("limited" /* 文字列リテラルの変更禁止 */),
        NO_LIMIT("no_limit" /* 文字列リテラルの変更禁止 */);

        @Getter
        private final String noRefactoringValue;
    }

    @AllArgsConstructor
    public enum LimitStatus1MonthApiValue implements ApiValue {
        LIMITED("limited" /* 文字列リテラルの変更禁止 */),
        NO_LIMIT("no_limit" /* 文字列リテラルの変更禁止 */);

        @Getter
        private final String noRefactoringValue;
    }

}
