package jp.co.biglobe.isp.mobile.ltethreeg.domain.connectperformance.speedstatus;

import jp.co.biglobe.lib.publication.enumeration.convert.EnumConvertForApi;
import jp.co.biglobe.lib.publication.enumeration.convert.EnumConvertForDb;
import jp.co.biglobe.lib.publication.enumeration.valuetype.ApiValue;
import jp.co.biglobe.lib.publication.enumeration.valuetype.DbValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum LimitStatus72Hour implements EnumConvertForDb, EnumConvertForApi {
    LIMITED(
            LimitStatus72HourValue.LIMITED,
            LimitStatus72HourApiValue.LIMITED
    ),
    NO_LIMIT(
            LimitStatus72HourValue.NO_LIMIT,
            LimitStatus72HourApiValue.NO_LIMIT
    );

    private final DbValue dbValue;

    private final LimitStatus72HourApiValue limitStatus72HourApiValue;

    @Override
    public String getDbValue() {
        return dbValue.getNoRefactoringValue();
    }

    @Override
    public String getApiValue(){
        return limitStatus72HourApiValue.getNoRefactoringValue();
    }

    public boolean isAlreadyLimited(){
        return this.equals(LimitStatus72Hour.LIMITED);
    }

    @AllArgsConstructor
    private enum LimitStatus72HourValue implements DbValue {
        LIMITED("limited" /* 文字列リテラルの変更禁止 */),
        NO_LIMIT("no_limit" /* 文字列リテラルの変更禁止 */);

        @Getter
        private final String noRefactoringValue;
    }

    @AllArgsConstructor
    public enum LimitStatus72HourApiValue implements ApiValue {
        LIMITED("limited" /* 文字列リテラルの変更禁止 */),
        NO_LIMIT("no_limit" /* 文字列リテラルの変更禁止 */);

        @Getter
        private final String noRefactoringValue;
    }

}
