package jp.co.biglobe.isp.auth.domain.auth.operatorauthbysessionid;

import jp.co.biglobe.isp.auth.domain.auth.totalauthresult.TotalAuthResult;
import jp.co.biglobe.isp.auth.domain.operatornouser.OperatorId;
import jp.co.biglobe.isp.auth.domain.user.SessionId;

public interface OperatorAuthBySessionIdRepository {

    public void auth(SessionId sessionId);

    public TotalAuthResult getBiglobeMember(SessionId sessionId);

    public TotalAuthResult getBiglobeMember(SessionId sessionId,OperatorId operatorId);
}
