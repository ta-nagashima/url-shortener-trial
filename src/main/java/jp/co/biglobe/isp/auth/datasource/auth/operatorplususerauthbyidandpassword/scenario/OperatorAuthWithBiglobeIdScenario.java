package jp.co.biglobe.isp.auth.datasource.auth.operatorplususerauthbyidandpassword.scenario;

import jp.co.biglobe.isp.auth.domain.operatornouser.OperatorId;
import jp.co.biglobe.isp.auth.domain.operatornouser.OperatorPassword;
import jp.co.biglobe.isp.auth.domain.user.UserId;
import jp.co.biglobe.lib.plugin.scenario.ScenarioExecutor;
import jp.co.biglobe.lib.publication.scenario.authentication.NoAuthentication;
import jp.co.biglobe.lib.publication.scenario.call.ASPFunctionCall;
import jp.co.biglobe.lib.publication.scenario.rule.NothingExceptionRule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OperatorAuthWithBiglobeIdScenario {

    /**
     * シナリオ名
     *
     * 仕様書のパス
     * \\Wufei.hq.biglobe.nec.co.jp\構成管理\論理サブシステム\会員管理\BO顧客管理\最新版\ＡＰＩ説明書\統合認証付き(A-3)\A-3-12　API説明書(統合認証).xls
     */
    private static final String SCENARIO_NAME = "C_authexp";

    @Autowired
    private ScenarioExecutor scenarioExecutor;

    public OperatorAuthWithBiglobeIdOutput auth(OperatorId operatorId, OperatorPassword operatorPassword, UserId userId) {

        OperatorAuthWithBiglobeIdInput operationAuthorityInput =
                new OperatorAuthWithBiglobeIdInput(operatorId, operatorPassword, userId);

        return scenarioExecutor.execute(
                new ASPFunctionCall(SCENARIO_NAME),
                operationAuthorityInput,
                OperatorAuthWithBiglobeIdOutput.class,
                new NoAuthentication(),
                new NothingExceptionRule()
        );
    }
}
