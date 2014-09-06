package jp.co.biglobe.isp.auth.api.form;

import jp.co.biglobe.isp.auth.domain.auth.TotalAuth;
import jp.co.biglobe.isp.auth.domain.operatornouser.OperatorId;
import jp.co.biglobe.isp.auth.domain.operatornouser.TantoSsoFlag;
import jp.co.biglobe.isp.auth.domain.user.SessionId;
import jp.co.biglobe.isp.auth.domain.user.UserId;
import jp.co.biglobe.lib.publication.scenario.authentication.AuthenticationMethod;

public class TotalAuthRequestFactory {

    public static final String DEFAULT_SESSION_ID = "SID";
    public static final String DEFAULT_OPERATOR_ID = "SSO";
    public static final String DEFAULT_USER_ID = "SSO";

    public static TotalAuth create(TotalAuthRequest totalAuthRequest){

        return new TotalAuth(
                getAuthenticationMethodDefault(totalAuthRequest.getAuthenticationMethodForm()),
                getTantoSsoFlagDefault(totalAuthRequest.getTantoSsoFlagForm()),
                getSessionIdDefault(totalAuthRequest.getSessionIdForm()),
                getOperatorIdDefault(totalAuthRequest.getOperatorIdForm()),
                getUserIdDefault(totalAuthRequest.getUserIdForm())
        );
    }

    private static AuthenticationMethod getAuthenticationMethodDefault(AuthenticationMethodForm authenticationMethodForm){
        if( authenticationMethodForm == null ){
            return AuthenticationMethod.PERSONAL_AUTHENTICATION;
        }
        return authenticationMethodForm.getValueObject();
    }

    private static TantoSsoFlag getTantoSsoFlagDefault(TantoSsoFlagForm tantoSsoFlagForm){
        if( tantoSsoFlagForm == null ){
            return TantoSsoFlag.OTHER;
        }
        return tantoSsoFlagForm.getValueObject();
    }

    private static SessionId getSessionIdDefault(SessionIdForm sessionIdForm){
        if( sessionIdForm == null ){
            return new SessionId(DEFAULT_SESSION_ID);
        }
        return sessionIdForm.getValueObject();
    }

    private static OperatorId getOperatorIdDefault(OperatorIdForm operatorIdForm){
        if( operatorIdForm == null || operatorIdForm.isValueBlank() ){
            return new OperatorId(DEFAULT_OPERATOR_ID);
        }
        return operatorIdForm.getValueObject();
    }

    private static UserId getUserIdDefault(OptionalUserIdForm optionalUserIdForm){
        if( optionalUserIdForm == null  || optionalUserIdForm.isValueBlank()  ){
            return new UserId(DEFAULT_USER_ID);
        }
        return optionalUserIdForm.getValueObject();
    }

}
