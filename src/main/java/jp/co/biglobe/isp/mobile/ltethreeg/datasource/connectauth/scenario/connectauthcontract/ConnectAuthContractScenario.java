package jp.co.biglobe.isp.mobile.ltethreeg.datasource.connectauth.scenario.connectauthcontract;


import jp.co.biglobe.isp.mobile.biglobemember.domain.BiglobeMemberRepository;
import jp.co.biglobe.isp.mobile.ltethreeg.domain.connectauth.LteThreeGConnectAuthChargeFreeEntity;
import jp.co.biglobe.isp.mobile.ltethreeg.domain.connectauth.LteThreeGConnectAuthUserEntity;
import jp.co.biglobe.isp.mobile.ltethreeg.domain.connectauth.bandcontroldevice.ApplicationDestinationPolicyId;
import jp.co.biglobe.lib.plugin.event.RequestEventTime;
import jp.co.biglobe.lib.plugin.scenario.ScenarioExecutor;
import jp.co.biglobe.lib.publication.scenario.authentication.NoAuthentication;
import jp.co.biglobe.lib.publication.scenario.call.ASPFunctionCall;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ConnectAuthContractScenario {

    /**
     * シナリオ名
     * <p/>
     * 仕様書のパス
     * \\Wufei.hq.biglobe.nec.co.jp\構成管理\論理サブシステム\会員管理\BO顧客管理\最新版\ＡＰＩ説明書\統合認証付き(A-3)\A-3-120　API説明書(統合認証版　汎用商品契約).xlsx
     */
    private static final String SCENARIO_NAME = "C_MemberContract_mo_add";

    /**
     * シナリオ実行オブジェクト
     */
    @Autowired
    ScenarioExecutor scenarioExecutor;

    @Autowired
    private RequestEventTime requestEventTime;

    @Autowired
    private BiglobeMemberRepository biglobeMemberRepository;

    public void contract(
            LteThreeGConnectAuthUserEntity connectAuthEntity,
            ApplicationDestinationPolicyId applicationDestinationPolicyId) {

        if (connectAuthEntity.isVpnCorporationExist()) {
            ConnectAuthContractUserInput connectAuthContractUserInput = ConnectAuthContractUserInput.createCorporationVpnOn(
                    connectAuthEntity, applicationDestinationPolicyId, requestEventTime);

            scenarioExecute(connectAuthContractUserInput);

            return;
        }

        ConnectAuthContractUserInput connectAuthContractUserInput = ConnectAuthContractUserInput.createPersonOrCorporationVpnOff(
                connectAuthEntity, applicationDestinationPolicyId, requestEventTime);

        scenarioExecute(connectAuthContractUserInput);
        return;
    }

    private void scenarioExecute(ConnectAuthContractUserInput connectAuthContractUserInput) {

        scenarioExecutor.execute(
                new ASPFunctionCall(SCENARIO_NAME),
                connectAuthContractUserInput,
                ConnectAuthContractOutput.class,
                new NoAuthentication(),
                new ConnectAuthContractExceptionRule()
        );
    }

    public void contractForChargeFree(
            LteThreeGConnectAuthChargeFreeEntity lteThreeGConnectAuthChargeFreeEntity,
            ApplicationDestinationPolicyId applicationDestinationPolicyId){


        ConnectAuthContractChargeFreeInput connectAuthContractChargeFreeInput = new ConnectAuthContractChargeFreeInput(
                lteThreeGConnectAuthChargeFreeEntity.getAfterBuyChargeFreeId(),
                requestEventTime.get_yyyyMMddHHmmss(),
                applicationDestinationPolicyId,
                lteThreeGConnectAuthChargeFreeEntity.getInternationalMsisdn(),
                lteThreeGConnectAuthChargeFreeEntity.getConnectType().getLteThreeGServicePlan().getAfterLteThreeGServicePlanCode(),
                lteThreeGConnectAuthChargeFreeEntity.getBandControlDevice().getConnectControlPolicy().getScenarioValue(),
                applicationDestinationPolicyId.getLineApplicationDestinationPolicyId()
        );


        scenarioExecutor.execute(
                new ASPFunctionCall(SCENARIO_NAME),
                connectAuthContractChargeFreeInput,
                ConnectAuthContractOutput.class,
                new NoAuthentication(),
                new ConnectAuthContractExceptionRule()
        );
    }


}
