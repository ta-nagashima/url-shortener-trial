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
public class CorporationNumberForScenario {

    @Getter
    private final String value;

}
