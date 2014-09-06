package jp.co.biglobe.isp.auth.datasource.operatorwithuser.scenario.sessionid;


import jp.co.biglobe.isp.auth.datasource.auth.AuthError;
import jp.co.biglobe.isp.auth.domain.auth.operatorplususerauthbysessionid.OperatorPlusUserAuthBySessionIdErrorStatus;
import jp.co.biglobe.isp.auth.domain.exception.authentication.AuthenticationException;
import jp.co.biglobe.lib.publication.scenario.bobio.BobioHeader;

public class OperatorPlusUserAuthBySessionIdBusinessErrorRule {

    public boolean verify(BobioHeader bobioHeader) throws RuntimeException {

        AuthError authError = new AuthError(bobioHeader);

        if(authError.isSuccess()){
            return true;
        }

        if(authError.isInvalidIdPwError()){
            throw new AuthenticationException(OperatorPlusUserAuthBySessionIdErrorStatus.INVALID_ID_PW);
        }

        if(authError.isBeforeServiceError()){
            throw new AuthenticationException(OperatorPlusUserAuthBySessionIdErrorStatus.BEFORE_SERVICE);
        }

        if(authError.isEndServiceError()){
            throw new AuthenticationException(OperatorPlusUserAuthBySessionIdErrorStatus.END_SERVICE);
        }

        if(authError.isSessionTimeoutError()){
            throw new AuthenticationException(OperatorPlusUserAuthBySessionIdErrorStatus.SESSION_TIMEOUT);
        }

        throw new AuthenticationException(OperatorPlusUserAuthBySessionIdErrorStatus.SYSTEM_ERROR);

    }

}
