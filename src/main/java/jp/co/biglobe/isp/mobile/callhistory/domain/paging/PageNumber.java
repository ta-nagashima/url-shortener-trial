package jp.co.biglobe.isp.mobile.callhistory.domain.paging;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@ToString(includeFieldNames = false)
@EqualsAndHashCode
@AllArgsConstructor
public class PageNumber {

    @Getter
    private final Integer value;

    public Integer getPreviousNumber() {

        Integer val = value - 1;

        /**
         * 負の数になったら無理矢理０にするようにした。
         * どうするかは要相談。
         */

        if(val < 0 ){
            val = 0;
        }

        return val;

    }

}
