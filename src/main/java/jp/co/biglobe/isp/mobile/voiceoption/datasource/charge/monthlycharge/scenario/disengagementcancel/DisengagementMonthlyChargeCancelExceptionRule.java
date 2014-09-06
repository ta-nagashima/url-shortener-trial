package jp.co.biglobe.isp.mobile.voiceoption.datasource.charge.monthlycharge.scenario.disengagementcancel;

import jp.co.biglobe.isp.mobile.voiceoption.domain.alarm.VoiceOptionAlarmIdentifier;
import jp.co.biglobe.lib.publication.scenario.bobio.BobioHeader;
import jp.co.biglobe.lib.publication.scenario.rule.DefaultExceptionRule;
import jp.co.biglobe.lib.publication.scenario.rule.ExceptionRule;


public class DisengagementMonthlyChargeCancelExceptionRule implements ExceptionRule {

    @Override
    public boolean verify(BobioHeader bobioHeader) throws RuntimeException {
        String errorMessage = "月額課金の更新に失敗しました。";
        DefaultExceptionRule defaultExceptionRule = new DefaultExceptionRule(errorMessage, VoiceOptionAlarmIdentifier.DEFAULT);

        return defaultExceptionRule.verify(bobioHeader);
    }
}
