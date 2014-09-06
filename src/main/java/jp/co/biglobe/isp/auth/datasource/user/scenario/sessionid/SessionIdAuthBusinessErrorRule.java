package jp.co.biglobe.isp.auth.datasource.user.scenario.sessionid;

import jp.co.biglobe.isp.auth.datasource.auth.AuthError;
import jp.co.biglobe.isp.auth.domain.auth.userauthbysessionid.SessionIdAuthErrorStatus;
import jp.co.biglobe.isp.auth.domain.exception.authentication.AuthenticationException;
import jp.co.biglobe.lib.publication.scenario.bobio.BobioHeader;

public class SessionIdAuthBusinessErrorRule {

    public boolean verify(BobioHeader bobioHeader) throws RuntimeException {

        AuthError authError = new AuthError(bobioHeader);

        if(authError.isSuccess()){
            return true;
        }

        if(authError.isInvalidIdPwError()){
            throw new AuthenticationException(SessionIdAuthErrorStatus.INVALID_ID_PW);
        }

        if(authError.isBeforeServiceError()){
            throw new AuthenticationException(SessionIdAuthErrorStatus.BEFORE_SERVICE);
        }

        if(authError.isEndServiceError()){
            throw new AuthenticationException(SessionIdAuthErrorStatus.END_SERVICE);
        }

        if(authError.isSessionTimeoutError()){
            throw new AuthenticationException(SessionIdAuthErrorStatus.SESSION_TIMEOUT);
        }

        throw new AuthenticationException(SessionIdAuthErrorStatus.SYSTEM_ERROR);
    }



}
