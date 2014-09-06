package jp.co.biglobe.isp.mobile.ltethreeg.option.charge.datasource.speedchargeengagement.scenario;

import jp.co.biglobe.isp.mobile.ltethreeg.domain.alarm.LteThreegAlarmIdentifier;
import jp.co.biglobe.lib.publication.scenario.bobio.BobioHeader;
import jp.co.biglobe.lib.publication.scenario.rule.DefaultExceptionRule;
import jp.co.biglobe.lib.publication.scenario.rule.ExceptionRule;

public class SpeedChargeSendMailExceptionRule implements ExceptionRule {

    @Override
    public boolean verify(BobioHeader bobioHeader) throws RuntimeException {
        String errorMessage = "スピードチャージのメール送信に失敗しました。";
        DefaultExceptionRule defaultExceptionRule = new DefaultExceptionRule(errorMessage, LteThreegAlarmIdentifier.DEFAULT);

        return defaultExceptionRule.verify(bobioHeader);
    }
}