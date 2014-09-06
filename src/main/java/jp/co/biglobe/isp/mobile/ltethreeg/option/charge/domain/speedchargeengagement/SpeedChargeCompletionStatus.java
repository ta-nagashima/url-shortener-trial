package jp.co.biglobe.isp.mobile.ltethreeg.option.charge.domain.speedchargeengagement;

import jp.co.biglobe.lib.publication.enumeration.convert.EnumConvertForApi;
import jp.co.biglobe.lib.publication.enumeration.convert.EnumConvertForDb;
import jp.co.biglobe.lib.publication.enumeration.valuetype.ApiValue;
import jp.co.biglobe.lib.publication.enumeration.valuetype.DbValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum SpeedChargeCompletionStatus implements EnumConvertForDb, EnumConvertForApi {
    INIT(SpeedChargeCompletionStatusValue.INIT, SpeedChargePurchaseCheckStatus.UN_PURCHASE),
    REMAINED(SpeedChargeCompletionStatusValue.REMAINED, SpeedChargePurchaseCheckStatus.PURCHASED),
    COMPLETION(SpeedChargeCompletionStatusValue.COMPLETION, SpeedChargePurchaseCheckStatus.COMPLETED);

    private final SpeedChargeCompletionStatusValue speedChargeCompletionStatusValue;

    @Getter
    private final SpeedChargePurchaseCheckStatus speedChargePurchaseCheckStatus;

    @Override
    public String getDbValue() {
        return speedChargeCompletionStatusValue.getNoRefactoringValue();
    }

    @Override
    public String getApiValue(){
        return speedChargeCompletionStatusValue.getNoRefactoringValue();
    }

    @AllArgsConstructor
    private enum SpeedChargeCompletionStatusValue implements DbValue, ApiValue {
        INIT("init" /* 文字列リテラルの変更禁止 */),
        REMAINED("remained" /* 文字列リテラルの変更禁止 */),
        COMPLETION("completion" /* 文字列リテラルの変更禁止 */);

        @Getter
        private final String noRefactoringValue;
    }


}
