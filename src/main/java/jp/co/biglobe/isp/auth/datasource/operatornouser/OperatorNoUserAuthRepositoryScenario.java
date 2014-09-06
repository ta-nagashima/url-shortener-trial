package jp.co.biglobe.isp.auth.datasource.operatornouser;


import jp.co.biglobe.isp.auth.datasource.operatornouser.scenario.sessionid.OperatorAuthBySessionIdOutput;
import jp.co.biglobe.isp.auth.datasource.operatornouser.scenario.sessionid.OperatorAuthBySessionIdScenario;
import jp.co.biglobe.isp.auth.datasource.user.scenario.sessionid.SessionIdAuthBusinessErrorRule;
import jp.co.biglobe.isp.auth.domain.operatornouser.OperatorId;
import jp.co.biglobe.isp.auth.domain.operatornouser.OperatorNoUserAuthRepository;
import jp.co.biglobe.isp.auth.domain.operatornouser.ValidOperatorNoUserAuth;
import jp.co.biglobe.isp.auth.domain.user.SessionId;
import jp.co.biglobe.lib.publication.scenario.bobio.BobioHeader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class OperatorNoUserAuthRepositoryScenario implements OperatorNoUserAuthRepository {

    @Autowired
    private OperatorAuthBySessionIdScenario operatorAuthBySessionIdScenario;

    @Override
    public ValidOperatorNoUserAuth authBySessionIdSsoNoUser(SessionId sessionId) {

        OperatorAuthBySessionIdOutput operatorAuthBySessionIdOutput = operatorAuthBySessionIdScenario.auth(sessionId);
        verify(operatorAuthBySessionIdOutput.getBobioHeader());

        return new ValidOperatorNoUserAuth(operatorAuthBySessionIdOutput.getOperatorId(), operatorAuthBySessionIdOutput.getName());
    }

    @Override
    public ValidOperatorNoUserAuth authBySessionIdNoSsoNoUser(SessionId sessionId, OperatorId operatorId) {

        OperatorAuthBySessionIdOutput operatorAuthBySessionIdOutput = operatorAuthBySessionIdScenario.auth(sessionId, operatorId);
        verify(operatorAuthBySessionIdOutput.getBobioHeader());

        return new ValidOperatorNoUserAuth(operatorAuthBySessionIdOutput.getOperatorId(), operatorAuthBySessionIdOutput.getName());
    }

    private void verify(BobioHeader bobioHeader) {
        SessionIdAuthBusinessErrorRule sessionIdAuthBusinessErrorRule = new SessionIdAuthBusinessErrorRule();
        sessionIdAuthBusinessErrorRule.verify(bobioHeader);
    }

}
