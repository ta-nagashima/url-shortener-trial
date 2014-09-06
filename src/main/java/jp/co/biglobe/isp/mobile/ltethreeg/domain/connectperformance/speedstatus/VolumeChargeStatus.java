package jp.co.biglobe.isp.mobile.ltethreeg.domain.connectperformance.speedstatus;

import jp.co.biglobe.lib.publication.enumeration.convert.EnumConvertForApi;
import jp.co.biglobe.lib.publication.enumeration.convert.EnumConvertForDb;
import jp.co.biglobe.lib.publication.enumeration.valuetype.ApiValue;
import jp.co.biglobe.lib.publication.enumeration.valuetype.DbValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum VolumeChargeStatus implements EnumConvertForDb, EnumConvertForApi {
    UN_PURCHASE(
            VolumeChargeStatusValue.UN_PURCHASE,
            VolumeChargeStatusApiValue.UN_PURCHASE,
            LimitExclude.FALSE
    ),
    UN_CHARGE(
            VolumeChargeStatusValue.UN_CHARGE,
            VolumeChargeStatusApiValue.UN_CHARGE,
            LimitExclude.TRUE
    ),
    CHARGING(
            VolumeChargeStatusValue.CHARGING,
            VolumeChargeStatusApiValue.CHARGING,
            LimitExclude.TRUE
    ),
    STOPPED(
            VolumeChargeStatusValue.STOPPED,
            VolumeChargeStatusApiValue.STOPPED,
            LimitExclude.FALSE
    ),
    COMPLETE(
            VolumeChargeStatusValue.COMPLETE,
            VolumeChargeStatusApiValue.COMPLETE,
            LimitExclude.FALSE
    );

    private final DbValue dbValue;

    private final VolumeChargeStatusApiValue volumeChargeStatusApiValue;

    private final LimitExclude limitExclude;

    public boolean isLimitExclude(){
        return limitExclude.isValue();
    }

    @Override
    public String getDbValue() {
        return dbValue.getNoRefactoringValue();
    }

    @Override
    public String getApiValue(){
        return volumeChargeStatusApiValue.getNoRefactoringValue();
    }

    @AllArgsConstructor
    private enum VolumeChargeStatusValue implements DbValue {
        UN_PURCHASE("un_purchase" /* 文字列リテラルの変更禁止 */),
        UN_CHARGE("un_charge" /* 文字列リテラルの変更禁止 */),
        CHARGING("charging" /* 文字列リテラルの変更禁止 */),
        STOPPED("stopped" /* 文字列リテラルの変更禁止 */),
        COMPLETE("completion" /* 文字列リテラルの変更禁止 */);

        @Getter
        private final String noRefactoringValue;
    }

    @AllArgsConstructor
    private enum VolumeChargeStatusApiValue implements ApiValue {
        UN_PURCHASE("un_purchase" /* 文字列リテラルの変更禁止 */),
        UN_CHARGE("un_charge" /* 文字列リテラルの変更禁止 */),
        CHARGING("charging" /* 文字列リテラルの変更禁止 */),
        STOPPED("stopped" /* 文字列リテラルの変更禁止 */),
        COMPLETE("completion" /* 文字列リテラルの変更禁止 */);

        @Getter
        private final String noRefactoringValue;
    }

    @AllArgsConstructor
    private enum LimitExclude {
        TRUE(true),
        FALSE(false);

        @Getter
        private final boolean value;
    }

    public boolean isCharging(){
        return this.equals(VolumeChargeStatus.CHARGING);
    }
}
