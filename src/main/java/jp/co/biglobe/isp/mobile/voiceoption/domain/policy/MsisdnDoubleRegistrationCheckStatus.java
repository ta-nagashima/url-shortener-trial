package jp.co.biglobe.isp.mobile.voiceoption.domain.policy;

import jp.co.biglobe.lib.publication.enumeration.businessstatus.BusinessStatusForApi;
import jp.co.biglobe.lib.publication.enumeration.checkable.CheckResult;
import jp.co.biglobe.lib.publication.enumeration.checkable.Checkable;
import jp.co.biglobe.lib.publication.enumeration.valuetype.ApiValue;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@ToString(includeFieldNames=false)
@AllArgsConstructor
public enum MsisdnDoubleRegistrationCheckStatus implements BusinessStatusForApi {

    UNREGISTERED(CheckResult.OK, "MSISDN未登録", MsisdnDoubleRegistrationApiValue.UNREGISTERED),
    REGISTERED(CheckResult.NG, "MSISDN登録済み", MsisdnDoubleRegistrationApiValue.REGISTERED);

    @Getter
    private final Checkable checkable;

    @Getter
    private final String message;

    @Getter
    private final MsisdnDoubleRegistrationApiValue msisdnDoubleRegistrationApiValue;

    @Override
    public String getApiValue(){
        return msisdnDoubleRegistrationApiValue.getNoRefactoringValue();
    }

    /**
     * APIとして公開される値（文字列リテラル）の定義
     */
    @AllArgsConstructor
    private enum MsisdnDoubleRegistrationApiValue implements ApiValue {
        UNREGISTERED("unregistered_msisdn" /* 文字列リテラルの変更禁止 */),
        REGISTERED("registered_msisdn" /* 文字列リテラルの変更禁止 */);

        @Getter
        private final String noRefactoringValue;
    }
}
