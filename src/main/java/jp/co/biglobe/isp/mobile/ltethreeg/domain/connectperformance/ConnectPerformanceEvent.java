package jp.co.biglobe.isp.mobile.ltethreeg.domain.connectperformance;

import jp.co.biglobe.lib.publication.enumeration.convert.EnumConvertForApi;
import jp.co.biglobe.lib.publication.enumeration.convert.EnumConvertForDb;
import jp.co.biglobe.lib.publication.enumeration.valuetype.ApiValue;
import jp.co.biglobe.lib.publication.enumeration.valuetype.DbValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum ConnectPerformanceEvent implements EnumConvertForDb, EnumConvertForApi {

    NEW_REGISTER(ConnectPerformanceEventValue.NEW_REGISTER),
    LIMIT_1_MONTH(ConnectPerformanceEventValue.LIMIT_1_MONTH),
    LIMIT_RELEASE_1_MONTH(ConnectPerformanceEventValue.LIMIT_RELEASE_1_MONTH),
    LIMIT_72_HOUR(ConnectPerformanceEventValue.LIMIT_72_HOUR),
    LIMIT_RELEASE_72_HOUR(ConnectPerformanceEventValue.LIMIT_RELEASE_72_HOUR),
    LIMIT_DESTINATION(ConnectPerformanceEventValue.LIMIT_DESTINATION),
    LIMIT_RELEASE_DESTINATION(ConnectPerformanceEventValue.LIMIT_RELEASE_DESTINATION),
    PLAN_CHANGE(ConnectPerformanceEventValue.PLAN_CHANGE),
    VOLUME_CHARGE_PURCHASE(ConnectPerformanceEventValue.VOLUME_CHARGE_PURCHASE),
    VOLUME_CHARGE_STOPPED(ConnectPerformanceEventValue.VOLUME_CHARGE_STOPPED),
    VOLUME_CHARGE_START(ConnectPerformanceEventValue.VOLUME_CHARGE_START),
    VOLUME_CHARGE_COMPLETE(ConnectPerformanceEventValue.VOLUME_CHARGE_COMPLETE),
    SPEED_CHARGE_PURCHASE(ConnectPerformanceEventValue.SPEED_CHARGE_PURCHASE),
    SPEED_CHARGE_COMPLETE(ConnectPerformanceEventValue.SPEED_CHARGE_COMPLETE);

    private final ConnectPerformanceEventValue connectPerformanceEventValue;

    @Override
    public String getDbValue() {
        return connectPerformanceEventValue.getNoRefactoringValue();
    }

    @Override
    public String getApiValue() {
        return connectPerformanceEventValue.getNoRefactoringValue();
    }

    @AllArgsConstructor
    private enum ConnectPerformanceEventValue implements DbValue, ApiValue {
        NEW_REGISTER("new_register" /* 文字列リテラルの変更禁止 */),
        LIMIT_1_MONTH("limit_1_month" /* 文字列リテラルの変更禁止 */),
        LIMIT_RELEASE_1_MONTH("limit_release_1_month" /* 文字列リテラルの変更禁止 */),
        LIMIT_72_HOUR("limit_72_hour" /* 文字列リテラルの変更禁止 */),
        LIMIT_RELEASE_72_HOUR("limit_release_72_hour" /* 文字列リテラルの変更禁止 */),
        LIMIT_DESTINATION("limit_destination" /* 文字列リテラルの変更禁止 */),
        LIMIT_RELEASE_DESTINATION("limit_release_destination" /* 文字列リテラルの変更禁止 */),
        PLAN_CHANGE("plan_change" /* 文字列リテラルの変更禁止 */),
        VOLUME_CHARGE_PURCHASE("volume_charge_purchase" /* 文字列リテラルの変更禁止 */),
        VOLUME_CHARGE_STOPPED("volume_charge_stopped" /* 文字列リテラルの変更禁止 */),
        VOLUME_CHARGE_START("volume_charge_start" /* 文字列リテラルの変更禁止 */),
        VOLUME_CHARGE_COMPLETE("volume_charge_complete" /* 文字列リテラルの変更禁止 */),
        SPEED_CHARGE_PURCHASE("speed_charge_purchase" /* 文字列リテラルの変更禁止 */),
        SPEED_CHARGE_COMPLETE("speed_charge_complete" /* 文字列リテラルの変更禁止 */);

        @Getter
        private final String noRefactoringValue;
    }
}
