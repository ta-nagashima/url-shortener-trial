package jp.co.biglobe.isp.mobile.biglobemember.datasource.scenario.wrapper;

import jp.co.biglobe.isp.auth.domain.user.UserId;
import jp.co.biglobe.isp.mobile.biglobemember.datasource.scenario.BiglobeMemberInput;
import jp.co.biglobe.isp.mobile.biglobemember.datasource.scenario.BiglobeMemberOutput;
import jp.co.biglobe.lib.plugin.scenario.ScenarioExecutor;
import jp.co.biglobe.lib.publication.scenario.authentication.SkipAuthentication;
import jp.co.biglobe.lib.publication.scenario.call.ASPFunctionCall;
import jp.co.biglobe.lib.publication.scenario.rule.NothingExceptionRule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BiglobeMemberScenario2 {

    // todo バッチサーバにMemLegmの穴が空いていないので、ラッパー経由で呼び出す。９月中に穴あけ完了するので、そのタイミングでリファクタ
    /**
     * シナリオ名
     *
     * 仕様書のパス
     * \\Wufei.hq.biglobe.nec.co.jp\構成管理\論理サブシステム\会員管理\会員管理\最新版\IF仕様書\シナリオ\M層シナリオ\会員情報参照(M_MemberRefMeminfo2)\02 会員情報参照　コンポーネントIO.xls
     *
     */
    //private static final String SCENARIO_NAME = "M_MemberRefMeminfo2";
    private static final String SCENARIO_NAME = "M_DDD_Wrapper_call";
    private static final long TIMEOUT_SECONDS = 300L;

    @Autowired
    private ScenarioExecutor scenarioExecutor;

    public BiglobeMemberOutput findByBiglobeId(UserId userId) {

        BiglobeMemberInput2 biglobeMemberInput = new BiglobeMemberInput2(userId);

        return scenarioExecutor.execute(
                //new ASPFunctionCallMemLegm(SCENARIO_NAME),
                new ASPFunctionCall(SCENARIO_NAME),
                biglobeMemberInput,
                BiglobeMemberOutput.class,
                new SkipAuthentication(),
                new NothingExceptionRule()
        );
    }
}
