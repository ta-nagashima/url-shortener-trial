package jp.co.biglobe.isp.mobile.voiceoption.datasource.charge.disengagementcharge.register;

import jp.co.biglobe.isp.auth.domain.operatornouser.OperatorId;
import jp.co.biglobe.isp.auth.domain.user.UserId;
import jp.co.biglobe.isp.mobile.voiceoption.domain.voiceengagement.VoiceEngagementNumber;
import jp.co.biglobe.isp.mobile.voiceoption.domain.voiceengagement.VoiceEngagementStartDate;
import jp.co.biglobe.lib.plugin.scenario.ScenarioExecutor;
import jp.co.biglobe.lib.publication.scenario.authentication.NoAuthentication;
import jp.co.biglobe.lib.publication.scenario.call.ASPFunctionCall;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class VoiceDisengagementChargeAddScenario {

    /**
     * シナリオ名：違約金登録（C_iyakukin_mo_add2）
     *
     * 仕様書のパス
     * 　仕様書
     * 　　\\wufei.hq.biglobe.nec.co.jp\プロジェクト管理\違約金システム\仕様書最新版\06.FD-FT\基盤\A-3-189 API説明書(統合認証版　違約金契約)_V110.xls
     *
     * インパラのマッピング表
     *     \\wufei.hq.biglobe.nec.co.jp\プロジェクト管理\docomo\RPQxxxx：LTE・３G音声通話サービス対応\06_FD-FT\01_FD\音声_IF仕様書\シナリオ\音声オプション_違約金契約API(C_iyakukin_mo_add2)マッピング.xlsx
     */

    private static final String SCENARIO_NAME = "C_iyakukin_mo_add2";

    @Autowired
    ScenarioExecutor scenarioExecutor;

    public VoiceDisengagementChargeAddOutput register(
                                                      UserId userId,
                                                      VoiceEngagementStartDate voiceEngagementStartDate,
                                                      VoiceEngagementNumber voiceEngagementNumber
    ) {

        VoiceDisengagementChargeAddInput voiceDisengagementChargeAddInput = new VoiceDisengagementChargeAddInput(
                userId,
                voiceEngagementStartDate,
                voiceEngagementNumber
        );

        // シナリオの実行
        return scenarioExecutor.execute(
                new ASPFunctionCall(SCENARIO_NAME),
                voiceDisengagementChargeAddInput,
                VoiceDisengagementChargeAddOutput.class,
                new NoAuthentication(),
                new VoiceDisengagementChargeAddExceptionRule()
        );
    }
}
