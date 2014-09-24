package jp.co.biglobe.isp.sample.user.domain.authentication;


public interface AuthenticationRepository {
    public AuthenticatedUserId authenticate(SessionId sessionId);
}
