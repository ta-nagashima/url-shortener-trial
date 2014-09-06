package jp.co.biglobe.isp.mobile.voiceoption.database;

import jp.co.biglobe.test.util.dbunit.DbUnitTester;
import jp.co.biglobe.test.util.dbunit.assertion.DatabaseAssert;

import java.util.Map;


public class VoiceMnpOutCompleteStateAssert {

    private static final String TABLE_NAME = "voice_mnp_out_complete_state";

    private DbUnitTester dbUnitTester;

    public VoiceMnpOutCompleteStateAssert(DbUnitTester dbUnitTester){
        this.dbUnitTester = dbUnitTester;
    }

    public void assertTableWithAllColumns(Map expectedData) throws Exception{

        String[] sortColumns = new String[]{"voice_mnp_out_id"};
        DatabaseAssert databaseAssert = new DatabaseAssert(dbUnitTester.getConnection());
        databaseAssert.assertTableWithAllColumns(expectedData, TABLE_NAME, sortColumns);

    }
}
