package jp.co.biglobe.isp.mobile.ltethreeg.domain.connectperformance.speedstatus;

import jp.co.biglobe.isp.mobile.ltethreeg.domain.connectperformance.limithistory.DestinationLimitStatusForHistory;
import jp.co.biglobe.isp.mobile.ltethreeg.domain.connectperformance.limithistory.SpeedLimitStatusForHistory;
import jp.co.biglobe.lib.publication.enumeration.convert.EnumConvertForDb;
import jp.co.biglobe.lib.publication.enumeration.valuetype.ApiValue;
import jp.co.biglobe.lib.publication.enumeration.valuetype.DbValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum LimitStatus implements SpeedLimitStatusForHistory,DestinationLimitStatusForHistory {

    LIMITED(LimitStatusValue.LIMITED),
    NO_LIMIT(LimitStatusValue.NO_LIMIT);

    private final LimitStatusValue limitStatusValue;

    @Override
    public String getDbValue() {
        return limitStatusValue.getNoRefactoringValue();
    }

    @Override
    public String getApiValue() {
        return limitStatusValue.getNoRefactoringValue();
    }


    @AllArgsConstructor
    private enum LimitStatusValue implements DbValue, ApiValue {
        LIMITED("limited" /* 文字列リテラルの変更禁止 */),
        NO_LIMIT("no_limit" /* 文字列リテラルの変更禁止 */);

        @Getter
        private final String noRefactoringValue;
    }
}
