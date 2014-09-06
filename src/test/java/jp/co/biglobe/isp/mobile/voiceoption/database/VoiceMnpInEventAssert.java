package jp.co.biglobe.isp.mobile.voiceoption.database;

import jp.co.biglobe.isp.mobile.voiceoption.datasource.mnpin.db.FixtureVoiceMnpInEvent;
import jp.co.biglobe.isp.mobile.voiceoption.datasource.mnpin.db.FixtureVoiceMnpInPersonalInfoEvent;
import jp.co.biglobe.isp.mobile.voiceoption.datasource.mnpin.db.FixtureVoiceMnpInActivationEvent;
import jp.co.biglobe.test.util.dbunit.DbUnitTester;
import jp.co.biglobe.test.util.dbunit.assertion.DatabaseAssert;

import java.util.Map;


public class VoiceMnpInEventAssert {

    private static final String MNP_IN_TABLE_NAME = "voice_mnp_in_event";
    private static final String MNP_IN_PERSONAL_TABLE_NAME = "voice_mnp_in_personal_info_e";
    private static final String MNP_IN_ACTIVATION_TABLE_NAME = "voice_mnp_in_activation_event";

    private DbUnitTester dbUnitTester;

    public VoiceMnpInEventAssert(DbUnitTester dbUnitTester){
        this.dbUnitTester = dbUnitTester;
    }


    public void assertTableWithAllColumns(Map expectedData) throws Exception{
        assertMnpInEvent(expectedData);
        assertMnpInPersonalEvent(expectedData);
        assertMnpInActivationEvent(expectedData);

    }

    private void assertMnpInEvent(Map expectedData)throws Exception {
        String[] sortColumns = new String[]{"event_id"};
        DatabaseAssert databaseAssert = new DatabaseAssert(dbUnitTester.getConnection());

        if(expectedData.containsKey(MNP_IN_TABLE_NAME)){
            databaseAssert.assertTableWithAllColumns(expectedData, MNP_IN_TABLE_NAME, sortColumns);
        }else{
            databaseAssert.assertTableNoCount(FixtureVoiceMnpInEvent.Nothing.getDefaultData(), MNP_IN_TABLE_NAME);
        }
    }

    private void assertMnpInPersonalEvent(Map expectedData)throws Exception {
        String[] sortColumns = new String[]{"event_id"};
        DatabaseAssert databaseAssert = new DatabaseAssert(dbUnitTester.getConnection());

        if(expectedData.containsKey(MNP_IN_PERSONAL_TABLE_NAME)){
            databaseAssert.assertTableWithAllColumns(expectedData, MNP_IN_PERSONAL_TABLE_NAME, sortColumns);
        }else{
            databaseAssert.assertTableNoCount(FixtureVoiceMnpInPersonalInfoEvent.Nothing.getDefaultData(), MNP_IN_PERSONAL_TABLE_NAME);
        }

    }

    private void assertMnpInActivationEvent(Map expectedData)throws Exception {
        String[] sortColumns = new String[]{"event_id"};
        DatabaseAssert databaseAssert = new DatabaseAssert(dbUnitTester.getConnection());

        if(expectedData.containsKey(MNP_IN_ACTIVATION_TABLE_NAME)){
            databaseAssert.assertTableWithAllColumns(expectedData, MNP_IN_ACTIVATION_TABLE_NAME, sortColumns);
        }else{
            databaseAssert.assertTableNoCount(FixtureVoiceMnpInActivationEvent.Nothing.getDefaultData(), MNP_IN_ACTIVATION_TABLE_NAME);
        }

    }





    public void assertTableNoCount()throws Exception {
        DatabaseAssert databaseAssert1 = new DatabaseAssert(dbUnitTester.getConnection());
        databaseAssert1.assertTableNoCount(FixtureVoiceMnpInEvent.Nothing.getDefaultData(),MNP_IN_TABLE_NAME);

        DatabaseAssert databaseAssert2 = new DatabaseAssert(dbUnitTester.getConnection());
        databaseAssert2.assertTableNoCount(FixtureVoiceMnpInPersonalInfoEvent.Nothing.getDefaultData(),MNP_IN_PERSONAL_TABLE_NAME);
    }
}
