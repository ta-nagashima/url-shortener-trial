package jp.co.biglobe.isp.mobile.ltethreeg.datasource.connectauth.scenario.bandcontroldevice;


import jp.co.biglobe.isp.mobile.ltethreeg.domain.connectauth.InternationalMsisdn;
import jp.co.biglobe.isp.mobile.ltethreeg.domain.connectauth.LteThreeGConnectAuthUserEntity;
import jp.co.biglobe.isp.mobile.ltethreeg.domain.connectperformance.connectcontrolpolicy.ConnectControlPolicy;
import jp.co.biglobe.lib.plugin.scenario.ScenarioExecutor;
import jp.co.biglobe.lib.publication.scenario.authentication.NoAuthentication;
import jp.co.biglobe.lib.publication.scenario.call.ASPFunctionCall;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class SessionBandControlDeviceScenario {

    /**
     * シナリオ名
     *
     * 仕様書のパス
     * \\Wufei.hq.biglobe.nec.co.jp\構成管理\
     */
    private static final String SCENARIO_NAME = "M_BDCM_BandControlDevice_Wrapper";

    /**
     * シナリオ実行オブジェクト
     */
    @Autowired
    ScenarioExecutor scenarioExecutor;

    public void update(InternationalMsisdn internationalMsisdn, ConnectControlPolicy connectControlPolicy){

        BandControlDeviceInput bandControlDeviceInput = new BandControlDeviceInput(
                internationalMsisdn,
                connectControlPolicy.getScenarioValue());

        // シナリオの実行
        scenarioExecutor.execute(
                new ASPFunctionCall(SCENARIO_NAME),
                bandControlDeviceInput,
                BandControlDeviceOutput.class,
                new NoAuthentication(),
                new BandControlDeviceExceptionRule()
        );

        return;

    }


}
