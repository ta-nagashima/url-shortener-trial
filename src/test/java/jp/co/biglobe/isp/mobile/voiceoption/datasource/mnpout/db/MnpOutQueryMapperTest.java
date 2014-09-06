package jp.co.biglobe.isp.mobile.voiceoption.datasource.mnpout.db;

import jp.co.biglobe.isp.mobile.ltethreeg.datasource.SIM_INFO;
import jp.co.biglobe.isp.mobile.ltethreeg.domain.sim.EquipmentNumber;
import jp.co.biglobe.isp.mobile.voiceoption.datasource.voiceengagement.db.FixtureVoiceEngagementState;
import jp.co.biglobe.isp.mobile.voiceoption.domain.mnpout.MnpOut;
import jp.co.biglobe.isp.mobile.voiceoption.domain.mnpout.MnpOutStatus;
import jp.co.biglobe.isp.mobile.voiceoption.domain.mnpout.ValidMnpOutBuilder;
import jp.co.biglobe.isp.mobile.voiceoption.domain.mnpout.mnpoutcompletion.MnpOutCompletionConfirmedDate;
import jp.co.biglobe.isp.mobile.voiceoption.domain.mnpout.mnpoutreservationnumber.NullMnpOutReservationNumber;
import jp.co.biglobe.isp.mobile.voiceoption.domain.voiceengagement.VoiceEngagementNumber;
import jp.co.biglobe.test.util.dbunit.DbUnitTester;
import org.dbunit.DatabaseUnitException;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;
import java.sql.SQLException;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

@RunWith(Enclosed.class)
public class MnpOutQueryMapperTest {

    @RunWith(Enclosed.class)
    public static class select系のテスト {

        @RunWith(SpringJUnit4ClassRunner.class)
        @ContextConfiguration(locations = {"classpath:context.xml"})
        public static class _findByVoiceEngagementNumber {

            @Autowired
            SqlSessionTemplate session;

            private MnpOutQueryMapper sut;

            @Autowired
            DbUnitTester tester;

            @Before
            public void setUp() throws DatabaseUnitException, SQLException, IOException {
                tester.executeAllClearTableAndSeq();
                sut = session.getMapper(MnpOutQueryMapper.class);
            }

            @Test
            public void _MNP転出登録済み_MNP予約番号未登録() throws Exception {
                // テストデータの準備
                tester.cleanInsertQuery(SIM_INFO.regist);
                tester.cleanInsertQuery(FixtureVoiceEngagementState.One.getDefaultData());
                tester.cleanInsertQuery(FixtureVoiceMnpOutState.One.getForRequestedWaiting());

                // 準備
                MnpOut expected = new ValidMnpOutBuilder()
                        .mnpOutReservationNumber(new NullMnpOutReservationNumber())
                        .build();

                // 実行
                MnpOut actual = sut.findByVoiceEngagementNumber(new VoiceEngagementNumber(new Integer(1)));

                // 評価
                assertThat(actual, is(expected));

            }

            @Test
            public void _MNP転出登録済み_MNP予約番号発行() throws Exception {
                // テストデータの準備
                tester.cleanInsertQuery(SIM_INFO.regist);
                tester.cleanInsertQuery(FixtureVoiceEngagementState.One.getDefaultData());
                tester.cleanInsertQuery(FixtureVoiceMnpOutState.One.getForNumbered());

                // 準備
                MnpOut expected = new ValidMnpOutBuilder()
                        .mnpOutStatus(MnpOutStatus.MNP_OUT_WAITING)
                        .mnpOutReservationNumber()
                        .build();

                // 実行
                MnpOut actual = sut.findByVoiceEngagementNumber(new VoiceEngagementNumber(new Integer(1)));

                // 評価
                assertThat(actual, is(expected));

            }

            @Test
            public void _MNP転出解約済み_MNP予約番号発行() throws Exception {
                // テストデータの準備
                tester.cleanInsertQuery(SIM_INFO.regist);
                tester.cleanInsertQuery(FixtureVoiceEngagementState.One.getDefaultData());
                tester.cleanInsertQuery(FixtureVoiceMnpOutState.One.getForCompletion());

                // 準備
                MnpOut expected = new ValidMnpOutBuilder()
                        .mnpOutDate(new MnpOutCompletionConfirmedDate("20140401"))
                        .mnpOutReservationNumber()
                        .mnpOutStatus(MnpOutStatus.MNP_OUT_COMPLETION)
                        .build();

                // 実行
                MnpOut actual = sut.findByVoiceEngagementNumber(new VoiceEngagementNumber(new Integer(1)));

                // 評価
                assertThat(actual, is(expected));

            }

        }
    }

    /**
     * 以下のテストは、参照制約の関係でテストデータの準備が難しいため、上位層のテストで担保する
     */

    /*
    @RunWith(SpringJUnit4ClassRunner.class)
    @WebAppConfiguration
    @ContextConfiguration(locations = {"classpath:context.xml"})
    public static class 更新系のテスト {

        @Autowired
        SqlSessionTemplate session;

        private MnpOutQueryMapper sut;

        @Autowired
        DbUnitTester tester;

        public final DateTime FIXATION_DATE = new DateTime(2014, 1, 1, 0, 0, 0);

        @Autowired
        private RequestEventProcess requestEventProcess;

        @Before
        public void setUp() throws DatabaseUnitException, SQLException, IOException {
            tester.executeAllClearTableAndSeq();
            sut = session.getMapper(MnpOutQueryMapper.class);

            // 「現在時刻」を固定化する
            DateTimeUtils.setCurrentMillisFixed(FIXATION_DATE.getMillis());
        }

        @After
        public void tearDown() {
            // 「現在時刻」をシステム日付に戻す
            DateTimeUtils.setCurrentMillisSystem();
        }

        @Test
        public void insertNewMnpOutEvent_insertMnpOutStateのテスト() throws Exception {
            // テストデータの準備
            tester.cleanInsertQuery(SIM_INFO.regist);
            tester.cleanInsertQuery(FixtureVoiceEngagementState.One.getForProvisional());

            // 準備
            ValidMnpOut validMnpOut = new ValidMnpOutBuilder()
                    .mnpOutReservationNumber(new NullMnpOutReservationNumber())
                    .build();

            MnpOutId mnpOutId = new MnpOutId(new Integer(1));

            // 実行
            sut.insertNewEvent(
                    EventType.INSERT,
                    mnpOutId,
                    new SystemDateTime().getValue(),
                    requestEventProcess.getValue(),
                    validMnpOut
            );
            sut.insertState(mnpOutId, validMnpOut);

            // 評価
            VoiceMnpOutEventAssert voiceMnpOutEventAssert = new VoiceMnpOutEventAssert(tester);
            voiceMnpOutEventAssert.assertTableWithAllColumns(ExpectedVoiceMnpOutEvent.One.getForProvisional());

            VoiceMnpOutStateAssert voiceMnpOutStateAssert = new VoiceMnpOutStateAssert(tester);
            voiceMnpOutStateAssert.assertTableWithAllColumns(ExpectedVoiceMnpOutState.One.getForProvisional());
        }

        @Test
        public void insertUpdateMnpOutEvent_updateMnpOutStateのテスト() throws Exception {
            // テストデータの準備
            tester.cleanInsertQuery(SIM_INFO.regist);
            tester.cleanInsertQuery(FixtureVoiceEngagementState.One.getForProvisional());
            tester.cleanInsertQuery(FixtureVoiceMnpOutState2.One.getForRequestedWaiting());

            // 準備
            ValidMnpOut validMnpOut = new ValidMnpOutBuilder()
                    .mnpOutStatus(MnpOutStatus.MNP_RESERVATION_NUMBER_WAITING)
                    .mnpOutReservationNumber(new NullMnpOutReservationNumber())
                    .build();

            // 実行
            sut.insertUpdateEvent(
                    EventType.UPDATE,
                    new SystemDateTime().getValue(),
                    requestEventProcess.getValue(),
                    validMnpOut
            );
            sut.updateState(validMnpOut);

            // 評価
            VoiceMnpOutEventAssert voiceMnpOutEventAssert = new VoiceMnpOutEventAssert(tester);
            voiceMnpOutEventAssert.assertTableWithAllColumns(ExpectedVoiceMnpOutEvent.One.getForRequested());

            VoiceMnpOutStateAssert2 voiceMnpOutStateAssert = new VoiceMnpOutStateAssert2(tester);
            voiceMnpOutStateAssert.assertTableWithAllColumns(FixtureVoiceMnpOutState2.One.getForRequested());
        }

        @Test
        public void deleteMnpOutStateのテスト() throws Exception {

            // 削除した際に、指定されたレコードだけ削除されることを評価するために、
            // あえて複数レコードを事前に登録しておく。

            // テストデータの準備
            tester.cleanInsertQuery(SIM_INFO.regist);
            tester.cleanInsertQuery(FixtureVoiceEngagementState.Two.getForProvisional());
            tester.cleanInsertQuery(FixtureVoiceMnpOutState.Two.getForProvisional());

            // 準備
            ValidMnpOut validMnpOut = new ValidMnpOutBuilder()
                    .voiceEngagementNumber(new VoiceEngagementNumber(new Integer(2)))
                    .mnpOutStatus(MnpOutStatus.MNP_OUT_CANCEL)
                    .mnpOutCancelReason(MnpOutCancelReason.USER_REQUEST)
                    .mnpOutReservationNumber(new NullMnpOutReservationNumber())
                    .build();

            // 実行
            sut.deleteState(validMnpOut);

            // 評価
            VoiceMnpOutStateAssert voiceMnpOutStateAssert = new VoiceMnpOutStateAssert(tester);
            voiceMnpOutStateAssert.assertTableWithAllColumns(ExpectedVoiceMnpOutState.One.getForProvisional());

        }
    }
    */
}
