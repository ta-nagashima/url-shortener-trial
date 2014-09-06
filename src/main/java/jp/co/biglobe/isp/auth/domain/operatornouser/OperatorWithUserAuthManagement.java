package jp.co.biglobe.isp.auth.domain.operatornouser;

import jp.co.biglobe.isp.auth.domain.user.SessionId;
import jp.co.biglobe.isp.auth.domain.user.UserId;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@ToString(includeFieldNames=false)
@AllArgsConstructor
public class OperatorWithUserAuthManagement {

    @Getter
    private final SessionId sessionId;

    @Getter
    private final TantoSsoFlag tantoSsoFlag;

    @Getter
    private final OperatorId operatorId;

    @Getter
    private final UserId userId;


    public OperatorAuthMethodType judgesAuthMethodType(){

        OperatorNoUserAuthManagement operatorNoUserAuthManagement =
                new OperatorNoUserAuthManagement(sessionId, tantoSsoFlag, operatorId);
        return operatorNoUserAuthManagement.judgesAuthMethodType();
    }

}
