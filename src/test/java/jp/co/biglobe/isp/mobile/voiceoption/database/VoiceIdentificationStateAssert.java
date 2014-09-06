package jp.co.biglobe.isp.mobile.voiceoption.database;

import jp.co.biglobe.isp.mobile.voiceoption.datasource.identification.db.FixtureVoiceIdentificationState;
import jp.co.biglobe.isp.mobile.voiceoption.datasource.identification.upload.db.FixtureVoiceIdentificationUploadS;
import jp.co.biglobe.isp.mobile.voiceoption.datasource.identificationresult.db.FixtureVoiceIdentificationNgState;
import jp.co.biglobe.isp.mobile.voiceoption.datasource.identificationresult.db.FixtureVoiceIdentificationResultEvent;
import jp.co.biglobe.isp.mobile.voiceoption.datasource.identificationresult.db.FixtureVoiceIdentificationResultState;
import jp.co.biglobe.isp.mobile.voiceoption.datasource.mnpin.db.FixtureVoiceMnpInPersonalInfoEvent;
import jp.co.biglobe.isp.mobile.voiceoption.datasource.mnpin.db.FixtureVoiceMnpInPersonalInfoState;
import jp.co.biglobe.test.util.dbunit.DbUnitTester;
import jp.co.biglobe.test.util.dbunit.assertion.DatabaseAssert;

import java.util.Map;


public class VoiceIdentificationStateAssert {

    private static final String VOICE_IDENTIFICATION_TABLE_NAME = "voice_identification_state";
    private static final String VOICE_IDENTIFICATION_RESULT_TABLE_NAME = "voice_identification_result_s";
    private static final String VOICE_IDENTIFICATION_NG_TABLE_NAME = "voice_identification_ng_s";
    private static final String VOICE_IDENTIFICATION_UPLOAD_TABLE_NAME = "voice_identification_upload_s";


    private DbUnitTester dbUnitTester;

    public VoiceIdentificationStateAssert(DbUnitTester dbUnitTester){
        this.dbUnitTester = dbUnitTester;
    }

    public void assertTableWithExcludeColumns(Map expectedData) throws Exception{

        String[] sortColumns = new String[]{"identification_receipt_number"};
        String[] excludeColumns = {"first_upload_date_time"};
        DatabaseAssert databaseAssert = new DatabaseAssert(dbUnitTester.getConnection());
        databaseAssert.assertTableWithExcludeColumns(expectedData, VOICE_IDENTIFICATION_TABLE_NAME, sortColumns, excludeColumns);

    }

    /**
     * 互換性のために残した。いつか消す。
     */
    public void assertTableWithAllColumns(Map expectedData) throws Exception{
        assertIdentificationState(expectedData);
    }



    public void assertAllTableWithAllColumns(Map expectedData) throws Exception{
        assertIdentificationState(expectedData);
        assertIdentificationResultState(expectedData);
        assertIdentificationNgState(expectedData);
        assertIdentificationUploadState(expectedData);
    }

    public void assertIdentificationState(Map expectedData) throws Exception{

        String[] sortColumns = new String[]{"identification_receipt_number"};
        DatabaseAssert databaseAssert = new DatabaseAssert(dbUnitTester.getConnection());

        if(expectedData.containsKey(VOICE_IDENTIFICATION_TABLE_NAME)){
            databaseAssert.assertTableWithAllColumns(
                    expectedData,
                    VOICE_IDENTIFICATION_TABLE_NAME,
                    sortColumns);

        }else {
            databaseAssert.assertTableNoCount(
                    FixtureVoiceMnpInPersonalInfoState.Nothing.getDefaultData(),
                    VOICE_IDENTIFICATION_TABLE_NAME);
        }
    }

    public void assertIdentificationResultState(Map expectedData) throws Exception{

        String[] sortColumns = new String[]{"identification_result_id"};
        DatabaseAssert databaseAssert = new DatabaseAssert(dbUnitTester.getConnection());


        if(expectedData.containsKey(VOICE_IDENTIFICATION_RESULT_TABLE_NAME)) {
            databaseAssert.assertTableWithAllColumns(
                    expectedData,
                    VOICE_IDENTIFICATION_RESULT_TABLE_NAME,
                    sortColumns);
        }else {
            databaseAssert.assertTableNoCount(
                    FixtureVoiceIdentificationResultState.Nothing.getDefaultData(),
                    VOICE_IDENTIFICATION_RESULT_TABLE_NAME);
        }

    }

    public void assertIdentificationNgState(Map expectedData) throws Exception{

        String[] sortColumns = new String[]{"identification_result_id"};
        DatabaseAssert databaseAssert = new DatabaseAssert(dbUnitTester.getConnection());


        if(expectedData.containsKey(VOICE_IDENTIFICATION_NG_TABLE_NAME)) {
            databaseAssert.assertTableWithAllColumns(
                    expectedData,
                    VOICE_IDENTIFICATION_NG_TABLE_NAME,
                    sortColumns);
        }else {
            databaseAssert.assertTableNoCount(
                    FixtureVoiceIdentificationNgState.Nothing.getDefaultData(),
                    VOICE_IDENTIFICATION_NG_TABLE_NAME);
        }

    }

    public void assertIdentificationUploadState(Map expectedData) throws Exception{

        String[] sortColumns = new String[]{"identification_receipt_number"};
        DatabaseAssert databaseAssert = new DatabaseAssert(dbUnitTester.getConnection());


        if(expectedData.containsKey(VOICE_IDENTIFICATION_UPLOAD_TABLE_NAME)) {
            databaseAssert.assertTableWithAllColumns(
                    expectedData,
                    VOICE_IDENTIFICATION_UPLOAD_TABLE_NAME,
                    sortColumns);
        }else {
            databaseAssert.assertTableNoCount(
                    FixtureVoiceIdentificationUploadS.Nothing.getDefaultData(),
                    VOICE_IDENTIFICATION_UPLOAD_TABLE_NAME);
        }

    }

    public void assertTableNoCount() throws Exception{
        DatabaseAssert databaseAssert = new DatabaseAssert(dbUnitTester.getConnection());
        databaseAssert.assertTableNoCount(FixtureVoiceIdentificationState.Nothing.getDefaultData(), VOICE_IDENTIFICATION_TABLE_NAME);
    }

}
