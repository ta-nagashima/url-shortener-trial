package jp.co.biglobe.isp.auth.domain.user;

import jp.co.biglobe.isp.mobile.biglobemember.domain.course.ContractType;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

/**
 * BIGLOBE会員
 */
@ToString(includeFieldNames=false)
@EqualsAndHashCode
@AllArgsConstructor
public class ValidUserAuth{
    @Getter
    private final UserId userId;
    @Getter
    private final FullName fullName;
    @Getter
    private final ContractType contractType;

}
