package jp.co.biglobe.isp.auth.datasource.operatorwithuser;

import jp.co.biglobe.isp.auth.datasource.operatorwithuser.scenario.sessionid.OperatorPlusUserAuthBySessionIdBusinessErrorRule;
import jp.co.biglobe.isp.auth.datasource.operatorwithuser.scenario.sessionid.OperatorPlusUserAuthBySessionIdOutput;
import jp.co.biglobe.isp.auth.datasource.operatorwithuser.scenario.sessionid.OperatorPlusUserAuthBySessionIdScenario;
import jp.co.biglobe.isp.auth.domain.operatornouser.OperatorId;
import jp.co.biglobe.isp.auth.domain.operatornouser.ValidOperatorNoUserAuth;
import jp.co.biglobe.isp.auth.domain.operatorwithuser.OperatorWithUserAuth;
import jp.co.biglobe.isp.auth.domain.operatorwithuser.OperatorWithUserAuthRepository;
import jp.co.biglobe.isp.auth.domain.operatorwithuser.SimpleUserAuth;
import jp.co.biglobe.isp.auth.domain.user.SessionId;
import jp.co.biglobe.isp.auth.domain.user.UserId;
import jp.co.biglobe.lib.publication.scenario.bobio.BobioHeader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class OperatorWithUserAuthRepositoryScenario implements OperatorWithUserAuthRepository {

    @Autowired
    private OperatorPlusUserAuthBySessionIdScenario operatorPlusUserAuthBySessionIdScenario;

    @Override
    public OperatorWithUserAuth authBySessionIdSso(SessionId sessionId){

        OperatorPlusUserAuthBySessionIdOutput operatorPlusUserAuthBySessionIdOutput = operatorPlusUserAuthBySessionIdScenario.auth(sessionId);
        verify(operatorPlusUserAuthBySessionIdOutput.getBobioHeader());

        return createOperatorWithAuth(operatorPlusUserAuthBySessionIdOutput);

    }

    @Override
    public OperatorWithUserAuth authBySessionIdNoSso(SessionId sessionId,OperatorId operatorId,UserId userId){

        OperatorPlusUserAuthBySessionIdOutput operatorPlusUserAuthBySessionIdOutput = operatorPlusUserAuthBySessionIdScenario.auth(sessionId,operatorId,userId);
        verify(operatorPlusUserAuthBySessionIdOutput.getBobioHeader());

        return createOperatorWithAuth(operatorPlusUserAuthBySessionIdOutput);
    }


    private void verify(BobioHeader bobioHeader){
        OperatorPlusUserAuthBySessionIdBusinessErrorRule operatorPlusUserAuthBySessionIdBusinessErrorRule =
                new OperatorPlusUserAuthBySessionIdBusinessErrorRule();
        operatorPlusUserAuthBySessionIdBusinessErrorRule.verify(bobioHeader);
    }

    private OperatorWithUserAuth createOperatorWithAuth(OperatorPlusUserAuthBySessionIdOutput operatorPlusUserAuthBySessionIdOutput){
        return new OperatorWithUserAuth(
                new ValidOperatorNoUserAuth(operatorPlusUserAuthBySessionIdOutput.getOperatorId(),operatorPlusUserAuthBySessionIdOutput.getOperatorFullName()),
                new SimpleUserAuth(operatorPlusUserAuthBySessionIdOutput.getUserId(), operatorPlusUserAuthBySessionIdOutput.getUserFullName())
        );
    }

}
