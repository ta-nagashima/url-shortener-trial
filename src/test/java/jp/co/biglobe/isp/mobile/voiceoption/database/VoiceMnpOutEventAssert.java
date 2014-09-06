package jp.co.biglobe.isp.mobile.voiceoption.database;

import jp.co.biglobe.isp.mobile.voiceoption.datasource.mnpout.db.FixtureVoiceMnpOutCompleteEvent;
import jp.co.biglobe.isp.mobile.voiceoption.datasource.mnpout.db.FixtureVoiceMnpOutEvent;
import jp.co.biglobe.isp.mobile.voiceoption.datasource.mnpout.db.FixtureVoiceMnpOutNumberEvent;
import jp.co.biglobe.isp.mobile.voiceoption.datasource.mnpout.db.FixtureVoiceMnpOutPersonalInfoEvent;
import jp.co.biglobe.test.util.dbunit.DbUnitTester;
import jp.co.biglobe.test.util.dbunit.assertion.DatabaseAssert;

import java.util.Map;

public class VoiceMnpOutEventAssert {

    private static final String VOICE_MNP_OUT_EVENT_TABLE_NAME = "voice_mnp_out_event";
    private static final String VOICE_MNP_OUT_PERSONAL_INFO_E_TABLE_NAME = "voice_mnp_out_personal_info_e";
    private static final String VOICE_MNP_OUT_NUMBER_EVENT_TABLE_NAME = "voice_mnp_out_number_event";
    private static final String VOICE_MNP_OUT_COMPLETE_EVENT_TABLE_NAME = "voice_mnp_out_complete_event";

    private DbUnitTester dbUnitTester;

    public VoiceMnpOutEventAssert(DbUnitTester dbUnitTester) {
        this.dbUnitTester = dbUnitTester;
    }


    public void assertTableWithAllColumns(Map expectedData) throws Exception {

        assertVoiceMnpOutEvent(expectedData);
        assertVoiceMnpOutPersonalInfoEvent(expectedData);
        assertVoiceMnpOutNumberEvent(expectedData);
        assertVoiceMnpOutCompleteEvent(expectedData);
    }


    private void assertVoiceMnpOutEvent(Map expectedData) throws Exception {
        String[] sortColumns = new String[]{"event_id"};
        DatabaseAssert databaseAssert = new DatabaseAssert(dbUnitTester.getConnection());
        if (expectedData.containsKey(VOICE_MNP_OUT_EVENT_TABLE_NAME)) {
            databaseAssert.assertTableWithAllColumns(expectedData, VOICE_MNP_OUT_EVENT_TABLE_NAME, sortColumns);
        } else {
            databaseAssert.assertTableNoCount(FixtureVoiceMnpOutEvent.Nothing.getDefaultData(), VOICE_MNP_OUT_EVENT_TABLE_NAME);
        }
    }

    private void assertVoiceMnpOutPersonalInfoEvent(Map expectedData) throws Exception {
        String[] sortColumns = new String[]{"event_id"};

        DatabaseAssert databaseAssert = new DatabaseAssert(dbUnitTester.getConnection());
        if (expectedData.containsKey(VOICE_MNP_OUT_PERSONAL_INFO_E_TABLE_NAME)) {
            databaseAssert.assertTableWithAllColumns(expectedData, VOICE_MNP_OUT_PERSONAL_INFO_E_TABLE_NAME, sortColumns);
        } else {
            databaseAssert.assertTableNoCount(FixtureVoiceMnpOutPersonalInfoEvent.Nothing.getDefaultData(), VOICE_MNP_OUT_PERSONAL_INFO_E_TABLE_NAME);
        }
    }

    private void assertVoiceMnpOutNumberEvent(Map expectedData) throws Exception {
        String[] sortColumns = new String[]{"event_id"};
        DatabaseAssert databaseAssert = new DatabaseAssert(dbUnitTester.getConnection());
        if (expectedData.containsKey(VOICE_MNP_OUT_NUMBER_EVENT_TABLE_NAME)) {
            databaseAssert.assertTableWithAllColumns(expectedData, VOICE_MNP_OUT_NUMBER_EVENT_TABLE_NAME, sortColumns);
        } else {
            databaseAssert.assertTableNoCount(FixtureVoiceMnpOutNumberEvent.Nothing.getDefaultData(), VOICE_MNP_OUT_NUMBER_EVENT_TABLE_NAME);
        }
    }

    private void assertVoiceMnpOutCompleteEvent(Map expectedData) throws Exception {
        String[] sortColumns = new String[]{"event_id"};
        DatabaseAssert databaseAssert = new DatabaseAssert(dbUnitTester.getConnection());
        if (expectedData.containsKey(VOICE_MNP_OUT_COMPLETE_EVENT_TABLE_NAME)) {
            databaseAssert.assertTableWithAllColumns(expectedData, VOICE_MNP_OUT_COMPLETE_EVENT_TABLE_NAME, sortColumns);
        } else {
            databaseAssert.assertTableNoCount(FixtureVoiceMnpOutCompleteEvent.Nothing.getDefaultData(), VOICE_MNP_OUT_COMPLETE_EVENT_TABLE_NAME);
        }
    }

}
