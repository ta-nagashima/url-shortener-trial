package jp.co.biglobe.isp.mobile.voiceoption.datasource.voiceengagement;

import jp.co.biglobe.isp.auth.domain.user.UserId;
import jp.co.biglobe.lib.publication.date.ForeverDate;
import jp.co.biglobe.isp.mobile.extension.exception.SystemCheckException;
import jp.co.biglobe.isp.mobile.ltethreeg.datasource.SIM_INFO;
import jp.co.biglobe.isp.mobile.ltethreeg.domain.ltethreegengagement.LteThreeGEngagementNumber;
import jp.co.biglobe.isp.mobile.ltethreeg.domain.sim.EquipmentNumber;
import jp.co.biglobe.isp.mobile.voiceoption.database.VoiceEngagementEventAssert;
import jp.co.biglobe.isp.mobile.voiceoption.database.VoiceEngagementStateAssert;
import jp.co.biglobe.isp.mobile.voiceoption.datasource.voiceengagement.db.FixtureVoiceEngagementEvent;
import jp.co.biglobe.isp.mobile.voiceoption.datasource.voiceengagement.db.FixtureVoiceEngagementState;
import jp.co.biglobe.isp.mobile.voiceoption.domain.voiceengagement.*;
import jp.co.biglobe.isp.mobile.voiceoption.domain.voiceengagement.cancel.NotExistVoiceEngagementCancel;
import jp.co.biglobe.isp.mobile.voiceoption.domain.voiceengagement.disengage.*;
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
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

@RunWith(Enclosed.class)
public class VoiceEngagementRepositoryDbTest {

    @RunWith(SpringJUnit4ClassRunner.class)
    @ContextConfiguration(locations = {"classpath:context.xml"})
    public static class findByEquipmentNumberForValidのテスト {

        @Autowired
        VoiceEngagementRepository sut;

        @Autowired
        public DbUnitTester tester;

        @Before
        public void setUp() throws DatabaseUnitException, SQLException, IOException {
            tester.executeAllClearTableAndSeq();
        }

        @Test
        public void findByEquipmentNumberForValid_登録済み() throws Exception {

            // テストケースの設定
            tester.cleanInsertQuery(SIM_INFO.regist);
            tester.cleanInsertQuery(FixtureVoiceEngagementState.One.getDefaultData());

            // 準備
            ValidVoiceEngagement expected = new ValidVoiceEngagementBuilder().build();

            // 実行
            ValidVoiceEngagement actual = sut.findByEquipmentNumberForValid(new EquipmentNumber("00000001"));

            // 確認
            assertThat(actual, is(expected));

        }

        @Test(expected = SystemCheckException.class)
        public void findByEquipmentNumberForValid_未登録() throws Exception {

            // テストケースの設定
            tester.cleanInsertQuery(SIM_INFO.regist);
            tester.cleanInsertQuery(FixtureVoiceEngagementState.One.getDefaultData());

            // 実行
            sut.findByEquipmentNumberForValid(new EquipmentNumber("99999999"));

        }
    }


    @RunWith(SpringJUnit4ClassRunner.class)
    @ContextConfiguration(locations = {"classpath:context.xml"})
    public static class findByEquipmentNumberのテスト {

        @Autowired
        VoiceEngagementRepository sut;

        @Autowired
        public DbUnitTester tester;

        @Before
        public void setUp() throws DatabaseUnitException, SQLException, IOException {
            tester.executeAllClearTableAndSeq();
        }

        @Test
        public void findByEquipmentNumberForValid_登録済み() throws Exception {

            // テストケースの設定
            tester.cleanInsertQuery(SIM_INFO.regist);
            tester.cleanInsertQuery(FixtureVoiceEngagementState.One.getDefaultData());

            // 準備
            VoiceEngagement expected = new ValidVoiceEngagementBuilder().build();

            // 実行
            VoiceEngagement actual = sut.findByEquipmentNumber(new EquipmentNumber("00000001"));

            // 確認
            assertThat(actual, is(expected));

        }

        @Test
        public void findByEquipmentNumberForValid_未登録() throws Exception {

            // テストケースの設定
            tester.cleanInsertQuery(SIM_INFO.regist);
            tester.cleanInsertQuery(FixtureVoiceEngagementState.One.getDefaultData());

            // 準備
            VoiceEngagement expected = new NotExistVoiceEngagement();

            // 実行
            VoiceEngagement actual = sut.findByEquipmentNumber(new EquipmentNumber("99999999"));

            // 確認
            assertThat(actual, is(expected));

        }
    }

    @RunWith(SpringJUnit4ClassRunner.class)
    @WebAppConfiguration
    @ContextConfiguration(locations = {"classpath:context.xml"})
    public static class findByLteThreeGEngagementNumberのテスト {

        @Autowired
        public DbUnitTester tester;

        @Autowired
        VoiceEngagementRepository voiceEngagementRepository;

        //        public final DateTime FIXATION_DATE = new DateTime().plusYears(1).plusMonths(1).plusDays(1);
        public final DateTime FIXATION_DATE = new DateTime(2014, 6, 30, 23, 59, 59, 1);

        @Before
        public void setUp() throws DatabaseUnitException, SQLException, IOException {
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
        public void findByLteThreeGEngagementNumber_未登録() throws Exception {
            // テストケースの設定
            tester.cleanInsertQuery(FixtureVoiceEngagementState.One.getForOrdered());

            // 準備
            VoiceEngagement expected = new NotExistVoiceEngagement();

            // 実行
            VoiceEngagement actual = voiceEngagementRepository.findByLteThreeGEngagementNumberForEnable(new LteThreeGEngagementNumber("99999999"));

            // 確認
            assertThat(actual, is(expected));
        }

        @Test
        public void findByLteThreeGEngagementNumber_申込中() throws Exception {
            // テストケースの設定
            tester.cleanInsertQuery(FixtureVoiceEngagementState.One.getForOrdered());

            // 準備
            VoiceEngagement expected = new ValidVoiceEngagement(
                    new VoiceEngagementNumber(1),
                    VoiceEngagementStatus.ORDERED,
                    new VoiceEngagementStartDate("29991231"),
                    new VoiceEngagementEndDateTime(new ForeverDate().getDate()),
                    new NotExistVoiceEngagementCancel(),
                    new NotExistVoiceEngagementDisengage(),
                    new VoiceEngagementLinkage(new UserId("abc12345"),
                            new LteThreeGEngagementNumber("00000001"),
                            new EquipmentNumber("00000001")),
                    new NewOrderInfo(
                            new VoiceSystemReceiptDateTime(new DateTime(2014, 1, 1, 0, 0, 0).toDate()),
                            new VoiceUserOrderDate("20140101"),
                            new VoiceJoinCode("webyn002")),
                    CancelListOutPutStatus.NOT_OUTPUT,
                    VoiceOrderType.ORDER_WITH_LTE
            );

            // 実行
            VoiceEngagement actual = voiceEngagementRepository.findByLteThreeGEngagementNumberForEnable(new LteThreeGEngagementNumber("00000001"));

            // 確認
            assertThat(actual, is(expected));
        }

        @Test
        public void findByLteThreeGEngagementNumber_契約中() throws Exception {
            // テストケースの設定
            tester.cleanInsertQuery(FixtureVoiceEngagementState.One.getForEngagement(new DateTime(2014, 4, 6, 23, 59, 58)));

            // 準備
            VoiceEngagement expected = new ValidVoiceEngagement(
                    new VoiceEngagementNumber(1),
                    VoiceEngagementStatus.ENGAGED,
                    new VoiceEngagementStartDate("20140406"),
                    new VoiceEngagementEndDateTime(new ForeverDate().getDate()),
                    new NotExistVoiceEngagementCancel(),
                    new NotExistVoiceEngagementDisengage(),
                    new VoiceEngagementLinkage(new UserId("abc12345"),
                            new LteThreeGEngagementNumber("00000001"),
                            new EquipmentNumber("00000001")),
                    new NewOrderInfo(
                            new VoiceSystemReceiptDateTime(new DateTime(2014, 1, 1, 0, 0, 0).toDate()),
                            new VoiceUserOrderDate("20140101"),
                            new VoiceJoinCode("webyn002")),
                    CancelListOutPutStatus.NOT_OUTPUT,
                    VoiceOrderType.ORDER_WITH_LTE
            );

            // 実行
            VoiceEngagement actual = voiceEngagementRepository.findByLteThreeGEngagementNumberForEnable(new LteThreeGEngagementNumber("00000001"));

            // 確認
            assertThat(actual, is(expected));
        }

        @Test
        public void findByLteThreeGEngagementNumber_解約予約中() throws Exception {
            // テストケースの設定
            tester.cleanInsertQuery(FixtureVoiceEngagementState.One.getForDisEngagementReserved());

            // 準備（解約予約は今月末）
            Calendar cal = new DateTime().toGregorianCalendar();
            cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
            Date lastOfMonth = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").parse(new SimpleDateFormat("yyyy/MM/dd").format(cal.getTime()) + " 23:59:59");
            VoiceEngagement expected = new ValidVoiceEngagement(
                    new VoiceEngagementNumber(1),
                    VoiceEngagementStatus.DISENGAGED,
                    new VoiceEngagementStartDate("29991231"),
                    new VoiceEngagementEndDateTime(lastOfMonth),
                    new NotExistVoiceEngagementCancel(),
                    new ValidVoiceEngagementDisengage(
                            new VoiceEngagementNumber(1),
                            VoiceEngagementDisengageReason.USER_REQUEST,
                            new VoiceEngagementDisengageSystemReceiptDateTime(new DateTime(2014, 1, 1, 0, 0, 0).toDate()),
                            new VoiceEngagementDisengageOrderDate("20140101")),
                    new VoiceEngagementLinkage(new UserId("abc12345"),
                            new LteThreeGEngagementNumber("00000001"),
                            new EquipmentNumber("00000001")),
                    new NewOrderInfo(
                            new VoiceSystemReceiptDateTime(new DateTime(2014, 1, 1, 0, 0, 0).toDate()),
                            new VoiceUserOrderDate("20140101"),
                            new VoiceJoinCode("webyn002")),
                    CancelListOutPutStatus.NOT_OUTPUT,
                    VoiceOrderType.ORDER_WITH_LTE
            );

            // 実行
            VoiceEngagement actual = voiceEngagementRepository.findByLteThreeGEngagementNumberForEnable(new LteThreeGEngagementNumber("00000001"));

            // 確認
            assertThat(actual, is(expected));
        }

        @Test
        public void findByLteThreeGEngagementNumber_解約済() throws Exception {
            // テストケースの設定
            tester.cleanInsertQuery(FixtureVoiceEngagementState.One.getForDisEngaged());

            // 準備
            VoiceEngagement expected = new NotExistVoiceEngagement();

            // 実行
            VoiceEngagement actual = voiceEngagementRepository.findByLteThreeGEngagementNumberForEnable(new LteThreeGEngagementNumber("00000001"));

            // 確認
            assertThat(actual, is(expected));
        }

        @Test
        public void findByLteThreeGEngagementNumber_キャンセル() throws Exception {
            // テストケースの設定
            tester.cleanInsertQuery(FixtureVoiceEngagementState.One.getForCancelForDelivery());

            // 準備
            VoiceEngagement expected = new NotExistVoiceEngagement();

            // 実行
            VoiceEngagement actual = voiceEngagementRepository.findByLteThreeGEngagementNumberForEnable(new LteThreeGEngagementNumber("00000001"));

            // 確認
            assertThat(actual, is(expected));
        }
    }

    @RunWith(SpringJUnit4ClassRunner.class)
    @WebAppConfiguration
    @ContextConfiguration(locations = {"classpath:context.xml"})
    public static class deleteのテスト {

        @Autowired
        public DbUnitTester tester;

        @Autowired
        VoiceEngagementRepository sut;

        //        public final DateTime FIXATION_DATE = new DateTime().plusYears(1).plusMonths(1).plusDays(1);
        public final DateTime FIXATION_DATE = new DateTime(2014, 01, 01, 00, 00, 00, 0);

        @Before
        public void setUp() throws DatabaseUnitException, SQLException, IOException {
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

        public deleteのテスト() {
            super();
        }

        @Test
        public void delete_正常() throws Exception {
            // テストデータの準備
            tester.cleanInsertQuery(SIM_INFO.regist);
            tester.cleanInsertQuery(FixtureVoiceEngagementState.One.getForOrdered());

            // 準備
            ValidVoiceEngagement validVoiceEngagement = new ValidVoiceEngagementBuilder().voiceEngagementNumber(1).build();

            // 実行
            sut.delete(validVoiceEngagement);

            VoiceEngagementEventAssert voiceEngagementEventAssert = new VoiceEngagementEventAssert(tester);
            voiceEngagementEventAssert.assertTableWithAllColumns(FixtureVoiceEngagementEvent.One.getForEventTypeToDelete());

            VoiceEngagementStateAssert voiceEngagementStateAssert = new VoiceEngagementStateAssert(tester);
            voiceEngagementStateAssert.assertTableNoCount();

        }
    }
}
