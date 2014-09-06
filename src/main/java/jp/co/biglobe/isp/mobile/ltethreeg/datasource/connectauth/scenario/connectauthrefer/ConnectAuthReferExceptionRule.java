package jp.co.biglobe.isp.mobile.ltethreeg.datasource.connectauth.scenario.connectauthrefer;

import jp.co.biglobe.isp.mobile.ltethreeg.domain.alarm.LteThreegAlarmIdentifier;
import jp.co.biglobe.lib.publication.scenario.bobio.BobioHeader;
import jp.co.biglobe.lib.publication.scenario.rule.DefaultExceptionRule;
import jp.co.biglobe.lib.publication.scenario.rule.ExceptionRule;
import jp.co.biglobe.lib.publication.scenario.rule.NotFoundExceptionRule;

public class ConnectAuthReferExceptionRule implements ExceptionRule{

    @Override
    public boolean verify(BobioHeader bobioHeader) throws RuntimeException {
        String errorMessage = "Radiusの参照に失敗しました";
        NotFoundExceptionRule notFoundExceptionRule = new NotFoundExceptionRule(errorMessage, LteThreegAlarmIdentifier.DEFAULT);

        return notFoundExceptionRule.verify(bobioHeader);
    }
}
