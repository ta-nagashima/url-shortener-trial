package jp.co.biglobe.isp.mobile.biglobemember.domain.corporation;


import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

/**
 * 法人番号
 */
@ToString(includeFieldNames = false)
@EqualsAndHashCode
@AllArgsConstructor
public class CorporationNumber {

    @Getter
    private final Integer value;

    public CorporationNumberForScenario getConnectAuthScenarioValue(){
        return new CorporationNumberForScenario(zeroPadding());
    }

    private String zeroPadding(){
        return String.format("%010d", value);
    }

}
