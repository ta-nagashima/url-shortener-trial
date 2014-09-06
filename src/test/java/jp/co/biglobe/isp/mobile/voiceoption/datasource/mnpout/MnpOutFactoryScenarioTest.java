package jp.co.biglobe.isp.mobile.voiceoption.datasource.mnpout;

import jp.co.biglobe.isp.mobile.voiceoption.domain.mnpout.MnpOutMsisdn;
import jp.co.biglobe.isp.mobile.voiceoption.domain.mnpout.ValidMnpOut;
import jp.co.biglobe.isp.mobile.voiceoption.domain.mnpout.ValidMnpOutBuilder;
import jp.co.biglobe.isp.mobile.voiceoption.domain.voiceengagement.ValidVoiceEngagement;
import jp.co.biglobe.isp.mobile.voiceoption.domain.voiceengagement.ValidVoiceEngagementBuilder;
import jp.co.biglobe.test.util.dbunit.DbUnitTester;
import jp.co.biglobe.test.util.usecase.BobioUseCase;
import org.dbunit.DatabaseUnitException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.io.IOException;
import java.sql.SQLException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;


@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = {"classpath:context.xml"})
public class MnpOutFactoryScenarioTest {

    @Autowired
    private MnpOutFactoryScenario sut;

    private static final String SCENARIO_NAME = "C_Membercourse_mo_ref";

    private BobioUseCase testcase = new BobioUseCase(SCENARIO_NAME);

    @Autowired
    public DbUnitTester tester;

    @Before
    public void setUp() throws DatabaseUnitException, SQLException, IOException {
        tester.executeAllClearTableAndSeq();
    }

    @After
    public void tearDown() throws IOException {
        testcase.unset();
    }

    @Test
    public void _EXIST() throws IOException {
        // テストケースの設定
        testcase.set("NORMAL");

        // 準備
        ValidVoiceEngagement validVoiceEngagement = new ValidVoiceEngagementBuilder().build();

        ValidMnpOut expected = new ValidMnpOutBuilder().build();

        // 実行
        ValidMnpOut actual = sut.create(validVoiceEngagement, new MnpOutMsisdn("090-1234-5678"));

        // 評価
        assertThat(actual, is(expected));
    }

    @Test(expected = RuntimeException.class)
    public void _NOTEXIST() throws IOException {

        // テストケースの設定
        testcase.set("NOTFOUND");

        // 準備
        ValidVoiceEngagement validVoiceEngagement = new ValidVoiceEngagementBuilder().build();

        // 実行
        sut.create(validVoiceEngagement, new MnpOutMsisdn("090-1234-5678"));

    }

}