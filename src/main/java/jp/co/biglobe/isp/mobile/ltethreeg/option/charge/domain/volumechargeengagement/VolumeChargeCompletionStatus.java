package jp.co.biglobe.isp.mobile.ltethreeg.option.charge.domain.volumechargeengagement;

import jp.co.biglobe.lib.publication.enumeration.convert.EnumConvertForApi;
import jp.co.biglobe.lib.publication.enumeration.convert.EnumConvertForDb;
import jp.co.biglobe.lib.publication.enumeration.valuetype.ApiValue;
import jp.co.biglobe.lib.publication.enumeration.valuetype.DbValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum VolumeChargeCompletionStatus implements EnumConvertForDb, EnumConvertForApi {
    INIT(VolumeChargeCompletionStatusValue.INIT,VolumeChargePurchaseCheckStatus.UN_PURCHASE),
    REMAINED(VolumeChargeCompletionStatusValue.REMAINED,VolumeChargePurchaseCheckStatus.PURCHASED),
    COMPLETION(VolumeChargeCompletionStatusValue.COMPLETION,VolumeChargePurchaseCheckStatus.COMPLETED);

    private final VolumeChargeCompletionStatusValue volumeChargeCompletionStatusValue;

    @Getter
    private final VolumeChargePurchaseCheckStatus volumeChargePurchaseCheckStatus;

    @Override
    public String getDbValue() {
        return volumeChargeCompletionStatusValue.getNoRefactoringValue();
    }

    @Override
    public String getApiValue() {
        return volumeChargeCompletionStatusValue.getNoRefactoringValue();
    }


    @AllArgsConstructor
    private enum VolumeChargeCompletionStatusValue implements DbValue, ApiValue {
        INIT("init" /* 文字列リテラルの変更禁止 */),
        REMAINED("remained" /* 文字列リテラルの変更禁止 */),
        COMPLETION("completion" /* 文字列リテラルの変更禁止 */);

        @Getter
        private final String noRefactoringValue;
    }
}
