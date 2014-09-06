package jp.co.biglobe.isp.mobile.voiceoption.service.engagement;

import jp.co.biglobe.isp.mobile.ltethreeg.datasource.SIM_INFO;
import jp.co.biglobe.isp.mobile.ltethreeg.domain.ltethreegengagement.LteThreeGEngagementNumber;
import jp.co.biglobe.isp.mobile.voiceoption.datasource.charge.engagementmonthdisengagementcharge.db.FixtureVoiceEngagementMonthDisengagementChargeState;
import jp.co.biglobe.isp.mobile.voiceoption.datasource.voiceengagement.db.FixtureVoiceEngagementState;
import jp.co.biglobe.isp.mobile.voiceoption.domain.view.voiceengagementdetailcontainer.VoiceEngagementDetailContainerList;
import jp.co.biglobe.isp.mobile.voiceoption.service.engagement.VoiceEngagementDetailListReferService;
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
public class VoiceEngagementDetailListReferServiceTest {

    @Autowired
    private VoiceEngagementDetailListReferService sut;

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
        VoiceEngagementDetailContainerList actual = sut.refer(new LteThreeGEngagementNumber("99999999"));

        // 確認
        assertThat(actual.getCount(), is(0));
    }

    @Test
    public void _1件() throws Exception {
        // テストケースの設定
        tester.cleanInsertQuery(SIM_INFO.regist);
        tester.cleanInsertQuery(FixtureVoiceEngagementState.One.getDefaultData());

        // 実行
        VoiceEngagementDetailContainerList actual = sut.refer(new LteThreeGEngagementNumber("00000001"));

        // 確認
        assertThat(actual.getCount(), is(1));
    }

    @Test
    public void _1件_契約月解約課金あり() throws Exception {
        // テストケースの設定
        tester.cleanInsertQuery(SIM_INFO.regist);
        tester.cleanInsertQuery(FixtureVoiceEngagementState.One.getDefaultData());
        tester.cleanInsertQuery(FixtureVoiceEngagementMonthDisengagementChargeState.One.getDefaultData());

        // 実行
        VoiceEngagementDetailContainerList actual = sut.refer(new LteThreeGEngagementNumber("00000001"));

        // 確認
        assertThat(actual.getCount(), is(1));
    }

    @Test
    public void _2件() throws Exception {
        // テストケースの設定
        tester.cleanInsertQuery(SIM_INFO.regist);
        tester.cleanInsertQuery(FixtureVoiceEngagementState.Two.getForDefaultPlusDisEngaged());

        // 実行
        VoiceEngagementDetailContainerList actual = sut.refer(new LteThreeGEngagementNumber("00000001"));

        // 確認
        assertThat(actual.getCount(), is(2));
    }

}
