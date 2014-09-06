package jp.co.biglobe.isp.mobile.ltethreeg.domain.connectauth.bandcontroldevice;

import jp.co.biglobe.lib.publication.enumeration.convert.EnumConvertForScenario;
import jp.co.biglobe.lib.publication.enumeration.valuetype.ScenarioValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum LineApplicationDestinationPolicyId implements EnumConvertForScenario{

    LTE(LineApplicationDestinationPolicyIdScenarioValue.LTE),
    B3G(LineApplicationDestinationPolicyIdScenarioValue.B3G);

    private final LineApplicationDestinationPolicyIdScenarioValue lineApplicationDestinationPolicyIdScenarioValue;

    @Override
    public String getScenarioValue(){
        return lineApplicationDestinationPolicyIdScenarioValue.getNoRefactoringValue();
    }

    /**
     * シナリオに格納される値（文字列リテラル）の定義
     */
    @AllArgsConstructor
    private enum LineApplicationDestinationPolicyIdScenarioValue implements ScenarioValue {
        LTE("LTE" /* 文字列リテラルの変更禁止 */),
        B3G("B3G" /* 文字列リテラルの変更禁止 */);

        @Getter
        private final String noRefactoringValue;
    }

}
