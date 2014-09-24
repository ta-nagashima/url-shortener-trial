package jp.co.biglobe.isp.sample.user.datasource.authentication;

import jp.co.biglobe.isp.sample.user.datasource.authentication.scenario.UserAuthenticationOutput;
import jp.co.biglobe.isp.sample.user.datasource.authentication.scenario.UserAuthenticationScenario;
import jp.co.biglobe.isp.sample.user.domain.authentication.AuthenticatedUserId;
import jp.co.biglobe.isp.sample.user.domain.authentication.AuthenticationRepository;
import jp.co.biglobe.isp.sample.user.domain.authentication.SessionId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class AuthenticationRepositoryScenario implements AuthenticationRepository {
    @Autowired
    private UserAuthenticationScenario userAuthenticationScenario;

    @Override
    public AuthenticatedUserId authenticate(SessionId sessionId) {

        UserAuthenticationOutput sessionIdAuthOutput = userAuthenticationScenario.authenticate(sessionId);

        return sessionIdAuthOutput.getAuthenticatedUserId();
    }
}
