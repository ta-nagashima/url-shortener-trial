package jp.co.biglobe.isp.mobile.voiceoption.datasource.charge.monthlycharge.scenario.disengagement;

import jp.co.biglobe.isp.auth.domain.user.UserId;
import jp.co.biglobe.isp.mobile.voiceoption.domain.voiceengagement.VoiceEngagementEndDateTime;
import jp.co.biglobe.isp.mobile.voiceoption.domain.voiceengagement.VoiceEngagementNumber;
import jp.co.biglobe.lib.plugin.scenario.ScenarioExecutor;
import jp.co.biglobe.lib.publication.scenario.authentication.NoAuthentication;
import jp.co.biglobe.lib.publication.scenario.call.ASPFunctionCall;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.text.SimpleDateFormat;

@Repository
public class DisengagementMonthlyChargeScenario {

    /**
     * シナリオ名
     * <p>
     * 仕様書のパス
     * \\Wufei.hq.biglobe.nec.co.jp\構成管理\論理サブシステム\会員管理\BO顧客管理\最新版\ＡＰＩ説明書\統合認証付き(A-3)\A-3-154　API説明書(統合認証版　汎用商品契約更新).xlsx
     */
    private static final String SCENARIO_NAME = "C_MemberContract_mo_mod";

    private static final String DATE_PATTERN = "yyyyMMddHHmmss";

    /**
     * シナリオ実行オブジェクト
     */
    @Autowired
    ScenarioExecutor scenarioExecutor;

    public DisengagementMonthlyChargeOutput delete(
            UserId userId,
            VoiceEngagementNumber voiceEngagementNumber,
            VoiceEngagementEndDateTime voiceEngagementEndDateTime) {

        DisengagementMonthlyChargeInput disengagementMonthlyChargeInput = new DisengagementMonthlyChargeInput(
                userId,
                voiceEngagementNumber,
                convertVoiceEngagementEndDateTimeToScenarioValue(voiceEngagementEndDateTime),
                convertVoiceEngagementEndDateTimeToScenarioValue(voiceEngagementEndDateTime)
        );

        // シナリオの実行
        return scenarioExecutor.execute(
                new ASPFunctionCall(SCENARIO_NAME),
                disengagementMonthlyChargeInput,
                DisengagementMonthlyChargeOutput.class,
                new NoAuthentication(),
                new DisengagementMonthlyChargeExceptionRule()
        );
    }

    public String convertVoiceEngagementEndDateTimeToScenarioValue(VoiceEngagementEndDateTime voiceEngagementEndDateTime) {
        return (new SimpleDateFormat(DATE_PATTERN)).format(voiceEngagementEndDateTime.getValue());
    }

}
