package jp.co.biglobe.isp.mobile.biglobemember.domain;

import lombok.EqualsAndHashCode;
import lombok.ToString;

@ToString(includeFieldNames=false)
@EqualsAndHashCode
public class NotExistBiglobeMember implements BiglobeMember{

    @Override
    public boolean isExist() { return false; }

}
