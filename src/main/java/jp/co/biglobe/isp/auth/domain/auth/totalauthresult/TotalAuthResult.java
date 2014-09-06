package jp.co.biglobe.isp.auth.domain.auth.totalauthresult;

import jp.co.biglobe.isp.auth.domain.operatornouser.OperatorAuth;
import jp.co.biglobe.isp.auth.domain.user.UserAuth;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@ToString(includeFieldNames=false)
@EqualsAndHashCode
@AllArgsConstructor
public class TotalAuthResult {

    @Getter
    private final OperatorAuth operatorAuth;

    @Getter
    private final UserAuth userAuth;

}
