package jp.co.biglobe.isp.mobile.extension.paging;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@ToString(includeFieldNames=false)
@EqualsAndHashCode
@AllArgsConstructor
public class PageNumber {
    @Getter
    private final int value;

}
