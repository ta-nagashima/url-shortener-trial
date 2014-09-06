package jp.co.biglobe.isp.mobile.voiceoption.service.engagement;

import jp.co.biglobe.isp.mobile.extension.exception.SystemCheckException;
import jp.co.biglobe.isp.mobile.ltethreeg.datasource.SIM_INFO;
import jp.co.biglobe.isp.mobile.ltethreeg.domain.sim.EquipmentNumber;
import jp.co.biglobe.isp.mobile.voiceoption.database.VoiceEngagementEventAssert;
import jp.co.biglobe.isp.mobile.voiceoption.database.VoiceEngagementStateAssert;
import jp.co.biglobe.isp.mobile.voiceoption.datasource.voiceengagement.db.FixtureVoiceEngagementEvent;
import jp.co.biglobe.isp.mobile.voiceoption.datasource.voiceengagement.db.FixtureVoiceEngagementState;
import jp.co.biglobe.isp.mobile.voiceoption.domain.voiceengagement.VoiceEngagementStartDate;
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
public class VoiceEngagementServiceTest {

    @Autowired
    private VoiceEngagementService sut;

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

    @Test
    public void start_正常() throws Exception {
        // テストケースの設定
        tester.cleanInsertQuery(SIM_INFO.regist);
        tester.cleanInsertQuery(FixtureVoiceEngagementState.One.getDefaultData());

        // 実行
        sut.engage(new EquipmentNumber("00000001"),
                new VoiceEngagementStartDate(FIXATION_DATE.toString("yyyyMMdd")));

        // 確認
        VoiceEngagementEventAssert voiceEngagementEventAssert = new VoiceEngagementEventAssert(tester);
        voiceEngagementEventAssert.assertTableWithAllColumns(FixtureVoiceEngagementEvent.One.getForEngagementEventToUpdate(FIXATION_DATE));

        VoiceEngagementStateAssert voiceEngagementStateAssert = new VoiceEngagementStateAssert(tester);
        voiceEngagementStateAssert.assertTableWithAllColumns(FixtureVoiceEngagementState.One.getForEngagement(FIXATION_DATE));
    }

    @Test(expected = SystemCheckException.class)
    public void start_異常_音声オプション契約のステータスが不正() throws Exception {
        // テストケースの設定
        tester.cleanInsertQuery(SIM_INFO.regist);
        tester.cleanInsertQuery(FixtureVoiceEngagementState.One.getForDisEngagementReserved());

        // 実行
        sut.engage(new EquipmentNumber("00000001"),
                new VoiceEngagementStartDate("20140101"));
    }

    @Test(expected = SystemCheckException.class)
    public void start_異常_音声オプション契約が見つからない() throws Exception {
        // 実行
        sut.engage(new EquipmentNumber("99999999"),
                new VoiceEngagementStartDate("20140101"));
    }
}
