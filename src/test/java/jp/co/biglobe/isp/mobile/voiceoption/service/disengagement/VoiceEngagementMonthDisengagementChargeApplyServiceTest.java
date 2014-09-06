package jp.co.biglobe.isp.mobile.voiceoption.service.disengagement;

import jp.co.biglobe.isp.mobile.extension.exception.BusinessException;
import jp.co.biglobe.isp.mobile.ltethreeg.datasource.SIM_INFO;
import jp.co.biglobe.isp.mobile.ltethreeg.domain.sim.EquipmentNumber;
import jp.co.biglobe.isp.mobile.voiceoption.database.VoiceEngagementMonthDisengagementChargeEventAssert;
import jp.co.biglobe.isp.mobile.voiceoption.database.VoiceEngagementMonthDisengagementChargeStateAssert;
import jp.co.biglobe.isp.mobile.voiceoption.datasource.charge.engagementmonthdisengagementcharge.db.FixtureVoiceEngagementMonthDisengagementChargeEvent;
import jp.co.biglobe.isp.mobile.voiceoption.datasource.charge.engagementmonthdisengagementcharge.db.FixtureVoiceEngagementMonthDisengagementChargeState;
import jp.co.biglobe.isp.mobile.voiceoption.datasource.voiceengagement.db.FixtureVoiceEngagementState;
import jp.co.biglobe.test.util.dbunit.DbUnitTester;
import org.dbunit.DatabaseUnitException;
import org.joda.time.DateTime;
import org.joda.time.DateTimeUtils;
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

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = {"classpath:context.xml"})
public class VoiceEngagementMonthDisengagementChargeApplyServiceTest {

    @Autowired
    private VoiceEngagementMonthDisengagementChargeApplyService sut;

    public final DateTime FIXATION_DATE = new DateTime(2014, 1, 1, 0, 0, 0);

    @Autowired
    DbUnitTester tester;

    @Before
    public void setup() throws IOException, DatabaseUnitException, SQLException {
        // 処理前にテーブルをクリアする
        tester.executeAllClearTableAndSeq();

        // 「現在時刻」を固定化する
        DateTimeUtils.setCurrentMillisFixed(FIXATION_DATE.getMillis());
    }

    @After
    public void tearDown() throws IOException {
        // 「現在時刻」をシステム日付に戻す
        DateTimeUtils.setCurrentMillisSystem();
    }

    @Test
    public void _正常_解約済で契約月課金未実施のため課金される() throws Exception {
        // テストケースの設定
        tester.cleanInsertQuery(SIM_INFO.regist);
        tester.cleanInsertQuery(FixtureVoiceEngagementState.One.getForDisengaged(
                new DateTime(2014, 7, 1, 0, 0, 0),
                new DateTime(2014, 7, 2, 0, 0, 0)
        ));

        // 実行
        sut.charge(new EquipmentNumber("00000001"));

        // 確認
        assertVoiceEngagementMonthDisengagementCharge();

    }

    @Test(expected = BusinessException.class)
    public void _契約月の翌月に解約したので課金されない() throws Exception {
        // テストケースの設定
        tester.cleanInsertQuery(SIM_INFO.regist);
        tester.cleanInsertQuery(FixtureVoiceEngagementState.One.getForDisengaged(
                new DateTime(2014, 7, 1, 0, 0, 0),
                new DateTime(2014, 8, 1, 0, 0, 0)
        ));

        // 実行
        sut.charge(new EquipmentNumber("00000001"));

    }

    @Test(expected = BusinessException.class)
    public void _契約中なので課金されない() throws Exception {
        // テストケースの設定
        tester.cleanInsertQuery(SIM_INFO.regist);
        tester.cleanInsertQuery(FixtureVoiceEngagementState.One.getForEngagement());

        // 実行
        sut.charge(new EquipmentNumber("00000001"));
    }

    @Test(expected = BusinessException.class)
    public void _課金済み() throws Exception {
        // テストケースの設定
        tester.cleanInsertQuery(SIM_INFO.regist);
        tester.cleanInsertQuery(FixtureVoiceEngagementState.One.getForEngagement());
        tester.cleanInsertQuery(FixtureVoiceEngagementMonthDisengagementChargeState.One.getDefaultData());

        // 実行
        sut.charge(new EquipmentNumber("00000001"));
    }

    private void assertVoiceEngagementMonthDisengagementCharge() throws Exception{
        VoiceEngagementMonthDisengagementChargeEventAssert voiceEngagementMonthDisengagementChargeEventAssert = new VoiceEngagementMonthDisengagementChargeEventAssert(tester);
        voiceEngagementMonthDisengagementChargeEventAssert.assertTableWithAllColumns(FixtureVoiceEngagementMonthDisengagementChargeEvent.One.getDefaultData());
        VoiceEngagementMonthDisengagementChargeStateAssert voiceEngagementMonthDisengagementChargeStateAssert = new VoiceEngagementMonthDisengagementChargeStateAssert(tester);
        voiceEngagementMonthDisengagementChargeStateAssert.assertTableWithAllColumns(FixtureVoiceEngagementMonthDisengagementChargeState.One.getDefaultData());
    }

}
