package jp.co.biglobe.isp.mobile.voiceoption.datasource.charge.mnpoutcharge.scenario;

import jp.co.biglobe.isp.auth.domain.operatornouser.OperatorId;
import jp.co.biglobe.isp.auth.domain.user.UserId;
import jp.co.biglobe.isp.mobile.voiceoption.domain.charge.mnpoutcharge.MnpOutFee;
import jp.co.biglobe.isp.mobile.voiceoption.domain.charge.mnpoutcharge.MnpOutItemCode;
import jp.co.biglobe.lib.plugin.event.RequestEventTime;
import jp.co.biglobe.lib.plugin.scenario.ScenarioExecutor;
import jp.co.biglobe.lib.publication.scenario.authentication.NoAuthentication;
import jp.co.biglobe.lib.publication.scenario.call.ASPFunctionCall;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class MnpOutChargeScenario {

    /**
     * シナリオ名
     *
     * 仕様書のパス
     * \\wufei.hq.biglobe.nec.co.jp\構成管理\論理サブシステム\会員管理\BO顧客管理\最新版\ＡＰＩ説明書\その他
     * aspログ編集課金生成ｉｆ仕様.xls
     */
    private static final String SCENARIO_NAME = "M_DDD_Wrapper_call";

    /**
     * シナリオ実行オブジェクト
     */
    @Autowired
    ScenarioExecutor scenarioExecutor;

    public MnpOutChargeOutput register(
            OperatorId operatorId,
            UserId userId,
            MnpOutItemCode mnpOutItemCode,
            MnpOutFee mnpOutFee,
            RequestEventTime requestEventTime){

        MnpOutChargeInput mnpOutChargeInput = new MnpOutChargeInput(
                operatorId,
                userId,
                mnpOutItemCode,
                mnpOutFee,
                requestEventTime.get_yyyyMMddHHmmss()
                );

        // シナリオの実行
        return scenarioExecutor.execute(
                new ASPFunctionCall(SCENARIO_NAME),
                mnpOutChargeInput,
                MnpOutChargeOutput.class,
                new NoAuthentication(),
                new MnpOutChargeExceptionRule()
        );
    }

}
