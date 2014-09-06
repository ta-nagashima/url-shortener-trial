package jp.co.biglobe.isp.mobile.voiceoption.api.disengagement.disengagementapply;

import jp.co.biglobe.isp.mobile.ltethreeg.datasource.SIM_INFO;
import jp.co.biglobe.isp.mobile.voiceoption.database.VoiceEngagementStateAssert;
import jp.co.biglobe.isp.mobile.voiceoption.datasource.mnpout.db.FixtureVoiceMnpOutState;
import jp.co.biglobe.isp.mobile.voiceoption.datasource.voiceengagement.db.FixtureVoiceEngagementState;
import jp.co.biglobe.test.util.dbunit.DbUnitTester;
import jp.co.biglobe.test.util.response.UpdateApiResponseAssert;
import jp.co.biglobe.test.util.response.ValidationErrorResponseAssert;
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

/**
 * 解約予約
 */

@RunWith(Enclosed.class)
public class VoiceDisengagementApplyApiTest {

    private static final String URI = "/voiceoption/outside/skip/disengagementapply/submit";

    public static final DateTime FIXATION_DATE = new DateTime(2014, 1, 1, 0, 0, 0);


    @RunWith(SpringJUnit4ClassRunner.class)
    @WebAppConfiguration
    @ContextConfiguration(locations = {"classpath:context.xml"})
    public static class 正常系異常系のテスト {

        @Autowired
        private WebApplicationContext wac;
        private MockMvc mockMvc;

        @Autowired
        public DbUnitTester tester;

        @Before
        public void setup() throws DatabaseUnitException, SQLException, IOException {
            mockMvc = webAppContextSetup(wac).build();

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

         // 音声契約条件
        @Test
        public void test_正常系_音声契約が契約中で転出情報がキャンセルの時は解約予約ができる() throws Exception {
            // テストケースの設定
            tester.cleanInsertQuery(SIM_INFO.regist);
            tester.cleanInsertQuery(FixtureVoiceEngagementState.One.getForEngagement());
            tester.cleanInsertQuery(FixtureVoiceMnpOutState.One.getForCancelFromNumbered());

            // 実行
            ResultActions resultActions = mockMvc.perform(post(URI)
                            .param("equipmentNumberForm", "00000001")
                            .param("voiceEngagementEndDateTimeForm", FIXATION_DATE.toString("yyyyMMddHHmmss"))
                            .param("voiceEngagementDisengageReasonForm", "user_request")
                            .param("voiceEngagementDisengageOrderDateForm", "20140101")


            );

            UpdateApiResponseAssert.assertJsonPath(resultActions);

            // 確認
            VoiceEngagementStateAssert voiceEngagementStateAssert = new VoiceEngagementStateAssert(tester);
            voiceEngagementStateAssert.assertTableWithAllColumns(FixtureVoiceEngagementState.One.getForImmediateDisEngagement());

        }

        @Test
        public void test_正常系_音声契約が契約中の時は解約予約ができる() throws Exception {
            // テストケースの設定
            tester.cleanInsertQuery(SIM_INFO.regist);
            tester.cleanInsertQuery(FixtureVoiceEngagementState.One.getForEngagement());

            // 実行
            ResultActions resultActions = mockMvc.perform(post(URI)
                            .param("equipmentNumberForm", "00000001")
                            .param("voiceEngagementEndDateTimeForm", FIXATION_DATE.toString("yyyyMMddHHmmss"))
                            .param("voiceEngagementDisengageReasonForm", "user_request")
                            .param("voiceEngagementDisengageOrderDateForm", "20140101")
            );

            UpdateApiResponseAssert.assertJsonPath(resultActions);

            // 確認
            VoiceEngagementStateAssert voiceEngagementStateAssert = new VoiceEngagementStateAssert(tester);
            voiceEngagementStateAssert.assertTableWithAllColumns(FixtureVoiceEngagementState.One.getForImmediateDisEngagement());

        }

        @Test
        public void test_異常_音声契約が申込中のときは解約予約できない() throws Exception {
            // テストケースの設定
            tester.cleanInsertQuery(SIM_INFO.regist);
            tester.cleanInsertQuery(FixtureVoiceEngagementState.One.getDefaultData());

            // 実行
            ResultActions resultActions = mockMvc.perform(post(URI)
                            .param("equipmentNumberForm", "00000001")
                            .param("voiceEngagementEndDateTimeForm", FIXATION_DATE.toString("yyyyMMddHHmmss"))
                            .param("voiceEngagementDisengageReasonForm", "user_request")
                            .param("voiceEngagementDisengageOrderDateForm", "20140101")
            );

            // 確認・JSON
            resultActions.andExpect(jsonPath("$.header.statusCode").value("system_error"));
            resultActions.andExpect(jsonPath("$.error.type").value("SystemCheckException"));
            resultActions.andExpect(jsonPath("$.error.message").value("音声オプション契約のステータスが異常"));
        }

        @Test
        public void test_検索結果が０件() throws Exception {
            // テストケースの設定
            tester.cleanInsertQuery(SIM_INFO.regist);
            tester.cleanInsertQuery(FixtureVoiceEngagementState.One.getForEngagement());
            tester.cleanInsertQuery(FixtureVoiceMnpOutState.One.getForNumbered());

            // 実行
            ResultActions resultActions = mockMvc.perform(post(URI)
                            .param("equipmentNumberForm", "99999999")
                            .param("voiceEngagementEndDateTimeForm", FIXATION_DATE.toString("yyyyMMddHHmmss"))
                            .param("voiceEngagementDisengageReasonForm", "user_request")
                            .param("voiceEngagementDisengageOrderDateForm", "20140101")
            );

            // 確認・JSON
            resultActions.andExpect(jsonPath("$.header.statusCode").value("system_error"));
            resultActions.andExpect(jsonPath("$.error.type").value("SystemCheckException"));
            resultActions.andExpect(jsonPath("$.error.message").value("音声オプション契約が見つかりません"));
        }
    }


    @RunWith(SpringJUnit4ClassRunner.class)
    @WebAppConfiguration
    @ContextConfiguration(locations = {"classpath:context.xml"})
    public static class バリデーションのテスト {

        @Autowired
        private WebApplicationContext wac;
        private MockMvc mockMvc;

        @Before
        public void setup() throws DatabaseUnitException, SQLException, IOException {
            mockMvc = webAppContextSetup(wac).build();
        }

        @Test
        public void test_equipmentNumberFormがない() throws Exception {

            // 実行
            ResultActions resultActions = mockMvc.perform(post(URI)
                            .param("voiceEngagementEndDateTimeForm", FIXATION_DATE.toString("yyyyMMddHHmmss"))
                            .param("voiceEngagementDisengageReasonForm", "user_request")
                            .param("voiceEngagementDisengageOrderDateForm", "20140101")
            );

            // 確認・JSON
            ValidationErrorResponseAssert.assertJsonPath(resultActions, "equipmentNumberForm");
        }

        @Test
        public void test_voiceEngagementEndDateFormがない() throws Exception {

            // 実行
            ResultActions resultActions = mockMvc.perform(post(URI)
                            .param("equipmentNumberForm", "00000001")
                            .param("voiceEngagementDisengageReasonForm", "user_request")
                            .param("voiceEngagementDisengageOrderDateForm", "20140101")
            );


            // 確認・JSON
            ValidationErrorResponseAssert.assertJsonPath(resultActions, "voiceEngagementEndDateTimeForm");
        }

        @Test
        public void test_voiceEngagementDisengageReasonFormがない() throws Exception {

            // 実行
            ResultActions resultActions = mockMvc.perform(post(URI)
                            .param("equipmentNumberForm", "00000001")
                            .param("voiceEngagementEndDateTimeForm", FIXATION_DATE.toString("yyyyMMddHHmmss"))
                            .param("voiceEngagementDisengageOrderDateForm", "20140101")
            );

            // 確認・JSON
            ValidationErrorResponseAssert.assertJsonPath(resultActions, "voiceEngagementDisengageReasonForm");
        }

        @Test
        public void test_voiceEngagementDisengageOrderDateFormがない() throws Exception {

            // 実行
            ResultActions resultActions = mockMvc.perform(post(URI)
                            .param("equipmentNumberForm", "00000001")
                            .param("voiceEngagementEndDateTimeForm", FIXATION_DATE.toString("yyyyMMddHHmmss"))
                            .param("voiceEngagementDisengageReasonForm", "user_request")
            );

            // 確認・JSON
            ValidationErrorResponseAssert.assertJsonPath(resultActions, "voiceEngagementDisengageOrderDateForm");
        }

        @Test
        public void test_equipmentNumberFormの桁間違い() throws Exception {

            // 実行
            ResultActions resultActions = mockMvc.perform(post(URI)
                            .param("equipmentNumberForm", "0000001")
                            .param("voiceEngagementEndDateTimeForm", FIXATION_DATE.toString("yyyyMMddHHmmss"))
                            .param("voiceEngagementDisengageReasonForm", "user_request")
                            .param("voiceEngagementDisengageOrderDateForm", "20140101")
            );

            // 確認・JSON
            ValidationErrorResponseAssert.assertJsonPath(resultActions, "equipmentNumberForm.value");
        }

        @Test
        public void test_voiceEngagementEndDateFormの桁間違い() throws Exception {

            // 実行
            ResultActions resultActions = mockMvc.perform(post(URI)
                            .param("equipmentNumberForm", "00000001")
                            .param("voiceEngagementEndDateTimeForm", FIXATION_DATE.toString("yyyyMMddHHmmss") + "123456789012345")
                            .param("voiceEngagementDisengageReasonForm", "user_request")
                            .param("voiceEngagementDisengageOrderDateForm", "20140101")
            );

            // 確認・JSON
            ValidationErrorResponseAssert.assertJsonPath(resultActions, "voiceEngagementEndDateTimeForm.value");
        }

        @Test
        public void test_voiceEngagementDisengageReasonFormの桁間違い() throws Exception {

            // 実行
            ResultActions resultActions = mockMvc.perform(post(URI)
                            .param("equipmentNumberForm", "00000001")
                            .param("voiceEngagementEndDateTimeForm", FIXATION_DATE.toString("yyyyMMddHHmmss"))
                            .param("voiceEngagementDisengageReasonForm", "user_re11quest")
                            .param("voiceEngagementDisengageOrderDateForm", "20140101")
            );

            // 確認・JSON
            ValidationErrorResponseAssert.assertJsonPath(resultActions, "voiceEngagementDisengageReasonForm.value");
        }

        @Test
        public void test_voiceEngagementDisengageOrderDateFormの桁間違い() throws Exception {

            // 実行
            ResultActions resultActions = mockMvc.perform(post(URI)
                            .param("equipmentNumberForm", "00000001")
                            .param("voiceEngagementEndDateTimeForm", FIXATION_DATE.toString("yyyyMMddHHmmss"))
                            .param("voiceEngagementDisengageReasonForm", "user_request")
                            .param("voiceEngagementDisengageOrderDateForm", "201400101")
            );

            // 確認・JSON
            ValidationErrorResponseAssert.assertJsonPath(resultActions, "voiceEngagementDisengageOrderDateForm.value");
        }

    }

}