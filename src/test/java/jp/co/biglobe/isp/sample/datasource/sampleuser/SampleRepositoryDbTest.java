package jp.co.biglobe.isp.sample.datasource.sampleuser;

import jp.co.biglobe.isp.sample.domain.sampleuser.SampleUser;
import jp.co.biglobe.isp.sample.domain.sampleuser.SampleUserId;
import jp.co.biglobe.isp.sample.domain.sampleuser.SampleUserName;
import jp.co.biglobe.isp.sample.fixture.FixtureSampleUser;
import jp.co.biglobe.test.util.dbunit.DbUnitTester;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = {"classpath:context.xml"})
public class SampleRepositoryDbTest {

    @Autowired
    private SampleRepositoryDb sut;

    @Autowired
    private DbUnitTester tester;

    @Before
    public void setUp() throws Exception {
        tester.executeAllClearTableAndSeq();
    }

    @After
    public void tearDown() {
    }

    @Test
    public void _findSampleUserId() throws Exception {
        // テストデータの準備
        tester.cleanInsertQuery(FixtureSampleUser.One.getDefaultData());

        // 準備
        SampleUser expected = new SampleUser(
                new SampleUserId(1),
                new SampleUserName("小池直樹")
        );

        // 実行
        SampleUser actual = sut.findBySampleUserId(new SampleUserId(1));

        // 評価
        assertThat(actual, is(expected));
    }
}
