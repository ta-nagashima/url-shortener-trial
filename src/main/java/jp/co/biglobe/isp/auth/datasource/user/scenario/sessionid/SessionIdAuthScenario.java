package jp.co.biglobe.isp.auth.datasource.user.scenario.sessionid;

import jp.co.biglobe.isp.auth.domain.user.SessionId;
import jp.co.biglobe.lib.plugin.scenario.ScenarioExecutor;
import jp.co.biglobe.lib.publication.scenario.authentication.PersonalSessionIDAuthentication;
import jp.co.biglobe.lib.publication.scenario.call.ASPFunctionCall;
import jp.co.biglobe.lib.publication.scenario.rule.NothingExceptionRule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SessionIdAuthScenario {

    /**
     * シナリオ名
     *
     * 仕様書のパス
     * \\Wufei.hq.biglobe.nec.co.jp\構成管理\論理サブシステム\会員管理\BO顧客管理\最新版\ＡＰＩ説明書\統合認証付き(A-3)\A-3-12　API説明書(統合認証).xls
     */
    private static final String SCENARIO_NAME = "C_authexp";

    @Autowired
    private ScenarioExecutor scenarioExecutor;

    public SessionIdAuthOutput auth(SessionId sessionId){


        SessionIdAuthInput sessionIdAuthInput = new SessionIdAuthInput();

        // シナリオの実行
        return scenarioExecutor.execute(
                new ASPFunctionCall(SCENARIO_NAME),
                sessionIdAuthInput,
                SessionIdAuthOutput.class,
                new PersonalSessionIDAuthentication(sessionId),
                new NothingExceptionRule()
        );

    }



}
