package jp.co.biglobe.isp.mobile.voiceoption.service.disengagement;

import jp.co.biglobe.isp.mobile.extension.exception.SystemCheckException;
import jp.co.biglobe.isp.mobile.ltethreeg.datasource.SIM_INFO;
import jp.co.biglobe.isp.mobile.ltethreeg.domain.sim.EquipmentNumber;
import jp.co.biglobe.isp.mobile.voiceoption.database.VoiceEngagementEventAssert;
import jp.co.biglobe.isp.mobile.voiceoption.database.VoiceEngagementStateAssert;
import jp.co.biglobe.isp.mobile.voiceoption.datasource.voiceengagement.db.FixtureVoiceEngagementEvent;
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
public class VoiceDisengagementCancelServiceTest {
    @Autowired
    private VoiceDisengagementCancelService sut;

    public final DateTime FIXATION_DATE = new DateTime(2014, 1, 1, 0, 0, 0);

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

    //音声契約
    @Test
    public void cancel_正常_音声契約が解約予約中の時は解約キャンセルができる() throws Exception {
        // テストケースの設定
        tester.cleanInsertQuery(SIM_INFO.regist);
        tester.cleanInsertQuery(FixtureVoiceEngagementState.One.getForDisEngagementReserved());

        // 実行
        sut.cancel(new EquipmentNumber("00000001"));

        // 確認
        VoiceEngagementEventAssert voiceEngagementEventAssert = new VoiceEngagementEventAssert(tester);
        voiceEngagementEventAssert.assertTableWithAllColumns(FixtureVoiceEngagementEvent.One.getForDisEngagedCancelEventToUpdate());

        VoiceEngagementStateAssert voiceEngagementStateAssert = new VoiceEngagementStateAssert(tester);
        voiceEngagementStateAssert.assertTableWithAllColumns(FixtureVoiceEngagementState.One.getForEngagement());

    }

    @Test(expected = SystemCheckException.class)
    public void cancel_異常_音声契約が申し込み中のときは解約キャンセルできない() throws Exception {
        // テストケースの設定
        tester.cleanInsertQuery(SIM_INFO.regist);
        tester.cleanInsertQuery(FixtureVoiceEngagementState.One.getDefaultData());

        // 実行
        sut.cancel(new EquipmentNumber("00000001"));
    }

    @Test(expected = SystemCheckException.class)
    public void cancel_異常_音声契約が契約中のときは解約キャンセルできない() throws Exception {
        // テストケースの設定
        tester.cleanInsertQuery(SIM_INFO.regist);
        tester.cleanInsertQuery(FixtureVoiceEngagementState.One.getForEngagement());

        // 実行
        sut.cancel(new EquipmentNumber("00000001"));
    }

    @Test(expected = SystemCheckException.class)
    public void cancel_異常_音声契約が解約済みのときは解約キャンセルできない() throws Exception {
        // テストケースの設定
        tester.cleanInsertQuery(SIM_INFO.regist);
        tester.cleanInsertQuery(FixtureVoiceEngagementState.One.getForDisEngaged());

        // 実行
        sut.cancel(new EquipmentNumber("00000001"));
    }


    @Test(expected = SystemCheckException.class)
    public void cancel_異常_音声契約がキャンセル済みのときは解約キャンセルできない() throws Exception {
        // テストケースの設定
        tester.cleanInsertQuery(SIM_INFO.regist);
        tester.cleanInsertQuery(FixtureVoiceEngagementState.One.getForEngagement());

        // 実行
        sut.cancel(new EquipmentNumber("00000001"));
    }

    @Test(expected = SystemCheckException.class)
    public void cancel_異常_音声オプション契約が見つからない() throws Exception {
        // 実行
        sut.cancel(new EquipmentNumber("99999999"));
    }

}