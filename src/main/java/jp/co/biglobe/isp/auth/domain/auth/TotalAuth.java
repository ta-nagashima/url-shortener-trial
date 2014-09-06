package jp.co.biglobe.isp.auth.domain.auth;

import jp.co.biglobe.isp.auth.domain.operatornouser.OperatorId;
import jp.co.biglobe.isp.auth.domain.operatornouser.TantoSsoFlag;
import jp.co.biglobe.isp.auth.domain.user.SessionId;
import jp.co.biglobe.isp.auth.domain.user.UserId;
import jp.co.biglobe.lib.publication.scenario.authentication.AuthenticationMethod;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@ToString(includeFieldNames=false)
@EqualsAndHashCode
@AllArgsConstructor
public class TotalAuth {

    @Getter
    private final AuthenticationMethod authenticationMethod;

    @Getter
    private final TantoSsoFlag tantoSsoFlag;

    @Getter
    private final SessionId sessionId;

    @Getter
    private final OperatorId operatorId;

    @Getter
    private final UserId userId;

}
