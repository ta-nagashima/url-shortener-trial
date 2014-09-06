package jp.co.biglobe.isp.mobile.ltethreeg.domain.connectauth.bandcontroldevice;

import jp.co.biglobe.lib.publication.enumeration.convert.EnumConvertForScenario;
import jp.co.biglobe.lib.publication.enumeration.valuetype.ScenarioValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum ApplicationDestinationPolicyId implements EnumConvertForScenario{

    LTEA(
            ApplicationDestinationPolicyIdScenarioValue.LTEA,
            LineApplicationDestinationPolicyId.LTE

    ),
    LTEB(
            ApplicationDestinationPolicyIdScenarioValue.LTEB,
            LineApplicationDestinationPolicyId.LTE

    ),
    B3G(
            ApplicationDestinationPolicyIdScenarioValue.B3G,
            LineApplicationDestinationPolicyId.B3G
    );

    private final ApplicationDestinationPolicyIdScenarioValue applicationDestinationPolicyIdScenarioValue;

    @Getter
    private final LineApplicationDestinationPolicyId lineApplicationDestinationPolicyId;

    @Override
    public String getScenarioValue(){
        return applicationDestinationPolicyIdScenarioValue.getNoRefactoringValue();
    }

    /**
     * シナリオに格納される値（文字列リテラル）の定義
     */
    @AllArgsConstructor
    private enum ApplicationDestinationPolicyIdScenarioValue implements ScenarioValue {
        LTEA("LTEA" /* 文字列リテラルの変更禁止 */),
        LTEB("LTEB" /* 文字列リテラルの変更禁止 */),
        B3G("B3G" /* 文字列リテラルの変更禁止 */);

        @Getter
        private final String noRefactoringValue;
    }

}
