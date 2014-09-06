package jp.co.biglobe.isp.mobile.voiceoption.datasource.voicesendmail.scenario.voicesendmailinput;

import jp.co.biglobe.lib.publication.enumeration.convert.EnumConvertForScenario;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * メール送信シナリオに渡すメールテンプレートの情報
 */


@AllArgsConstructor
public enum MailTemplates implements EnumConvertForScenario {
    BLTE_CONFIRM_RESULT(
            "本人確認（音声通話SIM MNP無）",
            "本人確認OKを通知するメール",
            MailTemplatesScenarioValue.BLTE_CONFIRM_RESULT),
    BLTE_CONFIRM_RESULT_MNP(
            "本人確認（音声通話SIM MNP有）",
            "本人確認OKのときに本人確認結果とアクティベーション予定日を通知するメール",
            MailTemplatesScenarioValue.BLTE_CONFIRM_RESULT_MNP),
    BLTE_TELSIM_RECEPT_END_MNP(
            "解約受付（音声通話SIM MNP有）",
            "転出申し込み完了メール",
            MailTemplatesScenarioValue.BLTE_TELSIM_RECEPT_END_MNP),
    BLTE_TELSIM_CONFIRM_NUM(
            "解約受付（音声通話SIM MNP有）予約番号通知",
            "転出予約番号を通知するメール",
            MailTemplatesScenarioValue.BLTE_TELSIM_CONFIRM_NUM);

    private String templateName;

    private String message;

    private MailTemplatesScenarioValue scenarioValue;

    @Override
    public String getScenarioValue() {
        return scenarioValue.getNoRefactoringValue();
    }


    @AllArgsConstructor
    private enum MailTemplatesScenarioValue {
        BLTE_CONFIRM_RESULT("BLTE_confirm_result" /* 文字列リテラルの変更禁止 */),
        BLTE_CONFIRM_RESULT_MNP("BLTE_confirm_result_mnp" /* 文字列リテラルの変更禁止 */),
        BLTE_TELSIM_RECEPT_END_MNP("BLTE_telsim_recept_end_mnp" /* 文字列リテラルの変更禁止 */),
        BLTE_TELSIM_CONFIRM_NUM("BLTE_telsim_confirm_num" /* 文字列リテラルの変更禁止 */);

        @Getter
        private final String noRefactoringValue;


    }




}
