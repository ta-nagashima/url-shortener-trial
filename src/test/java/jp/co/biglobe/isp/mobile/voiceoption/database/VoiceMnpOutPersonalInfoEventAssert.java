package jp.co.biglobe.isp.mobile.voiceoption.database;

import jp.co.biglobe.test.util.dbunit.DbUnitTester;
import jp.co.biglobe.test.util.dbunit.assertion.DatabaseAssert;

import java.util.Map;


public class VoiceMnpOutPersonalInfoEventAssert {

    private static final String TABLE_NAME = "voice_mnp_out_personal_info_e";

    private DbUnitTester dbUnitTester;

    public VoiceMnpOutPersonalInfoEventAssert(DbUnitTester dbUnitTester){
        this.dbUnitTester = dbUnitTester;
    }

    public void assertTableWithAllColumns(Map expectedData) throws Exception{

        String[] sortColumns = new String[]{"event_id"};
        DatabaseAssert databaseAssert = new DatabaseAssert(dbUnitTester.getConnection());
        databaseAssert.assertTableWithAllColumns(expectedData, TABLE_NAME, sortColumns);

    }

}
