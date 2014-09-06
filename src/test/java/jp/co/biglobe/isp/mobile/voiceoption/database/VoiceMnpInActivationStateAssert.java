package jp.co.biglobe.isp.mobile.voiceoption.database;

import jp.co.biglobe.isp.mobile.voiceoption.datasource.mnpin.db.FixtureVoiceMnpInActivationState;
import jp.co.biglobe.test.util.dbunit.DbUnitTester;
import jp.co.biglobe.test.util.dbunit.assertion.DatabaseAssert;

import java.util.Map;


public class VoiceMnpInActivationStateAssert {

    private static final String TABLE_NAME = "voice_mnp_in_activation_state";

    private DbUnitTester dbUnitTester;

    public VoiceMnpInActivationStateAssert(DbUnitTester dbUnitTester) {
        this.dbUnitTester = dbUnitTester;
    }

    public void assertTableWithAllColumns(Map expectedData) throws Exception {

        String[] sortColumns = new String[]{"voice_engagement_number"};
        DatabaseAssert databaseAssert = new DatabaseAssert(dbUnitTester.getConnection());
        databaseAssert.assertTableWithAllColumns(expectedData, TABLE_NAME, sortColumns);

    }

    public void assertTableNoCount() throws Exception {
        DatabaseAssert databaseAssert = new DatabaseAssert(dbUnitTester.getConnection());
        databaseAssert.assertTableNoCount(FixtureVoiceMnpInActivationState.Nothing.getDefaultData(), TABLE_NAME);
    }
}
