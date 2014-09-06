package jp.co.biglobe.isp.mobile.callhistory.domain.paging;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@ToString(includeFieldNames = false)
@EqualsAndHashCode
@AllArgsConstructor
public class DisplayCount {

    @Getter
    private final Integer value;

}
