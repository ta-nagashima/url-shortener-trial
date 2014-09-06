package jp.co.biglobe.isp.auth.datasource.auth.operatorplususerauthbyidandpassword;

import jp.co.biglobe.isp.auth.datasource.auth.operatorplususerauthbyidandpassword.scenario.OperatorAuthWithBiglobeIdBusinessErrorRule;
import jp.co.biglobe.isp.auth.datasource.auth.operatorplususerauthbyidandpassword.scenario.OperatorAuthWithBiglobeIdOutput;
import jp.co.biglobe.isp.auth.datasource.auth.operatorplususerauthbyidandpassword.scenario.OperatorAuthWithBiglobeIdScenario;
import jp.co.biglobe.isp.auth.domain.auth.operatorplususerauthbyidandpassword.OperatorPlusUserAuthByIdAndPasswordRepository;
import jp.co.biglobe.isp.auth.domain.operatornouser.OperatorId;
import jp.co.biglobe.isp.auth.domain.operatornouser.OperatorPassword;
import jp.co.biglobe.isp.auth.domain.user.UserId;
import jp.co.biglobe.isp.auth.domain.user.ValidUserAuthTemp;
import jp.co.biglobe.lib.publication.scenario.bobio.BobioHeader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class OperatorPlusUserAuthByIdAndPasswordRepositoryScenario implements OperatorPlusUserAuthByIdAndPasswordRepository {

    @Autowired
    private OperatorAuthWithBiglobeIdScenario operatorAuthWithBiglobeIdScenario;

    @Override
    public ValidUserAuthTemp auth(OperatorId operatorId, OperatorPassword operatorPassword, UserId userId){

        OperatorAuthWithBiglobeIdOutput operatorAuthWithBiglobeIdOutput = operatorAuthWithBiglobeIdScenario.auth(operatorId, operatorPassword, userId);
        verify(operatorAuthWithBiglobeIdOutput.getBobioHeader());

        return new ValidUserAuthTemp(
                operatorAuthWithBiglobeIdOutput.getUserId(),
                operatorAuthWithBiglobeIdOutput.getName()
        );
    }

    private void verify(BobioHeader bobioHeader){
        OperatorAuthWithBiglobeIdBusinessErrorRule operatorAuthWithBiglobeIdBusinessErrorRule =
                new OperatorAuthWithBiglobeIdBusinessErrorRule();
        operatorAuthWithBiglobeIdBusinessErrorRule.verify(bobioHeader);
    }


}
