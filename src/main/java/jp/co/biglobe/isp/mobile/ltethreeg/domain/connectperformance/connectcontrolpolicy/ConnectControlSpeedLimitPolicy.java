package jp.co.biglobe.isp.mobile.ltethreeg.domain.connectperformance.connectcontrolpolicy;

import jp.co.biglobe.lib.publication.enumeration.valuetype.ApiValue;
import jp.co.biglobe.lib.publication.enumeration.valuetype.DbValue;
import jp.co.biglobe.lib.publication.enumeration.valuetype.ScenarioValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum ConnectControlSpeedLimitPolicy implements ConnectControlPolicy {
    STANDARD_LIMIT(
            ConnectControlPolicyDbValue.STANDARD_LIMIT,
            ConnectControlPolicyScenarioValue.STANDARD_LIMIT
    ),
    LIGHT_M_LIMIT(
            ConnectControlPolicyDbValue.LIGHT_M_LIMIT,
            ConnectControlPolicyScenarioValue.LIGHT_M_LIMIT
    ),
    LIGHT_S_LIMIT(
            ConnectControlPolicyDbValue.LIGHT_S_LIMIT,
            ConnectControlPolicyScenarioValue.LIGHT_S_LIMIT
    ),
    LIGHT_SS_LIMIT(
            ConnectControlPolicyDbValue.LIGHT_SS_LIMIT,
            ConnectControlPolicyScenarioValue.LIGHT_SS_LIMIT
    ),
    ENTRY_LIMIT(
            ConnectControlPolicyDbValue.ENTRY_LIMIT,
            ConnectControlPolicyScenarioValue.ENTRY_LIMIT
    ),
    START_LIMIT(
            ConnectControlPolicyDbValue.START_LIMIT,
            ConnectControlPolicyScenarioValue.START_LIMIT
    );

    private final ConnectControlPolicyDbValue connectControlPolicyDbValue;

    private ScenarioValue scenarioValue;

    @Override
    public String getDbValue() {
        return connectControlPolicyDbValue.getNoRefactoringValue();
    }


    @Override
    public String getApiValue() {
        return "speed_limit";
    }

    @Override
    public String getScenarioValue() {
        return scenarioValue.getNoRefactoringValue();
    }

    @AllArgsConstructor
    private enum ConnectControlPolicyDbValue implements DbValue, ApiValue {
        STANDARD_LIMIT("standard_limit" /* 文字列リテラルの変更禁止 */),
        LIGHT_M_LIMIT("light_m_limit" /* 文字列リテラルの変更禁止 */),
        LIGHT_S_LIMIT("light_s_limit" /* 文字列リテラルの変更禁止 */),
        LIGHT_SS_LIMIT("light_ss_limit" /* 文字列リテラルの変更禁止 */),
        ENTRY_LIMIT("entry_limit" /* 文字列リテラルの変更禁止 */),
        START_LIMIT("start_limit" /* 文字列リテラルの変更禁止 */),
        BIGLOBE_ONLY_LIMIT("biglobe_only_limit" /* 文字列リテラルの変更禁止 */);

        @Getter
        private final String noRefactoringValue;
    }

    @AllArgsConstructor
    private enum ConnectControlPolicyScenarioValue implements ScenarioValue {
        STANDARD_LIMIT("41" /* 文字列リテラルの変更禁止 */),
        LIGHT_M_LIMIT("43" /* 文字列リテラルの変更禁止 */),
        LIGHT_S_LIMIT("44" /* 文字列リテラルの変更禁止 */),
        LIGHT_SS_LIMIT("45" /* 文字列リテラルの変更禁止 */),
        ENTRY_LIMIT("46" /* 文字列リテラルの変更禁止 */),
        START_LIMIT("47" /* 文字列リテラルの変更禁止 */),
        BIGLOBE_ONLY_LIMIT("51" /* 文字列リテラルの変更禁止 */);

        @Getter
        private final String noRefactoringValue;
    }

}
