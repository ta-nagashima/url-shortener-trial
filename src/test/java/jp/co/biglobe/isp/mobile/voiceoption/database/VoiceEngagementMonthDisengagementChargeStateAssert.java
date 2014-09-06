package jp.co.biglobe.isp.mobile.voiceoption.database;

import jp.co.biglobe.isp.mobile.voiceoption.datasource.charge.engagementmonthdisengagementcharge.db.FixtureVoiceEngagementMonthDisengagementChargeState;
import jp.co.biglobe.test.util.dbunit.DbUnitTester;
import jp.co.biglobe.test.util.dbunit.assertion.DatabaseAssert;

import java.util.Map;

public class VoiceEngagementMonthDisengagementChargeStateAssert {

    private static final String VOICE_ENGAGEMENT_MONTH_DISENGAGEMENT_CHARGE_STATE_TABLE_NAME = "voice_eng_month_diseng_chrg_s";

    private DbUnitTester dbUnitTester;

    public VoiceEngagementMonthDisengagementChargeStateAssert(DbUnitTester dbUnitTester) {
        this.dbUnitTester = dbUnitTester;
    }


    public void assertTableWithAllColumns(Map expectedData) throws Exception {

        assertVoiceEngagementMonthDisengagementChargeEventAssert(expectedData);
    }


    private void assertVoiceEngagementMonthDisengagementChargeEventAssert(Map expectedData) throws Exception {
        String[] sortColumns = new String[]{"voice_engagement_number"};
        DatabaseAssert databaseAssert = new DatabaseAssert(dbUnitTester.getConnection());
        if (expectedData.containsKey(VOICE_ENGAGEMENT_MONTH_DISENGAGEMENT_CHARGE_STATE_TABLE_NAME)) {
            databaseAssert.assertTableWithAllColumns(expectedData, VOICE_ENGAGEMENT_MONTH_DISENGAGEMENT_CHARGE_STATE_TABLE_NAME, sortColumns);
        } else {
            databaseAssert.assertTableNoCount(FixtureVoiceEngagementMonthDisengagementChargeState.Nothing.getDefaultData(), VOICE_ENGAGEMENT_MONTH_DISENGAGEMENT_CHARGE_STATE_TABLE_NAME);
        }
    }

    public void assertTableNoCount()throws Exception {
        DatabaseAssert databaseAssert = new DatabaseAssert(dbUnitTester.getConnection());
        databaseAssert.assertTableNoCount(FixtureVoiceEngagementMonthDisengagementChargeState.Nothing.getDefaultData(),VOICE_ENGAGEMENT_MONTH_DISENGAGEMENT_CHARGE_STATE_TABLE_NAME);
    }
}
