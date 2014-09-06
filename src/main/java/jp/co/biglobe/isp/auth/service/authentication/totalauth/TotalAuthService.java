package jp.co.biglobe.isp.auth.service.authentication.totalauth;

import jp.co.biglobe.isp.auth.domain.auth.TotalAuth;
import jp.co.biglobe.isp.auth.domain.auth.totalauthresult.TotalAuthResult;
import jp.co.biglobe.isp.auth.domain.operatornouser.TantoSsoFlag;
import jp.co.biglobe.lib.publication.scenario.authentication.AuthenticationMethod;
import lombok.EqualsAndHashCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@EqualsAndHashCode
public class TotalAuthService {

    @Autowired
    UserAuthBySessionIdTotalAuthService userAuthBySessionIdTotalAuthService;

    @Autowired
    OperatorPlusUserAuthBySessionIdTotalAuthService operatorPlusUserAuthBySessionIdTotalAuthService;

    @Autowired
    OperatorPlusUserAuthBySessionIdTotalAuthNoSsoService operatorPlusUserAuthBySessionIdTotalAuthNoSsoService;

    @Autowired
    OperatorAuthBySessionIdTotalAuthService operatorAuthBySessionIdTotalAuthService;

    @Autowired
    OperatorAuthBySessionIdTotalAuthNoSsoService operatorAuthBySessionIdTotalAuthNoSsoService;

    public TotalAuthResult auth(TotalAuth totalAuth) {

        AuthenticationMethod authenticationMethod = totalAuth.getAuthenticationMethod();
        TantoSsoFlag tantoSsoFlag = totalAuth.getTantoSsoFlag();

        if ( isPersonal(authenticationMethod) ) {
            return userAuthBySessionIdTotalAuthService.auth(totalAuth);
        } else if ( isOperatorWithUserIdSso(authenticationMethod,tantoSsoFlag ) ){
            return operatorPlusUserAuthBySessionIdTotalAuthService.auth(totalAuth);
        } else if ( isOperatorWithUserIdNoSso(authenticationMethod,tantoSsoFlag ) ){
            return operatorPlusUserAuthBySessionIdTotalAuthNoSsoService.auth(totalAuth);
        } else if ( isOperatorNoUserIdSso(authenticationMethod,tantoSsoFlag ) ) {
            return operatorAuthBySessionIdTotalAuthService.auth(totalAuth);
        } else {
            return operatorAuthBySessionIdTotalAuthNoSsoService.auth(totalAuth);
        }
    }

    private boolean isPersonal( AuthenticationMethod authenticationMethod ){
        return isPersonalAuthentication(authenticationMethod);
    }

    private boolean isOperatorWithUserIdSso( AuthenticationMethod authenticationMethod, TantoSsoFlag tantoSsoFlag ){
        return isOperationAuthorityAuthenticationBiglobeIdSpecification(authenticationMethod) && isSingleSignOn(tantoSsoFlag);
    }

    private boolean isOperatorWithUserIdNoSso( AuthenticationMethod authenticationMethod, TantoSsoFlag tantoSsoFlag ){
        return isOperationAuthorityAuthenticationBiglobeIdSpecification(authenticationMethod) && !isSingleSignOn(tantoSsoFlag);
    }

    private boolean isOperatorNoUserIdSso( AuthenticationMethod authenticationMethod, TantoSsoFlag tantoSsoFlag ){
        return isOperationAuthorityAuthenticationWithoutBigobeIdSpecification(authenticationMethod) && isSingleSignOn(tantoSsoFlag);
    }

    private boolean isOperatorNoUserIdNoSso( AuthenticationMethod authenticationMethod, TantoSsoFlag tantoSsoFlag ){
        return isOperationAuthorityAuthenticationWithoutBigobeIdSpecification(authenticationMethod) && !isSingleSignOn(tantoSsoFlag);
    }

    private boolean isPersonalAuthentication(AuthenticationMethod authenticationMethod){
        return authenticationMethod.equals(AuthenticationMethod.PERSONAL_AUTHENTICATION);
    };

    private boolean isOperationAuthorityAuthenticationBiglobeIdSpecification(AuthenticationMethod authenticationMethod){
        return authenticationMethod.equals(AuthenticationMethod.OPERATION_AUTHORITY_AUTHENTICATION_BIGLOBE_ID_SPECIFICATION);
    };

    private boolean isOperationAuthorityAuthenticationWithoutBigobeIdSpecification(AuthenticationMethod authenticationMethod){
        return authenticationMethod.equals(AuthenticationMethod.OPERATION_AUTHORITY_AUTHENTICATION_WITHOUT_BIGLOBE_ID_SPECIFICATION);
    };

    private boolean isSingleSignOn(TantoSsoFlag tantoSsoFlag){
        return tantoSsoFlag.equals(TantoSsoFlag.SINGLE_SIGN_ON);
    }
}