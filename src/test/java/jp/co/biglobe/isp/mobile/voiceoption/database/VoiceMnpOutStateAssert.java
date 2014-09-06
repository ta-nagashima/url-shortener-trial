package jp.co.biglobe.isp.mobile.voiceoption.database;

import jp.co.biglobe.isp.mobile.voiceoption.datasource.mnpout.db.FixtureVoiceMnpOutCompleteState;
import jp.co.biglobe.isp.mobile.voiceoption.datasource.mnpout.db.FixtureVoiceMnpOutNumberState;
import jp.co.biglobe.test.util.dbunit.DbUnitTester;
import jp.co.biglobe.test.util.dbunit.assertion.DatabaseAssert;

import java.util.Map;


public class VoiceMnpOutStateAssert {

    private static final String VOICE_MNP_OUT_STATE_TABLE_NAME = "voice_mnp_out_state";
    private static final String VOICE_MNP_OUT_PERSONAL_INFO_S_TABLE_NAME = "voice_mnp_out_personal_info_s";
    private static final String VOICE_MNP_OUT_NUMBER_STATE_TABLE_NAME = "voice_mnp_out_number_state";
    private static final String VOICE_MNP_OUT_COMPLETE_STATE_TABLE_NAME = "voice_mnp_out_complete_state";

    private DbUnitTester dbUnitTester;

    public VoiceMnpOutStateAssert(DbUnitTester dbUnitTester){
        this.dbUnitTester = dbUnitTester;
    }


    public void assertTableWithAllColumns(Map expectedData) throws Exception{

        assertVoiceMnpOut(expectedData);
        assertVoiceMnpOutPersonalInfo(expectedData);
        assertVoiceMnpOutComplete(expectedData);
        assertVoiceMnpOutNumber(expectedData);
    }

    private void assertVoiceMnpOut(Map expectedData) throws Exception{
        String[] sortColumns = new String[]{"voice_mnp_out_id"};
        DatabaseAssert databaseAssert = new DatabaseAssert(dbUnitTester.getConnection());
        databaseAssert.assertTableWithAllColumns(expectedData, VOICE_MNP_OUT_STATE_TABLE_NAME, sortColumns);
    }

    private void assertVoiceMnpOutPersonalInfo(Map expectedData) throws Exception{
        String[] sortColumns = new String[]{"voice_mnp_out_id"};
        DatabaseAssert databaseAssert = new DatabaseAssert(dbUnitTester.getConnection());
        databaseAssert.assertTableWithAllColumns(expectedData, VOICE_MNP_OUT_PERSONAL_INFO_S_TABLE_NAME, sortColumns);
    }

    private void assertVoiceMnpOutComplete(Map expectedData) throws Exception{
        String[] sortColumns = new String[]{"voice_mnp_out_id"};
        DatabaseAssert databaseAssert = new DatabaseAssert(dbUnitTester.getConnection());
        if(expectedData.containsKey(VOICE_MNP_OUT_NUMBER_STATE_TABLE_NAME)) {
            databaseAssert.assertTableWithAllColumns(expectedData, VOICE_MNP_OUT_NUMBER_STATE_TABLE_NAME, sortColumns);
        }else{
            databaseAssert.assertTableNoCount(FixtureVoiceMnpOutNumberState.Nothing.getDefaultData(), VOICE_MNP_OUT_NUMBER_STATE_TABLE_NAME);
        }
    }

    private void assertVoiceMnpOutNumber(Map expectedData) throws Exception{
        String[] sortColumns = new String[]{"voice_mnp_out_id"};
        DatabaseAssert databaseAssert = new DatabaseAssert(dbUnitTester.getConnection());
        if(expectedData.containsKey(VOICE_MNP_OUT_COMPLETE_STATE_TABLE_NAME)) {
            databaseAssert.assertTableWithAllColumns(expectedData, VOICE_MNP_OUT_COMPLETE_STATE_TABLE_NAME, sortColumns);
        }else{
            databaseAssert.assertTableNoCount(FixtureVoiceMnpOutCompleteState.Nothing.getDefaultData(), VOICE_MNP_OUT_COMPLETE_STATE_TABLE_NAME);
        }
    }

}
