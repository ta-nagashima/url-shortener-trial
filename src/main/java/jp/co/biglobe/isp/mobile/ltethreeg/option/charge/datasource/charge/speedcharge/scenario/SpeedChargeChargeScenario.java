package jp.co.biglobe.isp.mobile.ltethreeg.option.charge.datasource.charge.speedcharge.scenario;

import jp.co.biglobe.isp.auth.domain.operatornouser.OperatorId;
import jp.co.biglobe.isp.auth.domain.user.UserId;
import jp.co.biglobe.isp.mobile.ltethreeg.option.charge.domain.charge.speedcharge.SpeedChargeFee;
import jp.co.biglobe.isp.mobile.ltethreeg.option.charge.domain.charge.speedcharge.SpeedChargeItemCode;
import jp.co.biglobe.isp.mobile.ltethreeg.option.charge.datasource.charge.volumecharge.scenario.VolumeChargeChargeExceptionRule;
import jp.co.biglobe.lib.plugin.event.RequestEventTime;
import jp.co.biglobe.lib.plugin.scenario.ScenarioExecutor;
import jp.co.biglobe.lib.publication.scenario.authentication.NoAuthentication;
import jp.co.biglobe.lib.publication.scenario.call.ASPFunctionCall;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class SpeedChargeChargeScenario {

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

    public SpeedChargeChargeOutput register(
            OperatorId operatorId,
            UserId userId,
            SpeedChargeItemCode speedChargeItemCode,
            SpeedChargeFee speedChargeFee,
            RequestEventTime requestEventTime) {

        SpeedChargeChargeInput speedChargeChargeInput = new SpeedChargeChargeInput(
                operatorId,
                userId,
                speedChargeItemCode,
                speedChargeFee,
                requestEventTime.get_yyyyMMddHHmmss()
        );

        // シナリオの実行
        return scenarioExecutor.execute(
                new ASPFunctionCall(SCENARIO_NAME),
                speedChargeChargeInput,
                SpeedChargeChargeOutput.class,
                new NoAuthentication(),
                new VolumeChargeChargeExceptionRule()
        );
    }

}
