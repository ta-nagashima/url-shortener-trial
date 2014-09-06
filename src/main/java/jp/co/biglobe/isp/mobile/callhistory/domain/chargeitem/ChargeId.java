package jp.co.biglobe.isp.mobile.callhistory.domain.chargeitem;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@ToString(includeFieldNames = false)
@EqualsAndHashCode
@AllArgsConstructor
public class ChargeId {

    @Getter
    private final String value;
}
