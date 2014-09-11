package jp.co.biglobe.isp.mobile.voiceoption.api.engagement.engagementcountrefer;

import jp.co.biglobe.isp.mobile.ltethreeg.datasource.SIM_INFO;
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
public class VoiceEngagementCountReferApiTest {

    private static final String URI = "/voiceoption/outside/skip/engagementcount/refer";

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
            tester.cleanInsertQuery(SIM_INFO.regist);
            tester.cleanInsertQuery(FixtureVoiceEngagementState.One.getDefaultData());

            // 実行
            ResultActions resultActions = mockMvc.perform(post(URI)
                    .param("lteThreeGEngagementNumberForm", "99999999"));

            // 確認・JASON
            ReferApiResponseAssert.assertJsonPath(resultActions);
            resultActions.andExpect(jsonPath("$.result").value("not_exist"));
            resultActions.andExpect(jsonPath("$.orderedCount").value("0"));
            resultActions.andExpect(jsonPath("$.engagedCount").value("0"));
            resultActions.andExpect(jsonPath("$.disengagedReservationCount").value("0"));

        }

        @Test
        public void test_正常_申込中() throws Exception {
            // テストケースの設定;
            tester.cleanInsertQuery(SIM_INFO.regist);
            tester.cleanInsertQuery(FixtureVoiceEngagementState.One.getDefaultData());

            // 実行
            ResultActions resultActions = mockMvc.perform(post(URI)
                    .param("lteThreeGEngagementNumberForm", "00000001"));

            // 確認・JASON
            ReferApiResponseAssert.assertJsonPath(resultActions);
            resultActions.andExpect(jsonPath("$.result").value("exist"));
            resultActions.andExpect(jsonPath("$.orderedCount").value("1"));
            resultActions.andExpect(jsonPath("$.engagedCount").value("0"));
            resultActions.andExpect(jsonPath("$.disengagedReservationCount").value("0"));
        }

        @Test
        public void test_正常_契約中() throws Exception {
            // テストケースの設定;
            tester.cleanInsertQuery(SIM_INFO.regist);
            tester.cleanInsertQuery(FixtureVoiceEngagementState.One.getForEngagement());

            // 実行
            ResultActions resultActions = mockMvc.perform(post(URI)
                    .param("lteThreeGEngagementNumberForm", "00000001"));

            // 確認・JASON
            ReferApiResponseAssert.assertJsonPath(resultActions);
            resultActions.andExpect(jsonPath("$.result").value("exist"));
            resultActions.andExpect(jsonPath("$.orderedCount").value("0"));
            resultActions.andExpect(jsonPath("$.engagedCount").value("1"));
            resultActions.andExpect(jsonPath("$.disengagedReservationCount").value("0"));
        }

        @Test
        public void test_正常_解約予約中() throws Exception {
            // テストケースの設定;
            tester.cleanInsertQuery(SIM_INFO.regist);
            tester.cleanInsertQuery(FixtureVoiceEngagementState.One.getForDisEngagementReserved());

            // 実行
            ResultActions resultActions = mockMvc.perform(post(URI)
                    .param("lteThreeGEngagementNumberForm", "00000001"));

            // 確認・JASON
            ReferApiResponseAssert.assertJsonPath(resultActions);
            resultActions.andExpect(jsonPath("$.result").value("exist"));
            resultActions.andExpect(jsonPath("$.orderedCount").value("0"));
            resultActions.andExpect(jsonPath("$.engagedCount").value("0"));
            resultActions.andExpect(jsonPath("$.disengagedReservationCount").value("1"));
        }

        @Test
        public void test_正常_解約済() throws Exception {
            // テストケースの設定;
            tester.cleanInsertQuery(SIM_INFO.regist);
            tester.cleanInsertQuery(FixtureVoiceEngagementState.One.getForDisEngaged());

            // 実行
            ResultActions resultActions = mockMvc.perform(post(URI)
                    .param("lteThreeGEngagementNumberForm", "00000001"));

            // 確認・JASON
            ReferApiResponseAssert.assertJsonPath(resultActions);
            resultActions.andExpect(jsonPath("$.result").value("not_exist"));
            resultActions.andExpect(jsonPath("$.orderedCount").value("0"));
            resultActions.andExpect(jsonPath("$.engagedCount").value("0"));
            resultActions.andExpect(jsonPath("$.disengagedReservationCount").value("0"));
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
        public void _lteThreeGEngagementNumberFormがない() throws Exception {

            // 実行
            ResultActions resultActions = mockMvc.perform(post(URI));

            // 確認・JASON
            ValidationErrorResponseAssert.assertJsonPath(resultActions, "lteThreeGEngagementNumberForm");
        }


        @Test
        public void _lteThreeGEngagementNumberFormの桁間違い() throws Exception {

            // 実行
            ResultActions resultActions = mockMvc.perform(post(URI)
                    .param("lteThreeGEngagementNumberForm", "000001"));

            // 確認・JASON
            ValidationErrorResponseAssert.assertJsonPath(resultActions, "lteThreeGEngagementNumberForm.value");
        }

    }

}
