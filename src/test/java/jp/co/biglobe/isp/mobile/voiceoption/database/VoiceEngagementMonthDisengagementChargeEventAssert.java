package jp.co.biglobe.isp.mobile.voiceoption.database;

import jp.co.biglobe.isp.mobile.voiceoption.datasource.charge.engagementmonthdisengagementcharge.db.FixtureVoiceEngagementMonthDisengagementChargeEvent;
import jp.co.biglobe.isp.mobile.voiceoption.datasource.mnpout.db.FixtureVoiceMnpOutEvent;
import jp.co.biglobe.test.util.dbunit.DbUnitTester;
import jp.co.biglobe.test.util.dbunit.assertion.DatabaseAssert;

import java.util.Map;

public class VoiceEngagementMonthDisengagementChargeEventAssert {

    private static final String VOICE_ENGAGEMENT_MONTH_DISENGAGEMENT_CHARGE_EVENT_TABLE_NAME = "voice_eng_month_diseng_chrg_e";

    private DbUnitTester dbUnitTester;

    public VoiceEngagementMonthDisengagementChargeEventAssert(DbUnitTester dbUnitTester) {
        this.dbUnitTester = dbUnitTester;
    }


    public void assertTableWithAllColumns(Map expectedData) throws Exception {

        assertVoiceEngagementMonthDisengagementChargeEventAssert(expectedData);
    }


    private void assertVoiceEngagementMonthDisengagementChargeEventAssert(Map expectedData) throws Exception {
        String[] sortColumns = new String[]{"event_id"};
        DatabaseAssert databaseAssert = new DatabaseAssert(dbUnitTester.getConnection());
        if (expectedData.containsKey(VOICE_ENGAGEMENT_MONTH_DISENGAGEMENT_CHARGE_EVENT_TABLE_NAME)) {
            databaseAssert.assertTableWithAllColumns(expectedData, VOICE_ENGAGEMENT_MONTH_DISENGAGEMENT_CHARGE_EVENT_TABLE_NAME, sortColumns);
        } else {
            databaseAssert.assertTableNoCount(FixtureVoiceEngagementMonthDisengagementChargeEvent.Nothing.getDefaultData(), VOICE_ENGAGEMENT_MONTH_DISENGAGEMENT_CHARGE_EVENT_TABLE_NAME);
        }
    }

    public void assertTableNoCount()throws Exception {
        DatabaseAssert databaseAssert = new DatabaseAssert(dbUnitTester.getConnection());
        databaseAssert.assertTableNoCount(FixtureVoiceEngagementMonthDisengagementChargeEvent.Nothing.getDefaultData(),VOICE_ENGAGEMENT_MONTH_DISENGAGEMENT_CHARGE_EVENT_TABLE_NAME);
    }
}
