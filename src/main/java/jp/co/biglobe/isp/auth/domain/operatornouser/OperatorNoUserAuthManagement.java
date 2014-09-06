package jp.co.biglobe.isp.auth.domain.operatornouser;

import jp.co.biglobe.isp.auth.domain.user.SessionId;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@ToString(includeFieldNames=false)
@AllArgsConstructor
public class OperatorNoUserAuthManagement {

    @Getter
    private final SessionId sessionId;

    @Getter
    private final TantoSsoFlag tantoSsoFlag;

    @Getter
    private final OperatorId operatorId;

    public OperatorAuthMethodType judgesAuthMethodType(){

        if(isSingleSignOn()){
            return OperatorAuthMethodType.SESSION_ID_SSO;
        }
        return OperatorAuthMethodType.SESSION_ID_NO_SSO;
    }

    private boolean isSingleSignOn(){
        return tantoSsoFlag.equals(TantoSsoFlag.SINGLE_SIGN_ON);
    }

}
