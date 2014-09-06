package jp.co.biglobe.isp.mobile.biglobemember.domain.corporation;


import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@ToString(includeFieldNames = false)
@EqualsAndHashCode
@AllArgsConstructor
public class ValidCorporation implements Corporation{
    @Getter
    private final CorporationNumber corporationNumber;
}
