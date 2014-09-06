package jp.co.biglobe.isp.mobile.voiceoption.datasource.charge.engagementmonthdisengagementcharge.scenario;

import jp.co.biglobe.isp.auth.domain.operatornouser.OperatorId;
import jp.co.biglobe.isp.auth.domain.user.UserId;
import jp.co.biglobe.isp.mobile.voiceoption.domain.charge.engagementmonthdisengagementcharge.EngagementMonthDisengagementFee;
import jp.co.biglobe.isp.mobile.voiceoption.domain.charge.engagementmonthdisengagementcharge.EngagementMonthDisengagementItemCode;
import jp.co.biglobe.lib.plugin.event.RequestEventTime;
import jp.co.biglobe.lib.plugin.scenario.ScenarioExecutor;
import jp.co.biglobe.lib.publication.scenario.authentication.NoAuthentication;
import jp.co.biglobe.lib.publication.scenario.call.ASPFunctionCall;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class EngagementMonthDisengagementChargeScenario {

    /**
     * シナリオ名
     *
     * 仕様書のパス
     * \\wufei.hq.biglobe.nec.co.jp\構成管理\論理サブシステム\会員管理\BO顧客管理\最新版\ＡＰＩ説明書\その他
     * aspログ編集課金生成ｉｆ仕様.xls
     */
    private static final String SCENARIO_NAME = "M_DDD_Wrapper_call";

    /**
     * シナリオ実行プロジェクト
     */
    @Autowired
    ScenarioExecutor scenarioExecutor;

    public EngagementMonthDisengagementChargeOutput register(
            OperatorId operatorId,
            UserId userId,
            EngagementMonthDisengagementItemCode engagementMonthDisengagementItemCode,
            EngagementMonthDisengagementFee engagementMonthDisengagementFee,
            RequestEventTime requestEventTime){

        EngagementMonthDisengagementChargeInput engagementMonthDisengagementChargeInput = new EngagementMonthDisengagementChargeInput(
                operatorId,
                userId,
                engagementMonthDisengagementItemCode,
                engagementMonthDisengagementFee,
                requestEventTime.get_yyyyMMddHHmmss()
        );

        // シナリオの実行
        return scenarioExecutor.execute(
                new ASPFunctionCall(SCENARIO_NAME),
                engagementMonthDisengagementChargeInput,
                EngagementMonthDisengagementChargeOutput.class,
                new NoAuthentication(),
                new EngagementMonthDisengagementChargeExceptionRule()
        );
    }
}
