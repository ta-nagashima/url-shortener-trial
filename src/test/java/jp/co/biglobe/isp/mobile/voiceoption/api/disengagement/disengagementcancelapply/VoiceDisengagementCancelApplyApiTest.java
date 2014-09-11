package jp.co.biglobe.isp.mobile.voiceoption.api.disengagement.disengagementcancelapply;

import jp.co.biglobe.isp.mobile.ltethreeg.datasource.SIM_INFO;
import jp.co.biglobe.isp.mobile.voiceoption.database.VoiceEngagementStateAssert;
import jp.co.biglobe.isp.mobile.voiceoption.datasource.voiceengagement.db.FixtureVoiceEngagementState;
import jp.co.biglobe.test.util.dbunit.DbUnitTester;
import jp.co.biglobe.test.util.response.UpdateApiResponseAssert;
import jp.co.biglobe.test.util.response.ValidationErrorResponseAssert;
import org.dbunit.DatabaseUnitException;
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

/**
 * 解約予約キャンセル
 */

@RunWith(Enclosed.class)
public class VoiceDisengagementCancelApplyApiTest {

    private static final String URI = "/voiceoption/outside/skip/disengagementreservationcancel/submit";

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
        }

        //音声契約
        @Test
        public void test_正常系_音声契約が解約予約中の時は解約キャンセルができる() throws Exception {
            // テストケースの設定
            tester.cleanInsertQuery(SIM_INFO.regist);
            tester.cleanInsertQuery(FixtureVoiceEngagementState.One.getForDisEngagementReserved());

            // 実行
            ResultActions resultActions = mockMvc.perform(post(URI).param("equipmentNumberForm", "00000001"));

            // 確認・JASON
            UpdateApiResponseAssert.assertJsonPath(resultActions);

            VoiceEngagementStateAssert voiceEngagementStateAssert = new VoiceEngagementStateAssert(tester);
            voiceEngagementStateAssert.assertTableWithAllColumns(FixtureVoiceEngagementState.One.getForEngagement());
        }

        @Test
        public void test_異常_音声契約が申し込み中のときは解約キャンセルできない() throws Exception {
            // テストケースの設定
            tester.cleanInsertQuery(SIM_INFO.regist);
            tester.cleanInsertQuery(FixtureVoiceEngagementState.One.getDefaultData());

            // 実行
            ResultActions resultActions = mockMvc.perform(post(URI).param("equipmentNumberForm", "00000001"));

            // 確認・JASON
            resultActions.andExpect(jsonPath("$.header.statusCode").value("system_error"));
            resultActions.andExpect(jsonPath("$.error.type").value("SystemCheckException"));
            resultActions.andExpect(jsonPath("$.error.message").value("音声オプション契約のステータスが異常"));
        }

        @Test
        public void test_異常_音声契約が契約中のときは解約キャンセルできない() throws Exception {
            // テストケースの設定
            tester.cleanInsertQuery(SIM_INFO.regist);
            tester.cleanInsertQuery(FixtureVoiceEngagementState.One.getForEngagement());

            // 実行
            ResultActions resultActions = mockMvc.perform(post(URI).param("equipmentNumberForm", "00000001"));

            // 確認・JASON
            resultActions.andExpect(jsonPath("$.header.statusCode").value("system_error"));
            resultActions.andExpect(jsonPath("$.error.type").value("SystemCheckException"));
            resultActions.andExpect(jsonPath("$.error.message").value("音声オプション契約のステータスが異常"));
        }

        @Test
        public void test_異常_音声契約が解約済みのときは解約キャンセルできない() throws Exception {
            // テストケースの設定
            tester.cleanInsertQuery(SIM_INFO.regist);
            tester.cleanInsertQuery(FixtureVoiceEngagementState.One.getForDisEngaged());

            // 実行
            ResultActions resultActions = mockMvc.perform(post(URI).param("equipmentNumberForm", "00000001"));

            // 確認・JASON
            resultActions.andExpect(jsonPath("$.header.statusCode").value("system_error"));
            resultActions.andExpect(jsonPath("$.error.type").value("SystemCheckException"));
            resultActions.andExpect(jsonPath("$.error.message").value("音声オプション契約のステータスが異常"));
        }

        @Test
        public void test_異常_音声契約がキャンセル済みのときは解約キャンセルできない() throws Exception {
            // テストケースの設定
            tester.cleanInsertQuery(SIM_INFO.regist);
            tester.cleanInsertQuery(FixtureVoiceEngagementState.One.getForEngagement());

            // 実行
            ResultActions resultActions = mockMvc.perform(post(URI).param("equipmentNumberForm", "00000001"));

            // 確認・JASON
            resultActions.andExpect(jsonPath("$.header.statusCode").value("system_error"));
            resultActions.andExpect(jsonPath("$.error.type").value("SystemCheckException"));
            resultActions.andExpect(jsonPath("$.error.message").value("音声オプション契約のステータスが異常"));
        }

        @Test
        public void test_検索結果が０件() throws Exception {
            // テストケースの設定
            tester.cleanInsertQuery(SIM_INFO.regist);
            tester.cleanInsertQuery(FixtureVoiceEngagementState.One.getForDisEngagementReserved());

            // 実行
            ResultActions resultActions = mockMvc.perform(post(URI).param("equipmentNumberForm", "99999999"));

            // 確認・JASON
            resultActions.andExpect(jsonPath("$.header.statusCode").value("system_error"));
            resultActions.andExpect(jsonPath("$.error.type").value("SystemCheckException"));
            resultActions.andExpect(jsonPath("$.error.message").value("音声オプション契約が見つかりません"));
        }

        @Test
        public void test_ステータスが不正エラー() throws Exception {
            // テストケースの設定
            tester.cleanInsertQuery(SIM_INFO.regist);
            tester.cleanInsertQuery(FixtureVoiceEngagementState.One.getForDisEngaged());

            // 実行
            ResultActions resultActions = mockMvc.perform(post(URI).param("equipmentNumberForm", "00000001"));

            // 確認・JASON
            resultActions.andExpect(jsonPath("$.header.statusCode").value("system_error"));
            resultActions.andExpect(jsonPath("$.error.type").value("SystemCheckException"));
            resultActions.andExpect(jsonPath("$.error.message").value("音声オプション契約のステータスが異常"));

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
            ResultActions resultActions = mockMvc.perform(post(URI));

            // 確認・JASON
            ValidationErrorResponseAssert.assertJsonPath(resultActions, "equipmentNumberForm");
        }

        @Test
        public void test_equipmentNumberFormの桁間違い() throws Exception {

            // 実行
            ResultActions resultActions = mockMvc.perform(post(URI).param("equipmentNumberForm", "0000001"));

            // 確認・JASON
            ValidationErrorResponseAssert.assertJsonPath(resultActions, "equipmentNumberForm.value");
        }

    }
}