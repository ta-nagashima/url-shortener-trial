package jp.co.biglobe.isp.mobile.voiceoption.datasource.voicesendmail.scenario;

import jp.co.biglobe.isp.mobile.voiceoption.datasource.voicesendmail.scenario.voicesendmailinput.VoiceSendMailInput;
import jp.co.biglobe.lib.plugin.scenario.ScenarioExecutor;
import jp.co.biglobe.lib.publication.scenario.authentication.NoAuthentication;
import jp.co.biglobe.lib.publication.scenario.call.ASPFunctionCall;
import jp.co.biglobe.lib.publication.scenario.rule.NothingExceptionRule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class VoiceSendMailScenario {

    /**
     * シナリオ名
     *
     * 仕様書のパス
     * \\Wufei.hq.biglobe.nec.co.jp\構成管理\サービス\受注管理基盤\★最新版\06_FD-FT\01_FD\IF仕様書\(サブ-02)メール送信シナリオ Ｉ／Ｆ仕様書.xls
     */
    private static final String SCENARIO_NAME = "M_JKBS_Mail_send";

    /**
     * シナリオ実行オブジェクト
     */
    @Autowired
    ScenarioExecutor scenarioExecutor;

    public VoiceSendMailOutput send(VoiceSendMailInput voiceSendMailInput) {

        // シナリオの実行
        return scenarioExecutor.execute(
                new ASPFunctionCall(SCENARIO_NAME),
                voiceSendMailInput,
                VoiceSendMailOutput.class,
                new NoAuthentication(),
                new VoiceSendMailExceptionRule()
        );

    }

    /**
     * 送信に失敗しても例外をスローしないメソッド
     */
    public VoiceSendMailOutput sendNoException(VoiceSendMailInput voiceSendMailInput) {

        // シナリオの実行
        return scenarioExecutor.execute(
                new ASPFunctionCall(SCENARIO_NAME),
                voiceSendMailInput,
                VoiceSendMailOutput.class,
                new NoAuthentication(),
                new NothingExceptionRule()
        );

    }
}