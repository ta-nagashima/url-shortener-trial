package jp.co.biglobe.isp.mobile.extension.paging;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@ToString(includeFieldNames = false)
@EqualsAndHashCode
@AllArgsConstructor
public class Paging {

    private final PageNumber pageNumber;

    private final PageSize pageSize;


    public int getLow(){

        return pageNumber.getValue() * pageSize.getValue() + 1;

    }

    public int getHigh(){

        return getLow() + pageSize.getValue() -1;

    }

}
