package jp.co.biglobe.isp.mobile.voiceoption.database;

import jp.co.biglobe.isp.mobile.voiceoption.datasource.mnpin.db.FixtureVoiceMnpInActivationState;
import jp.co.biglobe.isp.mobile.voiceoption.datasource.mnpin.db.FixtureVoiceMnpInPersonalInfoState;
import jp.co.biglobe.isp.mobile.voiceoption.datasource.mnpin.db.FixtureVoiceMnpInState;
import jp.co.biglobe.test.util.dbunit.DbUnitTester;
import jp.co.biglobe.test.util.dbunit.assertion.DatabaseAssert;

import java.util.Map;


public class VoiceMnpInStateAssert {

    private static final String MNP_IN_TABLE_NAME = "voice_mnp_in_state";
    private static final String MNP_IN_PERSONAL_TABLE_NAME = "voice_mnp_in_personal_info_s";
    private static final String MNP_IN_ACTIVATION_TABLE = "voice_mnp_in_activation_state";

    private DbUnitTester dbUnitTester;

    public VoiceMnpInStateAssert(DbUnitTester dbUnitTester) {
        this.dbUnitTester = dbUnitTester;
    }

    public void assertTableWithAllColumns(Map expectedData) throws Exception {
        assertMnpInState(expectedData);
        assertMnpPersonalState(expectedData);
        assertActivateState(expectedData);
    }

    private void assertMnpInState(Map expectedData) throws Exception {
        String[] sortColumns = new String[]{"voice_engagement_number"};
        DatabaseAssert databaseAssert = new DatabaseAssert(dbUnitTester.getConnection());
//        databaseAssert.assertTableWithAllColumns(expectedData, MNP_IN_TABLE_NAME, sortColumns);
        if (expectedData.containsKey(MNP_IN_TABLE_NAME)) {
            databaseAssert.assertTableWithAllColumns(expectedData, MNP_IN_TABLE_NAME, sortColumns);
        } else {
            databaseAssert.assertTableNoCount(FixtureVoiceMnpInState.Nothing.getDefaultData(), MNP_IN_TABLE_NAME);
        }
    }

    private void assertMnpPersonalState(Map expectedData) throws Exception {
        String[] sortColumns = new String[]{"voice_engagement_number"};
        DatabaseAssert databaseAssert = new DatabaseAssert(dbUnitTester.getConnection());

        if (expectedData.containsKey(MNP_IN_PERSONAL_TABLE_NAME)) {
            databaseAssert.assertTableWithAllColumns(expectedData, MNP_IN_PERSONAL_TABLE_NAME, sortColumns);
        } else {
            databaseAssert.assertTableNoCount(FixtureVoiceMnpInPersonalInfoState.Nothing.getDefaultData(), MNP_IN_PERSONAL_TABLE_NAME);
        }
    }

    private void assertActivateState(Map expectedData) throws Exception {
        String[] sortColumns = new String[]{"voice_engagement_number"};
        DatabaseAssert databaseAssert = new DatabaseAssert(dbUnitTester.getConnection());

        if (expectedData.containsKey(MNP_IN_ACTIVATION_TABLE)) {
            databaseAssert.assertTableWithAllColumns(expectedData, MNP_IN_ACTIVATION_TABLE, sortColumns);
        } else {
            databaseAssert.assertTableNoCount(FixtureVoiceMnpInActivationState.Nothing.getDefaultData(), MNP_IN_ACTIVATION_TABLE);
        }
    }

    public void assertTableNoCount() throws Exception {
        DatabaseAssert databaseAssert = new DatabaseAssert(dbUnitTester.getConnection());
        databaseAssert.assertTableNoCount(FixtureVoiceMnpInState.Nothing.getDefaultData(), MNP_IN_TABLE_NAME);

        DatabaseAssert databaseAssert2 = new DatabaseAssert(dbUnitTester.getConnection());
        databaseAssert2.assertTableNoCount(FixtureVoiceMnpInPersonalInfoState.Nothing.getDefaultData(), MNP_IN_PERSONAL_TABLE_NAME);
    }

}
