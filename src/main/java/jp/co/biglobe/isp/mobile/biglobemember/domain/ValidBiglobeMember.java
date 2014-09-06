package jp.co.biglobe.isp.mobile.biglobemember.domain;

import jp.co.biglobe.isp.auth.domain.user.FullName;
import jp.co.biglobe.isp.auth.domain.user.UserId;
import jp.co.biglobe.isp.mobile.biglobemember.domain.address.BiglobeMemberAddress;
import jp.co.biglobe.isp.mobile.biglobemember.domain.corporation.Corporation;
import jp.co.biglobe.isp.mobile.biglobemember.domain.corporation.CorporationNumber;
import jp.co.biglobe.isp.mobile.biglobemember.domain.course.Course;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@ToString(includeFieldNames = false)
@EqualsAndHashCode
@AllArgsConstructor
public class ValidBiglobeMember implements BiglobeMember{

    @Getter
    private UserId userId;

    @Getter
    private FullName fullName;

    @Getter
    private BiglobeMemberAddress address;

    private Course course;

    @Getter
    private Corporation corporation;

    @Override
    public boolean isExist() { return true; }


}
