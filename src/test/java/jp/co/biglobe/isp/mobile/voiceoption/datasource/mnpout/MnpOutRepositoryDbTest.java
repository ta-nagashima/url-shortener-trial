package jp.co.biglobe.isp.mobile.voiceoption.datasource.mnpout;

import jp.co.biglobe.isp.auth.domain.operatornouser.OperatorId;
import jp.co.biglobe.isp.mobile.ltethreeg.datasource.SIM_INFO;
import jp.co.biglobe.isp.mobile.voiceoption.database.*;
import jp.co.biglobe.isp.mobile.voiceoption.datasource.mnpout.db.*;
import jp.co.biglobe.isp.mobile.voiceoption.datasource.voiceengagement.db.FixtureVoiceEngagementState;
import jp.co.biglobe.isp.mobile.voiceoption.domain.executiondate.ExecutionDate;
import jp.co.biglobe.isp.mobile.voiceoption.domain.mnpin.MnpReservationNumber;
import jp.co.biglobe.isp.mobile.voiceoption.domain.mnpout.*;
import jp.co.biglobe.isp.mobile.voiceoption.domain.mnpout.mnpoutcompletion.MnpOutCompletionConfirmedDate;
import jp.co.biglobe.isp.mobile.voiceoption.domain.mnpout.mnpoutreservationnumber.ExpireDate;
import jp.co.biglobe.isp.mobile.voiceoption.domain.mnpout.mnpoutreservationnumber.NullMnpOutReservationNumber;
import jp.co.biglobe.isp.mobile.voiceoption.domain.mnpout.mnpoutreservationnumber.ValidMnpOutReservationNumber;
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

@RunWith(Enclosed.class)
public class MnpOutRepositoryDbTest {

    @RunWith(SpringJUnit4ClassRunner.class)
    @WebAppConfiguration
    @ContextConfiguration(locations = {"classpath:context.xml"})
    public static class register {

        @Autowired
        private MnpOutRepository sut;

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
        public void _新規の転出申し込み() throws Exception {
            // テストデータの準備
            tester.cleanInsertQuery(SIM_INFO.regist);
            tester.cleanInsertQuery(FixtureVoiceEngagementState.One.getDefaultData());

            // 準備
            ValidMnpOut validMnpOut = new ValidMnpOutBuilder()
                    .mnpOutReservationNumber(new NullMnpOutReservationNumber())
                    .build();

            // 実行
            sut.insert(validMnpOut);

            // 評価
            VoiceMnpOutEventAssert voiceMnpOutEventAssert = new VoiceMnpOutEventAssert(tester);
            voiceMnpOutEventAssert.assertTableWithAllColumns(FixtureVoiceMnpOutEvent.One.getForMnpOutRequestFromNoRequest());

            VoiceMnpOutStateAssert voiceMnpOutStateAssert = new VoiceMnpOutStateAssert(tester);
            voiceMnpOutStateAssert.assertTableWithAllColumns(FixtureVoiceMnpOutState.One.getForRequestedWaiting());
        }


        @Test
        public void _転出申し込み_過去に申し込み実績あり_MNP転出番号なし() throws Exception {
            // テストデータの準備
            tester.cleanInsertQuery(SIM_INFO.regist);
            tester.cleanInsertQuery(FixtureVoiceEngagementState.One.getDefaultData());
            tester.cleanInsertQuery(FixtureVoiceMnpOutState.One.getForRequestedWaiting());

            // 準備
            MnpOut oldMnpOut = new ValidMnpOutBuilder()
                    .mnpOutStatus(MnpOutStatus.REQUEST_WAITING)
                    .build();


            ValidMnpOut validMnpOut = new ValidMnpOutBuilder()
                    .mnpOutReservationNumber(new NullMnpOutReservationNumber())
                    .build();

            // 実行
            sut.delete(oldMnpOut);
            sut.insert(validMnpOut);

            // 評価
            VoiceMnpOutEventAssert voiceMnpOutEventAssert = new VoiceMnpOutEventAssert(tester);
            voiceMnpOutEventAssert.assertTableWithAllColumns(FixtureVoiceMnpOutEvent.Two.getRequstedNoMnp());

            VoiceMnpOutStateAssert voiceMnpOutStateAssert = new VoiceMnpOutStateAssert(tester);
            voiceMnpOutStateAssert.assertTableWithAllColumns(FixtureVoiceMnpOutState.One.getForRequestedWaiting());
        }

        @Test
        public void registerのテスト_転出申し込み_過去に申し込み実績あり_MNP転出番号あり() throws Exception {
            // テストデータの準備
            tester.cleanInsertQuery(SIM_INFO.regist);
            tester.cleanInsertQuery(FixtureVoiceEngagementState.Two.getDefaultData());
            tester.cleanInsertQuery(FixtureVoiceMnpOutState.Two.getDefaultData());
            tester.cleanInsertQuery(FixtureVoiceMnpOutPersonalInfoState.Two.getDefaultData());
            tester.cleanInsertQuery(FixtureVoiceMnpOutNumberState.Two.getDefaultData());

            // 準備
            MnpOut oldMnpOut = new ValidMnpOutBuilder()
                    .mnpOutReservationNumber()
                    .build();

            ValidMnpOut validMnpOut = new ValidMnpOutBuilder()
                    .mnpOutReservationNumber(new NullMnpOutReservationNumber())
                    .build();

            // 実行
            sut.delete(oldMnpOut);
            sut.insert(validMnpOut);

            // 評価
            VoiceMnpOutNumberEventAssert voiceMnpOutNumberEventAssert = new VoiceMnpOutNumberEventAssert(tester);
            voiceMnpOutNumberEventAssert.assertTableWithAllColumns(FixtureVoiceMnpOutNumberEvent.One.getForDelete("1"));

            VoiceMnpOutNumberStateAssert VoiceMnpOutNumberStateAssert = new VoiceMnpOutNumberStateAssert(tester);
            VoiceMnpOutNumberStateAssert.assertTableWithAllColumns(FixtureVoiceMnpOutNumberState.One.getDefaultData("2"));

            VoiceMnpOutEventAssert voiceMnpOutEventAssert = new VoiceMnpOutEventAssert(tester);
            voiceMnpOutEventAssert.assertTableWithAllColumns(FixtureVoiceMnpOutEvent.Two.getRequstedWithMnp());

            VoiceMnpOutStateAssert voiceMnpOutStateAssert = new VoiceMnpOutStateAssert(tester);
            voiceMnpOutStateAssert.assertTableWithAllColumns(FixtureVoiceMnpOutState.Two.getRequstedWithMnp());


        }

        @Test
        public void updateのテスト_MNP予約番号発行依頼() throws Exception {
            // テストデータの準備
            tester.cleanInsertQuery(SIM_INFO.regist);
            tester.cleanInsertQuery(FixtureVoiceEngagementState.One.getDefaultData());
            tester.cleanInsertQuery(FixtureVoiceMnpOutState.One.getDefaultData());
            tester.cleanInsertQuery(FixtureVoiceMnpOutPersonalInfoState.One.getDefaultData());

            // 準備
            ValidMnpOut validMnpOut = new ValidMnpOutBuilder()
                    .mnpOutStatus(MnpOutStatus.MNP_RESERVATION_NUMBER_WAITING)
                    .mnpOutReservationNumber(new NullMnpOutReservationNumber())
                    .build();

            // 実行
            sut.update(validMnpOut);

            // 評価
            VoiceMnpOutEventAssert voiceMnpOutEventAssert = new VoiceMnpOutEventAssert(tester);
            voiceMnpOutEventAssert.assertTableWithAllColumns(FixtureVoiceMnpOutEvent.One.getForRequested());

            VoiceMnpOutStateAssert voiceMnpOutStateAssert = new VoiceMnpOutStateAssert(tester);
            voiceMnpOutStateAssert.assertTableWithAllColumns(FixtureVoiceMnpOutState.One.getForRequested());


        }

        @Test
        public void updateのテスト_MNP予約番号発行完了() throws Exception {
            // テストデータの準備
            tester.cleanInsertQuery(SIM_INFO.regist);
            tester.cleanInsertQuery(FixtureVoiceEngagementState.One.getDefaultData());
            tester.cleanInsertQuery(FixtureVoiceMnpOutState.One.getDefaultData());
            tester.cleanInsertQuery(FixtureVoiceMnpOutPersonalInfoState.One.getDefaultData());

            // 準備
            ValidMnpOut validMnpOut = new ValidMnpOutBuilder()
                    .mnpOutStatus(MnpOutStatus.MNP_OUT_WAITING)
                    .mnpOutReservationNumber()
                    .build();

            // 実行
            sut.update(validMnpOut);

            // 評価
            VoiceMnpOutNumberEventAssert voiceMnpOutNumberEventAssert = new VoiceMnpOutNumberEventAssert(tester);
            voiceMnpOutNumberEventAssert.assertTableWithAllColumns(FixtureVoiceMnpOutNumberEvent.One.getDefaultData());

            VoiceMnpOutNumberStateAssert VoiceMnpOutNumberStateAssert = new VoiceMnpOutNumberStateAssert(tester);
            VoiceMnpOutNumberStateAssert.assertTableWithAllColumns(FixtureVoiceMnpOutNumberState.One.getDefaultData());

            VoiceMnpOutEventAssert voiceMnpOutEventAssert = new VoiceMnpOutEventAssert(tester);
            voiceMnpOutEventAssert.assertTableWithAllColumns(FixtureVoiceMnpOutEvent.One.getForNumbered());

            VoiceMnpOutStateAssert voiceMnpOutStateAssert = new VoiceMnpOutStateAssert(tester);
            voiceMnpOutStateAssert.assertTableWithAllColumns(FixtureVoiceMnpOutState.One.getForNumbered());

        }

        @Test
        public void updateのテスト_転出完了() throws Exception {
            // テストデータの準備
            tester.cleanInsertQuery(SIM_INFO.regist);
            tester.cleanInsertQuery(FixtureVoiceEngagementState.One.getDefaultData());
            tester.cleanInsertQuery(FixtureVoiceMnpOutState.One.getDefaultData());
            tester.cleanInsertQuery(FixtureVoiceMnpOutPersonalInfoState.One.getDefaultData());
            tester.cleanInsertQuery(FixtureVoiceMnpOutNumberState.One.getDefaultData());

            // 準備
            // 準備
            ValidMnpOut validMnpOut = new ValidMnpOutBuilder()
                    .mnpOutStatus(MnpOutStatus.MNP_OUT_COMPLETION)
                    .mnpOutDate(new MnpOutCompletionConfirmedDate("20140401"))
                    .mnpOutReservationNumber(new ValidMnpOutReservationNumber(
                            new VoiceMnpOutId(1),
                            new MnpReservationNumber("11-222-33333"),
                            new ExpireDate("20140415"),
                            new ExecutionDate("20140401"),
                            new OperatorId("abc12345")
                    ))
                    .build();

            // 実行
            sut.update(validMnpOut);

            // 評価
            VoiceMnpOutCompleteEventAssert voiceMnpOutCompleteEventAssert = new VoiceMnpOutCompleteEventAssert(tester);
            voiceMnpOutCompleteEventAssert.assertTableWithAllColumns(FixtureVoiceMnpOutCompleteEvent.One.getDefaultData());

            VoiceMnpOutCompleteStateAssert voiceMnpOutCompleteStateAssert = new VoiceMnpOutCompleteStateAssert(tester);
            voiceMnpOutCompleteStateAssert.assertTableWithAllColumns(FixtureVoiceMnpOutCompleteState.One.getDefaultData());

            VoiceMnpOutNumberStateAssert VoiceMnpOutNumberStateAssert = new VoiceMnpOutNumberStateAssert(tester);
            VoiceMnpOutNumberStateAssert.assertTableWithAllColumns(FixtureVoiceMnpOutNumberState.One.getDefaultData());

            VoiceMnpOutEventAssert voiceMnpOutEventAssert = new VoiceMnpOutEventAssert(tester);
            voiceMnpOutEventAssert.assertTableWithAllColumns(FixtureVoiceMnpOutEvent.One.getForCompletion());

            VoiceMnpOutStateAssert voiceMnpOutStateAssert = new VoiceMnpOutStateAssert(tester);
            voiceMnpOutStateAssert.assertTableWithAllColumns(FixtureVoiceMnpOutState.One.getForCompletion());
        }


    }
}
