package jp.co.biglobe.isp.mobile.ltethreeg.domain.ltethreegengagement.serviceplan;


import jp.co.biglobe.isp.mobile.ltethreeg.domain.connectperformance.connectcontrolpolicy.ConnectControlSpeedLimitPolicy;
import jp.co.biglobe.isp.mobile.ltethreeg.domain.connectperformance.connectcontrolpolicy.ConnectControlSpeedNoLimitPolicy;
import jp.co.biglobe.lib.publication.enumeration.convert.EnumConvertForApi;
import jp.co.biglobe.lib.publication.enumeration.convert.EnumConvertForDb;
import jp.co.biglobe.lib.publication.enumeration.convert.EnumConvertForScenario;
import jp.co.biglobe.lib.publication.enumeration.valuetype.ApiValue;
import jp.co.biglobe.lib.publication.enumeration.valuetype.DbValue;
import jp.co.biglobe.lib.publication.enumeration.valuetype.ScenarioValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum LteThreeGServicePlanCode implements EnumConvertForDb, EnumConvertForScenario, EnumConvertForApi {

    SERVICE_BEFORE_START(
            LteThreeGServicePlanCodeValue.SERVICE_BEFORE_START,
            // サービス開始前プランは通信先制限のみなので、スピード制限の設定はしない。使うな。
            null,
            null,
            ChargeOption.NONE
    ),
    STANDARD(
            LteThreeGServicePlanCodeValue.STANDARD,
            ConnectControlSpeedNoLimitPolicy.STANDARD_NO_LIMIT,
            ConnectControlSpeedLimitPolicy.STANDARD_LIMIT,
            ChargeOption.VOLUME
    ),
    LIGHT_M(
            LteThreeGServicePlanCodeValue.LIGHT_M,
            ConnectControlSpeedNoLimitPolicy.LIGHT_M_NO_LIMIT,
            ConnectControlSpeedLimitPolicy.LIGHT_M_LIMIT,
            ChargeOption.VOLUME
    ),
    LIGHT_S(
            LteThreeGServicePlanCodeValue.LIGHT_S,
            ConnectControlSpeedNoLimitPolicy.LIGHT_S_NO_LIMIT,
            ConnectControlSpeedLimitPolicy.LIGHT_S_LIMIT,
            ChargeOption.VOLUME
    ),
    LIGHT_SS(
            LteThreeGServicePlanCodeValue.LIGHT_SS,
            ConnectControlSpeedNoLimitPolicy.LIGHT_SS_NO_LIMIT,
            ConnectControlSpeedLimitPolicy.LIGHT_SS_LIMIT,
            ChargeOption.SPEED
    ),
    ENTRY(
            LteThreeGServicePlanCodeValue.ENTRY,
            ConnectControlSpeedNoLimitPolicy.ENTRY_NO_LIMIT,
            ConnectControlSpeedLimitPolicy.ENTRY_LIMIT,
            ChargeOption.VOLUME
    ),
    START(
            LteThreeGServicePlanCodeValue.START,
            ConnectControlSpeedNoLimitPolicy.START_NO_LIMIT,
            ConnectControlSpeedLimitPolicy.START_LIMIT,
            ChargeOption.VOLUME
    );

    private final LteThreeGServicePlanCodeValue value;

    @Getter
    private final ConnectControlSpeedNoLimitPolicy connectControlSpeedNoLimitPolicy;

    @Getter
    private final ConnectControlSpeedLimitPolicy connectControlSpeedLimitPolicy;

    private final ChargeOption chargeOption;


    @Override
    public String getDbValue() {
        return value.getNoRefactoringValue();
    }

    @Override
    public String getScenarioValue(){
        return value.getNoRefactoringValue();
    }

    @Override
    public String getApiValue(){
        return value.getNoRefactoringValue();
    }

    public boolean isSpeedChargeValid(){
        return chargeOption.equals(ChargeOption.SPEED);
    }

    public boolean isVolumeChargeValid(){
        return chargeOption.equals(ChargeOption.VOLUME);
    }


    @AllArgsConstructor
    private enum LteThreeGServicePlanCodeValue implements DbValue, ScenarioValue, ApiValue {
        SERVICE_BEFORE_START("LDM00001" /* 文字列リテラルの変更禁止 */),
        STANDARD("LTA00001" /* 文字列リテラルの変更禁止 */),
        LIGHT_M("LTA00003"  /* 文字列リテラルの変更禁止 */ ),
        LIGHT_S("LTA00004"  /* 文字列リテラルの変更禁止 */ ),
        LIGHT_SS("LTA00005"  /* 文字列リテラルの変更禁止 */ ),
        ENTRY("LTA00006" /* 文字列リテラルの変更禁止 */),
        START("LTA00007" /* 文字列リテラルの変更禁止 */);

        @Getter
        private final String noRefactoringValue;
    }

    @AllArgsConstructor
    private enum ChargeOption {
        NONE,
        VOLUME,
        SPEED;

    }

}
