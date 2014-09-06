package jp.co.biglobe.isp.mobile.voiceoption.database;

import jp.co.biglobe.isp.mobile.voiceoption.datasource.voiceengagement.db.FixtureVoiceEngagementCancelEvent;
import jp.co.biglobe.isp.mobile.voiceoption.datasource.voiceengagement.db.FixtureVoiceEngagementDisengageEvent;
import jp.co.biglobe.test.util.dbunit.DbUnitTester;
import jp.co.biglobe.test.util.dbunit.assertion.DatabaseAssert;

import java.util.Map;


public class VoiceEngagementEventAssert {

    private static final String VOICE_ENGAGEMENT_TABLE_NAME = "voice_engagement_event";
    private static final String VOICE_ENGAGEMENT_DISENGAGE_TABLE_NAME = "voice_engagement_disengage_e";
    private static final String VOICE_ENGAGEMENT_CANCEL_TABLE_NAME = "voice_engagement_cancel_event";

    private DbUnitTester dbUnitTester;

    public VoiceEngagementEventAssert(DbUnitTester dbUnitTester) {
        this.dbUnitTester = dbUnitTester;
    }

    public void assertTableWithAllColumns(Map expectedData) throws Exception{

        assertVoiceEngagement(expectedData);
        assertVoiceEngagementDisengage(expectedData);
        assertVoiceEngagementCancel(expectedData);

    }

    private void assertVoiceEngagement(Map expectedData) throws Exception{
        String[] sortColumns = new String[]{"event_id"};
        DatabaseAssert databaseAssert = new DatabaseAssert(dbUnitTester.getConnection());
        databaseAssert.assertTableWithAllColumns(expectedData, VOICE_ENGAGEMENT_TABLE_NAME, sortColumns);
    }

    private void assertVoiceEngagementDisengage(Map expectedData) throws Exception{
        String[] sortColumns = new String[]{"event_id"};

        DatabaseAssert databaseAssert = new DatabaseAssert(dbUnitTester.getConnection());
        if(expectedData.containsKey(VOICE_ENGAGEMENT_DISENGAGE_TABLE_NAME)){
            databaseAssert.assertTableWithAllColumns(expectedData, VOICE_ENGAGEMENT_DISENGAGE_TABLE_NAME, sortColumns);
        }else{
            databaseAssert.assertTableNoCount(FixtureVoiceEngagementDisengageEvent.Nothing.getDefaultData(), VOICE_ENGAGEMENT_DISENGAGE_TABLE_NAME);
        }
    }

    private void assertVoiceEngagementCancel(Map expectedData) throws Exception{
        String[] sortColumns = new String[]{"event_id"};
        DatabaseAssert databaseAssert = new DatabaseAssert(dbUnitTester.getConnection());
        if(expectedData.containsKey(VOICE_ENGAGEMENT_CANCEL_TABLE_NAME)){
            databaseAssert.assertTableWithAllColumns(expectedData, VOICE_ENGAGEMENT_CANCEL_TABLE_NAME, sortColumns);
        }else{
            databaseAssert.assertTableNoCount(FixtureVoiceEngagementCancelEvent.Nothing.getDefaultData(), VOICE_ENGAGEMENT_CANCEL_TABLE_NAME);
        }
    }

}
