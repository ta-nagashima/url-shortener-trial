package jp.co.biglobe.isp.mobile.voiceoption.database;

import jp.co.biglobe.isp.mobile.voiceoption.datasource.voiceengagement.db.FixtureVoiceEngagementCancelState;
import jp.co.biglobe.isp.mobile.voiceoption.datasource.voiceengagement.db.FixtureVoiceEngagementDisengageState;
import jp.co.biglobe.isp.mobile.voiceoption.datasource.voiceengagement.db.FixtureVoiceEngagementState;
import jp.co.biglobe.test.util.dbunit.DbUnitTester;
import jp.co.biglobe.test.util.dbunit.assertion.DatabaseAssert;

import java.util.Map;


public class VoiceEngagementStateAssert {

    private static final String VOICE_ENGAGEMENT_TABLE_NAME = "voice_engagement_state";
    private static final String VOICE_ENGAGEMENT_DISENGAGE_TABLE_NAME = "voice_engagement_disengage_s";
    private static final String VOICE_ENGAGEMENT_CANCEL_TABLE_NAME = "voice_engagement_cancel_state";

    private DbUnitTester dbUnitTester;

    public VoiceEngagementStateAssert(DbUnitTester dbUnitTester) {
        this.dbUnitTester = dbUnitTester;

    }

    public void assertTableWithAllColumns(Map expectedData) throws Exception {
        assertVoiceEngagement(expectedData);
        assertVoiceEngagementDisengage(expectedData);
        assertVoiceEngagementCancel(expectedData);
    }

    private void assertVoiceEngagement(Map expectedData) throws Exception {
        String[] sortColumns = new String[]{"voice_engagement_number"};
        DatabaseAssert databaseAssert = new DatabaseAssert(dbUnitTester.getConnection());
        databaseAssert.assertTableWithAllColumns(expectedData, VOICE_ENGAGEMENT_TABLE_NAME, sortColumns);
    }

    private void assertVoiceEngagementDisengage(Map expectedData) throws Exception {
        String[] sortColumns = new String[]{"voice_engagement_number"};
        DatabaseAssert databaseAssert = new DatabaseAssert(dbUnitTester.getConnection());
        if (expectedData.containsKey(VOICE_ENGAGEMENT_DISENGAGE_TABLE_NAME)) {
            databaseAssert.assertTableWithAllColumns(expectedData, VOICE_ENGAGEMENT_DISENGAGE_TABLE_NAME, sortColumns);
        } else {
            databaseAssert.assertTableNoCount(FixtureVoiceEngagementDisengageState.Nothing.getDefaultData(), VOICE_ENGAGEMENT_DISENGAGE_TABLE_NAME);
        }
    }

    private void assertVoiceEngagementCancel(Map expectedData) throws Exception {
        String[] sortColumns = new String[]{"voice_engagement_number"};
        DatabaseAssert databaseAssert = new DatabaseAssert(dbUnitTester.getConnection());
        if (expectedData.containsKey(VOICE_ENGAGEMENT_CANCEL_TABLE_NAME)) {
            databaseAssert.assertTableWithAllColumns(expectedData, VOICE_ENGAGEMENT_CANCEL_TABLE_NAME, sortColumns);
        } else {
            databaseAssert.assertTableNoCount(FixtureVoiceEngagementCancelState.Nothing.getDefaultData(), VOICE_ENGAGEMENT_CANCEL_TABLE_NAME);
        }
    }

    public void assertTableNoCount() throws Exception {
        DatabaseAssert databaseAssert = new DatabaseAssert(dbUnitTester.getConnection());
        databaseAssert.assertTableNoCount(FixtureVoiceEngagementState.Nothing.getDefaultData(), VOICE_ENGAGEMENT_TABLE_NAME);
    }


}
