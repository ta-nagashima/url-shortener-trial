package jp.co.biglobe.isp.auth.datasource.operatornouser.scenario.sessionid;

import jp.co.biglobe.isp.auth.datasource.operatornouser.scenario.sessionid.operatorauthbysessionIdinput.OperatorAuthBySessionIdInput;
import jp.co.biglobe.isp.auth.datasource.operatornouser.scenario.sessionid.operatorauthbysessionIdinput.OperatorAuthBySessionIdNoSsoInput;
import jp.co.biglobe.isp.auth.datasource.operatornouser.scenario.sessionid.operatorauthbysessionIdinput.OperatorAuthBySessionIdSsoInput;
import jp.co.biglobe.isp.auth.domain.operatornouser.OperatorId;
import jp.co.biglobe.isp.auth.domain.user.SessionId;
import jp.co.biglobe.lib.plugin.scenario.ScenarioExecutor;
import jp.co.biglobe.lib.publication.scenario.authentication.NoAuthentication;
import jp.co.biglobe.lib.publication.scenario.call.ASPFunctionCall;
import jp.co.biglobe.lib.publication.scenario.rule.NothingExceptionRule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OperatorAuthBySessionIdScenario {
    /**
     * シナリオ名
     *
     * 仕様書のパス
     * \\Wufei.hq.biglobe.nec.co.jp\構成管理\論理サブシステム\会員管理\BO顧客管理\最新版\ＡＰＩ説明書\統合認証付き(A-3)\A-3-12　API説明書(統合認証).xls
     */
    private static final String SCENARIO_NAME = "C_authexp";

    @Autowired
    private ScenarioExecutor scenarioExecutor;

    public OperatorAuthBySessionIdOutput auth(SessionId sessionId) {

        OperatorAuthBySessionIdInput operatorAuthBySessionIdInput =
                new OperatorAuthBySessionIdSsoInput(sessionId);

        return scenarioExecutor.execute(
                new ASPFunctionCall(SCENARIO_NAME),
                operatorAuthBySessionIdInput,
                OperatorAuthBySessionIdOutput.class,
                new NoAuthentication(),
                new NothingExceptionRule()
        );
    }

    public OperatorAuthBySessionIdOutput auth(SessionId sessionId,OperatorId operatorId) {

        OperatorAuthBySessionIdInput operatorAuthBySessionIdInput =
                new OperatorAuthBySessionIdNoSsoInput(operatorId, sessionId );

        return scenarioExecutor.execute(
                new ASPFunctionCall(SCENARIO_NAME),
                operatorAuthBySessionIdInput,
                OperatorAuthBySessionIdOutput.class,
                new NoAuthentication(),
                new NothingExceptionRule()
        );
    }

}
