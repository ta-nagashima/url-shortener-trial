package jp.co.biglobe.isp.mobile.voiceoption.datasource.mnpout.scenario;

import jp.co.biglobe.isp.auth.domain.operatornouser.OperatorId;
import jp.co.biglobe.isp.auth.domain.user.UserId;
import jp.co.biglobe.lib.plugin.scenario.ScenarioExecutor;
import jp.co.biglobe.lib.publication.scenario.authentication.NoAuthentication;
import jp.co.biglobe.lib.publication.scenario.authentication.SkipAuthentication;
import jp.co.biglobe.lib.publication.scenario.call.ASPFunctionCall;
import jp.co.biglobe.lib.publication.scenario.call.ASPFunctionCallMemLegm;
import jp.co.biglobe.lib.publication.scenario.rule.NothingExceptionRule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MnpOutPersonalInfoScenario {
    /**
     * シナリオ名
     *
     * 仕様書のパス
     * \\Wufei.hq.biglobe.nec.co.jp\構成管理\論理サブシステム\会員管理\BO顧客管理\最新版\ＡＰＩ説明書\統合認証付き(A-3)\A-3-25　API説明書(会員コース情報参照)_V211.xls
     *
     */
    private static final String SCENARIO_NAME = "C_Membercourse_mo_ref";

    @Autowired
    private ScenarioExecutor scenarioExecutor;

    public MnpOutPersonalInfoScenarioOutput findByBiglobeId(UserId userId) {

        MnpOutPersonalInfoScenarioInput mnpOutPersonalInfoScenarioInput = new MnpOutPersonalInfoScenarioInput(userId);

        return scenarioExecutor.execute(
                new ASPFunctionCall(SCENARIO_NAME),
                mnpOutPersonalInfoScenarioInput,
                MnpOutPersonalInfoScenarioOutput.class,
                new NoAuthentication(),
                new MnpOutPersonalInfoExceptionRule()
        );
    }
}
