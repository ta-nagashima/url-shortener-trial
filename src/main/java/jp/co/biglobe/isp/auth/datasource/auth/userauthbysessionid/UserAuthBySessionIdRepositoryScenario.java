package jp.co.biglobe.isp.auth.datasource.auth.userauthbysessionid;


import jp.co.biglobe.isp.auth.datasource.user.scenario.sessionid.SessionIdAuthBusinessErrorRule;
import jp.co.biglobe.isp.auth.datasource.user.scenario.sessionid.SessionIdAuthOutput;
import jp.co.biglobe.isp.auth.datasource.user.scenario.sessionid.SessionIdAuthScenario;
import jp.co.biglobe.isp.auth.domain.auth.totalauthresult.TotalAuthResult;
import jp.co.biglobe.isp.auth.domain.auth.userauthbysessionid.SessionIdAuth;
import jp.co.biglobe.isp.auth.domain.auth.userauthbysessionid.UserAuthBySessionIdRepository;
import jp.co.biglobe.isp.auth.domain.operatornouser.NullOperatorAuth;
import jp.co.biglobe.isp.auth.domain.user.SessionId;
import jp.co.biglobe.isp.auth.domain.user.ValidUserAuthTemp;
import jp.co.biglobe.lib.publication.scenario.bobio.BobioHeader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserAuthBySessionIdRepositoryScenario implements UserAuthBySessionIdRepository {

    @Autowired
    private SessionIdAuthScenario sessionIdAuthScenario;

    @Override
    public SessionIdAuth auth(SessionId sessionId){

        SessionIdAuthOutput sessionIdAuthOutput = sessionIdAuthScenario.auth(sessionId);
        verify(sessionIdAuthOutput.getBobioHeader());

        return new SessionIdAuth(
                sessionIdAuthOutput.getUserId(),
                sessionIdAuthOutput.getName(),
                sessionIdAuthOutput.getCourseId()
        );
    }

    @Override
    public TotalAuthResult auth2(SessionId sessionId){

        SessionIdAuthOutput sessionIdAuthOutput = sessionIdAuthScenario.auth(sessionId);
        verify(sessionIdAuthOutput.getBobioHeader());

        return createTotalAuthResult(sessionIdAuthOutput);
    }


    private void verify(BobioHeader bobioHeader){
        SessionIdAuthBusinessErrorRule sessionIdAuthBusinessErrorRule = new SessionIdAuthBusinessErrorRule();
        sessionIdAuthBusinessErrorRule.verify(bobioHeader);
    }

    private TotalAuthResult createTotalAuthResult(SessionIdAuthOutput sessionIdAuthOutput){
        return new TotalAuthResult(
                new NullOperatorAuth(),
                new ValidUserAuthTemp(sessionIdAuthOutput.getUserId(),sessionIdAuthOutput.getName())
        );
    }
}
