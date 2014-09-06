package jp.co.biglobe.isp.mobile.ltethreeg.domain.connectperformance;

import jp.co.biglobe.isp.mobile.ltethreeg.domain.connectperformance.speedstatus.LimitStatus;
import jp.co.biglobe.lib.publication.enumeration.convert.EnumConvertForApi;
import jp.co.biglobe.lib.publication.enumeration.convert.EnumConvertForDb;
import jp.co.biglobe.lib.publication.enumeration.valuetype.ApiValue;
import jp.co.biglobe.lib.publication.enumeration.valuetype.DbValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum DestinationStatus implements EnumConvertForDb, EnumConvertForApi {
    LIMITED_BIGLOBE_ONLY(
            LteConnectDestinationStatusValue.LIMITED_BIGLOBE_ONLY,
            LteConnectDestinationStatusApiValue.LIMITED_BIGLOBE_ONLY,
            LimitStatus.LIMITED
    ),
    NO_LIMIT(
            LteConnectDestinationStatusValue.NO_LIMIT,
            LteConnectDestinationStatusApiValue.NO_LIMIT,
            LimitStatus.NO_LIMIT
    );

    private final DbValue dbValue;

    private final LteConnectDestinationStatusApiValue lteConnectDestinationStatusApiValue;

    private final LimitStatus limitStatus;

    @Override
    public String getDbValue() {
        return dbValue.getNoRefactoringValue();
    }

    @Override
    public String getApiValue(){
        return lteConnectDestinationStatusApiValue.getNoRefactoringValue();
    }

    public boolean isLimited(){
        return this.equals(DestinationStatus.LIMITED_BIGLOBE_ONLY);
    }

    public LimitStatus getDestinationLimitStatus(){
        return limitStatus;
    }

    @AllArgsConstructor
    private enum LteConnectDestinationStatusValue implements DbValue {
        LIMITED_BIGLOBE_ONLY("limited_biglobe_only" /* 文字列リテラルの変更禁止 */),
        NO_LIMIT("no_limit" /* 文字列リテラルの変更禁止 */);

        @Getter
        private final String noRefactoringValue;
    }

    @AllArgsConstructor
    private enum LteConnectDestinationStatusApiValue implements ApiValue {
        LIMITED_BIGLOBE_ONLY("limited_biglobe_only" /* 文字列リテラルの変更禁止 */),
        NO_LIMIT("no_limit" /* 文字列リテラルの変更禁止 */);

        @Getter
        private final String noRefactoringValue;
    }
}
