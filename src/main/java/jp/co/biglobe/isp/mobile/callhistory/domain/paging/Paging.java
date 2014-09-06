package jp.co.biglobe.isp.mobile.callhistory.domain.paging;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@ToString(includeFieldNames = false)
@EqualsAndHashCode
@AllArgsConstructor
public class Paging {

    private final DisplayCount displayCount;

    private final PageNumber pageNumber;

    public Integer getLow() {

        return pageNumber.getPreviousNumber() * displayCount.getValue() + 1;
    }

    public Integer getHigh() {

        return getLow() + displayCount.getValue();
    }

}
