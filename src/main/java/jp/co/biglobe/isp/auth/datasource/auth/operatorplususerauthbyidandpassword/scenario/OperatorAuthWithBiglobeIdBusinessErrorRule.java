package jp.co.biglobe.isp.auth.datasource.auth.operatorplususerauthbyidandpassword.scenario;

import jp.co.biglobe.isp.auth.datasource.auth.AuthError;
import jp.co.biglobe.isp.auth.domain.auth.operatorplususerauthbyidandpassword.OperatorPlusUserAuthByIdAndPasswordErrorStatus;
import jp.co.biglobe.isp.auth.domain.exception.authentication.AuthenticationException;
import jp.co.biglobe.lib.publication.scenario.bobio.BobioHeader;

public class OperatorAuthWithBiglobeIdBusinessErrorRule {

    public boolean verify(BobioHeader bobioHeader) throws RuntimeException {

        AuthError authError = new AuthError(bobioHeader);

        if(authError.isSuccess()){
            return true;
        }

        if(authError.isInvalidIdPwError()){
            throw new AuthenticationException(OperatorPlusUserAuthByIdAndPasswordErrorStatus.INVALID_ID_PW);
        }

        if(authError.isBeforeServiceError()){
            throw new AuthenticationException(OperatorPlusUserAuthByIdAndPasswordErrorStatus.BEFORE_SERVICE);
        }

        if(authError.isEndServiceError()){
            throw new AuthenticationException(OperatorPlusUserAuthByIdAndPasswordErrorStatus.END_SERVICE);
        }

        throw new AuthenticationException(OperatorPlusUserAuthByIdAndPasswordErrorStatus.SYSTEM_ERROR);
    }

}
