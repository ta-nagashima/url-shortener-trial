package jp.co.biglobe.isp.mobile.biglobemember.domain.address;

import jp.co.biglobe.isp.mobile.biglobemember.domain.address.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

/**
 * 住所
 */
@ToString(includeFieldNames = false)
@EqualsAndHashCode
@AllArgsConstructor
public class BiglobeMemberAddress {

    @Getter
    private ZipCode zipCode;

    @Getter
    private PrefectureCode prefectureCode;

    @Getter
    private City city;

    @Getter
    private HouseNumber houseNumber;

    @Getter
    private Building building;

}
