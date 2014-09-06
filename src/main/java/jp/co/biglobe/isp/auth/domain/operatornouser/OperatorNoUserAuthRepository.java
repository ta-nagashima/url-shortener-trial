package jp.co.biglobe.isp.auth.domain.operatornouser;

import jp.co.biglobe.isp.auth.domain.user.SessionId;

public interface OperatorNoUserAuthRepository {

    public ValidOperatorNoUserAuth authBySessionIdSsoNoUser(SessionId sessionId);

    public ValidOperatorNoUserAuth authBySessionIdNoSsoNoUser(SessionId sessionId, OperatorId operatorId);

}
