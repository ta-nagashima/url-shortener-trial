package jp.co.biglobe.isp.sample.user.datasource.authentication.scenario;

import jp.co.biglobe.isp.sample.user.domain.authentication.SessionId;
import jp.co.biglobe.lib.plugin.scenario.ScenarioExecutor;
import jp.co.biglobe.lib.publication.scenario.authentication.PersonalSessionIDAuthentication;
import jp.co.biglobe.lib.publication.scenario.call.ASPFunctionCall;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserAuthenticationScenario {
    /**
     * シナリオ名
     * <p/>
     * 仕様書のパス
     * \\Wufei.hq.biglobe.nec.co.jp\構成管理\論理サブシステム\会員管理\BO顧客管理\最新版\ＡＰＩ説明書\統合認証付き(A-3)\A-3-12　API説明書(統合認証).xls
     */
    private static final String SCENARIO_NAME = "C_authexp";

    @Autowired
    private ScenarioExecutor scenarioExecutor;

    public UserAuthenticationOutput authenticate(SessionId sessionId) {

        UserAuthenticationInput userAuthenticationInput = new UserAuthenticationInput();

        // シナリオの実行
        return scenarioExecutor.execute(
                new ASPFunctionCall(SCENARIO_NAME),
                userAuthenticationInput,
                UserAuthenticationOutput.class,
                new PersonalSessionIDAuthentication(sessionId),
                new UserAuthenticationExceptionRule()
        );

    }
}
