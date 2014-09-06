package jp.co.biglobe.isp.auth.datasource.operatorwithuser.scenario.sessionid;

import jp.co.biglobe.isp.auth.datasource.operatorwithuser.scenario.sessionid.operatorplususerauthbysessionidinput.OperatorPlusUserAuthBySessionIdInput;
import jp.co.biglobe.isp.auth.datasource.operatorwithuser.scenario.sessionid.operatorplususerauthbysessionidinput.OperatorPlusUserAuthBySessionIdNoSsoInput;
import jp.co.biglobe.isp.auth.datasource.operatorwithuser.scenario.sessionid.operatorplususerauthbysessionidinput.OperatorPlusUserAuthBySessionIdSsoInput;
import jp.co.biglobe.isp.auth.domain.operatornouser.OperatorId;
import jp.co.biglobe.isp.auth.domain.user.SessionId;
import jp.co.biglobe.isp.auth.domain.user.UserId;
import jp.co.biglobe.lib.plugin.scenario.ScenarioExecutor;
import jp.co.biglobe.lib.publication.scenario.authentication.NoAuthentication;
import jp.co.biglobe.lib.publication.scenario.call.ASPFunctionCall;
import jp.co.biglobe.lib.publication.scenario.rule.NothingExceptionRule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OperatorPlusUserAuthBySessionIdScenario {

    /**
     * シナリオ名
     *
     * 仕様書のパス
     * \\Wufei.hq.biglobe.nec.co.jp\構成管理\論理サブシステム\会員管理\BO顧客管理\最新版\ＡＰＩ説明書\統合認証付き(A-3)\A-3-12　API説明書(統合認証).xls
     */
    private static final String SCENARIO_NAME = "C_authexp";

    @Autowired
    private ScenarioExecutor scenarioExecutor;

    public OperatorPlusUserAuthBySessionIdOutput auth(SessionId sessionId) {

        OperatorPlusUserAuthBySessionIdInput operatorPlusUserAuthBySessionIdSsoInput =
                new OperatorPlusUserAuthBySessionIdSsoInput(sessionId);

        return scenarioExecutor.execute(
                new ASPFunctionCall(SCENARIO_NAME),
                operatorPlusUserAuthBySessionIdSsoInput,
                OperatorPlusUserAuthBySessionIdOutput.class,
                new NoAuthentication(),
                new NothingExceptionRule()

        );
    }

    public OperatorPlusUserAuthBySessionIdOutput auth(SessionId sessionId,OperatorId operatorId,UserId userId) {

        OperatorPlusUserAuthBySessionIdInput operatorPlusUserAuthBySessionIdSsoInput =
                new OperatorPlusUserAuthBySessionIdNoSsoInput(operatorId,userId,sessionId);

        return scenarioExecutor.execute(
                new ASPFunctionCall(SCENARIO_NAME),
                operatorPlusUserAuthBySessionIdSsoInput,
                OperatorPlusUserAuthBySessionIdOutput.class,
                new NoAuthentication(),
                new NothingExceptionRule()
        );
    }

}
