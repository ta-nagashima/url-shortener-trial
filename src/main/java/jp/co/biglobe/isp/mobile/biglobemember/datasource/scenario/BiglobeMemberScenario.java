package jp.co.biglobe.isp.mobile.biglobemember.datasource.scenario;

import jp.co.biglobe.isp.auth.domain.user.UserId;
import jp.co.biglobe.lib.plugin.scenario.ScenarioExecutor;
import jp.co.biglobe.lib.publication.scenario.authentication.SkipAuthentication;
import jp.co.biglobe.lib.publication.scenario.call.ASPFunctionCallMemLegm;
import jp.co.biglobe.lib.publication.scenario.rule.NothingExceptionRule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BiglobeMemberScenario {
    /**
     * シナリオ名
     *
     * 仕様書のパス
     * \\Wufei.hq.biglobe.nec.co.jp\構成管理\論理サブシステム\会員管理\会員管理\最新版\IF仕様書\シナリオ\M層シナリオ\会員情報参照(M_MemberRefMeminfo2)\02 会員情報参照　コンポーネントIO.xls
     *
     */
    private static final String SCENARIO_NAME = "M_MemberRefMeminfo2";
    private static final long TIMEOUT_SECONDS = 300L;

    @Autowired
    private ScenarioExecutor scenarioExecutor;

    public BiglobeMemberOutput findByBiglobeId(UserId userId) {

        BiglobeMemberInput biglobeMemberInput = new BiglobeMemberInput(userId);

        return scenarioExecutor.execute(
                new ASPFunctionCallMemLegm(SCENARIO_NAME),
                biglobeMemberInput,
                BiglobeMemberOutput.class,
                new SkipAuthentication(),
                new NothingExceptionRule()
        );
    }
}
