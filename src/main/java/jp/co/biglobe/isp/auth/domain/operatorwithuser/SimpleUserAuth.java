package jp.co.biglobe.isp.auth.domain.operatorwithuser;

import jp.co.biglobe.isp.auth.domain.user.FullName;
import jp.co.biglobe.isp.auth.domain.user.UserId;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@ToString(includeFieldNames=false)
@EqualsAndHashCode
@AllArgsConstructor
public class SimpleUserAuth {
    @Getter
    private final UserId userId;
    @Getter
    private final FullName fullName;

}
