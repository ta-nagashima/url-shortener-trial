package jp.co.biglobe.isp.auth.datasource.operatornouser.scenario.sessionid;

import jp.co.biglobe.isp.auth.datasource.auth.AuthError;
import jp.co.biglobe.isp.auth.domain.auth.operatorauthbysessionid.OperatorAuthBySessionIdErrorStatus;
import jp.co.biglobe.isp.auth.domain.exception.authentication.AuthenticationException;
import jp.co.biglobe.lib.publication.scenario.bobio.BobioHeader;

public class OperatorAuthBySessionIdBusinessErrorRule {

    public boolean verify(BobioHeader bobioHeader) throws RuntimeException {

        AuthError authError = new AuthError(bobioHeader);

        if(authError.isSuccess()){
            return true;
        }

        if(authError.isInvalidIdPwError()){
            throw new AuthenticationException(OperatorAuthBySessionIdErrorStatus.INVALID_ID_PW);
        }

        if(authError.isBeforeServiceError()){
            throw new AuthenticationException(OperatorAuthBySessionIdErrorStatus.BEFORE_SERVICE);
        }

        if(authError.isEndServiceError()){
            throw new AuthenticationException(OperatorAuthBySessionIdErrorStatus.END_SERVICE);
        }

        if(authError.isSessionTimeoutError()){
            throw new AuthenticationException(OperatorAuthBySessionIdErrorStatus.SESSION_TIMEOUT);
        }

        throw new AuthenticationException(OperatorAuthBySessionIdErrorStatus.SYSTEM_ERROR);
    }

}
