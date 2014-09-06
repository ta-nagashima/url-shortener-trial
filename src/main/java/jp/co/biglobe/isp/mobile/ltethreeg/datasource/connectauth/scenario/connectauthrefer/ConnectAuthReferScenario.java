package jp.co.biglobe.isp.mobile.ltethreeg.datasource.connectauth.scenario.connectauthrefer;

import jp.co.biglobe.isp.auth.domain.user.UserId;
import jp.co.biglobe.isp.mobile.ltethreeg.domain.alarm.LteThreegAlarmIdentifier;
import jp.co.biglobe.isp.mobile.ltethreeg.domain.connectauth.AfterBuyChargeFreeId;
import jp.co.biglobe.isp.mobile.ltethreeg.domain.connectauth.InternationalMsisdn;
import jp.co.biglobe.isp.mobile.ltethreeg.domain.connectauth.bandcontroldevice.ApplicationDestinationPolicyId;
import jp.co.biglobe.isp.mobile.ltethreeg.domain.sim.Msisdn;
import jp.co.biglobe.lib.plugin.event.RequestEventProcess;
import jp.co.biglobe.lib.plugin.scenario.ScenarioExecutor;
import jp.co.biglobe.lib.publication.scenario.authentication.NoAuthentication;
import jp.co.biglobe.lib.publication.scenario.call.ASPFunctionCall;
import jp.co.biglobe.lib.publication.scenario.rule.NotFoundExceptionRule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ConnectAuthReferScenario {

    /**
     * シナリオ名
     * <p/>
     * 仕様書のパス
     * \\Wufei.hq.biglobe.nec.co.jp\構成管理\論理サブシステム\会員管理\BO顧客管理\最新版\ＡＰＩ説明書\統合認証付き(A-3)\A-3-24　API説明書(統合認証版　利用商品契約複数参照).xls
     */

    private static final String SCENARIO_NAME = "C_Goodscontracts_mo_ref2";

    @Autowired
    ScenarioExecutor scenarioExecutor;

    @Autowired
    private RequestEventProcess requestEventProcess;

    public ConnectAuthReferOutput referAfterBuy(Msisdn msisdn,AfterBuyChargeFreeId afterBuyChargeFreeId){

        ConnectAuthChargeFreeReferInput connectAuthChargeFreeReferInput = new ConnectAuthChargeFreeReferInput(msisdn,afterBuyChargeFreeId);

        return scenarioExecutor.execute(
                new ASPFunctionCall(SCENARIO_NAME),
                connectAuthChargeFreeReferInput,
                ConnectAuthReferOutput.class,
                new NoAuthentication(),
                new NotFoundExceptionRule("Radiusの参照に失敗しました", LteThreegAlarmIdentifier.DEFAULT)
                );

    }

    public ConnectAuthReferOutput referForChargeFree(Msisdn msisdn, AfterBuyChargeFreeId afterBuyChargeFreeId, ApplicationDestinationPolicyId applicationDestinationPolicyId){

        ConnectAuthChargeFreeReferInput connectAuthChargeFreeReferInput = new ConnectAuthChargeFreeReferInput(msisdn,afterBuyChargeFreeId,applicationDestinationPolicyId);

        return scenarioExecutor.execute(
                new ASPFunctionCall(SCENARIO_NAME),
                connectAuthChargeFreeReferInput,
                ConnectAuthReferOutput.class,
                new NoAuthentication(),
                new ConnectAuthReferExceptionRule()
        );

    }

    public ConnectAuthReferOutput referForUser(
            UserId userId,
            ApplicationDestinationPolicyId applicationDestinationPolicyId,
            InternationalMsisdn internationalMsisdn){

        ConnectAuthReferInput connectAuthReferInput = new ConnectAuthReferInput(
                userId,
                applicationDestinationPolicyId,
                internationalMsisdn);

        return scenarioExecutor.execute(
                new ASPFunctionCall(SCENARIO_NAME),
                connectAuthReferInput,
                ConnectAuthReferOutput.class,
                new NoAuthentication(),
                new ConnectAuthReferExceptionRule()
        );

    }

}
