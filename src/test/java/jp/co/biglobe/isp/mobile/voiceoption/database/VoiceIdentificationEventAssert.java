package jp.co.biglobe.isp.mobile.voiceoption.database;

import jp.co.biglobe.isp.mobile.voiceoption.datasource.identification.db.FixtureVoiceIdentificationEvent;
import jp.co.biglobe.isp.mobile.voiceoption.datasource.identification.db.FixtureVoiceIdentificationState;
import jp.co.biglobe.isp.mobile.voiceoption.datasource.identification.upload.db.FixtureVoiceIdentificationUploadE;
import jp.co.biglobe.isp.mobile.voiceoption.datasource.identificationresult.db.FixtureVoiceIdentificationResultEvent;
import jp.co.biglobe.isp.mobile.voiceoption.datasource.identificationresult.db.FixtureVoiceIdentificationResultNgEvent;
import jp.co.biglobe.test.util.dbunit.DbUnitTester;
import jp.co.biglobe.test.util.dbunit.assertion.DatabaseAssert;

import java.util.Map;


public class VoiceIdentificationEventAssert {

    private static final String VOICE_IDENTIFICATION_TABLE_NAME = "voice_identification_event";
    private static final String VOICE_IDENTIFICATION_UPLOAD_TABLE_NAME = "voice_identification_upload_e";
    private static final String VOICE_IDENTIFICATION_RESULT_TABLE_NAME = "voice_identification_result_e";
    private static final String VOICE_IDENTIFICATION_NG_REASON_TABLE_NAME = "voice_identification_ng_e";


    private static final String[] SORT_COLUMNS = new String[]{"event_id"};


    private DbUnitTester dbUnitTester;

    public VoiceIdentificationEventAssert(DbUnitTester dbUnitTester) {
        this.dbUnitTester = dbUnitTester;
    }

    public void assertTableWithExcludeColumns(Map expectedData) throws Exception {

        String[] sortColumns = new String[]{"event_id"};
        String[] excludeColumns = {"first_upload_date_time"};
        DatabaseAssert databaseAssert = new DatabaseAssert(dbUnitTester.getConnection());
        databaseAssert.assertTableWithExcludeColumns(expectedData, VOICE_IDENTIFICATION_TABLE_NAME, sortColumns, excludeColumns);

    }

    /**
     * 互換性のために残した。いつか消す。
     */
    public void assertTableWithAllColumns(Map expectedData) throws Exception {
        assertIdentificationEvent(expectedData);

    }


    public void assertAllTableWithAllColumns(Map expectedData) throws Exception {
        assertIdentificationEvent(expectedData);
        assertIdentificationResultEvent(expectedData);
        assertNgReasonEvent(expectedData);
        assertIdentificationUploadEvent(expectedData);

    }

    public void assertIdentificationEvent(Map expectedData) throws Exception {

        DatabaseAssert databaseAssert = new DatabaseAssert(dbUnitTester.getConnection());

        if (expectedData.containsKey(VOICE_IDENTIFICATION_TABLE_NAME)) {
            databaseAssert.assertTableWithAllColumns(
                    expectedData,
                    VOICE_IDENTIFICATION_TABLE_NAME,
                    SORT_COLUMNS);

        } else {
            databaseAssert.assertTableNoCount(
                    FixtureVoiceIdentificationEvent.Nothing.getDefaultData(),
                    VOICE_IDENTIFICATION_TABLE_NAME);
        }
    }

    public void assertIdentificationUploadEvent(Map expectedData) throws Exception {

        DatabaseAssert databaseAssert = new DatabaseAssert(dbUnitTester.getConnection());


        if (expectedData.containsKey(VOICE_IDENTIFICATION_UPLOAD_TABLE_NAME)) {
            databaseAssert.assertTableWithAllColumns(
                    expectedData,
                    VOICE_IDENTIFICATION_UPLOAD_TABLE_NAME,
                    SORT_COLUMNS);
        } else {
            databaseAssert.assertTableNoCount(
                    FixtureVoiceIdentificationUploadE.Nothing.getDefaultData(),
                    VOICE_IDENTIFICATION_UPLOAD_TABLE_NAME);
        }

    }


    public void assertIdentificationResultEvent(Map expectedData) throws Exception {

        DatabaseAssert databaseAssert = new DatabaseAssert(dbUnitTester.getConnection());


        if (expectedData.containsKey(VOICE_IDENTIFICATION_RESULT_TABLE_NAME)) {
            databaseAssert.assertTableWithAllColumns(
                    expectedData,
                    VOICE_IDENTIFICATION_RESULT_TABLE_NAME,
                    SORT_COLUMNS);
        } else {
            databaseAssert.assertTableNoCount(
                    FixtureVoiceIdentificationResultEvent.Nothing.getDefaultData(),
                    VOICE_IDENTIFICATION_RESULT_TABLE_NAME);
        }

    }

    public void assertNgReasonEvent(Map expectedData) throws Exception {

        DatabaseAssert databaseAssert = new DatabaseAssert(dbUnitTester.getConnection());


        if (expectedData.containsKey(VOICE_IDENTIFICATION_NG_REASON_TABLE_NAME)) {
            databaseAssert.assertTableWithAllColumns(
                    expectedData,
                    VOICE_IDENTIFICATION_NG_REASON_TABLE_NAME,
                    SORT_COLUMNS);
        } else {
            databaseAssert.assertTableNoCount(
                    FixtureVoiceIdentificationResultNgEvent.Nothing.getDefaultData(),
                    VOICE_IDENTIFICATION_NG_REASON_TABLE_NAME);
        }

    }

    public void assertTableNoCount() throws Exception{
        DatabaseAssert databaseAssert = new DatabaseAssert(dbUnitTester.getConnection());
        databaseAssert.assertTableNoCount(FixtureVoiceIdentificationEvent.Nothing.getDefaultData(), VOICE_IDENTIFICATION_TABLE_NAME);
    }

}
