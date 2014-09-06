package jp.co.biglobe.isp.mobile.ltethreeg.option.charge.domain.speedchargeengagement;

import jp.co.biglobe.lib.publication.enumeration.businessstatus.BusinessStatusForApi;
import jp.co.biglobe.lib.publication.enumeration.checkable.CheckResult;
import jp.co.biglobe.lib.publication.enumeration.checkable.Checkable;
import jp.co.biglobe.lib.publication.enumeration.valuetype.ApiValue;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@ToString(includeFieldNames=false)
@AllArgsConstructor
public enum SpeedChargePurchaseCheckStatus implements BusinessStatusForApi {

    UN_PURCHASE(CheckResult.OK, "未購入な状態", SpeedChargePurchaseCheckStatusApiValue.UN_PURCHASE),
    COMPLETED(CheckResult.OK, "消費完了済みの状態", SpeedChargePurchaseCheckStatusApiValue.COMPLETED),
    PURCHASED(CheckResult.NG, "購入して消費完了していない状態", SpeedChargePurchaseCheckStatusApiValue.PURCHASED),
    NOT_EXIST(CheckResult.NG, "LTE3G契約が存在しない状態", SpeedChargePurchaseCheckStatusApiValue.NOT_EXIST_ENGAGED),
    ORDERED(CheckResult.NG, "LTE3G契約が申込中の状態", SpeedChargePurchaseCheckStatusApiValue.ORDERED),
    INVALID_SERVICE_PLAN(CheckResult.NG, "申込できないサービスプランコードを契約中の状態", SpeedChargePurchaseCheckStatusApiValue.INVALID_SERVICE_PLAN);

    @Getter
    private final Checkable checkable;

    @Getter
    private final String message;

    private final SpeedChargePurchaseCheckStatusApiValue speedChargePurchaseCheckStatusApiValue;

    @Override
    public String getApiValue() {
        return this.speedChargePurchaseCheckStatusApiValue.getNoRefactoringValue();
    }

    /**
     * APIとして公開される値（文字列リテラル）の定義
     */
    @AllArgsConstructor
    private enum SpeedChargePurchaseCheckStatusApiValue implements ApiValue {
        UN_PURCHASE("ok_speed_charge_un_purchase" /* 文字列リテラルの変更禁止 */),
        COMPLETED("ok_speed_charge_completed" /* 文字列リテラルの変更禁止 */),
        PURCHASED("ng_speed_charge_purchased" /* 文字列リテラルの変更禁止 */),
        NOT_EXIST_ENGAGED("ng_lte3g_not_exist" /* 文字列リテラルの変更禁止 */),
        ORDERED("ng_lte3g_ordered" /* 文字列リテラルの変更禁止 */),
        INVALID_SERVICE_PLAN("ng_lte3g_invalid_service_plan" /* 文字列リテラルの変更禁止 */);

        @Getter
        private final String noRefactoringValue;
    }
}
