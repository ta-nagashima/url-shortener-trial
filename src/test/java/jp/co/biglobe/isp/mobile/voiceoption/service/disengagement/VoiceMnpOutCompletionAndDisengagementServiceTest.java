package jp.co.biglobe.isp.mobile.voiceoption.service.disengagement;

import jp.co.biglobe.isp.mobile.extension.exception.SystemCheckException;
import jp.co.biglobe.isp.mobile.ltethreeg.datasource.SIM_INFO;
import jp.co.biglobe.isp.mobile.ltethreeg.domain.sim.EquipmentNumber;
import jp.co.biglobe.isp.mobile.voiceoption.database.*;
import jp.co.biglobe.isp.mobile.voiceoption.datasource.charge.mnpoutcharge.db.FixtureVoiceMnpOutChargeEvent;
import jp.co.biglobe.isp.mobile.voiceoption.datasource.mnpout.db.FixtureVoiceMnpOutEvent;
import jp.co.biglobe.isp.mobile.voiceoption.datasource.mnpout.db.FixtureVoiceMnpOutState;
import jp.co.biglobe.isp.mobile.voiceoption.datasource.voiceengagement.db.FixtureVoiceEngagementEvent;
import jp.co.biglobe.isp.mobile.voiceoption.datasource.voiceengagement.db.FixtureVoiceEngagementState;
import jp.co.biglobe.isp.mobile.voiceoption.domain.mnpout.mnpoutcompletion.MnpOutCompletionConfirmedDate;
import jp.co.biglobe.isp.mobile.voiceoption.domain.voiceengagement.VoiceEngagementEndDateTime;
import jp.co.biglobe.isp.mobile.voiceoption.domain.voiceengagement.disengage.VoiceEngagementDisengageOrderDate;
import jp.co.biglobe.test.util.dbunit.DbUnitTester;
import jp.co.biglobe.test.util.usecase.BobioUseCase;
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
public class VoiceMnpOutCompletionAndDisengagementServiceTest {


    private static final String SCENARIO_NAME = "M_DDD_Wrapper_call";
    private BobioUseCase testcase = new BobioUseCase(SCENARIO_NAME);

    @Autowired
    private VoiceMnpOutCompletionAndDisengagementService sut;

    public final DateTime FIXATION_DATE = new DateTime(2014, 1, 1, 0, 0, 0);

    @Autowired
    public DbUnitTester tester;

    @Before
    public void setup() throws IOException, DatabaseUnitException, SQLException {
        // 処理前にテーブルをクリアする
        tester.executeAllClearTableAndSeq();

        // 「現在時刻」を固定化する
        DateTimeUtils.setCurrentMillisFixed(FIXATION_DATE.getMillis());

        // シナリオのテストデータ設定
        testcase.set("DEFAULT");
    }

    @After
    public void tearDown() throws IOException{
        // 「現在時刻」をシステム日付に戻す
        DateTimeUtils.setCurrentMillisSystem();

        // シナリオのテストデータをデフォルトに戻す
        testcase.unset();
    }

    @Test
    public void _正常_契約中でユーザの転出待ちのため処理OK() throws Exception {
        // テストケースの設定
        tester.cleanInsertQuery(SIM_INFO.regist);
        tester.cleanInsertQuery(FixtureVoiceEngagementState.One.getForEngagement());
        tester.cleanInsertQuery(FixtureVoiceMnpOutState.One.getForNumbered());
        testcase.set("DEFAULT");

        // 実行
        sut.disengage(new EquipmentNumber("00000001"),
                new MnpOutCompletionConfirmedDate("20140401"), new VoiceEngagementEndDateTime(new DateTime().toDate()),
                new VoiceEngagementDisengageOrderDate("20140101"));

        // 確認
        assertVoiceEngagement();
        assertMnpOut();
        assertMnpOutCharge();

    }

    private void assertVoiceEngagement() throws Exception{
        VoiceEngagementEventAssert voiceEngagementEventAssert = new VoiceEngagementEventAssert(tester);
        voiceEngagementEventAssert.assertTableWithAllColumns(FixtureVoiceEngagementEvent.One.getForImmediateDisEngagementEventToUpdate());

        VoiceEngagementStateAssert voiceEngagementStateAssert = new VoiceEngagementStateAssert(tester);
        voiceEngagementStateAssert.assertTableWithAllColumns(FixtureVoiceEngagementState.One.getForImmediateDisEngagement());
    }


    private void assertMnpOut() throws Exception{
        VoiceMnpOutEventAssert voiceMnpOutEventAssert = new VoiceMnpOutEventAssert(tester);
        voiceMnpOutEventAssert.assertTableWithAllColumns(FixtureVoiceMnpOutEvent.One.getForCompletion());

        VoiceMnpOutStateAssert voiceMnpOutStateAssert = new VoiceMnpOutStateAssert(tester);
        voiceMnpOutStateAssert.assertTableWithAllColumns(FixtureVoiceMnpOutState.One.getForCompletion());
    }

    private void assertMnpOutCharge() throws Exception{

        VoiceMnpOutChargeEventAssert voiceMnpOutChargeEventAssert = new VoiceMnpOutChargeEventAssert(tester);
        voiceMnpOutChargeEventAssert.assertTableWithAllColumns(FixtureVoiceMnpOutChargeEvent.One.getDefaultData());
    }

    @Test(expected = SystemCheckException.class)
    public void _異常_申込中のため() throws Exception {
        // テストケースの設定
        tester.cleanInsertQuery(SIM_INFO.regist);
        tester.cleanInsertQuery(FixtureVoiceEngagementState.One.getDefaultData());

        // 実行
        sut.disengage(new EquipmentNumber("00000001"), 
                new MnpOutCompletionConfirmedDate("20140401"), new VoiceEngagementEndDateTime(new DateTime().toDate()),
                new VoiceEngagementDisengageOrderDate("20140101"));
    }

    @Test(expected = SystemCheckException.class)
    public void _異常_キャンセルのため() throws Exception {
        // テストケースの設定
        tester.cleanInsertQuery(SIM_INFO.regist);
        tester.cleanInsertQuery(FixtureVoiceEngagementState.One.getForCancelForCourse());

        // 実行
        sut.disengage(new EquipmentNumber("00000001"), 
                new MnpOutCompletionConfirmedDate("20140401"), new VoiceEngagementEndDateTime(new DateTime().toDate()),
                new VoiceEngagementDisengageOrderDate("20140101"));
    }

    @Test(expected = SystemCheckException.class)
    public void _異常_解約中のため() throws Exception {
        // テストケースの設定
        tester.cleanInsertQuery(SIM_INFO.regist);
        tester.cleanInsertQuery(FixtureVoiceEngagementState.One.getForDisEngaged());

        // 実行
        sut.disengage(new EquipmentNumber("00000001"), 
                new MnpOutCompletionConfirmedDate("20140401"), new VoiceEngagementEndDateTime(new DateTime().toDate()),
                new VoiceEngagementDisengageOrderDate("20140101"));
    }

    @Test(expected = SystemCheckException.class)
    public void _異常_解約済みのため() throws Exception {
        // テストケースの設定
        tester.cleanInsertQuery(SIM_INFO.regist);
        tester.cleanInsertQuery(FixtureVoiceEngagementState.One.getForDisEngagementReserved());

        // 実行
        sut.disengage(new EquipmentNumber("00000001"), 
                new MnpOutCompletionConfirmedDate("20140401"), new VoiceEngagementEndDateTime(new DateTime().toDate()),
                new VoiceEngagementDisengageOrderDate("20140101"));
    }

    @Test(expected = SystemCheckException.class)
    public void _異常_契約中で転出申込中でMNP予約番号発行の依頼待ちのため() throws Exception {
        // テストケースの設定
        tester.cleanInsertQuery(SIM_INFO.regist);
        tester.cleanInsertQuery(FixtureVoiceEngagementState.One.getForEngagement());
        tester.cleanInsertQuery(FixtureVoiceMnpOutState.One.getForRequestedWaiting());

        // 実行
        sut.disengage(new EquipmentNumber("00000001"), 
                new MnpOutCompletionConfirmedDate("20140401"), new VoiceEngagementEndDateTime(new DateTime().toDate()),
                new VoiceEngagementDisengageOrderDate("20140101"));
    }

    @Test(expected = SystemCheckException.class)
    public void _異常_契約中で転出申込中でALADINでMNP予約番号発行を依頼済み() throws Exception {
        // テストケースの設定
        tester.cleanInsertQuery(SIM_INFO.regist);
        tester.cleanInsertQuery(FixtureVoiceEngagementState.One.getForEngagement());
        tester.cleanInsertQuery(FixtureVoiceMnpOutState.One.getForRequested());

        // 実行
        sut.disengage(new EquipmentNumber("00000001"), 
                new MnpOutCompletionConfirmedDate("20140401"), new VoiceEngagementEndDateTime(new DateTime().toDate()),
                new VoiceEngagementDisengageOrderDate("20140101"));
    }


    @Test(expected = SystemCheckException.class)
    public void _異常_契約中で転出完了済み() throws Exception {
        // テストケースの設定
        tester.cleanInsertQuery(SIM_INFO.regist);
        tester.cleanInsertQuery(FixtureVoiceEngagementState.One.getForEngagement());
        tester.cleanInsertQuery(FixtureVoiceMnpOutState.One.getForCompletion());

        // 実行
        sut.disengage(new EquipmentNumber("00000001"), 
                new MnpOutCompletionConfirmedDate("20140401"), new VoiceEngagementEndDateTime(new DateTime().toDate()),
                new VoiceEngagementDisengageOrderDate("20140101"));
    }

    @Test(expected = SystemCheckException.class)
    public void _異常_契約中で転出キャンセル() throws Exception {
        // テストケースの設定
        tester.cleanInsertQuery(SIM_INFO.regist);
        tester.cleanInsertQuery(FixtureVoiceEngagementState.One.getForEngagement());
        tester.cleanInsertQuery(FixtureVoiceMnpOutState.One.getForCancelFromNumbered());

        // 実行
        sut.disengage(new EquipmentNumber("00000001"), 
                new MnpOutCompletionConfirmedDate("20140401"), new VoiceEngagementEndDateTime(new DateTime().toDate()),
                new VoiceEngagementDisengageOrderDate("20140101"));
    }

}
