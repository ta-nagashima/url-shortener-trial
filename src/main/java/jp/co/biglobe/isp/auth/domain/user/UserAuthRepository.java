package jp.co.biglobe.isp.auth.domain.user;

public interface UserAuthRepository {

    public ValidUserAuth authBySessionId(SessionId sessionId);

}
