package jp.co.biglobe.isp.auth.datasource.auth.operatorauthbyidandpassword.scenario;

import jp.co.biglobe.isp.auth.datasource.auth.AuthError;
import jp.co.biglobe.isp.auth.domain.auth.operatorauthbyidandpassword.OperatorAuthByIdAndPasswordErrorStatus;
import jp.co.biglobe.isp.auth.domain.exception.authentication.AuthenticationException;
import jp.co.biglobe.lib.publication.scenario.bobio.BobioHeader;

public class OperatorAuthByIdAndPasswordBusinessErrorRule {

    public boolean verify(BobioHeader bobioHeader) throws RuntimeException {

        AuthError authError = new AuthError(bobioHeader);

        if(authError.isSuccess()){
            return true;
        }

        if(authError.isInvalidIdPwError()){
            throw new AuthenticationException(OperatorAuthByIdAndPasswordErrorStatus.INVALID_ID_PW);
        }

        if(authError.isBeforeServiceError()){
            throw new AuthenticationException(OperatorAuthByIdAndPasswordErrorStatus.BEFORE_SERVICE);
        }

        if(authError.isEndServiceError()){
            throw new AuthenticationException(OperatorAuthByIdAndPasswordErrorStatus.END_SERVICE);
        }

        throw new AuthenticationException(OperatorAuthByIdAndPasswordErrorStatus.SYSTEM_ERROR);
    }

}
