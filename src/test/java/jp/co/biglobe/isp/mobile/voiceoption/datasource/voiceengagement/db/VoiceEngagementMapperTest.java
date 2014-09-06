package jp.co.biglobe.isp.mobile.voiceoption.datasource.voiceengagement.db;

import jp.co.biglobe.isp.auth.domain.user.UserId;
import jp.co.biglobe.isp.mobile.extension.date.SystemDateTime;
import jp.co.biglobe.isp.mobile.ltethreeg.datasource.SIM_INFO;
import jp.co.biglobe.isp.mobile.ltethreeg.domain.sim.EquipmentNumber;
import jp.co.biglobe.isp.mobile.voiceoption.database.VoiceEngagementEventAssert;
import jp.co.biglobe.isp.mobile.voiceoption.database.VoiceEngagementStateAssert;
import jp.co.biglobe.isp.mobile.voiceoption.domain.voiceengagement.ValidVoiceEngagement;
import jp.co.biglobe.isp.mobile.voiceoption.domain.voiceengagement.ValidVoiceEngagementBuilder;
import jp.co.biglobe.lib.plugin.event.RequestEventProcess;
import jp.co.biglobe.lib.publication.datasource.EventType;
import jp.co.biglobe.test.util.dbunit.DbUnitTester;
import org.dbunit.DatabaseUnitException;
import org.joda.time.DateTime;
import org.joda.time.DateTimeUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.io.IOException;
import java.sql.SQLException;

import static org.junit.Assert.assertEquals;

@RunWith(Enclosed.class)
public class VoiceEngagementMapperTest {

    @RunWith(SpringJUnit4ClassRunner.class)
    @ContextConfiguration(locations = {"classpath:context.xml"})
    public static class Select系のテスト {

        @Autowired
        SqlSessionTemplate session;

        VoiceEngagementMapper sut;

        @Autowired
        public DbUnitTester tester;

        @Before
        public void setUp() throws DatabaseUnitException, SQLException, IOException {
            tester.executeAllClearTableAndSeq();
            sut = session.getMapper(VoiceEngagementMapper.class);
        }

        @Test
        public void findByEquipmentNumber_登録済み() throws Exception {

            // テストケースの設定
            tester.cleanInsertQuery(SIM_INFO.regist);
            tester.cleanInsertQuery(FixtureVoiceEngagementState.One.getDefaultData());

            // 準備
            ValidVoiceEngagement expected = new ValidVoiceEngagementBuilder().build();

            // 実行
            ValidVoiceEngagement actual = sut.findByEquipmentNumber(new EquipmentNumber("00000001"));

            // 確認
            assertEquals(expected, actual);

        }

        @Test
        public void findByUserId_登録済み() throws Exception {

            // テストケースの設定
            tester.cleanInsertQuery(SIM_INFO.lte);
            tester.cleanInsertQuery(FixtureVoiceEngagementState.One.getDefaultData());

            // 準備
            ValidVoiceEngagement expected = new ValidVoiceEngagementBuilder().build();

            // 実行
            ValidVoiceEngagement actual = sut.findByUserId(new UserId("abc12345"));

            // 確認
            assertEquals(expected, actual);

        }

    }

    @RunWith(SpringJUnit4ClassRunner.class)
    @WebAppConfiguration
    @ContextConfiguration(locations = {"classpath:context.xml"})
    public static class InsertEventのテスト {

        @Autowired
        SqlSessionTemplate session;

        VoiceEngagementMapper sut;

        public final DateTime FIXATION_DATE = new DateTime(2014, 1, 1, 0, 0, 0);

        @Autowired
        public DbUnitTester tester;

        @Autowired
        private RequestEventProcess requestEventProcess;

        @Before
        public void setUp() throws DatabaseUnitException, SQLException, IOException {
            tester.executeAllClearTableAndSeq();
            sut = session.getMapper(VoiceEngagementMapper.class);

            // 「現在時刻」を固定化する
            DateTimeUtils.setCurrentMillisFixed(FIXATION_DATE.getMillis());
        }

        @After
        public void tearDown(){
            // 「現在時刻」をシステム日付に戻す
            DateTimeUtils.setCurrentMillisSystem();
        }

        @Test
        public void InsertEvent_イベント登録() throws Exception {

            // 実行
            sut.insertEvent(
                    EventType.INSERT,
                    new SystemDateTime().getValue(),
                    requestEventProcess.getValue(),
                    new ValidVoiceEngagementBuilder().build()
            );

            // 確認
            VoiceEngagementEventAssert voiceEngagementEventAssert = new VoiceEngagementEventAssert(tester);
            voiceEngagementEventAssert.assertTableWithAllColumns(FixtureVoiceEngagementEvent.One.getDefaultData());
        }

    }

    @RunWith(SpringJUnit4ClassRunner.class)
    @ContextConfiguration(locations = {"classpath:context.xml"})
    public static class Updateのテスト {

        @Autowired
        SqlSessionTemplate session;

        VoiceEngagementMapper sut;

        @Autowired
        public DbUnitTester tester;

        @Before
        public void setUp() throws DatabaseUnitException, SQLException, IOException {
            tester.executeAllClearTableAndSeq();
            sut = session.getMapper(VoiceEngagementMapper.class);
        }

        @Test
        public void update_テスト() throws Exception {

            // テストケースの設定
            tester.cleanInsertQuery(SIM_INFO.regist);
            tester.cleanInsertQuery(FixtureVoiceEngagementState.One.getDefaultData());

            // 準備
            ValidVoiceEngagement validVoiceEngagement = new ValidVoiceEngagementBuilder().build();

            // 実行
            sut.updateState(validVoiceEngagement, validVoiceEngagement);

            // 確認
            VoiceEngagementStateAssert voiceEngagementStateAssert = new VoiceEngagementStateAssert(tester);
            voiceEngagementStateAssert.assertTableWithAllColumns(FixtureVoiceEngagementState.One.getDefaultData());

        }

    }


}
