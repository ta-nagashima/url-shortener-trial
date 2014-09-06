package jp.co.biglobe.isp.mobile.voiceoption.api.engagement.engagementdetailrefer;

import jp.co.biglobe.isp.mobile.ltethreeg.datasource.SIM_INFO;
import jp.co.biglobe.isp.mobile.voiceoption.datasource.charge.engagementmonthdisengagementcharge.db.FixtureVoiceEngagementMonthDisengagementChargeState;
import jp.co.biglobe.isp.mobile.voiceoption.datasource.identification.db.FixtureVoiceIdentificationState;
import jp.co.biglobe.isp.mobile.voiceoption.datasource.mnpin.db.FixtureVoiceMnpInState;
import jp.co.biglobe.isp.mobile.voiceoption.datasource.mnpout.db.FixtureVoiceMnpOutState;
import jp.co.biglobe.isp.mobile.voiceoption.datasource.voiceengagement.db.FixtureVoiceEngagementState;
import jp.co.biglobe.test.util.dbunit.DbUnitTester;
import jp.co.biglobe.test.util.response.ReferApiResponseAssert;
import jp.co.biglobe.test.util.response.ValidationErrorResponseAssert;
import jp.co.biglobe.test.util.usecase.BobioUseCase;
import org.dbunit.DatabaseUnitException;
import org.joda.time.DateTime;
import org.joda.time.DateTimeUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Enclosed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.web.context.WebApplicationContext;

import java.io.IOException;
import java.sql.SQLException;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;


@RunWith(Enclosed.class)
public class VoiceEngagementDetailReferApiTest {

    private static final String URI = "/voiceoption/outside/skip/engagementdetail/refer";

    @RunWith(SpringJUnit4ClassRunner.class)
    @WebAppConfiguration
    @ContextConfiguration(locations = {"classpath:context.xml"})
    public static class _概要のテスト {

        public final DateTime FIXATION_DATE = new DateTime(2014, 1, 1, 0, 0, 0);

        @Autowired
        private WebApplicationContext wac;
        private MockMvc mockMvc;

        @Autowired
        public DbUnitTester tester;

        @Before
        public void setup() throws DatabaseUnitException, SQLException, IOException {
            mockMvc = webAppContextSetup(wac).build();


            // 「現在時刻」を固定化する
            DateTimeUtils.setCurrentMillisFixed(FIXATION_DATE.getMillis());

            // 処理前にテーブルをクリアする
            tester.executeAllClearTableAndSeq();
        }

        @After
        public void tearDown() {
            // 「現在時刻」をシステム日付に戻す
            DateTimeUtils.setCurrentMillisSystem();
        }

        @Test
        public void test_未契約() throws Exception {
            // テストケースの設定;

            // 実行
            ResultActions resultActions = mockMvc.perform(post(URI)
                    .param("equipmentNumberForm", "00000001"));

            // 確認・JASON
            ReferApiResponseAssert.assertJsonPath(resultActions);
            resultActions.andExpect(jsonPath("$.voiceEngagementResult").value("not_exist"));
            resultActions.andExpect(jsonPath("$.mnpInResult").doesNotExist());
            resultActions.andExpect(jsonPath("$.mnpOutResult").doesNotExist());
            resultActions.andExpect(jsonPath("$.voiceEngagement").doesNotExist());
            resultActions.andExpect(jsonPath("$.mnpIn").doesNotExist());
            resultActions.andExpect(jsonPath("$.mnpOut").doesNotExist());

        }

        @Test
        public void test_申込中_転入なし_転出なし() throws Exception {
            // テストケースの設定;
            tester.cleanInsertQuery(SIM_INFO.regist);
            tester.cleanInsertQuery(FixtureVoiceEngagementState.One.getForEngagement());

            // 実行
            ResultActions resultActions = mockMvc.perform(post(URI)
                    .param("equipmentNumberForm", "00000001"));

            // 確認・JASON
            ReferApiResponseAssert.assertJsonPath(resultActions);
            resultActions.andExpect(jsonPath("$.voiceEngagementResult").value("exist"));
            resultActions.andExpect(jsonPath("$.mnpInResult").value("not_exist"));
            resultActions.andExpect(jsonPath("$.mnpOutResult").value("not_exist"));
            resultActions.andExpect(jsonPath("$.voiceEngagement").exists());
            resultActions.andExpect(jsonPath("$.mnpIn").doesNotExist());
            resultActions.andExpect(jsonPath("$.mnpOut").doesNotExist());
        }

        @Test
        public void test_申込中_転入あり_転出なし() throws Exception {
            // テストケースの設定;
            tester.cleanInsertQuery(SIM_INFO.regist);
            tester.cleanInsertQuery(FixtureVoiceEngagementState.One.getForEngagement());
            tester.cleanInsertQuery(FixtureVoiceMnpInState.One.getForProvisional());

            // 実行
            ResultActions resultActions = mockMvc.perform(post(URI)
                    .param("equipmentNumberForm", "00000001"));

            // 確認・JASON
            ReferApiResponseAssert.assertJsonPath(resultActions);

            resultActions.andExpect(jsonPath("$.voiceEngagementResult").value("exist"));
            resultActions.andExpect(jsonPath("$.mnpInResult").value("exist"));
            resultActions.andExpect(jsonPath("$.mnpOutResult").value("not_exist"));
            resultActions.andExpect(jsonPath("$.voiceEngagement").exists());
            resultActions.andExpect(jsonPath("$.mnpIn").exists());
            resultActions.andExpect(jsonPath("$.mnpOut").doesNotExist());

        }

        @Test
        public void test_申込中_転入なし_転出あり() throws Exception {
            // テストケースの設定;
            tester.cleanInsertQuery(SIM_INFO.regist);
            tester.cleanInsertQuery(FixtureVoiceEngagementState.One.getForEngagement());
            tester.cleanInsertQuery(FixtureVoiceMnpOutState.One.getDefaultData());

            // 実行
            ResultActions resultActions = mockMvc.perform(post(URI)
                    .param("equipmentNumberForm", "00000001"));

            // 確認・JASON
            ReferApiResponseAssert.assertJsonPath(resultActions);

            resultActions.andExpect(jsonPath("$.voiceEngagementResult").value("exist"));
            resultActions.andExpect(jsonPath("$.mnpInResult").value("not_exist"));
            resultActions.andExpect(jsonPath("$.mnpOutResult").value("exist"));
            resultActions.andExpect(jsonPath("$.voiceEngagement").exists());
            resultActions.andExpect(jsonPath("$.mnpIn").doesNotExist());
            resultActions.andExpect(jsonPath("$.mnpOut").exists());

        }

        @Test
        public void test_申込中_転入あり_転出あり() throws Exception {
            // テストケースの設定;
            tester.cleanInsertQuery(SIM_INFO.regist);
            tester.cleanInsertQuery(FixtureVoiceEngagementState.One.getForEngagement());
            tester.cleanInsertQuery(FixtureVoiceMnpInState.One.getForProvisional());
            tester.cleanInsertQuery(FixtureVoiceMnpOutState.One.getDefaultData());

            // 実行
            ResultActions resultActions = mockMvc.perform(post(URI)
                    .param("equipmentNumberForm", "00000001"));

            // 確認・JASON
            ReferApiResponseAssert.assertJsonPath(resultActions);

            resultActions.andExpect(jsonPath("$.voiceEngagementResult").value("exist"));
            resultActions.andExpect(jsonPath("$.mnpInResult").value("exist"));
            resultActions.andExpect(jsonPath("$.mnpOutResult").value("exist"));
            resultActions.andExpect(jsonPath("$.voiceEngagement").exists());
            resultActions.andExpect(jsonPath("$.mnpIn").exists());
            resultActions.andExpect(jsonPath("$.mnpOut").exists());

            resultActions.andExpect(jsonPath("$.engagement_month_disengagement_charge.executeDateTime").value(""));
            resultActions.andExpect(jsonPath("$.engagement_month_disengagement_charge.amount").value(""));

        }

        @Test
        public void test_申込中_転入あり_転出あり_契約月解約課金あり() throws Exception {
            // テストケースの設定;
            tester.cleanInsertQuery(SIM_INFO.regist);
            tester.cleanInsertQuery(FixtureVoiceEngagementState.One.getForEngagement());
            tester.cleanInsertQuery(FixtureVoiceMnpInState.One.getForProvisional());
            tester.cleanInsertQuery(FixtureVoiceMnpOutState.One.getDefaultData());
            tester.cleanInsertQuery(FixtureVoiceEngagementMonthDisengagementChargeState.One.getDefaultData());

            // 実行
            ResultActions resultActions = mockMvc.perform(post(URI)
                    .param("equipmentNumberForm", "00000001"));

            // 確認・JASON
            ReferApiResponseAssert.assertJsonPath(resultActions);

            resultActions.andExpect(jsonPath("$.voiceEngagementResult").value("exist"));
            resultActions.andExpect(jsonPath("$.mnpInResult").value("exist"));
            resultActions.andExpect(jsonPath("$.mnpOutResult").value("exist"));
            resultActions.andExpect(jsonPath("$.voiceEngagement").exists());
            resultActions.andExpect(jsonPath("$.mnpIn").exists());
            resultActions.andExpect(jsonPath("$.mnpOut").exists());

            resultActions.andExpect(jsonPath("$.engagement_month_disengagement_charge.executeDateTime").value("20140101000000"));
            resultActions.andExpect(jsonPath("$.engagement_month_disengagement_charge.amount").value("900"));

        }

        @Test
        public void test_本人確認() throws Exception {
            // テストケースの設定;
            tester.cleanInsertQuery(SIM_INFO.regist);
            tester.cleanInsertQuery(FixtureVoiceEngagementState.One.getForEngagement());
            tester.cleanInsertQuery(FixtureVoiceIdentificationState.One.getDefaultData());

            // 実行
            ResultActions resultActions = mockMvc.perform(post(URI)
                    .param("equipmentNumberForm", "00000001"));

            // 確認・JASON
            ReferApiResponseAssert.assertJsonPath(resultActions);
            resultActions.andExpect(jsonPath("$.voiceEngagementResult").value("exist"));
            resultActions.andExpect(jsonPath("$.identificationResult").value("exist"));
            resultActions.andExpect(jsonPath("$.identification.voiceClerkRequestStatus").value("requested"));
            resultActions.andExpect(jsonPath("$.mnpInResult").value("not_exist"));
            resultActions.andExpect(jsonPath("$.mnpOutResult").value("not_exist"));
            resultActions.andExpect(jsonPath("$.voiceEngagement").exists());
            resultActions.andExpect(jsonPath("$.mnpIn").doesNotExist());
            resultActions.andExpect(jsonPath("$.mnpOut").doesNotExist());
        }

        @Test
        public void test_仮受付() throws Exception {
            // テストケースの設定;
            tester.cleanInsertQuery(SIM_INFO.regist);
            tester.cleanInsertQuery(FixtureVoiceEngagementState.One.getForEngagement());
            tester.cleanInsertQuery(FixtureVoiceIdentificationState.One.getForProvisional());

            // 実行
            ResultActions resultActions = mockMvc.perform(post(URI)
                    .param("equipmentNumberForm", "00000001"));

            // 確認・JASON
            ReferApiResponseAssert.assertJsonPath(resultActions);
            resultActions.andExpect(jsonPath("$.voiceEngagementResult").value("exist"));
            resultActions.andExpect(jsonPath("$.identificationResult").value("exist"));
            resultActions.andExpect(jsonPath("$.identification.voiceClerkRequestStatus").value("unrequested"));
            resultActions.andExpect(jsonPath("$.mnpInResult").value("not_exist"));
            resultActions.andExpect(jsonPath("$.mnpOutResult").value("not_exist"));
            resultActions.andExpect(jsonPath("$.voiceEngagement").exists());
            resultActions.andExpect(jsonPath("$.mnpIn").doesNotExist());
            resultActions.andExpect(jsonPath("$.mnpOut").doesNotExist());
        }

        @Test
        public void test_本人確認不要() throws Exception {
            // テストケースの設定;
            tester.cleanInsertQuery(SIM_INFO.regist);
            tester.cleanInsertQuery(FixtureVoiceEngagementState.One.getForEngagement());
            tester.cleanInsertQuery(FixtureVoiceIdentificationState.One.getForNoShipping());

            // 実行
            ResultActions resultActions = mockMvc.perform(post(URI)
                    .param("equipmentNumberForm", "00000001"));

            // 確認・JASON
            ReferApiResponseAssert.assertJsonPath(resultActions);
            resultActions.andExpect(jsonPath("$.voiceEngagementResult").value("exist"));
            resultActions.andExpect(jsonPath("$.identificationResult").value("exist"));
            resultActions.andExpect(jsonPath("$.identification.voiceClerkRequestStatus").value("unnecessary"));
            resultActions.andExpect(jsonPath("$.mnpInResult").value("not_exist"));
            resultActions.andExpect(jsonPath("$.mnpOutResult").value("not_exist"));
            resultActions.andExpect(jsonPath("$.voiceEngagement").exists());
            resultActions.andExpect(jsonPath("$.mnpIn").doesNotExist());
            resultActions.andExpect(jsonPath("$.mnpOut").doesNotExist());
        }

    }

    @RunWith(Enclosed.class)
    public static class entitytest {

        @RunWith(SpringJUnit4ClassRunner.class)
        @WebAppConfiguration
        @ContextConfiguration(locations = {"classpath:context.xml"})
        public static class _音声通話契約のテスト {

            public final DateTime FIXATION_DATE = new DateTime(2014, 1, 1, 0, 0, 0);

            @Autowired
            private WebApplicationContext wac;
            private MockMvc mockMvc;

            @Autowired
            public DbUnitTester tester;

            @Before
            public void setup() throws DatabaseUnitException, SQLException, IOException {
                mockMvc = webAppContextSetup(wac).build();


                // 「現在時刻」を固定化する
                DateTimeUtils.setCurrentMillisFixed(FIXATION_DATE.getMillis());

                // 処理前にテーブルをクリアする
                tester.executeAllClearTableAndSeq();
            }

            @After
            public void tearDown() {
                // 「現在時刻」をシステム日付に戻す
                DateTimeUtils.setCurrentMillisSystem();
            }

            private void assertResponse(ResultActions resultActions , String status, String endDate) throws Exception{
                resultActions.andExpect(jsonPath("$.voiceEngagement.voiceEngagementNumber").value("1"));
                resultActions.andExpect(jsonPath("$.voiceEngagement.equipmentNumber").value("00000001"));
                resultActions.andExpect(jsonPath("$.voiceEngagement.lteThreeGEngagementNumber").value("00000001"));
                resultActions.andExpect(jsonPath("$.voiceEngagement.userId").value("abc12345"));
                resultActions.andExpect(jsonPath("$.voiceEngagement.voiceJoinCode").value("webyn002"));
                resultActions.andExpect(jsonPath("$.voiceEngagement.voiceEngagementStatus").value(status));
                resultActions.andExpect(jsonPath("$.voiceEngagement.voiceUserOrderDate").value("20140101"));
                resultActions.andExpect(jsonPath("$.voiceEngagement.voiceSystemReceiptDateTime").value("20140101000000"));
                resultActions.andExpect(jsonPath("$.voiceEngagement.voiceEngagementStartDate").value("29991231"));
                resultActions.andExpect(jsonPath("$.voiceEngagement.voiceEngagementEndDateTime").value(endDate));
            }

            @Test
            public void test_申込中() throws Exception {
                // テストケースの設定;
                tester.cleanInsertQuery(SIM_INFO.regist);
                tester.cleanInsertQuery(FixtureVoiceEngagementState.One.getDefaultData());

                // 実行
                ResultActions resultActions = mockMvc.perform(post(URI).param("equipmentNumberForm", "00000001"));

                // 確認・JASON
                assertResponse(resultActions, "ordered", "29991231235959");

            }

            @Test
            public void test_契約中() throws Exception {
                // テストケースの設定;
                tester.cleanInsertQuery(SIM_INFO.regist);
                tester.cleanInsertQuery(FixtureVoiceEngagementState.One.getForEngagement());

                // 実行
                ResultActions resultActions = mockMvc.perform(post(URI).param("equipmentNumberForm", "00000001"));

                // 確認・JASON
                assertResponse(resultActions, "engaged", "29991231235959");

            }

            @Test
            public void test_解約予約中() throws Exception {
                // テストケースの設定;
                tester.cleanInsertQuery(SIM_INFO.regist);
                tester.cleanInsertQuery(FixtureVoiceEngagementState.One.getForDisEngagementReserved());

                // 実行
                ResultActions resultActions = mockMvc.perform(post(URI).param("equipmentNumberForm", "00000001"));

                // 確認・JASON
                assertResponse(resultActions, "disengaged_reservation", "20140131235959");

            }


            @Test
            public void test_解約中() throws Exception {
                // テストケースの設定;
                tester.cleanInsertQuery(SIM_INFO.regist);
                tester.cleanInsertQuery(FixtureVoiceEngagementState.One.getForDisEngaged());

                // 実行
                ResultActions resultActions = mockMvc.perform(post(URI).param("equipmentNumberForm", "00000001"));

                // 確認・JASON
                assertResponse(resultActions, "disengaged", "20131231235959");

            }

            @Test
            public void test_キャンセル() throws Exception {
                // テストケースの設定;
                tester.cleanInsertQuery(SIM_INFO.regist);
                tester.cleanInsertQuery(FixtureVoiceEngagementState.One.getForCancelForCourse());

                // 実行
                ResultActions resultActions = mockMvc.perform(post(URI).param("equipmentNumberForm", "00000001"));

                // 確認・JASON
                assertResponse(resultActions, "canceled", "29991231235959");

            }
        }

        @RunWith(SpringJUnit4ClassRunner.class)
        @WebAppConfiguration
        @ContextConfiguration(locations = {"classpath:context.xml"})
        public static class _amountのテスト {

            public final DateTime FIXATION_DATE = new DateTime(2014, 1, 1, 0, 0, 0);

            private static final String SCENARIO_NAME = "C_iyakukin_mo_ref";

            private static BobioUseCase testcase = new BobioUseCase(SCENARIO_NAME);

            @Autowired
            private WebApplicationContext wac;
            private MockMvc mockMvc;

            @Autowired
            public DbUnitTester tester;

            @Before
            public void setup() throws DatabaseUnitException, SQLException, IOException {
                mockMvc = webAppContextSetup(wac).build();


                // 「現在時刻」を固定化する
                DateTimeUtils.setCurrentMillisFixed(FIXATION_DATE.getMillis());

                // 処理前にテーブルをクリアする
                tester.executeAllClearTableAndSeq();
            }

            @After
            public void tearDown() throws IOException{
                // 「現在時刻」をシステム日付に戻す
                DateTimeUtils.setCurrentMillisSystem();

                testcase.unset();
            }

            @Test
            public void test_最低利用期間の契約解除料発生中() throws Exception {
                // テストケースの設定;
                tester.cleanInsertQuery(SIM_INFO.lte);
                tester.cleanInsertQuery(FixtureVoiceEngagementState.One.getForEngagement());
                testcase.set("NOTEND");

                // 実行
                ResultActions resultActions = mockMvc.perform(post(URI).param("equipmentNumberForm", "00000001"));

                // 確認・JASON
                resultActions.andExpect(jsonPath("$.disengagement_charge.status").value("cost"));
                resultActions.andExpect(jsonPath("$.disengagement_charge.end_month").value("299912"));
                resultActions.andExpect(jsonPath("$.disengagement_charge.amount").value("6000"));

            }

            @Test
            public void test_最低利用期間の契約解除料発生しない() throws Exception {
                // テストケースの設定;
                tester.cleanInsertQuery(SIM_INFO.lte);
                tester.cleanInsertQuery(FixtureVoiceEngagementState.One.getForEngagement());
                testcase.set("NOTEXIST");

                // 実行
                ResultActions resultActions = mockMvc.perform(post(URI).param("equipmentNumberForm", "00000001"));

                // 確認・JASON
                resultActions.andExpect(jsonPath("$.disengagement_charge.status").value("not_cost"));
                resultActions.andExpect(jsonPath("$.disengagement_charge.end_month").value(""));
                resultActions.andExpect(jsonPath("$.disengagement_charge.amount").value(""));

            }

        }

        @RunWith(SpringJUnit4ClassRunner.class)
        @WebAppConfiguration
        @ContextConfiguration(locations = {"classpath:context.xml"})
        public static class _転入のテスト {

            public final DateTime FIXATION_DATE = new DateTime(2014, 1, 1, 0, 0, 0);

            @Autowired
            private WebApplicationContext wac;
            private MockMvc mockMvc;

            @Autowired
            public DbUnitTester tester;

            @Before
            public void setup() throws DatabaseUnitException, SQLException, IOException {
                mockMvc = webAppContextSetup(wac).build();


                // 「現在時刻」を固定化する
                DateTimeUtils.setCurrentMillisFixed(FIXATION_DATE.getMillis());

                // 処理前にテーブルをクリアする
                tester.executeAllClearTableAndSeq();
            }

            @After
            public void tearDown() {
                // 「現在時刻」をシステム日付に戻す
                DateTimeUtils.setCurrentMillisSystem();
            }

            private void assertResponse(ResultActions resultActions , String mnpFullName, String mnpFullNameKana,
                                        String mnpGender, String mnpBirthday, String activationDueDate) throws Exception{
                resultActions.andExpect(jsonPath("$.mnpIn.mnpMsisdn").value("090-1234-5678"));
                resultActions.andExpect(jsonPath("$.mnpIn.mnpReservationNumber").value("00-111-12345"));
                resultActions.andExpect(jsonPath("$.mnpIn.mnpFullName").value(mnpFullName));
                resultActions.andExpect(jsonPath("$.mnpIn.mnpFullNameKana").value(mnpFullNameKana));
                resultActions.andExpect(jsonPath("$.mnpIn.mnpGender").value(mnpGender));
                resultActions.andExpect(jsonPath("$.mnpIn.mnpBirthday").value(mnpBirthday));
                resultActions.andExpect(jsonPath("$.mnpIn.activationDueDate").value(activationDueDate));
            }

            private void assertResponseForConstancy(ResultActions resultActions, String mnpGender) throws Exception{
                assertResponse(resultActions, "小池　直樹", "ｺｲｹ ﾅｵｷ", mnpGender, "19840309", "");
            }

            @Test
            public void test_転入あり_仮受付() throws Exception {
                // テストケースの設定;
                tester.cleanInsertQuery(SIM_INFO.regist);
                tester.cleanInsertQuery(FixtureVoiceEngagementState.One.getDefaultData());
                tester.cleanInsertQuery(FixtureVoiceMnpInState.One.getForProvisional());

                // 実行
                ResultActions resultActions = mockMvc.perform(post(URI).param("equipmentNumberForm", "00000001"));

                // 確認・JASON
                assertResponse(resultActions, "", "", "", "", "");

            }

            @Test
            public void test_本人確認OKでアクティベーション予定日あり() throws Exception {
                // テストケースの設定;
                tester.cleanInsertQuery(SIM_INFO.regist);
                tester.cleanInsertQuery(FixtureVoiceEngagementState.One.getDefaultData());
                tester.cleanInsertQuery(FixtureVoiceMnpInState.One.getForMnpInActivationDueDate());

                // 実行
                ResultActions resultActions = mockMvc.perform(post(URI).param("equipmentNumberForm", "00000001"));

                // 確認・JASON
                assertResponse(resultActions, "小池　直樹", "ｺｲｹ ﾅｵｷ", "male", "19840309", "20140101");

            }

            @Test
            public void test_転入あり_本受付_男() throws Exception {
                // テストケースの設定;
                tester.cleanInsertQuery(SIM_INFO.regist);
                tester.cleanInsertQuery(FixtureVoiceEngagementState.One.getDefaultData());
                tester.cleanInsertQuery(FixtureVoiceMnpInState.One.getForConstancyForMale());

                // 実行
                ResultActions resultActions = mockMvc.perform(post(URI).param("equipmentNumberForm", "00000001"));

                // 確認・JASON
                assertResponseForConstancy(resultActions, "male");

            }

            @Test
            public void test_転入あり_本受付_女() throws Exception {
                // テストケースの設定;
                tester.cleanInsertQuery(SIM_INFO.regist);
                tester.cleanInsertQuery(FixtureVoiceEngagementState.One.getDefaultData());
                tester.cleanInsertQuery(FixtureVoiceMnpInState.One.getForConstancyForFemale());

                // 実行
                ResultActions resultActions = mockMvc.perform(post(URI).param("equipmentNumberForm", "00000001"));

                // 確認・JASON
                assertResponseForConstancy(resultActions, "female");

            }

            @Test
            public void test_転入あり_本受付_不明() throws Exception {
                // テストケースの設定;
                tester.cleanInsertQuery(SIM_INFO.regist);
                tester.cleanInsertQuery(FixtureVoiceEngagementState.One.getDefaultData());
                tester.cleanInsertQuery(FixtureVoiceMnpInState.One.getForConstancyForUnknown());

                // 実行
                ResultActions resultActions = mockMvc.perform(post(URI).param("equipmentNumberForm", "00000001"));

                // 確認・JASON
                assertResponseForConstancy(resultActions, "unknown");

            }


        }


        @RunWith(SpringJUnit4ClassRunner.class)
        @WebAppConfiguration
        @ContextConfiguration(locations = {"classpath:context.xml"})
        public static class _mnpOutTest {

            public final DateTime FIXATION_DATE = new DateTime(2014, 1, 1, 0, 0, 0);

            @Autowired
            private WebApplicationContext wac;
            private MockMvc mockMvc;

            @Autowired
            public DbUnitTester tester;

            @Before
            public void setup() throws DatabaseUnitException, SQLException, IOException {
                mockMvc = webAppContextSetup(wac).build();


                // 「現在時刻」を固定化する
                DateTimeUtils.setCurrentMillisFixed(FIXATION_DATE.getMillis());

                // 処理前にテーブルをクリアする
                tester.executeAllClearTableAndSeq();
            }

            @After
            public void tearDown() {
                // 「現在時刻」をシステム日付に戻す
                DateTimeUtils.setCurrentMillisSystem();
            }

            private void assertResponse(ResultActions resultActions , String mnpGender, String mnpReservationNumber,
                                        String expireDate, String executionDate, String mnpOutStatus) throws Exception{
                resultActions.andExpect(jsonPath("$.mnpOut.mnpMsisdn").value("090-1234-5678"));
                resultActions.andExpect(jsonPath("$.mnpOut.mnpFullName").value("小池　直樹"));
                resultActions.andExpect(jsonPath("$.mnpOut.mnpFullNameKana").value("ｺｲｹ ﾅｵｷ"));
                resultActions.andExpect(jsonPath("$.mnpOut.mnpGender").value(mnpGender));
                resultActions.andExpect(jsonPath("$.mnpOut.mnpBirthday").value("19840309"));
                resultActions.andExpect(jsonPath("$.mnpOut.mnpReservationNumber").value(mnpReservationNumber));
                resultActions.andExpect(jsonPath("$.mnpOut.expireDate").value(expireDate));
                resultActions.andExpect(jsonPath("$.mnpOut.executionDate").value(executionDate));
                resultActions.andExpect(jsonPath("$.mnpOut.mnpOutStatus").value(mnpOutStatus));
            }

            private void assertResponseForGender(ResultActions resultActions, String mnpGender) throws Exception{
                assertResponse(resultActions, mnpGender, "11-222-33333", "20140415", "20140401", "mnp_out_completion");
            }

            private void assertResponseForMnpReservation(ResultActions resultActions, String mnpReservationNumber,
                                                         String expireDate, String executionDate, String mnpOutStatus) throws Exception{
                assertResponse(resultActions, "male", mnpReservationNumber, expireDate, executionDate, mnpOutStatus);
            }


            @Test
            public void test_事務局へのリクエスト依頼待ち() throws Exception {
                // テストケースの設定;
                tester.cleanInsertQuery(SIM_INFO.regist);
                tester.cleanInsertQuery(FixtureVoiceEngagementState.One.getDefaultData());
                tester.cleanInsertQuery(FixtureVoiceMnpOutState.One.getForRequestedWaiting());

                // 実行
                ResultActions resultActions = mockMvc.perform(post(URI).param("equipmentNumberForm", "00000001"));

                // 確認・JASON
                assertResponseForMnpReservation(resultActions, "", "", "", "request_waiting");

            }

            @Test
            public void test_事務局へのリクエスト依頼済み() throws Exception {
                // テストケースの設定;
                tester.cleanInsertQuery(SIM_INFO.regist);
                tester.cleanInsertQuery(FixtureVoiceEngagementState.One.getDefaultData());
                tester.cleanInsertQuery(FixtureVoiceMnpOutState.One.getForRequested());

                // 実行
                ResultActions resultActions = mockMvc.perform(post(URI).param("equipmentNumberForm", "00000001"));

                // 確認・JASON
                assertResponseForMnpReservation(resultActions, "", "", "", "mnp_reservation_number_waiting");

            }

            @Test
            public void test_予約番号発行済() throws Exception {
                // テストケースの設定;
                tester.cleanInsertQuery(SIM_INFO.regist);
                tester.cleanInsertQuery(FixtureVoiceEngagementState.One.getDefaultData());
                tester.cleanInsertQuery(FixtureVoiceMnpOutState.One.getForNumbered());

                // 実行
                ResultActions resultActions = mockMvc.perform(post(URI).param("equipmentNumberForm", "00000001"));

                // 確認・JASON
                assertResponseForMnpReservation(resultActions, "11-222-33333", "20140415", "20140401", "mnp_out_waiting");

            }

            @Test
            public void test_転出完了() throws Exception {
                // テストケースの設定;
                tester.cleanInsertQuery(SIM_INFO.regist);
                tester.cleanInsertQuery(FixtureVoiceEngagementState.One.getDefaultData());
                tester.cleanInsertQuery(FixtureVoiceMnpOutState.One.getForCompletion());

                // 実行
                ResultActions resultActions = mockMvc.perform(post(URI).param("equipmentNumberForm", "00000001"));

                // 確認・JASON
                assertResponseForMnpReservation(resultActions, "11-222-33333", "20140415", "20140401", "mnp_out_completion");

            }

            @Test
            public void test_依頼前に転出キャンセル() throws Exception {
                // テストケースの設定;
                tester.cleanInsertQuery(SIM_INFO.regist);
                tester.cleanInsertQuery(FixtureVoiceEngagementState.One.getDefaultData());
                tester.cleanInsertQuery(FixtureVoiceMnpOutState.One.getForCancelFromRequestedWaiting());

                // 実行
                ResultActions resultActions = mockMvc.perform(post(URI).param("equipmentNumberForm", "00000001"));

                // 確認・JASON
                assertResponseForMnpReservation(resultActions, "", "", "", "mnp_out_cancel");

            }


            @Test
            public void test_男() throws Exception {
                // テストケースの設定;
                tester.cleanInsertQuery(SIM_INFO.regist);
                tester.cleanInsertQuery(FixtureVoiceEngagementState.One.getDefaultData());
                tester.cleanInsertQuery(FixtureVoiceMnpOutState.One.getForCompletion());

                // 実行
                ResultActions resultActions = mockMvc.perform(post(URI).param("equipmentNumberForm", "00000001"));

                // 確認・JASON
                assertResponseForGender(resultActions, "male");

            }

            @Test
            public void test_女() throws Exception {
                // テストケースの設定;
                tester.cleanInsertQuery(SIM_INFO.regist);
                tester.cleanInsertQuery(FixtureVoiceEngagementState.One.getDefaultData());
                tester.cleanInsertQuery(FixtureVoiceMnpOutState.One.getForCompletionForFemale());

                // 実行
                ResultActions resultActions = mockMvc.perform(post(URI).param("equipmentNumberForm", "00000001"));

                // 確認・JASON
                assertResponseForGender(resultActions, "female");

            }

            @Test
            public void test_不明() throws Exception {
                // テストケースの設定;
                tester.cleanInsertQuery(SIM_INFO.regist);
                tester.cleanInsertQuery(FixtureVoiceEngagementState.One.getDefaultData());
                tester.cleanInsertQuery(FixtureVoiceMnpOutState.One.getForCompletionForUnknown());

                // 実行
                ResultActions resultActions = mockMvc.perform(post(URI).param("equipmentNumberForm", "00000001"));

                // 確認・JASON
                assertResponseForGender(resultActions, "unknown");

            }


        }
    }


    @RunWith(SpringJUnit4ClassRunner.class)
    @WebAppConfiguration
    @ContextConfiguration(locations = {"classpath:context.xml"})
    public static class _バリデーションエラー {

        @Autowired
        private WebApplicationContext wac;
        private MockMvc mockMvc;

        @Before
        public void setup() throws DatabaseUnitException, SQLException, IOException {
            mockMvc = webAppContextSetup(wac).build();
        }

        @Test
        public void _equipmentNumberFormがない() throws Exception {

            // 実行
            ResultActions resultActions = mockMvc.perform(post(URI));

            // 確認・JASON
            ValidationErrorResponseAssert.assertJsonPath(resultActions, "equipmentNumberForm");
        }

        @Test
        public void _equipmentNumberFormの桁間違い() throws Exception {

            // 実行
            ResultActions resultActions = mockMvc.perform(post(URI)
                    .param("equipmentNumberForm", "000001"));

            // 確認・JASON
            ValidationErrorResponseAssert.assertJsonPath(resultActions, "equipmentNumberForm.value");
        }
    }

}