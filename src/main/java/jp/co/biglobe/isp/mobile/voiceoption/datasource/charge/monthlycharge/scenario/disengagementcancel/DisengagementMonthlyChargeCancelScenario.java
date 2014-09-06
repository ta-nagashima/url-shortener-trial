package jp.co.biglobe.isp.mobile.voiceoption.datasource.charge.monthlycharge.scenario.disengagementcancel;

import jp.co.biglobe.isp.auth.domain.user.UserId;
import jp.co.biglobe.isp.mobile.voiceoption.domain.voiceengagement.VoiceEngagementNumber;
import jp.co.biglobe.lib.plugin.scenario.ScenarioExecutor;
import jp.co.biglobe.lib.publication.scenario.authentication.NoAuthentication;
import jp.co.biglobe.lib.publication.scenario.call.ASPFunctionCall;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class DisengagementMonthlyChargeCancelScenario {

    /**
     * シナリオ名
     * <p>
     * 仕様書のパス
     * \\Wufei.hq.biglobe.nec.co.jp\構成管理\論理サブシステム\会員管理\BO顧客管理\最新版\ＡＰＩ説明書\統合認証付き(A-3)\A-3-154　API説明書(統合認証版　汎用商品契約更新).xlsx
     */
    private static final String SCENARIO_NAME = "C_MemberContract_mo_mod";

    /**
     * シナリオ実行オブジェクト
     */
    @Autowired
    ScenarioExecutor scenarioExecutor;

    public DisengagementMonthlyChargeCancelOutput update(
            UserId userId,
            VoiceEngagementNumber voiceEngagementNumber) {

        DisengagementMonthlyChargeCancelInput disengagementMonthlyChargeCancelInput
                = new DisengagementMonthlyChargeCancelInput(userId, voiceEngagementNumber);

        // シナリオの実行
        return scenarioExecutor.execute(
                new ASPFunctionCall(SCENARIO_NAME),
                disengagementMonthlyChargeCancelInput,
                DisengagementMonthlyChargeCancelOutput.class,
                new NoAuthentication(),
                new DisengagementMonthlyChargeCancelExceptionRule()
        );
    }


}
