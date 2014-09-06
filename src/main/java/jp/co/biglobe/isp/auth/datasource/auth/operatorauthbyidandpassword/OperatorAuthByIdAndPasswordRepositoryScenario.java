package jp.co.biglobe.isp.auth.datasource.auth.operatorauthbyidandpassword;


import jp.co.biglobe.isp.auth.datasource.auth.operatorauthbyidandpassword.scenario.OperatorAuthByIdAndPasswordBusinessErrorRule;
import jp.co.biglobe.isp.auth.datasource.auth.operatorauthbyidandpassword.scenario.OperatorAuthByIdAndPasswordOutput;
import jp.co.biglobe.isp.auth.datasource.auth.operatorauthbyidandpassword.scenario.OperatorAuthByIdAndPasswordScenario;
import jp.co.biglobe.isp.auth.domain.auth.operatorauthbyidandpassword.OperatorAuthByIdAndPasswordRepository;
import jp.co.biglobe.isp.auth.domain.operatornouser.OperatorId;
import jp.co.biglobe.isp.auth.domain.operatornouser.OperatorPassword;
import jp.co.biglobe.lib.publication.scenario.bobio.BobioHeader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class OperatorAuthByIdAndPasswordRepositoryScenario implements OperatorAuthByIdAndPasswordRepository {

    @Autowired
    private OperatorAuthByIdAndPasswordScenario operatorAuthByIdAndPasswordScenario;


    @Override
    public void auth(OperatorId operatorId, OperatorPassword operatorPassword){

        OperatorAuthByIdAndPasswordOutput operatorAuthByIdAndPasswordOutput = operatorAuthByIdAndPasswordScenario.auth(operatorId, operatorPassword);
        verify(operatorAuthByIdAndPasswordOutput.getBobioHeader());

        return;
    }

    private void verify(BobioHeader bobioHeader){
        OperatorAuthByIdAndPasswordBusinessErrorRule operatorAuthByIdAndPasswordBusinessErrorRule = new OperatorAuthByIdAndPasswordBusinessErrorRule();
        operatorAuthByIdAndPasswordBusinessErrorRule.verify(bobioHeader);
    }
}
