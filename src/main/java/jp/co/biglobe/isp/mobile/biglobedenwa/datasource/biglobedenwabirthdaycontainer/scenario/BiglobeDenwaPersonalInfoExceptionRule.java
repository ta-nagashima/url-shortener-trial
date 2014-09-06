package jp.co.biglobe.isp.mobile.biglobedenwa.datasource.biglobedenwabirthdaycontainer.scenario;

import jp.co.biglobe.isp.mobile.voiceoption.domain.alarm.VoiceOptionAlarmIdentifier;
import jp.co.biglobe.lib.publication.scenario.bobio.BobioHeader;
import jp.co.biglobe.lib.publication.scenario.rule.DefaultExceptionRule;
import jp.co.biglobe.lib.publication.scenario.rule.ExceptionRule;


public class BiglobeDenwaPersonalInfoExceptionRule implements ExceptionRule {

    @Override
    public boolean verify(BobioHeader bobioHeader) throws RuntimeException {
        String errorMessage = "契約者の氏名、ふりがな、性別の検索に失敗しました。";
        DefaultExceptionRule defaultExceptionRule = new DefaultExceptionRule(errorMessage, VoiceOptionAlarmIdentifier.DEFAULT);

        return defaultExceptionRule.verify(bobioHeader);
    }
}
