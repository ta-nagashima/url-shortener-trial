package jp.co.biglobe.isp.auth.datasource.auth.operatorauthbysessionid;

import jp.co.biglobe.isp.auth.datasource.operatornouser.scenario.sessionid.OperatorAuthBySessionIdBusinessErrorRule;
import jp.co.biglobe.isp.auth.datasource.operatornouser.scenario.sessionid.OperatorAuthBySessionIdOutput;
import jp.co.biglobe.isp.auth.datasource.operatornouser.scenario.sessionid.OperatorAuthBySessionIdScenario;
import jp.co.biglobe.isp.auth.domain.auth.operatorauthbysessionid.OperatorAuthBySessionIdRepository;
import jp.co.biglobe.isp.auth.domain.auth.totalauthresult.TotalAuthResult;
import jp.co.biglobe.isp.auth.domain.operatornouser.OperatorId;
import jp.co.biglobe.isp.auth.domain.operatornouser.ValidOperatorNoUserAuth;
import jp.co.biglobe.isp.auth.domain.user.NullUserAuth;
import jp.co.biglobe.isp.auth.domain.user.SessionId;
import jp.co.biglobe.lib.publication.scenario.bobio.BobioHeader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class OperatorAuthBySessionIdRepositoryScenario implements OperatorAuthBySessionIdRepository {

    @Autowired
    private OperatorAuthBySessionIdScenario operatorAuthBySessionIdScenario;

    @Override
    public void auth(SessionId sessionId){

        OperatorAuthBySessionIdOutput operatorAuthBySessionIdOutput = operatorAuthBySessionIdScenario.auth(sessionId);
        verify(operatorAuthBySessionIdOutput.getBobioHeader());

        return;

    }

    @Override
    public TotalAuthResult getBiglobeMember(SessionId sessionId){

        OperatorAuthBySessionIdOutput operatorAuthBySessionIdOutput = operatorAuthBySessionIdScenario.auth(sessionId);
        verify(operatorAuthBySessionIdOutput.getBobioHeader());

        return createTotalAuthResult(operatorAuthBySessionIdOutput);
    }

    @Override
    public TotalAuthResult getBiglobeMember(SessionId sessionId,OperatorId operatorId){

        OperatorAuthBySessionIdOutput operatorAuthBySessionIdOutput = operatorAuthBySessionIdScenario.auth(sessionId,operatorId);
        verify(operatorAuthBySessionIdOutput.getBobioHeader());

        return createTotalAuthResult(operatorAuthBySessionIdOutput);

    }

    private void verify(BobioHeader bobioHeader){
        OperatorAuthBySessionIdBusinessErrorRule operatorAuthBySessionIdBusinessErrorRule = new OperatorAuthBySessionIdBusinessErrorRule();
        operatorAuthBySessionIdBusinessErrorRule.verify(bobioHeader);
    }

    private TotalAuthResult createTotalAuthResult(OperatorAuthBySessionIdOutput operatorAuthBySessionIdOutput){
        return new TotalAuthResult(
                new ValidOperatorNoUserAuth(operatorAuthBySessionIdOutput.getOperatorId(),operatorAuthBySessionIdOutput.getName()),
                new NullUserAuth()
            );
    }
}
