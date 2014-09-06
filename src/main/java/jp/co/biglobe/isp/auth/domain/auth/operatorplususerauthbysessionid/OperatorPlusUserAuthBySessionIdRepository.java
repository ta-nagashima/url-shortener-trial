package jp.co.biglobe.isp.auth.domain.auth.operatorplususerauthbysessionid;

import jp.co.biglobe.isp.auth.domain.auth.totalauthresult.TotalAuthResult;
import jp.co.biglobe.isp.auth.domain.operatornouser.OperatorId;
import jp.co.biglobe.isp.auth.domain.user.SessionId;
import jp.co.biglobe.isp.auth.domain.user.UserId;

public interface OperatorPlusUserAuthBySessionIdRepository {

    public TotalAuthResult getBiglobeMember(SessionId sessionId);

    public TotalAuthResult getBiglobeMember(SessionId sessionId,OperatorId operatorId,UserId userId);

}
