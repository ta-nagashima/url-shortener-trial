package jp.co.biglobe.isp.mobile.ltethreeg.domain.connectperformance.speedstatus;

import jp.co.biglobe.lib.publication.enumeration.convert.EnumConvertForApi;
import jp.co.biglobe.lib.publication.enumeration.convert.EnumConvertForDb;
import jp.co.biglobe.lib.publication.enumeration.valuetype.ApiValue;
import jp.co.biglobe.lib.publication.enumeration.valuetype.DbValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum SpeedChargeStatus implements EnumConvertForDb, EnumConvertForApi {
    UN_PURCHASE(
            SpeedChargeStatusValue.UN_PURCHASE,
            SpeedChargeStatusApiValue.UN_PURCHASE,
            Limit72HourExclude.FALSE
    ),
    CHARGING(
            SpeedChargeStatusValue.CHARGING,
            SpeedChargeStatusApiValue.CHARGING,
            Limit72HourExclude.TRUE
    ),
    COMPLETE(
            SpeedChargeStatusValue.COMPLETE,
            SpeedChargeStatusApiValue.COMPLETE,
            Limit72HourExclude.FALSE
    );

    private final DbValue dbValue;
    private final SpeedChargeStatusApiValue speedChargeStatusApiValue;

    private final Limit72HourExclude limit72HourExclude;

    public boolean getLimit72HourExclude(){
        return limit72HourExclude.isValue();
    }

    @Override
    public String getDbValue() {
        return dbValue.getNoRefactoringValue();
    }

    @Override
    public String getApiValue(){
        return speedChargeStatusApiValue.getNoRefactoringValue();
    }

    @AllArgsConstructor
    private enum SpeedChargeStatusValue implements DbValue {
        UN_PURCHASE("un_purchase" /* 文字列リテラルの変更禁止 */),
        CHARGING("charging" /* 文字列リテラルの変更禁止 */),
        COMPLETE("completion" /* 文字列リテラルの変更禁止 */);

        @Getter
        private final String noRefactoringValue;
    }

    @AllArgsConstructor
    private enum SpeedChargeStatusApiValue implements ApiValue {
        UN_PURCHASE("un_purchase" /* 文字列リテラルの変更禁止 */),
        CHARGING("charging" /* 文字列リテラルの変更禁止 */),
        COMPLETE("completion" /* 文字列リテラルの変更禁止 */);

        @Getter
        private final String noRefactoringValue;
    }

    @AllArgsConstructor
    private enum Limit72HourExclude {
        TRUE(true),
        FALSE(false);

        @Getter
        private final boolean value;
    }

    public boolean isCharging(){
        return this.equals(SpeedChargeStatus.CHARGING);
    }
}
