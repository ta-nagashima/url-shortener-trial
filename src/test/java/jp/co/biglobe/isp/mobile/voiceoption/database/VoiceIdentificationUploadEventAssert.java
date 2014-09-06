package jp.co.biglobe.isp.mobile.voiceoption.database;

import jp.co.biglobe.test.util.dbunit.DbUnitTester;
import jp.co.biglobe.test.util.dbunit.assertion.DatabaseAssert;

import java.util.Map;

public class VoiceIdentificationUploadEventAssert {

    private static final String TABLE_NAME = "voice_identification_upload_e";

    private DbUnitTester dbUnitTester;

    public VoiceIdentificationUploadEventAssert(DbUnitTester dbUnitTester){
        this.dbUnitTester = dbUnitTester;
    }

    public void assertTableWithAllColumns(Map expectedData) throws Exception{

        String[] sortColumns = new String[]{"identification_receipt_number"};
        DatabaseAssert databaseAssert = new DatabaseAssert(dbUnitTester.getConnection());
        databaseAssert.assertTableWithAllColumns(expectedData, TABLE_NAME, sortColumns);

    }

}
