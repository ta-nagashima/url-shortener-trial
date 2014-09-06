package jp.co.biglobe.isp.mobile.biglobedenwa.domain.policy;

import jp.co.biglobe.isp.mobile.biglobedenwa.domain.alarm.BiglobeDenwaAlarmIdentifier;
import jp.co.biglobe.isp.mobile.biglobedenwa.domain.linkage.BiglobeDenwaRegisterErrorStatus;
import jp.co.biglobe.isp.mobile.extension.exception.BusinessException;
import jp.co.biglobe.lib.publication.enumeration.businessstatus.BusinessStatusForApi;
import jp.co.biglobe.lib.publication.enumeration.checkable.CheckResult;
import jp.co.biglobe.lib.publication.enumeration.checkable.Checkable;
import jp.co.biglobe.lib.publication.enumeration.valuetype.ApiValue;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@ToString(includeFieldNames=false)
@AllArgsConstructor
public enum BiglobeDenwaBirthDayCheckStatus implements BusinessStatusForApi {

    OK(CheckResult.OK, "認証OK" , BiglobeDenwaBirthDayCheckStatusApiValue.OK),
    NG(CheckResult.NG, "認証NG" , BiglobeDenwaBirthDayCheckStatusApiValue.NG),
    UNREGISTERED_LTE3G(CheckResult.NG,"unregistered_lte3g" , BiglobeDenwaBirthDayCheckStatusApiValue.UNREGISTERED_LTE3G),
    UNREGISTERED_DENWA(CheckResult.NG,"unregistered_denwa" , BiglobeDenwaBirthDayCheckStatusApiValue.UNREGISTERED_DENWA);


    @Getter
    private final Checkable checkable;

    @Getter
    private final  String message;

    @Getter
    private final BiglobeDenwaBirthDayCheckStatusApiValue biglobeDenwaBirthDayCheckStatusApiValue;

    @Override
    public String getApiValue() { return biglobeDenwaBirthDayCheckStatusApiValue.getNoRefactoringValue(); }

    /**
     * APIとして公開される値（文字列リテラル）の定義
     */
    @AllArgsConstructor
    private enum BiglobeDenwaBirthDayCheckStatusApiValue implements ApiValue {
        OK("ok" /* 文字列リテラルの変更禁止 */),
        NG("ng" /* 文字列リテラルの変更禁止 */),
        UNREGISTERED_LTE3G("unregistered_lte3g" /* 文字列リテラルの変更禁止 */),
        UNREGISTERED_DENWA("unregistered_denwa" /* 文字列リテラルの変更禁止 */);

        @Getter
        private final String noRefactoringValue;
    }

    public void verifyForBiglobeDenwaBirthDayAuthResult(){
        if(this.equals(BiglobeDenwaBirthDayCheckStatus.NG)){
            //TODO
            //専用のExceptionをつくる
            throw new BusinessException("生年月日が違います", BiglobeDenwaRegisterErrorStatus.INCORRECT_BIRTHDAY, BiglobeDenwaAlarmIdentifier.DEFAULT);
        }
    }
}
