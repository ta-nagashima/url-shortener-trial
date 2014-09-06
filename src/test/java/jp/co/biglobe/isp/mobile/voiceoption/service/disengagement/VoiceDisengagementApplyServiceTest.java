package jp.co.biglobe.isp.mobile.voiceoption.service.disengagement;

import jp.co.biglobe.isp.mobile.extension.exception.SystemCheckException;
import jp.co.biglobe.isp.mobile.ltethreeg.datasource.SIM_INFO;
import jp.co.biglobe.isp.mobile.ltethreeg.domain.sim.EquipmentNumber;
import jp.co.biglobe.isp.mobile.voiceoption.database.VoiceEngagementEventAssert;
import jp.co.biglobe.isp.mobile.voiceoption.database.VoiceEngagementStateAssert;
import jp.co.biglobe.isp.mobile.voiceoption.datasource.mnpout.db.FixtureVoiceMnpOutState;
import jp.co.biglobe.isp.mobile.voiceoption.datasource.voiceengagement.db.FixtureVoiceEngagementEvent;
import jp.co.biglobe.isp.mobile.voiceoption.datasource.voiceengagement.db.FixtureVoiceEngagementState;
import jp.co.biglobe.isp.mobile.voiceoption.domain.voiceengagement.VoiceEngagementEndDateTime;
import jp.co.biglobe.isp.mobile.voiceoption.domain.voiceengagement.disengage.VoiceEngagementDisengageOrderDate;
import jp.co.biglobe.isp.mobile.voiceoption.domain.voiceengagement.disengage.VoiceEngagementDisengageReason;
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
public class VoiceDisengagementApplyServiceTest {

    @Autowired
    private VoiceDisengagementApplyService sut;

    public final DateTime FIXATION_DATE = new DateTime(2014, 1, 1, 0, 0, 0);

    public final DateTime DISENGAGEMENT_DATE = FIXATION_DATE.dayOfMonth().withMaximumValue().withHourOfDay(23).withMinuteOfHour(59).withSecondOfMinute(59);

    @Autowired
    public DbUnitTester tester;

    @Before
    public void setup() throws IOException, DatabaseUnitException, SQLException {
        // 処理前にテーブルをクリアする
        tester.executeAllClearTableAndSeq();

        // 「現在時刻」を固定化する
        DateTimeUtils.setCurrentMillisFixed(FIXATION_DATE.getMillis());
    }

    @After
    public void tearDown() {
        // 「現在時刻」をシステム日付に戻す
        DateTimeUtils.setCurrentMillisSystem();
    }

    @Test
    public void reserve_正常_音声契約が契約中で転出キャンセルの時は解約予約ができる() throws Exception {
        // テストケースの設定
        tester.cleanInsertQuery(SIM_INFO.regist);
        tester.cleanInsertQuery(FixtureVoiceEngagementState.One.getForEngagement());
        tester.cleanInsertQuery(FixtureVoiceMnpOutState.One.getForCancelFromNumbered());

        // 実行
        sut.disengage(new EquipmentNumber("00000001"),
                new VoiceEngagementEndDateTime(DISENGAGEMENT_DATE.toDate()),
                VoiceEngagementDisengageReason.USER_REQUEST,
                new VoiceEngagementDisengageOrderDate("20140101"));

        // 確認
        VoiceEngagementEventAssert voiceEngagementEventAssert = new VoiceEngagementEventAssert(tester);
        voiceEngagementEventAssert.assertTableWithAllColumns(FixtureVoiceEngagementEvent.One.getForDisEngagementReservedEventToUpdate());

        VoiceEngagementStateAssert voiceEngagementStateAssert = new VoiceEngagementStateAssert(tester);
        voiceEngagementStateAssert.assertTableWithAllColumns(FixtureVoiceEngagementState.One.getForDisEngagementReserved());

    }

    @Test
    public void reserve_正常_音声契約が契約中の時は解約予約ができる() throws Exception {
        // テストケースの設定
        tester.cleanInsertQuery(SIM_INFO.regist);
        tester.cleanInsertQuery(FixtureVoiceEngagementState.One.getForEngagement());

        // 実行
        sut.disengage(new EquipmentNumber("00000001"),
                new VoiceEngagementEndDateTime(DISENGAGEMENT_DATE.toDate()),
                VoiceEngagementDisengageReason.USER_REQUEST,
                new VoiceEngagementDisengageOrderDate("20140101"));

        // 確認
        VoiceEngagementEventAssert voiceEngagementEventAssert = new VoiceEngagementEventAssert(tester);
        voiceEngagementEventAssert.assertTableWithAllColumns(FixtureVoiceEngagementEvent.One.getForDisEngagementReservedEventToUpdate());

        VoiceEngagementStateAssert voiceEngagementStateAssert = new VoiceEngagementStateAssert(tester);
        voiceEngagementStateAssert.assertTableWithAllColumns(FixtureVoiceEngagementState.One.getForDisEngagementReserved());

    }

    @Test(expected = SystemCheckException.class)
    public void reserve_異常_音声契約が申込中のときは解約予約できない() throws Exception {
        // テストケースの設定
        tester.cleanInsertQuery(SIM_INFO.regist);
        tester.cleanInsertQuery(FixtureVoiceEngagementState.One.getDefaultData());

        // 実行
        sut.disengage(new EquipmentNumber("00000001"),
                new VoiceEngagementEndDateTime(DISENGAGEMENT_DATE.toDate()),
                VoiceEngagementDisengageReason.USER_REQUEST,
                new VoiceEngagementDisengageOrderDate("20140101"));
    }

    @Test(expected = SystemCheckException.class)
    public void reserve_異常_音声契約が申込キャンセルのときは解約予約できない() throws Exception {
        // テストケースの設定
        tester.cleanInsertQuery(SIM_INFO.regist);
        tester.cleanInsertQuery(FixtureVoiceEngagementState.One.getForCancelForUserRequest());


        // 実行
        sut.disengage(new EquipmentNumber("00000001"),
                new VoiceEngagementEndDateTime(DISENGAGEMENT_DATE.toDate()),
                VoiceEngagementDisengageReason.USER_REQUEST,
                new VoiceEngagementDisengageOrderDate("20140101"));
    }

    @Test(expected = SystemCheckException.class)
    public void reserve_異常_音声契約が転出なし解約予約中のときは解約予約できない() throws Exception {
        // テストケースの設定
        tester.cleanInsertQuery(SIM_INFO.regist);
        tester.cleanInsertQuery(FixtureVoiceEngagementState.One.getForDisEngagementReserved());

        // 実行
        sut.disengage(new EquipmentNumber("00000001"),
                new VoiceEngagementEndDateTime(DISENGAGEMENT_DATE.toDate()),
                VoiceEngagementDisengageReason.USER_REQUEST,
                new VoiceEngagementDisengageOrderDate("20140101"));
    }

    @Test(expected = SystemCheckException.class)
    public void reserve_異常_音声契約が転出なし解約済みのときは解約予約できない() throws Exception {
        // テストケースの設定
        tester.cleanInsertQuery(SIM_INFO.regist);
        tester.cleanInsertQuery(FixtureVoiceEngagementState.One.getForDisEngaged());

        // 実行
        sut.disengage(new EquipmentNumber("00000001"),
                new VoiceEngagementEndDateTime(DISENGAGEMENT_DATE.toDate()),
                VoiceEngagementDisengageReason.USER_REQUEST,
                new VoiceEngagementDisengageOrderDate("20140101"));
    }

    @Test(expected = SystemCheckException.class)
    public void start_異常_転出中_利用者依頼済みの時はキャンセルできない() throws Exception {
        // テストケースの設定
        tester.cleanInsertQuery(SIM_INFO.regist);
        tester.cleanInsertQuery(FixtureVoiceEngagementState.One.getForEngagement());
        tester.cleanInsertQuery(FixtureVoiceMnpOutState.One.getForRequestedWaiting());

        // 実行
        sut.disengage(new EquipmentNumber("00000001"),
                new VoiceEngagementEndDateTime(DISENGAGEMENT_DATE.toDate()),
                VoiceEngagementDisengageReason.USER_REQUEST,
                new VoiceEngagementDisengageOrderDate("20140101"));
    }

    @Test(expected = SystemCheckException.class)
    public void start_異常_転出中_アラジン依頼済みの時はキャンセルできない() throws Exception {
        // テストケースの設定
        tester.cleanInsertQuery(SIM_INFO.regist);
        tester.cleanInsertQuery(FixtureVoiceEngagementState.One.getForEngagement());
        tester.cleanInsertQuery(FixtureVoiceMnpOutState.One.getForRequested());

        // 実行
        sut.disengage(new EquipmentNumber("00000001"),
                new VoiceEngagementEndDateTime(DISENGAGEMENT_DATE.toDate()),
                VoiceEngagementDisengageReason.USER_REQUEST,
                new VoiceEngagementDisengageOrderDate("20140101"));
    }

    @Test(expected = SystemCheckException.class)
    public void start_異常_転出中_予約番号発行済みの時はキャンセルできない() throws Exception {
        // テストケースの設定
        tester.cleanInsertQuery(SIM_INFO.regist);
        tester.cleanInsertQuery(FixtureVoiceEngagementState.One.getForEngagement());
        tester.cleanInsertQuery(FixtureVoiceMnpOutState.One.getForNumbered());

        // 実行
        sut.disengage(new EquipmentNumber("00000001"),
                new VoiceEngagementEndDateTime(DISENGAGEMENT_DATE.toDate()),
                VoiceEngagementDisengageReason.USER_REQUEST,
                new VoiceEngagementDisengageOrderDate("20140101"));
    }

    @Test(expected = SystemCheckException.class)
    public void start_正常_転出完了済みの時はキャンセルできない() throws Exception {
        // テストケースの設定
        tester.cleanInsertQuery(SIM_INFO.regist);
        tester.cleanInsertQuery(FixtureVoiceEngagementState.One.getForEngagement());
        tester.cleanInsertQuery(FixtureVoiceMnpOutState.One.getForCompletion());

        // 実行
        sut.disengage(new EquipmentNumber("00000001"),
                new VoiceEngagementEndDateTime(DISENGAGEMENT_DATE.toDate()),
                VoiceEngagementDisengageReason.USER_REQUEST,
                new VoiceEngagementDisengageOrderDate("20140101"));


    }

    @Test(expected = SystemCheckException.class)
    public void start_異常_音声オプション契約が見つからない() throws Exception {
        // 実行
        sut.disengage(new EquipmentNumber("00000001"),
                new VoiceEngagementEndDateTime(DISENGAGEMENT_DATE.toDate()),
                VoiceEngagementDisengageReason.USER_REQUEST,
                new VoiceEngagementDisengageOrderDate("20140101"));

    }
}