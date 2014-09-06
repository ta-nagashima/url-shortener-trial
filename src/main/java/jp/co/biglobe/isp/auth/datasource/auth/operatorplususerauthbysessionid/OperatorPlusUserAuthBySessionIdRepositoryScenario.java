package jp.co.biglobe.isp.auth.datasource.auth.operatorplususerauthbysessionid;

import jp.co.biglobe.isp.auth.datasource.operatorwithuser.scenario.sessionid.OperatorPlusUserAuthBySessionIdBusinessErrorRule;
import jp.co.biglobe.isp.auth.datasource.operatorwithuser.scenario.sessionid.OperatorPlusUserAuthBySessionIdOutput;
import jp.co.biglobe.isp.auth.datasource.operatorwithuser.scenario.sessionid.OperatorPlusUserAuthBySessionIdScenario;
import jp.co.biglobe.isp.auth.domain.auth.operatorplususerauthbysessionid.OperatorPlusUserAuthBySessionIdRepository;
import jp.co.biglobe.isp.auth.domain.auth.totalauthresult.TotalAuthResult;
import jp.co.biglobe.isp.auth.domain.operatornouser.OperatorId;
import jp.co.biglobe.isp.auth.domain.operatornouser.ValidOperatorNoUserAuth;
import jp.co.biglobe.isp.auth.domain.user.SessionId;
import jp.co.biglobe.isp.auth.domain.user.UserId;
import jp.co.biglobe.isp.auth.domain.user.ValidUserAuthTemp;
import jp.co.biglobe.lib.publication.scenario.bobio.BobioHeader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class OperatorPlusUserAuthBySessionIdRepositoryScenario implements OperatorPlusUserAuthBySessionIdRepository {

    @Autowired
    private OperatorPlusUserAuthBySessionIdScenario operatorPlusUserAuthBySessionIdScenario;

    @Override
    public TotalAuthResult getBiglobeMember(SessionId sessionId){

        OperatorPlusUserAuthBySessionIdOutput operatorPlusUserAuthBySessionIdOutput = operatorPlusUserAuthBySessionIdScenario.auth(sessionId);
        verify(operatorPlusUserAuthBySessionIdOutput.getBobioHeader());

        return createTotalAuthResult(operatorPlusUserAuthBySessionIdOutput);
    }

    @Override
    public TotalAuthResult getBiglobeMember(SessionId sessionId,OperatorId operatorId,UserId userId){

        OperatorPlusUserAuthBySessionIdOutput operatorPlusUserAuthBySessionIdOutput = operatorPlusUserAuthBySessionIdScenario.auth(sessionId,operatorId,userId);
        verify(operatorPlusUserAuthBySessionIdOutput.getBobioHeader());

        return createTotalAuthResult(operatorPlusUserAuthBySessionIdOutput);
    }


    private void verify(BobioHeader bobioHeader){
        OperatorPlusUserAuthBySessionIdBusinessErrorRule operatorPlusUserAuthBySessionIdBusinessErrorRule =
                new OperatorPlusUserAuthBySessionIdBusinessErrorRule();
        operatorPlusUserAuthBySessionIdBusinessErrorRule.verify(bobioHeader);
    }

    private TotalAuthResult createTotalAuthResult(OperatorPlusUserAuthBySessionIdOutput operatorPlusUserAuthBySessionIdOutput){
        return new TotalAuthResult(
                //Todo:あとでオペレーターの情報に修正
                new ValidOperatorNoUserAuth(operatorPlusUserAuthBySessionIdOutput.getOperatorId(),operatorPlusUserAuthBySessionIdOutput.getOperatorFullName()),
                new ValidUserAuthTemp(operatorPlusUserAuthBySessionIdOutput.getUserId(),operatorPlusUserAuthBySessionIdOutput.getUserFullName())
        );
    }

}
