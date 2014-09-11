package jp.co.biglobe.isp.mobile.voiceoption.api.engagement.engagementapply;

import jp.co.biglobe.isp.mobile.ltethreeg.datasource.SIM_INFO;
import jp.co.biglobe.isp.mobile.voiceoption.database.VoiceEngagementStateAssert;
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
public class VoiceEngagementApplyApiTest {

    private static final String URI = "/voiceoption/outside/skip/engagementapply/submit";

    @RunWith(SpringJUnit4ClassRunner.class)
    @WebAppConfiguration
    @ContextConfiguration(locations = {"classpath:context.xml"})
    public static class 正常系異常系のテスト {

        @Autowired
        private WebApplicationContext wac;
        private MockMvc mockMvc;

        @Autowired
        public DbUnitTester tester;

        public final DateTime FIXATION_DATE = new DateTime(2014, 1, 1, 0, 0, 0);

        @Before
        public void setup() throws DatabaseUnitException, SQLException, IOException {
            mockMvc = webAppContextSetup(wac).build();

            // 処理前にテーブルをクリアする
            tester.executeAllClearTableAndSeq();


            // 「現在時刻」を固定化する
            DateTimeUtils.setCurrentMillisFixed(FIXATION_DATE.getMillis());
        }

        @After
        public void tearDown(){
            // 「現在時刻」をシステム日付に戻す
            DateTimeUtils.setCurrentMillisSystem();
        }

        @Test
        public void test_正常系() throws Exception {
            // テストケースの設定
            tester.cleanInsertQuery(SIM_INFO.regist);
            tester.cleanInsertQuery(FixtureVoiceEngagementState.One.getDefaultData());

            // 実行
            ResultActions resultActions = mockMvc.perform(post(URI)
                    .param("equipmentNumberForm", "00000001")
                    .param("voiceEngagementStartDateForm", "20140101"));

            // 確認・JASON
            UpdateApiResponseAssert.assertJsonPath(resultActions);

            VoiceEngagementStateAssert voiceEngagementStateAssert = new VoiceEngagementStateAssert(tester);
            voiceEngagementStateAssert.assertTableWithAllColumns(FixtureVoiceEngagementState.One.getForEngagement(FIXATION_DATE));
        }

        @Test
        public void test_検索結果が０件() throws Exception {
            // テストケースの設定
            tester.cleanInsertQuery(SIM_INFO.regist);
            tester.cleanInsertQuery(FixtureVoiceEngagementState.One.getDefaultData());

            // 実行
            ResultActions resultActions = mockMvc.perform(post(URI)
                    .param("equipmentNumberForm", "99999999")
                    .param("voiceEngagementStartDateForm", "20140101"));

            // 確認・JASON
            resultActions.andExpect(jsonPath("$.header.statusCode").value("system_error"));
            resultActions.andExpect(jsonPath("$.error.type").value("SystemCheckException"));
            resultActions.andExpect(jsonPath("$.error.message").value("音声オプション契約が見つかりません"));
        }

        @Test
        public void test_ステータスが不正エラー() throws Exception {
            // テストケースの設定
            tester.cleanInsertQuery(SIM_INFO.regist);
            tester.cleanInsertQuery(FixtureVoiceEngagementState.One.getForEngagement());

            // 実行
            ResultActions resultActions = mockMvc.perform(post(URI)
                    .param("equipmentNumberForm", "00000001")
                    .param("voiceEngagementStartDateForm", "20140101"));

            // 確認・JASON
            resultActions.andExpect(jsonPath("$.header.statusCode").value("system_error"));
            resultActions.andExpect(jsonPath("$.error.type").value("SystemCheckException"));
            resultActions.andExpect(jsonPath("$.error.message").value("音声オプション契約のステータスが異常"));

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
        public void equipmentNumberFormの桁間違い() throws Exception {

            // 実行
            ResultActions resultActions = mockMvc.perform(post(URI)
                    .param("equipmentNumberForm", "000001")
                    .param("voiceEngagementStartDateForm", "20140101"));

            // 確認・JASON
            ValidationErrorResponseAssert.assertJsonPath(resultActions, "equipmentNumberForm.value");
        }


        @Test
        public void voiceEngagementStartDateがない() throws Exception {

            // 実行
            ResultActions resultActions = mockMvc.perform(post(URI)
                    .param("equipmentNumberForm", "00000001"));

            // 確認・JASON
            ValidationErrorResponseAssert.assertJsonPath(resultActions, "voiceEngagementStartDateForm");
        }

        @Test
        public void voiceEngagementStartDateに不正な値() throws Exception {

            // 実行
            ResultActions resultActions = mockMvc.perform(post(URI)
                    .param("equipmentNumberForm", "00000001")
                    .param("voiceEngagementStartDateForm", "201a0101"));

            // 確認・JASON
            ValidationErrorResponseAssert.assertJsonPath(resultActions, "voiceEngagementStartDateForm.value");
        }

        @Test
        public void test_バリデーションエラー_equipmentNumberFormがない() throws Exception {

            // 実行
            ResultActions resultActions = mockMvc.perform(post(URI)
                    .param("voiceEngagementStartDateForm", "20140101"));

            // 確認・JASON
            ValidationErrorResponseAssert.assertJsonPath(resultActions, "equipmentNumberForm");
        }

        @Test
        public void test_バリデーションエラー_voiceEngagementStartDateがない() throws Exception {

            // 実行
            ResultActions resultActions = mockMvc.perform(post(URI)
                    .param("equipmentNumberForm", "00000001"));

            // 確認・JASON
            ValidationErrorResponseAssert.assertJsonPath(resultActions, "voiceEngagementStartDateForm");
        }


    }

}
