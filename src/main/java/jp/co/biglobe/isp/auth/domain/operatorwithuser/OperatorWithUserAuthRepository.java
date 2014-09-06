package jp.co.biglobe.isp.auth.domain.operatorwithuser;

import jp.co.biglobe.isp.auth.domain.operatornouser.OperatorId;
import jp.co.biglobe.isp.auth.domain.user.SessionId;
import jp.co.biglobe.isp.auth.domain.user.UserId;

public interface OperatorWithUserAuthRepository {

    public OperatorWithUserAuth authBySessionIdSso(SessionId sessionId);

    public OperatorWithUserAuth authBySessionIdNoSso(SessionId sessionId, OperatorId operatorId, UserId userId);


}
