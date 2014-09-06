package jp.co.biglobe.isp.auth.domain.auth.userauthbysessionid;

import jp.co.biglobe.isp.mobile.biglobemember.domain.course.ContractType;
import jp.co.biglobe.isp.auth.domain.user.FullName;
import jp.co.biglobe.isp.auth.domain.user.UserId;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@ToString(includeFieldNames=false)
@EqualsAndHashCode
@AllArgsConstructor
public class SessionIdAuthResult {

    @Getter
    private final UserId userId;

    @Getter
    private final FullName fullName;

    @Getter
    private final ContractType contractType;

}
