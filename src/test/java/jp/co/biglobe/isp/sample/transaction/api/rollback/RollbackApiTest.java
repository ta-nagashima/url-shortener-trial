package jp.co.biglobe.isp.sample.transaction.api.rollback;

import jp.co.biglobe.isp.sample.user.fixture.FixtureSampleUser;
import jp.co.biglobe.test.util.dbunit.DbUnitTester;
import jp.co.biglobe.test.util.dbunit.assertion.DatabaseAssert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import java.util.Map;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = {"classpath:context.xml"})
public class RollbackApiTest {

    private static final String URI = "/sample/rollback";

    @Autowired
    public DbUnitTester tester;

    @Autowired
    private WebApplicationContext wac;
    private MockMvc mockMvc;

    @Before
    public void setup() throws Exception {
        mockMvc = webAppContextSetup(wac).build();

        // 処理前にテーブルをクリアする
        tester.executeAllClearTableAndSeq();

    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void _invoke() throws Exception {
        // テストデータの準備
        tester.cleanInsertQuery(FixtureSampleUser.One.getDefaultData());

        // 実行
        mockMvc.perform(post(URI));

        // DBが更新されてないことを確認
        SampleUserAssert sampleUserAssert = new SampleUserAssert(tester);
        sampleUserAssert.assertTableWithAllColumns(FixtureSampleUser.One.getDefaultData());
    }

    public class SampleUserAssert {

        private static final String TABLE_NAME = "sample_user";

        private DbUnitTester dbUnitTester;

        public SampleUserAssert(DbUnitTester dbUnitTester) {
            this.dbUnitTester = dbUnitTester;
        }


        public void assertTableWithAllColumns(Map expectedData) throws Exception {
            String[] sortColumns = new String[]{"sample_user_id"};
            DatabaseAssert databaseAssert = new DatabaseAssert(dbUnitTester.getConnection());
            databaseAssert.assertTableWithAllColumns(expectedData, TABLE_NAME, sortColumns);
        }
    }

}
