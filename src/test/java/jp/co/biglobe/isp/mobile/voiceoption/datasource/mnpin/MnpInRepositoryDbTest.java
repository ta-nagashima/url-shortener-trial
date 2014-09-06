package jp.co.biglobe.isp.mobile.voiceoption.datasource.mnpin;

import jp.co.biglobe.isp.mobile.ltethreeg.datasource.SIM_INFO;
import jp.co.biglobe.isp.mobile.voiceoption.database.VoiceMnpInEventAssert;
import jp.co.biglobe.isp.mobile.voiceoption.database.VoiceMnpInPersonalInfoEventAssert;
import jp.co.biglobe.isp.mobile.voiceoption.database.VoiceMnpInPersonalInfoStateAssert;
import jp.co.biglobe.isp.mobile.voiceoption.database.VoiceMnpInStateAssert;
import jp.co.biglobe.isp.mobile.voiceoption.datasource.mnpin.db.FixtureVoiceMnpInEvent;
import jp.co.biglobe.isp.mobile.voiceoption.datasource.mnpin.db.FixtureVoiceMnpInPersonalInfoEvent;
import jp.co.biglobe.isp.mobile.voiceoption.datasource.mnpin.db.FixtureVoiceMnpInPersonalInfoState;
import jp.co.biglobe.isp.mobile.voiceoption.datasource.mnpin.db.FixtureVoiceMnpInState;
import jp.co.biglobe.isp.mobile.voiceoption.datasource.voiceengagement.db.FixtureVoiceEngagementState;
import jp.co.biglobe.isp.mobile.voiceoption.domain.mnpin.*;
import jp.co.biglobe.isp.mobile.voiceoption.domain.voiceengagement.ValidVoiceEngagementBuilder;
import jp.co.biglobe.isp.mobile.voiceoption.domain.voiceengagement.VoiceEngagementNumber;
import jp.co.biglobe.test.util.dbunit.DbUnitTester;
import org.dbunit.DatabaseUnitException;
import org.joda.time.DateTime;
import org.joda.time.DateTimeUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.io.IOException;
import java.sql.SQLException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

@RunWith(Enclosed.class)
public class MnpInRepositoryDbTest {

    @RunWith(SpringJUnit4ClassRunner.class)
    @WebAppConfiguration
    @ContextConfiguration(locations = {"classpath:context.xml"})
    public static class _update {

        @Autowired
        private MnpInRepository sut;

        @Autowired
        private DbUnitTester tester;

        public final DateTime FIXATION_DATE = new DateTime(2014, 1, 1, 0, 0, 0);

        @Before
        public void setUp() throws DatabaseUnitException, SQLException, IOException {
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
        public void _regiter_validMnpInPersonalInfo() throws Exception {
            // テストデータの準備
            tester.cleanInsertQuery(SIM_INFO.regist);
            tester.cleanInsertQuery(FixtureVoiceEngagementState.One.getDefaultData());

            // 準備
            ValidMnpIn validMnpIn = new ValidMnpInBuilder().buildKoike();

            // 実行
            sut.insert(validMnpIn);

            // 評価
            VoiceMnpInPersonalInfoEventAssert voiceMnpInPersonalInfoEventAssert = new VoiceMnpInPersonalInfoEventAssert(tester);
            voiceMnpInPersonalInfoEventAssert.assertTableWithAllColumns(FixtureVoiceMnpInPersonalInfoEvent.One.getDefaultData());

            VoiceMnpInPersonalInfoStateAssert voiceMnpInPersonalInfoStateAssert = new VoiceMnpInPersonalInfoStateAssert(tester);
            voiceMnpInPersonalInfoStateAssert.assertTableWithAllColumns(FixtureVoiceMnpInPersonalInfoState.One.getDefaultData());

            VoiceMnpInEventAssert voiceMnpInEventAssert = new VoiceMnpInEventAssert(tester);
            voiceMnpInEventAssert.assertTableWithAllColumns(FixtureVoiceMnpInEvent.One.getDefaultData());

            VoiceMnpInStateAssert voiceMnpInStateAssert = new VoiceMnpInStateAssert(tester);
            voiceMnpInStateAssert.assertTableWithAllColumns(FixtureVoiceMnpInState.One.getDefaultData());
        }

        @Test
        public void _regiter_nullMnpInPersonalInfo() throws Exception {
            // テストデータの準備
            tester.cleanInsertQuery(SIM_INFO.regist);
            tester.cleanInsertQuery(FixtureVoiceEngagementState.Two.getDefaultData());
            tester.cleanInsertQuery(FixtureVoiceMnpInState.One.getForProvisional());
            tester.cleanInsertQuery(FixtureVoiceMnpInPersonalInfoState.One.getDefaultData());

            // 準備
            ValidMnpIn validMnpIn = new ValidMnpInBuilder().buildYamadaNull();

            // 実行
            sut.insert(validMnpIn);

            // 評価
            // todo : データがない事を確認したいが、メソッドがないため一旦保留
            // VoiceMnpInPersonalInfoEventAssert voiceMnpInPersonalInfoEventAssert = new VoiceMnpInPersonalInfoEventAssert(tester.getConnection());
            // voiceMnpInPersonalInfoEventAssert.assertTableWithAllColumns(ExpectedVoiceMnpInPersonalInfoEvent.One.getForProvisional());

            VoiceMnpInEventAssert voiceMnpInEventAssert = new VoiceMnpInEventAssert(tester);
            voiceMnpInEventAssert.assertTableWithAllColumns(FixtureVoiceMnpInEvent.One.getForSecondData());

            VoiceMnpInStateAssert voiceMnpInStateAssert = new VoiceMnpInStateAssert(tester);
            voiceMnpInStateAssert.assertTableWithAllColumns(FixtureVoiceMnpInState.Two.getOneMnpInPersonalInfoState());
        }
    }


    @RunWith(SpringJUnit4ClassRunner.class)
    @WebAppConfiguration
    @ContextConfiguration(locations = {"classpath:context.xml"})
    public static class _select {

        @Autowired
        private MnpInRepository sut;

        @Autowired
        private DbUnitTester tester;

        public final DateTime FIXATION_DATE = new DateTime(2014, 1, 1, 0, 0, 0);

        @Before
        public void setUp() throws DatabaseUnitException, SQLException, IOException {
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
        public void _findByVoiceEngagementNumber_voiceEngagementNumberが引数_PersonalInfoあり() throws Exception {
            // テストデータの準備
            tester.cleanInsertQuery(SIM_INFO.regist);
            tester.cleanInsertQuery(FixtureVoiceEngagementState.One.getDefaultData());
            tester.cleanInsertQuery(FixtureVoiceMnpInState.One.getForProvisional());
            tester.cleanInsertQuery(FixtureVoiceMnpInPersonalInfoState.One.getDefaultData());

            // 準備
            MnpIn expected = new ValidMnpInBuilder().buildKoike();

            // 実行
            MnpIn actual = sut.findByVoiceEngagementNumber(new VoiceEngagementNumber(1));

            // 評価
            assertThat(actual, is(expected));
        }

        @Test
        public void _findByVoiceEngagementNumber_voiceEngagementNumberが引数_PersonalInfoなし() throws Exception {
            // テストデータの準備
            tester.cleanInsertQuery(SIM_INFO.regist);
            tester.cleanInsertQuery(FixtureVoiceEngagementState.Two.getDefaultData());
            tester.cleanInsertQuery(FixtureVoiceMnpInState.One.getForProvisional());

            // 準備
            MnpIn expected = new ValidMnpInBuilder().buildKoikeNull();

            // 実行
            MnpIn actual = sut.findByVoiceEngagementNumber(new VoiceEngagementNumber(1));

            // 評価
            assertThat(actual, is(expected));
        }

        @Test
        public void _findByVoiceEngagementNumber_voiceEngagementNumberが引数_MnpInが見つからない() throws Exception {
            // テストデータの準備
            tester.cleanInsertQuery(SIM_INFO.regist);
            tester.cleanInsertQuery(FixtureVoiceEngagementState.Two.getDefaultData());
            tester.cleanInsertQuery(FixtureVoiceMnpInState.One.getForProvisional());

            // 準備
            MnpIn expected = new NotExistMnpIn();

            // 実行
            MnpIn actual = sut.findByVoiceEngagementNumber(new VoiceEngagementNumber(999));

            // 評価
            assertThat(actual, is(expected));
        }

        @Test
        public void _findByVoiceEngagementNumber_voiceEngagementが引数_PersonalInfoあり() throws Exception {
            // テストデータの準備
            tester.cleanInsertQuery(SIM_INFO.regist);
            tester.cleanInsertQuery(FixtureVoiceEngagementState.One.getDefaultData());
            tester.cleanInsertQuery(FixtureVoiceMnpInState.One.getForProvisional());
            tester.cleanInsertQuery(FixtureVoiceMnpInPersonalInfoState.One.getDefaultData());

            // 準備
            MnpIn expected = new ValidMnpInBuilder().buildKoike();

            // 実行
            MnpIn actual = sut.findByVoiceEngagementNumber(new ValidVoiceEngagementBuilder().build());

            // 評価
            assertThat(actual, is(expected));
        }


        @Test
        public void _findByVoiceEngagementNumber_voiceEngagementが引数_PersonalInfoなし() throws Exception {
            // テストデータの準備
            tester.cleanInsertQuery(SIM_INFO.regist);
            tester.cleanInsertQuery(FixtureVoiceEngagementState.Two.getDefaultData());
            tester.cleanInsertQuery(FixtureVoiceMnpInState.One.getForProvisional());

            // 準備
            MnpIn expected = new ValidMnpInBuilder().buildKoikeNull();

            // 実行
            MnpIn actual = sut.findByVoiceEngagementNumber(new ValidVoiceEngagementBuilder().build());

            // 評価
            assertThat(actual, is(expected));
        }

        @Test
        public void _findByVoiceEngagementNumber_voiceEngagementが引数_MnpInが見つからない() throws Exception {
            // テストデータの準備
            tester.cleanInsertQuery(SIM_INFO.regist);
            tester.cleanInsertQuery(FixtureVoiceEngagementState.Two.getDefaultData());
            tester.cleanInsertQuery(FixtureVoiceMnpInState.One.getForProvisional());

            // 準備
            MnpIn expected = new NotExistMnpIn();

            // 実行
            MnpIn actual = sut.findByVoiceEngagementNumber(new ValidVoiceEngagementBuilder().voiceEngagementNumber(999).build());

            // 評価
            assertThat(actual, is(expected));
        }

    }
}