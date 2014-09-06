package jp.co.biglobe.isp.mobile.ltethreeg.domain.connectperformance.connectcontrolpolicy;

import jp.co.biglobe.lib.publication.enumeration.valuetype.ApiValue;
import jp.co.biglobe.lib.publication.enumeration.valuetype.DbValue;
import jp.co.biglobe.lib.publication.enumeration.valuetype.ScenarioValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum ConnectControlSpeedNoLimitPolicy implements ConnectControlPolicy {

    STANDARD_NO_LIMIT(
            ConnectControlPolicyDbValue.STANDARD_NO_LIMIT,
            ConnectControlPolicyScenarioValue.STANDARD_NO_LIMIT
    ),
    LIGHT_M_NO_LIMIT(
            ConnectControlPolicyDbValue.LIGHT_M_NO_LIMIT,
            ConnectControlPolicyScenarioValue.LIGHT_M_NO_LIMIT
    ),
    LIGHT_S_NO_LIMIT(
            ConnectControlPolicyDbValue.LIGHT_S_NO_LIMIT,
            ConnectControlPolicyScenarioValue.LIGHT_S_NO_LIMIT
    ),
    LIGHT_SS_NO_LIMIT(
            ConnectControlPolicyDbValue.LIGHT_SS_NO_LIMIT,
            ConnectControlPolicyScenarioValue.LIGHT_SS_NO_LIMIT
    ),
    ENTRY_NO_LIMIT(
            ConnectControlPolicyDbValue.ENTRY_NO_LIMIT,
            ConnectControlPolicyScenarioValue.ENTRY_NO_LIMIT
    ),
    START_NO_LIMIT(
            ConnectControlPolicyDbValue.START_NO_LIMIT,
            ConnectControlPolicyScenarioValue.START_NO_LIMIT
    ),
    SPEED_CHARGE_NO_LIMIT(
            ConnectControlPolicyDbValue.SPEED_CHARGE_NO_LIMIT,
            ConnectControlPolicyScenarioValue.SPEED_CHARGE_NO_LIMIT
    );

    private final ConnectControlPolicyDbValue connectControlPolicyDbValue;

    private ScenarioValue scenarioValue;

    @Override
    public String getDbValue() {
        return connectControlPolicyDbValue.getNoRefactoringValue();
    }

    @Override
    public String getApiValue() {
        return "no_limit";
    }


    @Override
    public String getScenarioValue() {
        return scenarioValue.getNoRefactoringValue();
    }

    @AllArgsConstructor
    private enum ConnectControlPolicyDbValue implements DbValue {
        STANDARD_NO_LIMIT("standard_no_limit" /* 文字列リテラルの変更禁止 */),
        LIGHT_M_NO_LIMIT("light_m_no_limit" /* 文字列リテラルの変更禁止 */),
        LIGHT_S_NO_LIMIT("light_s_no_limit" /* 文字列リテラルの変更禁止 */),
        LIGHT_SS_NO_LIMIT("light_ss_no_limit" /* 文字列リテラルの変更禁止 */),
        ENTRY_NO_LIMIT("entry_no_limit" /* 文字列リテラルの変更禁止 */),
        START_NO_LIMIT("start_no_limit" /* 文字列リテラルの変更禁止 */),
        SPEED_CHARGE_NO_LIMIT("speed_charge_no_limit" /* 文字列リテラルの変更禁止 */);

        @Getter
        private final String noRefactoringValue;
    }

    @AllArgsConstructor
    private enum ConnectControlPolicyScenarioValue implements ScenarioValue {
        STANDARD_NO_LIMIT("31" /* 文字列リテラルの変更禁止 */),
        LIGHT_M_NO_LIMIT("33" /* 文字列リテラルの変更禁止 */),
        LIGHT_S_NO_LIMIT("34" /* 文字列リテラルの変更禁止 */),
        LIGHT_SS_NO_LIMIT("35" /* 文字列リテラルの変更禁止 */),
        ENTRY_NO_LIMIT("36" /* 文字列リテラルの変更禁止 */),
        START_NO_LIMIT("37" /* 文字列リテラルの変更禁止 */),
        SPEED_CHARGE_NO_LIMIT("31" /* 文字列リテラルの変更禁止 */);

        @Getter
        private final String noRefactoringValue;
    }

}
