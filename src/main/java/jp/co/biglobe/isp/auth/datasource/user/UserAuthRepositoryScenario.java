package jp.co.biglobe.isp.auth.datasource.user;


import jp.co.biglobe.isp.auth.datasource.user.scenario.sessionid.SessionIdAuthBusinessErrorRule;
import jp.co.biglobe.isp.auth.datasource.user.scenario.sessionid.SessionIdAuthOutput;
import jp.co.biglobe.isp.auth.datasource.user.scenario.sessionid.SessionIdAuthScenario;
import jp.co.biglobe.isp.mobile.biglobemember.domain.course.ContractTypeFactory;
import jp.co.biglobe.isp.auth.domain.user.SessionId;
import jp.co.biglobe.isp.auth.domain.user.UserAuthRepository;
import jp.co.biglobe.isp.auth.domain.user.ValidUserAuth;
import jp.co.biglobe.lib.publication.scenario.bobio.BobioHeader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserAuthRepositoryScenario implements UserAuthRepository {

    @Autowired
    private SessionIdAuthScenario sessionIdAuthScenario;

    @Override
    public ValidUserAuth authBySessionId(SessionId sessionId){

        SessionIdAuthOutput sessionIdAuthOutput = sessionIdAuthScenario.auth(sessionId);
        verify(sessionIdAuthOutput.getBobioHeader());

        return new ValidUserAuth(
                sessionIdAuthOutput.getUserId(),
                sessionIdAuthOutput.getName(),
                new ContractTypeFactory().getContractType(sessionIdAuthOutput.getCourseId()));
    }

    private void verify(BobioHeader bobioHeader){
        SessionIdAuthBusinessErrorRule sessionIdAuthBusinessErrorRule = new SessionIdAuthBusinessErrorRule();
        sessionIdAuthBusinessErrorRule.verify(bobioHeader);
    }

}
