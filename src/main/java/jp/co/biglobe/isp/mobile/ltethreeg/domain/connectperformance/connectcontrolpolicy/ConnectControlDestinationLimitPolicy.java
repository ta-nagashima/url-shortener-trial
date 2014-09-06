package jp.co.biglobe.isp.mobile.ltethreeg.domain.connectperformance.connectcontrolpolicy;

import jp.co.biglobe.lib.publication.enumeration.valuetype.ApiValue;
import jp.co.biglobe.lib.publication.enumeration.valuetype.DbValue;
import jp.co.biglobe.lib.publication.enumeration.valuetype.ScenarioValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum ConnectControlDestinationLimitPolicy implements ConnectControlPolicy{
    BIGLOBE_ONLY_LIMIT(
            ConnectControlPolicyDbValue.BIGLOBE_ONLY_LIMIT,
            ConnectControlPolicyScenarioValue.BIGLOBE_ONLY_LIMIT
    );

    private final ConnectControlPolicyDbValue connectControlPolicyDbValue;

    private ScenarioValue scenarioValue;

    @Override
    public String getDbValue() {
        return connectControlPolicyDbValue.getNoRefactoringValue();
    }


    @Override
    public String getApiValue() {
        return "destination_limit";
    }

    @Override
    public String getScenarioValue() {
        return scenarioValue.getNoRefactoringValue();
    }

    @AllArgsConstructor
    private enum ConnectControlPolicyDbValue implements DbValue, ApiValue {
        BIGLOBE_ONLY_LIMIT("biglobe_only_limit" /* 文字列リテラルの変更禁止 */);

        @Getter
        private final String noRefactoringValue;
    }

    @AllArgsConstructor
    private enum ConnectControlPolicyScenarioValue implements ScenarioValue {
        BIGLOBE_ONLY_LIMIT("51" /* 文字列リテラルの変更禁止 */);

        @Getter
        private final String noRefactoringValue;
    }

}
