package jp.co.biglobe.isp.mobile.ltethreeg.option.charge.datasource.charge.volumecharge.scenario;

import jp.co.biglobe.isp.auth.domain.operatornouser.OperatorId;
import jp.co.biglobe.isp.auth.domain.user.UserId;
import jp.co.biglobe.isp.mobile.ltethreeg.option.charge.domain.charge.volumecharge.VolumeChargeFee;
import jp.co.biglobe.isp.mobile.ltethreeg.option.charge.domain.charge.volumecharge.VolumeChargeItemCode;
import jp.co.biglobe.lib.plugin.event.RequestEventTime;
import jp.co.biglobe.lib.plugin.scenario.ScenarioExecutor;
import jp.co.biglobe.lib.publication.scenario.authentication.NoAuthentication;
import jp.co.biglobe.lib.publication.scenario.call.ASPFunctionCall;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class VolumeChargeChargeScenario {

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

    public VolumeChargeChargeOutput register(
            OperatorId operatorId,
            UserId userId,
            VolumeChargeItemCode volumeChargeItemCode,
            VolumeChargeFee volumeChargeFee,
            RequestEventTime requestEventTime) {

        VolumeChargeChargeInput volumeChargeChargeInput = new VolumeChargeChargeInput(
                operatorId,
                userId,
                volumeChargeItemCode,
                volumeChargeFee,
                requestEventTime.get_yyyyMMddHHmmss()
        );

        // シナリオの実行
        return scenarioExecutor.execute(
                new ASPFunctionCall(SCENARIO_NAME),
                volumeChargeChargeInput,
                VolumeChargeChargeOutput.class,
                new NoAuthentication(),
                new VolumeChargeChargeExceptionRule()
        );
    }

}
