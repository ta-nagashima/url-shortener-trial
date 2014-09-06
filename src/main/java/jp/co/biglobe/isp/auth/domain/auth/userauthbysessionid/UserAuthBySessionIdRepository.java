package jp.co.biglobe.isp.auth.domain.auth.userauthbysessionid;

import jp.co.biglobe.isp.auth.domain.auth.totalauthresult.TotalAuthResult;
import jp.co.biglobe.isp.auth.domain.user.SessionId;

public interface UserAuthBySessionIdRepository {

    public SessionIdAuth auth(SessionId sessionId);

    public TotalAuthResult auth2(SessionId sessionId);

}
