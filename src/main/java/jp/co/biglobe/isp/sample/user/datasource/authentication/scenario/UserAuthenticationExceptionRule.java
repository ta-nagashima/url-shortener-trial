package jp.co.biglobe.isp.sample.user.datasource.authentication.scenario;

import jp.co.biglobe.lib.danger.alarmidentifier.DefaultAlarmIdentifier;
import jp.co.biglobe.lib.publication.scenario.bobio.BobioHeader;
import jp.co.biglobe.lib.publication.scenario.rule.DefaultExceptionRule;
import jp.co.biglobe.lib.publication.scenario.rule.ExceptionRule;

public class UserAuthenticationExceptionRule implements ExceptionRule {
    @Override
    public boolean verify(BobioHeader bobioHeader) throws RuntimeException {
        String errorMessage = "認証に失敗しました";
        DefaultExceptionRule defaultExceptionRule = new DefaultExceptionRule(errorMessage, DefaultAlarmIdentifier.DEFAULT);

        return defaultExceptionRule.verify(bobioHeader);
    }
}
