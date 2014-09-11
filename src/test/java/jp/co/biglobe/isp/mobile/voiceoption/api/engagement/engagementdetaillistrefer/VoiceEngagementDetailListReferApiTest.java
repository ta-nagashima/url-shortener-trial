package jp.co.biglobe.isp.mobile.voiceoption.api.engagement.engagementdetaillistrefer;

import jp.co.biglobe.isp.mobile.ltethreeg.datasource.SIM_INFO;
import jp.co.biglobe.isp.mobile.ltethreeg.datasource.ltethreegengagement.db.FixtureLteContractInfo;
import jp.co.biglobe.isp.mobile.voiceoption.datasource.charge.engagementmonthdisengagementcharge.db.FixtureVoiceEngagementMonthDisengagementChargeState;
import jp.co.biglobe.isp.mobile.voiceoption.datasource.mnpin.db.FixtureVoiceMnpInState;
import jp.co.biglobe.isp.mobile.voiceoption.datasource.mnpout.db.FixtureVoiceMnpOutState;
import jp.co.biglobe.isp.mobile.voiceoption.datasource.voiceengagement.db.FixtureVoiceEngagementState;
import jp.co.biglobe.test.util.dbunit.DbUnitTester;
import jp.co.biglobe.test.util.response.ReferApiResponseAssert;
import jp.co.biglobe.test.util.response.ValidationErrorResponseAssert;
import org.dbunit.DatabaseUnitException;
import org.joda.time.DateTime;
import org.joda.time.DateTimeUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.experimental.runners.Enclosed;
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
public class VoiceEngagementDetailListReferApiTest {

    private static final String URI = "/voiceoption/outside/skip/engagementdetaillist/refer";

    @RunWith(SpringJUnit4ClassRunner.class)
    @WebAppConfiguration
    @ContextConfiguration(locations = {"classpath:context.xml"})
    public static class _コントローラの処理フローのテスト {

        @Autowired
        public DbUnitTester tester;

        @Autowired
        private WebApplicationContext wac;
        private MockMvc mockMvc;

        public final DateTime FIXATION_DATE = new DateTime(2014, 1, 1, 0, 0, 0);

        @Before
        public void setup() throws IOException, DatabaseUnitException, SQLException {
            mockMvc = webAppContextSetup(wac).build();

            // 「現在時刻」を固定化する
            DateTimeUtils.setCurrentMillisFixed(FIXATION_DATE.getMillis());

            // 処理前にテーブルをクリアする
            tester.executeAllClearTableAndSeq();

        }

        @After
        public void tearDown() throws IOException {
            // 「現在時刻」をシステム日付に戻す
            DateTimeUtils.setCurrentMillisSystem();

        }

        @Test
        public void _0件() throws Exception {

            // 事前準備

            // 実行
            ResultActions resultActions = mockMvc.perform(post(URI)
                            .param("lteThreeGEngagementNumberForm", "00000001")
            );

            // 確認：JSON（API固有部分）
            ReferApiResponseAssert.assertJsonPath(resultActions);
            resultActions.andExpect(jsonPath("$.resultCount").value(0));
            resultActions.andExpect(jsonPath("$.body[0].mnpInResult").doesNotExist());
            resultActions.andExpect(jsonPath("$.body[0].mnpOutResult").doesNotExist());
            resultActions.andExpect(jsonPath("$.body[0].voiceEngagement").doesNotExist());
            resultActions.andExpect(jsonPath("$.body[0].mnpIn").doesNotExist());
            resultActions.andExpect(jsonPath("$.body[0].mnpOut").doesNotExist());

        }

        @Test
        public void _1件() throws Exception {

            // 事前準備
            tester.cleanInsertQuery(SIM_INFO.lte);
            tester.cleanInsertQuery(FixtureVoiceEngagementState.One.getForEngagement());
            tester.cleanInsertQuery(FixtureVoiceMnpInState.One.getForMnpInActivationDueDate());
            tester.cleanInsertQuery(FixtureVoiceMnpOutState.One.getForCompletion());

            // 実行
            ResultActions resultActions = mockMvc.perform(post(URI)
                    .param("lteThreeGEngagementNumberForm", "00000001")
            );

            // 確認：JSON（API固有部分）
            ReferApiResponseAssert.assertJsonPath(resultActions);
            resultActions.andExpect(jsonPath("$.resultCount").value(1));
            resultActions.andExpect(jsonPath("$.body[0].mnpInResult").value("exist"));
            resultActions.andExpect(jsonPath("$.body[0].mnpOutResult").value("exist"));
            resultActions.andExpect(jsonPath("$.body[0].voiceEngagement").exists());
            resultActions.andExpect(jsonPath("$.body[0].mnpIn").exists());
            resultActions.andExpect(jsonPath("$.body[0].mnpOut").exists());

            resultActions.andExpect(jsonPath("$.body[0].voiceEngagement.voiceEngagementNumber").value("1"));
            resultActions.andExpect(jsonPath("$.body[0].voiceEngagement.equipmentNumber").value("00000001"));
            resultActions.andExpect(jsonPath("$.body[0].voiceEngagement.lteThreeGEngagementNumber").value("00000001"));
            resultActions.andExpect(jsonPath("$.body[0].voiceEngagement.userId").value("abc12345"));
            resultActions.andExpect(jsonPath("$.body[0].voiceEngagement.voiceJoinCode").value("webyn002"));
            resultActions.andExpect(jsonPath("$.body[0].voiceEngagement.voiceEngagementStatus").value("engaged"));
            resultActions.andExpect(jsonPath("$.body[0].voiceEngagement.voiceUserOrderDate").value("20140101"));
            resultActions.andExpect(jsonPath("$.body[0].voiceEngagement.voiceSystemReceiptDateTime").value("20140101000000"));
            resultActions.andExpect(jsonPath("$.body[0].voiceEngagement.voiceEngagementStartDate").value("29991231"));
            resultActions.andExpect(jsonPath("$.body[0].voiceEngagement.voiceEngagementEndDateTime").value("29991231235959"));

            resultActions.andExpect(jsonPath("$.body[0].mnpIn.mnpMsisdn").value("090-1234-5678"));
            resultActions.andExpect(jsonPath("$.body[0].mnpIn.mnpReservationNumber").value("00-111-12345"));
            resultActions.andExpect(jsonPath("$.body[0].mnpIn.mnpFullName").value("小池　直樹"));
            resultActions.andExpect(jsonPath("$.body[0].mnpIn.mnpFullNameKana").value("ｺｲｹ ﾅｵｷ"));
            resultActions.andExpect(jsonPath("$.body[0].mnpIn.mnpGender").value("male"));
            resultActions.andExpect(jsonPath("$.body[0].mnpIn.mnpBirthday").value("19840309"));
            resultActions.andExpect(jsonPath("$.body[0].mnpIn.activationDueDate").value("20140101"));

            resultActions.andExpect(jsonPath("$.body[0].mnpOut.mnpMsisdn").value("090-1234-5678"));
            resultActions.andExpect(jsonPath("$.body[0].mnpOut.mnpFullName").value("小池　直樹"));
            resultActions.andExpect(jsonPath("$.body[0].mnpOut.mnpFullNameKana").value("ｺｲｹ ﾅｵｷ"));
            resultActions.andExpect(jsonPath("$.body[0].mnpOut.mnpGender").value("male"));
            resultActions.andExpect(jsonPath("$.body[0].mnpOut.mnpBirthday").value("19840309"));
            resultActions.andExpect(jsonPath("$.body[0].mnpOut.mnpReservationNumber").value("11-222-33333"));
            resultActions.andExpect(jsonPath("$.body[0].mnpOut.expireDate").value("20140415"));
            resultActions.andExpect(jsonPath("$.body[0].mnpOut.executionDate").value("20140401"));
            resultActions.andExpect(jsonPath("$.body[0].mnpOut.mnpOutStatus").value("mnp_out_completion"));

            resultActions.andExpect(jsonPath("$.body[0].engagement_month_disengagement_charge.executeDateTime").value(""));
            resultActions.andExpect(jsonPath("$.body[0].engagement_month_disengagement_charge.amount").value(""));
        }

        @Test
        public void _1件_契約月解約課金済() throws Exception {

            // 事前準備
            tester.cleanInsertQuery(FixtureLteContractInfo.One.解約済み());
            tester.cleanInsertQuery(FixtureVoiceEngagementState.One.getForDisengaged(
                    new DateTime(2013, 7, 1, 0, 0, 0),
                    new DateTime(2013, 7, 31, 0, 0, 0)
            ));
            tester.cleanInsertQuery(FixtureVoiceMnpInState.One.getForMnpInActivationDueDate());
            tester.cleanInsertQuery(FixtureVoiceMnpOutState.One.getForCompletion());
            tester.cleanInsertQuery(FixtureVoiceEngagementMonthDisengagementChargeState.One.getDefaultData());

            // 実行
            ResultActions resultActions = mockMvc.perform(post(URI)
                            .param("lteThreeGEngagementNumberForm", "00000001")
            );

            // 確認：JSON（API固有部分）
            ReferApiResponseAssert.assertJsonPath(resultActions);
            resultActions.andExpect(jsonPath("$.resultCount").value(1));
            resultActions.andExpect(jsonPath("$.body[0].mnpInResult").value("exist"));
            resultActions.andExpect(jsonPath("$.body[0].mnpOutResult").value("exist"));
            resultActions.andExpect(jsonPath("$.body[0].voiceEngagement").exists());
            resultActions.andExpect(jsonPath("$.body[0].mnpIn").exists());
            resultActions.andExpect(jsonPath("$.body[0].mnpOut").exists());

            resultActions.andExpect(jsonPath("$.body[0].voiceEngagement.voiceEngagementNumber").value("1"));
            resultActions.andExpect(jsonPath("$.body[0].voiceEngagement.equipmentNumber").value("00000001"));
            resultActions.andExpect(jsonPath("$.body[0].voiceEngagement.lteThreeGEngagementNumber").value("00000001"));
            resultActions.andExpect(jsonPath("$.body[0].voiceEngagement.userId").value("abc12345"));
            resultActions.andExpect(jsonPath("$.body[0].voiceEngagement.voiceJoinCode").value("webyn002"));
            resultActions.andExpect(jsonPath("$.body[0].voiceEngagement.voiceEngagementStatus").value("disengaged"));
            resultActions.andExpect(jsonPath("$.body[0].voiceEngagement.voiceUserOrderDate").value("20140101"));
            resultActions.andExpect(jsonPath("$.body[0].voiceEngagement.voiceSystemReceiptDateTime").value("20140101000000"));
            resultActions.andExpect(jsonPath("$.body[0].voiceEngagement.voiceEngagementStartDate").value("20130701"));
            resultActions.andExpect(jsonPath("$.body[0].voiceEngagement.voiceEngagementEndDateTime").value("20130731235959"));

            resultActions.andExpect(jsonPath("$.body[0].mnpIn.mnpMsisdn").value("090-1234-5678"));
            resultActions.andExpect(jsonPath("$.body[0].mnpIn.mnpReservationNumber").value("00-111-12345"));
            resultActions.andExpect(jsonPath("$.body[0].mnpIn.mnpFullName").value("小池　直樹"));
            resultActions.andExpect(jsonPath("$.body[0].mnpIn.mnpFullNameKana").value("ｺｲｹ ﾅｵｷ"));
            resultActions.andExpect(jsonPath("$.body[0].mnpIn.mnpGender").value("male"));
            resultActions.andExpect(jsonPath("$.body[0].mnpIn.mnpBirthday").value("19840309"));
            resultActions.andExpect(jsonPath("$.body[0].mnpIn.activationDueDate").value("20140101"));

            resultActions.andExpect(jsonPath("$.body[0].mnpOut.mnpMsisdn").value("090-1234-5678"));
            resultActions.andExpect(jsonPath("$.body[0].mnpOut.mnpFullName").value("小池　直樹"));
            resultActions.andExpect(jsonPath("$.body[0].mnpOut.mnpFullNameKana").value("ｺｲｹ ﾅｵｷ"));
            resultActions.andExpect(jsonPath("$.body[0].mnpOut.mnpGender").value("male"));
            resultActions.andExpect(jsonPath("$.body[0].mnpOut.mnpBirthday").value("19840309"));
            resultActions.andExpect(jsonPath("$.body[0].mnpOut.mnpReservationNumber").value("11-222-33333"));
            resultActions.andExpect(jsonPath("$.body[0].mnpOut.expireDate").value("20140415"));
            resultActions.andExpect(jsonPath("$.body[0].mnpOut.executionDate").value("20140401"));
            resultActions.andExpect(jsonPath("$.body[0].mnpOut.mnpOutStatus").value("mnp_out_completion"));

            resultActions.andExpect(jsonPath("$.body[0].engagement_month_disengagement_charge.executeDateTime").value("20140101000000"));
            resultActions.andExpect(jsonPath("$.body[0].engagement_month_disengagement_charge.amount").value("900"));
        }

        @Test
        public void _2件() throws Exception {

            // 事前準備
            tester.cleanInsertQuery(SIM_INFO.lte);
            tester.cleanInsertQuery(FixtureVoiceEngagementState.Two.getDefaultData());
            tester.cleanInsertQuery(FixtureVoiceMnpInState.One.getForProvisional());
            tester.cleanInsertQuery(FixtureVoiceMnpOutState.One.getDefaultData());

            // 実行
            ResultActions resultActions = mockMvc.perform(post(URI)
                            .param("lteThreeGEngagementNumberForm", "00000001")
            );

            // 確認：JSON（API固有部分）
            ReferApiResponseAssert.assertJsonPath(resultActions);
            resultActions.andExpect(jsonPath("$.resultCount").value(2));

            resultActions.andExpect(jsonPath("$.body[0].mnpInResult").value("exist"));
            resultActions.andExpect(jsonPath("$.body[0].mnpOutResult").value("exist"));
            resultActions.andExpect(jsonPath("$.body[0].voiceEngagement").exists());
            resultActions.andExpect(jsonPath("$.body[0].mnpIn").exists());
            resultActions.andExpect(jsonPath("$.body[0].mnpOut").exists());

            resultActions.andExpect(jsonPath("$.body[1].mnpInResult").value("not_exist"));
            resultActions.andExpect(jsonPath("$.body[1].mnpOutResult").value("not_exist"));
            resultActions.andExpect(jsonPath("$.body[1].voiceEngagement").exists());
            resultActions.andExpect(jsonPath("$.body[1].mnpIn").doesNotExist());
            resultActions.andExpect(jsonPath("$.body[1].mnpOut").doesNotExist());
        }
    }

    @RunWith(SpringJUnit4ClassRunner.class)
    @WebAppConfiguration
    @ContextConfiguration(locations = {"classpath:context.xml"})
    public static class _バリデーションのテスト {

        @Autowired
        private WebApplicationContext wac;
        private MockMvc mockMvc;

        @Before
        public void setup() {
            mockMvc = webAppContextSetup(wac).build();
        }

        @Test
        public void _lteThreeGEngagementNumberFormがない() throws Exception {
            // 実行
            ResultActions resultActions = mockMvc.perform(post(URI));

            // 確認：JSON（API固有部分）
            ValidationErrorResponseAssert.assertJsonPath(resultActions, "lteThreeGEngagementNumberForm");
        }

        @Test
        public void _lteThreeGEngagementNumberFormの値が不正() throws Exception {
            // 実行
            ResultActions resultActions = mockMvc.perform(post(URI)
                    .param("lteThreeGEngagementNumberForm", "0000ああ0001"));

            // 確認：JSON（API固有部分）
            ValidationErrorResponseAssert.assertJsonPath(resultActions, "lteThreeGEngagementNumberForm.value");
        }

    }

}
