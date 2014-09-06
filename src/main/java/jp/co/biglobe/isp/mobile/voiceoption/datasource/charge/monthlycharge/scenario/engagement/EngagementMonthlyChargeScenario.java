package jp.co.biglobe.isp.mobile.voiceoption.datasource.charge.monthlycharge.scenario.engagement;

import jp.co.biglobe.isp.auth.domain.user.UserId;
import jp.co.biglobe.isp.mobile.voiceoption.domain.voiceengagement.VoiceEngagementNumber;
import jp.co.biglobe.isp.mobile.voiceoption.domain.voiceengagement.VoiceSystemReceiptDateTime;
import jp.co.biglobe.lib.plugin.scenario.ScenarioExecutor;
import jp.co.biglobe.lib.publication.scenario.authentication.NoAuthentication;
import jp.co.biglobe.lib.publication.scenario.call.ASPFunctionCall;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.text.SimpleDateFormat;

@Repository
public class EngagementMonthlyChargeScenario {

    /**
     * シナリオ名
     * <p/>
     * 仕様書のパス
     * \\Wufei.hq.biglobe.nec.co.jp\構成管理\論理サブシステム\会員管理\BO顧客管理\最新版\ＡＰＩ説明書\統合認証付き(A-3)\A-3-120　API説明書(統合認証版　汎用商品契約).xlsx
     */
    private static final String SCENARIO_NAME = "C_MemberContract_mo_add";

    private static final String DATE_PATTERN = "yyyyMMddHHmmss";

    /**
     * シナリオ実行オブジェクト
     */
    @Autowired
    ScenarioExecutor scenarioExecutor;

    public EngagementMonthlyChargeOutput register(
            UserId userId,
            VoiceSystemReceiptDateTime voiceSystemReceiptDateTime,
            VoiceEngagementNumber voiceEngagementNumber) {

        EngagementMonthlyChargeInput engagementMonthlyChargeInput = new EngagementMonthlyChargeInput(
                userId,
                convertVoiceSystemReceiptDateTimeToScenarioValue(voiceSystemReceiptDateTime),
                voiceEngagementNumber
        );

        // シナリオの実行
        return scenarioExecutor.execute(
                new ASPFunctionCall(SCENARIO_NAME),
                engagementMonthlyChargeInput,
                EngagementMonthlyChargeOutput.class,
                new NoAuthentication(),
                new EngagementMonthlyChargeExceptionRule()
        );
    }

    public String convertVoiceSystemReceiptDateTimeToScenarioValue(VoiceSystemReceiptDateTime voiceSystemReceiptDateTime) {
        return (new SimpleDateFormat(DATE_PATTERN)).format(voiceSystemReceiptDateTime.getValue());
    }
}
