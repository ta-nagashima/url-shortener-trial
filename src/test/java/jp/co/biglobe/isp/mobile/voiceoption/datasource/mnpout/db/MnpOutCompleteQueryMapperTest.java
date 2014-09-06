package jp.co.biglobe.isp.mobile.voiceoption.datasource.mnpout.db;

import jp.co.biglobe.isp.auth.domain.operatornouser.OperatorId;
import jp.co.biglobe.isp.mobile.extension.date.SystemDateTime;
import jp.co.biglobe.isp.mobile.ltethreeg.datasource.SIM_INFO;
import jp.co.biglobe.isp.mobile.voiceoption.database.VoiceMnpOutCompleteEventAssert;
import jp.co.biglobe.isp.mobile.voiceoption.database.VoiceMnpOutCompleteStateAssert;
import jp.co.biglobe.isp.mobile.voiceoption.datasource.voiceengagement.db.FixtureVoiceEngagementState;
import jp.co.biglobe.isp.mobile.voiceoption.domain.executiondate.ExecutionDate;
import jp.co.biglobe.isp.mobile.voiceoption.domain.mnpin.MnpReservationNumber;
import jp.co.biglobe.isp.mobile.voiceoption.domain.mnpout.MnpOutStatus;
import jp.co.biglobe.isp.mobile.voiceoption.domain.mnpout.ValidMnpOut;
import jp.co.biglobe.isp.mobile.voiceoption.domain.mnpout.ValidMnpOutBuilder;
import jp.co.biglobe.isp.mobile.voiceoption.domain.mnpout.VoiceMnpOutId;
import jp.co.biglobe.isp.mobile.voiceoption.domain.mnpout.mnpoutcompletion.MnpOutCompletionConfirmedDate;
import jp.co.biglobe.isp.mobile.voiceoption.domain.mnpout.mnpoutcompletion.ValidMnpOutCompletion;
import jp.co.biglobe.isp.mobile.voiceoption.domain.mnpout.mnpoutreservationnumber.ExpireDate;
import jp.co.biglobe.isp.mobile.voiceoption.domain.mnpout.mnpoutreservationnumber.ValidMnpOutReservationNumber;
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

@RunWith(Enclosed.class)
public class MnpOutCompleteQueryMapperTest {

    @RunWith(SpringJUnit4ClassRunner.class)
    @WebAppConfiguration
    @ContextConfiguration(locations = {"classpath:context.xml"})
    public static class 更新系のテスト {

        @Autowired
        SqlSessionTemplate session;

        private MnpOutCompleteQueryMapper sut;

        @Autowired
        DbUnitTester tester;

        public final DateTime FIXATION_DATE = new DateTime(2014, 1, 1, 0, 0, 0);

        @Autowired
        private RequestEventProcess requestEventProcess;

        @Before
        public void setUp() throws DatabaseUnitException, SQLException, IOException {
            tester.executeAllClearTableAndSeq();
            sut = session.getMapper(MnpOutCompleteQueryMapper.class);

            // 「現在時刻」を固定化する
            DateTimeUtils.setCurrentMillisFixed(FIXATION_DATE.getMillis());
        }

        @After
        public void tearDown() {
            // 「現在時刻」をシステム日付に戻す
            DateTimeUtils.setCurrentMillisSystem();
        }

        @Test
        public void _insertNewVoiceMnpOutCompleteEvent() throws Exception {
            // テストデータの準備
            insertTestData(tester);

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
            sut.insertEvent(
                    EventType.INSERT,
                    new SystemDateTime().getValue(),
                    requestEventProcess.getValue(),
                    (ValidMnpOutCompletion)validMnpOut.getMnpOutCompletion()
            );

            // 評価
            VoiceMnpOutCompleteEventAssert voiceMnpOutCompleteEventAssert = new VoiceMnpOutCompleteEventAssert(tester);
            voiceMnpOutCompleteEventAssert.assertTableWithAllColumns(FixtureVoiceMnpOutCompleteEvent.One.getDefaultData());
        }

        @Test
        public void _insertMnpOutCompleteState() throws Exception {
            // テストデータの準備
            insertTestData(tester);

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
            sut.insertState((ValidMnpOutCompletion)validMnpOut.getMnpOutCompletion());

            // 評価
            VoiceMnpOutCompleteStateAssert voiceMnpOutCompleteStateAssert = new VoiceMnpOutCompleteStateAssert(tester);
            voiceMnpOutCompleteStateAssert.assertTableWithAllColumns(FixtureVoiceMnpOutCompleteState.One.getDefaultData());
        }


        /**
         * テストデータをInsertする部分を共通メソッド化
         * （@Beforeに書いたら動かなかった）
         */
        private void insertTestData(DbUnitTester tester){

            tester.cleanInsertQuery(SIM_INFO.regist);
            tester.cleanInsertQuery(FixtureVoiceEngagementState.One.getDefaultData());
            tester.cleanInsertQuery(FixtureVoiceMnpOutState.One.getDefaultData());
            tester.cleanInsertQuery(FixtureVoiceMnpOutNumberState.One.getDefaultData());
        }


    }
}
