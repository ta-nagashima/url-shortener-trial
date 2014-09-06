package jp.co.biglobe.isp.mobile.voiceoption.datasource.charge.disengagementcharge.refer;

import jp.co.biglobe.isp.mobile.voiceoption.domain.alarm.VoiceOptionAlarmIdentifier;
import jp.co.biglobe.lib.publication.scenario.bobio.BobioHeader;
import jp.co.biglobe.lib.publication.scenario.rule.DefaultExceptionRule;
import jp.co.biglobe.lib.publication.scenario.rule.ExceptionRule;


public class VoiceDisengagementChargeReferExceptionRule implements ExceptionRule {

    @Override
    public boolean verify(BobioHeader bobioHeader) throws RuntimeException {
        String errorMessage = "契約解除料の検索に失敗しました。";
        DefaultExceptionRule defaultExceptionRule = new DefaultExceptionRule(errorMessage, VoiceOptionAlarmIdentifier.DEFAULT);

        return defaultExceptionRule.verify(bobioHeader);
    }
}
