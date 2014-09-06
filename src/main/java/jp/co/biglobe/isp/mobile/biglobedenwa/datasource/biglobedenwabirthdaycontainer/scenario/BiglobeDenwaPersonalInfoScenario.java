package jp.co.biglobe.isp.mobile.biglobedenwa.datasource.biglobedenwabirthdaycontainer.scenario;


import jp.co.biglobe.isp.auth.domain.user.UserId;
import jp.co.biglobe.lib.plugin.scenario.ScenarioExecutor;
import jp.co.biglobe.lib.publication.scenario.authentication.NoAuthentication;
import jp.co.biglobe.lib.publication.scenario.call.ASPFunctionCall;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class BiglobeDenwaPersonalInfoScenario {


    /**
     * シナリオ名
     * <p>
     * 仕様書のパス
     * \\Wufei.hq.biglobe.nec.co.jp\構成管理\論理サブシステム\会員管理\BO顧客管理\最新版\ＡＰＩ説明書\統合認証付き(A-3)\A-3-25　API説明書(会員コース情報参照)_V211.xls
     */
    private static final String SCENARIO_NAME = "M_MemberRefMeminfo2";

    @Autowired
    private ScenarioExecutor scenarioExecutor;

    public BiglobeDenwaPersonalInfoScenarioOutput findByBiglobeId(UserId userId) {

        BiglobeDenwaPersonalInfoScenarioInput biglobeDenwaPersonalInfoScenarioInput = new BiglobeDenwaPersonalInfoScenarioInput(userId);

        return scenarioExecutor.execute(
                new ASPFunctionCall(SCENARIO_NAME),
                biglobeDenwaPersonalInfoScenarioInput,
                BiglobeDenwaPersonalInfoScenarioOutput.class,
                new NoAuthentication(),
                new BiglobeDenwaPersonalInfoExceptionRule()
        );
    }
}



