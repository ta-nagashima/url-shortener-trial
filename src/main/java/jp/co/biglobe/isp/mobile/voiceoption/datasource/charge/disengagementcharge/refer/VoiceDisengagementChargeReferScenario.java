package jp.co.biglobe.isp.mobile.voiceoption.datasource.charge.disengagementcharge.refer;

import jp.co.biglobe.isp.auth.domain.operatornouser.OperatorId;
import jp.co.biglobe.isp.auth.domain.user.UserId;
import jp.co.biglobe.isp.mobile.voiceoption.domain.voiceengagement.VoiceEngagementNumber;
import jp.co.biglobe.lib.plugin.scenario.ScenarioExecutor;
import jp.co.biglobe.lib.publication.scenario.authentication.NoAuthentication;
import jp.co.biglobe.lib.publication.scenario.call.ASPFunctionCall;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class VoiceDisengagementChargeReferScenario {

    /**
     * 契約解除料を検索するための部品ですが、契約解除料は違約金システムを使うため、
     * この層ではコメントなどに「違約金」という名前を使っている。
     */

    /**
     * シナリオ名：違約金参照シナリオ
     *
     * 仕様書のパス
     * \\Wufei.hq.biglobe.nec.co.jp\構成管理\論理サブシステム\会員管理\BO顧客管理\最新版\ＡＰＩ説明書\統合認証付き(A-3)\A-3-180　API説明書(統合認証版　違約金参照)_V280.xlsx
     */
    private static final String SCENARIO_NAME = "C_iyakukin_mo_ref";

    /**
     * シナリオ実行オブジェクト
     */
    @Autowired
    ScenarioExecutor scenarioExecutor;


    public VoiceDisengagementChargeReferOutput refer(
            UserId userId,
            VoiceEngagementNumber voiceEngagementNumber){

        VoiceDisengagementChargeReferInput voiceDisengagementChargeReferInput
                = new VoiceDisengagementChargeReferInput(userId, voiceEngagementNumber);


        // シナリオの実行
        return scenarioExecutor.execute(
                new ASPFunctionCall(SCENARIO_NAME),
                voiceDisengagementChargeReferInput,
                VoiceDisengagementChargeReferOutput.class,
                new NoAuthentication(),
                new VoiceDisengagementChargeReferExceptionRule()
        );


    }

}
