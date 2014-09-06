package jp.co.biglobe.isp.mobile.ltethreeg.datasource.connectauth.scenario.connectauthupdate;

import jp.co.biglobe.isp.mobile.ltethreeg.datasource.connectauth.scenario.connectauthdelete.scenarioparam.KeiyakuEdano;
import jp.co.biglobe.isp.mobile.ltethreeg.datasource.connectauth.scenario.connectauthdelete.scenarioparam.RiyoguchiNo;
import jp.co.biglobe.isp.mobile.ltethreeg.datasource.connectauth.scenario.connectauthrefer.ConnectAuthReferScenario;
import jp.co.biglobe.isp.mobile.ltethreeg.domain.connectauth.LteThreeGConnectAuthUserEntity;
import jp.co.biglobe.lib.plugin.scenario.ScenarioExecutor;
import jp.co.biglobe.lib.publication.scenario.authentication.NoAuthentication;
import jp.co.biglobe.lib.publication.scenario.call.ASPFunctionCallLegm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ConnectAuthUpdateScenario {

    /**
     * シナリオ名
     * <p/>
     * 仕様書のパス
     * \\wufei.hq.biglobe.nec.co.jp\構成管理\論理サブシステム\会員管理\BO顧客管理\最新版\ＡＰＩ仕様書\02T,03-商契
     * 03-1-04 商品契約更新(API).xls
    **/
    private static final String SCENARIO_NAME = "BOContexp_changeGoodsContract";

    @Autowired
    ScenarioExecutor scenarioExecutor;

    @Autowired
    ConnectAuthReferScenario connectAuthReferScenario;


    public void update(LteThreeGConnectAuthUserEntity lteThreeGConnectAuthUserEntity,
            RiyoguchiNo riyoguchiNo,KeiyakuEdano keiyakuEdano){

        ConnectAuthUpdateInput connectAuthUpdateInput = new ConnectAuthUpdateInput(
                lteThreeGConnectAuthUserEntity.getUserId(),
                lteThreeGConnectAuthUserEntity.getUserId(),
                riyoguchiNo,
                keiyakuEdano,
                lteThreeGConnectAuthUserEntity.getConnectType().getLteThreeGServicePlan().getAfterLteThreeGServicePlanCode(),
                lteThreeGConnectAuthUserEntity.getConnectType().getLteThreeGServicePlan().getLteThreeGServicePlanChange().getBeforeServicePlanCodeScenarioValue(),
                lteThreeGConnectAuthUserEntity.getConnectType().getLteThreeGServicePlan().getLteThreeGServicePlanChange().getSwitchingDateScenarioValue(),
                lteThreeGConnectAuthUserEntity.getConnectControlPolicy().getScenarioValue(),
                lteThreeGConnectAuthUserEntity.getLteThreeGEngagementNumber());

        // シナリオの実行
        scenarioExecutor.execute(
                new ASPFunctionCallLegm(SCENARIO_NAME),
                connectAuthUpdateInput,
                ConnectAuthUpdateOutput.class,
                new NoAuthentication(),
                new ConnectAuthUpdateExceptionRule()
        );

    }

}
