package jp.co.biglobe.isp.mobile.voiceoption.service.engagement;

import jp.co.biglobe.isp.mobile.ltethreeg.datasource.SIM_INFO;
import jp.co.biglobe.isp.mobile.ltethreeg.domain.ltethreegengagement.LteThreeGEngagementNumber;
import jp.co.biglobe.isp.mobile.voiceoption.datasource.voiceengagement.db.FixtureVoiceEngagementState;
import jp.co.biglobe.isp.mobile.voiceoption.domain.view.voiceengagementcountrefer.VoiceEngagementCountContainer;
import jp.co.biglobe.test.util.dbunit.DbUnitTester;
import org.dbunit.DatabaseUnitException;
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

public class VoiceEngagementCountReferServiceTest {

    @Autowired
    private VoiceEngagementCountReferService sut;

    @Autowired
    public DbUnitTester tester;

    @Before
    public void setup() throws IOException, DatabaseUnitException, SQLException {
        // 処理前にテーブルをクリアする
        tester.executeAllClearTableAndSeq();

    }

    // 詳細なテストはAPI層でやる！！！

    @Test
    public void _0件() throws Exception {
        // テストケースの設定
        tester.cleanInsertQuery(SIM_INFO.regist);
        tester.cleanInsertQuery(FixtureVoiceEngagementState.One.getDefaultData());

        // 実行
        VoiceEngagementCountContainer actual = sut.refer(new LteThreeGEngagementNumber("99999999"));

        // 確認
        assertThat(actual.getVoiceEngagementCountOrdered().getApiValue(),is("0"));
        assertThat(actual.getVoiceEngagementCountEngaged().getApiValue(),is("0"));
        assertThat(actual.getVoiceEngagementCountDisengagedReserved().getApiValue(),is("0"));
    }

    @Test
    public void _申込中が1件() throws Exception {
        // テストケースの設定
        tester.cleanInsertQuery(SIM_INFO.regist);
        tester.cleanInsertQuery(FixtureVoiceEngagementState.One.getDefaultData());

        // 実行
        VoiceEngagementCountContainer actual = sut.refer(new LteThreeGEngagementNumber("00000001"));

        // 確認
        assertThat(actual.getVoiceEngagementCountOrdered().getApiValue(),is("1"));
        assertThat(actual.getVoiceEngagementCountEngaged().getApiValue(),is("0"));
        assertThat(actual.getVoiceEngagementCountDisengagedReserved().getApiValue(),is("0"));
    }

    @Test
    public void _契約中が1件() throws Exception {
        // テストケースの設定
        tester.cleanInsertQuery(SIM_INFO.regist);
        tester.cleanInsertQuery(FixtureVoiceEngagementState.One.getForEngagement());

        // 実行
        VoiceEngagementCountContainer actual = sut.refer(new LteThreeGEngagementNumber("00000001"));

        // 確認
        assertThat(actual.getVoiceEngagementCountOrdered().getApiValue(),is("0"));
        assertThat(actual.getVoiceEngagementCountEngaged().getApiValue(),is("1"));
        assertThat(actual.getVoiceEngagementCountDisengagedReserved().getApiValue(),is("0"));
    }

    @Test
    public void _解約予約中が1件() throws Exception {
        // テストケースの設定
        tester.cleanInsertQuery(SIM_INFO.regist);
        tester.cleanInsertQuery(FixtureVoiceEngagementState.One.getForDisEngagementReserved());

        // 実行
        VoiceEngagementCountContainer actual = sut.refer(new LteThreeGEngagementNumber("00000001"));

        // 確認
        assertThat(actual.getVoiceEngagementCountOrdered().getApiValue(),is("0"));
        assertThat(actual.getVoiceEngagementCountEngaged().getApiValue(),is("0"));
        assertThat(actual.getVoiceEngagementCountDisengagedReserved().getApiValue(),is("1"));
    }

    @Test
    public void _0件参照_解約済みが1件() throws Exception {
        // テストケースの設定
        tester.cleanInsertQuery(SIM_INFO.regist);
        tester.cleanInsertQuery(FixtureVoiceEngagementState.One.getForDisEngaged());

        // 実行
        VoiceEngagementCountContainer actual = sut.refer(new LteThreeGEngagementNumber("00000001"));

        // 確認
        assertThat(actual.getVoiceEngagementCountOrdered().getApiValue(),is("0"));
        assertThat(actual.getVoiceEngagementCountEngaged().getApiValue(),is("0"));
        assertThat(actual.getVoiceEngagementCountDisengagedReserved().getApiValue(),is("0"));
    }

}
