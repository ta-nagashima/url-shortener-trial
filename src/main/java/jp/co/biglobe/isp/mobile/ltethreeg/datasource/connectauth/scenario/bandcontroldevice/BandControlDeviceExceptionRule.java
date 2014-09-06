package jp.co.biglobe.isp.mobile.ltethreeg.datasource.connectauth.scenario.bandcontroldevice;


import jp.co.biglobe.isp.mobile.ltethreeg.domain.alarm.LteThreegAlarmIdentifier;
import jp.co.biglobe.lib.publication.scenario.bobio.BobioHeader;
import jp.co.biglobe.lib.publication.scenario.rule.DefaultExceptionRule;
import jp.co.biglobe.lib.publication.scenario.rule.ExceptionRule;

public class BandControlDeviceExceptionRule implements ExceptionRule {

    @Override
    public boolean verify(BobioHeader bobioHeader) throws RuntimeException {
        String errorMessage = "帯域制御装置のポリシー更新に失敗しました。";
        DefaultExceptionRule defaultExceptionRule = new DefaultExceptionRule(errorMessage, LteThreegAlarmIdentifier.DEFAULT);

        return defaultExceptionRule.verify(bobioHeader);
    }
}
