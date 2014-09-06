package jp.co.biglobe.isp.mobile.voiceoption.api.disengagement.disengagementbyequipmentnumbercheck;

import jp.co.biglobe.isp.mobile.ltethreeg.datasource.SIM_INFO;
import jp.co.biglobe.isp.mobile.voiceoption.datasource.mnpout.db.FixtureVoiceMnpOutState;
import jp.co.biglobe.isp.mobile.voiceoption.datasource.voiceengagement.db.FixtureVoiceEngagementState;
import jp.co.biglobe.test.util.dbunit.DbUnitTester;
import jp.co.biglobe.test.util.response.MultiCheckApiResponseAssert;
import jp.co.biglobe.test.util.response.ValidationErrorResponseAssert;
import jp.co.biglobe.test.util.usecase.BobioUseCase;
import org.dbunit.DatabaseUnitException;
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
public class VoiceDisengagementByEquipmentNumberCheckApiTest {

    private static final String URI = "/voiceoption/outside/skip/disengagementbyequipmentnumber/check";

    private static final String SCENARIO_NAME = "C_iyakukin_mo_ref";

    private static BobioUseCase testcase = new BobioUseCase(SCENARIO_NAME);

    private static void assertForCostPenaltyCharge(ResultActions resultActions) throws Exception {

        resultActions.andExpect(jsonPath("$.disengagement_charge.status").value("cost"));
        resultActions.andExpect(jsonPath("$.disengagement_charge.end_month").value("299912"));
        resultActions.andExpect(jsonPath("$.disengagement_charge.amount").value("6000"));

    }

    private static void assertForNotCostPenaltyCharge(ResultActions resultActions) throws Exception {

        resultActions.andExpect(jsonPath("$.disengagement_charge.status").value("not_cost"));
        resultActions.andExpect(jsonPath("$.disengagement_charge.end_month").value("201404"));
        resultActions.andExpect(jsonPath("$.disengagement_charge.amount").value("6000"));

    }

    private static void assertForNotExistCostPenaltyCharge(ResultActions resultActions) throws Exception {

        resultActions.andExpect(jsonPath("$.disengagement_charge.status").value("not_cost"));
        resultActions.andExpect(jsonPath("$.disengagement_charge.end_month").value(""));
        resultActions.andExpect(jsonPath("$.disengagement_charge.amount").value(""));

    }

    private static ResultActions getValidResultActions(MockMvc mockMvc, String URI) throws Exception {
        return mockMvc.perform(post(URI)
                        .param("equipmentNumberForm", "00000001")
        );
    }

    @RunWith(Enclosed.class)
    public static class _正常処理 {

        @RunWith(SpringJUnit4ClassRunner.class)
        @WebAppConfiguration
        @ContextConfiguration(locations = {"classpath:context.xml"})
        public static class _契約中 {

            @Autowired
            public DbUnitTester tester;

            @Autowired
            private WebApplicationContext wac;
            private MockMvc mockMvc;

            @Before
            public void setup() throws IOException, DatabaseUnitException, SQLException {
                mockMvc = webAppContextSetup(wac).build();

                testcase.set("DEFAULT");

                // 処理前にテーブルをクリアする
                tester.executeAllClearTableAndSeq();

            }

            @After
            public void tearDown() throws IOException {
                testcase.unset();
            }

            @Test
            public void _契約中_転出実績なし_転出処理なし_契約解除料発生のときに解約可能_契約解除料発生() throws Exception {

                // 事前準備
                tester.cleanInsertQuery(SIM_INFO.lte);
                tester.cleanInsertQuery(FixtureVoiceEngagementState.One.getForEngagement());
                testcase.set("NOTEND");

                // 実行
                ResultActions resultActions = getValidResultActions(mockMvc, URI);

                // 確認：JSON（API固有部分）
                assertForCostPenaltyCharge(resultActions);
                resultActions.andExpect(jsonPath("$.disengagement.result").value("ok"));
                resultActions.andExpect(jsonPath("$.mnp_out_completion_and_disengagement.result").value("ng"));
                MultiCheckApiResponseAssert.assertJsonPath(resultActions);
            }

            @Test
            public void _契約中_転出実績なし_転出処理なし_契約解除料発生しないときに解約可能_契約解除料なし() throws Exception {

                // 事前準備
                tester.cleanInsertQuery(SIM_INFO.lte);
                tester.cleanInsertQuery(FixtureVoiceEngagementState.One.getForEngagement());
                testcase.set("END");

                // 実行
                ResultActions resultActions = getValidResultActions(mockMvc, URI);

                // 確認：JSON（API固有部分）
                assertForNotCostPenaltyCharge(resultActions);
                resultActions.andExpect(jsonPath("$.disengagement.result").value("ok"));
                resultActions.andExpect(jsonPath("$.mnp_out_completion_and_disengagement.result").value("ng"));
                MultiCheckApiResponseAssert.assertJsonPath(resultActions);
            }

            /**
             * 契約中で契約解除料がないことは基本的にあり得ないが、
             * 違約金システムが裏でどうなっているのか分からないのと、
             * 違約金免除で契約解除料が削除されることがあるかもしれないので
             * 例外を飛ばさずにそのまま処理する。
             */
            @Test
            public void _契約中_転出実績なし_転出処理なし_契約解除料がないときに解約可能_契約解除料なし() throws Exception {

                // 事前準備
                tester.cleanInsertQuery(SIM_INFO.lte);
                tester.cleanInsertQuery(FixtureVoiceEngagementState.One.getForEngagement());
                testcase.set("NOTEXIST");

                // 実行
                ResultActions resultActions = getValidResultActions(mockMvc, URI);

                // 確認：JSON（API固有部分）
                assertForNotExistCostPenaltyCharge(resultActions);
                resultActions.andExpect(jsonPath("$.disengagement.result").value("ok"));
                resultActions.andExpect(jsonPath("$.mnp_out_completion_and_disengagement.result").value("ng"));
                MultiCheckApiResponseAssert.assertJsonPath(resultActions);
            }

            @Test
            public void _契約中_転出実績あり_転出処理なし_契約解除料発生のときに解約可能_契約解除料発生() throws Exception {

                // 事前準備
                tester.cleanInsertQuery(SIM_INFO.lte);
                tester.cleanInsertQuery(FixtureVoiceEngagementState.One.getForEngagement());
                tester.cleanInsertQuery(FixtureVoiceMnpOutState.One.getForCancelFromNumbered());
                testcase.set("NOTEND");

                // 実行
                ResultActions resultActions = getValidResultActions(mockMvc, URI);

                // 確認：JSON（API固有部分）
                assertForCostPenaltyCharge(resultActions);
                resultActions.andExpect(jsonPath("$.disengagement.result").value("ok"));
                resultActions.andExpect(jsonPath("$.mnp_out_completion_and_disengagement.result").value("ng"));
                MultiCheckApiResponseAssert.assertJsonPath(resultActions);
            }

            @Test
            public void _契約中_転出実績あり_転出処理なし_契約解除料発生しないときに解約可能_契約解除料なし() throws Exception {

                // 事前準備
                tester.cleanInsertQuery(SIM_INFO.lte);
                tester.cleanInsertQuery(FixtureVoiceEngagementState.One.getForEngagement());
                tester.cleanInsertQuery(FixtureVoiceMnpOutState.One.getForCancelFromNumbered());
                testcase.set("END");

                // 実行
                ResultActions resultActions = getValidResultActions(mockMvc, URI);

                // 確認：JSON（API固有部分）
                assertForNotCostPenaltyCharge(resultActions);
                resultActions.andExpect(jsonPath("$.disengagement.result").value("ok"));
                resultActions.andExpect(jsonPath("$.mnp_out_completion_and_disengagement.result").value("ng"));
                MultiCheckApiResponseAssert.assertJsonPath(resultActions);
            }

            @Test
            public void _契約中_転出処理依頼まえ_契約解除料発生のときに解約不可能_契約解除料あり() throws Exception {

                // 事前準備
                tester.cleanInsertQuery(SIM_INFO.lte);
                tester.cleanInsertQuery(FixtureVoiceEngagementState.One.getForEngagement());
                tester.cleanInsertQuery(FixtureVoiceMnpOutState.One.getForRequestedWaiting());
                testcase.set("NOTEND");

                // 実行
                ResultActions resultActions = getValidResultActions(mockMvc, URI);

                // 確認：JSON（API固有部分）
                assertForCostPenaltyCharge(resultActions);
                resultActions.andExpect(jsonPath("$.disengagement.result").value("ng"));
                resultActions.andExpect(jsonPath("$.mnp_out_completion_and_disengagement.result").value("ng"));
                MultiCheckApiResponseAssert.assertJsonPath(resultActions);
            }

            @Test
            public void _契約中_転出処理依頼まえ_契約解除料発生しないときに解約不可能_契約解除料なし() throws Exception {

                // 事前準備
                tester.cleanInsertQuery(SIM_INFO.lte);
                tester.cleanInsertQuery(FixtureVoiceEngagementState.One.getForEngagement());
                tester.cleanInsertQuery(FixtureVoiceMnpOutState.One.getForRequestedWaiting());
                testcase.set("END");

                // 実行
                ResultActions resultActions = getValidResultActions(mockMvc, URI);

                // 確認：JSON（API固有部分）
                assertForNotCostPenaltyCharge(resultActions);
                resultActions.andExpect(jsonPath("$.disengagement.result").value("ng"));
                resultActions.andExpect(jsonPath("$.mnp_out_completion_and_disengagement.result").value("ng"));
                MultiCheckApiResponseAssert.assertJsonPath(resultActions);
            }

            @Test
            public void _契約中_転出処理依頼済み_契約解除料発生のときに解約不可能_契約解除料あり() throws Exception {

                // 事前準備
                tester.cleanInsertQuery(SIM_INFO.lte);
                tester.cleanInsertQuery(FixtureVoiceEngagementState.One.getForEngagement());
                tester.cleanInsertQuery(FixtureVoiceMnpOutState.One.getForRequested());
                testcase.set("NOTEND");

                // 実行
                ResultActions resultActions = getValidResultActions(mockMvc, URI);

                // 確認：JSON（API固有部分）
                assertForCostPenaltyCharge(resultActions);
                resultActions.andExpect(jsonPath("$.disengagement.result").value("ng"));
                resultActions.andExpect(jsonPath("$.mnp_out_completion_and_disengagement.result").value("ng"));
                MultiCheckApiResponseAssert.assertJsonPath(resultActions);
            }

            @Test
            public void _契約中_転出処理依頼済み_契約解除料発生しないときに解約不可能_契約解除料なし() throws Exception {

                // 事前準備
                tester.cleanInsertQuery(SIM_INFO.lte);
                tester.cleanInsertQuery(FixtureVoiceEngagementState.One.getForEngagement());
                tester.cleanInsertQuery(FixtureVoiceMnpOutState.One.getForRequested());
                testcase.set("END");

                // 実行
                ResultActions resultActions = getValidResultActions(mockMvc, URI);

                // 確認：JSON（API固有部分）
                assertForNotCostPenaltyCharge(resultActions);
                resultActions.andExpect(jsonPath("$.disengagement.result").value("ng"));
                resultActions.andExpect(jsonPath("$.mnp_out_completion_and_disengagement.result").value("ng"));
                MultiCheckApiResponseAssert.assertJsonPath(resultActions);
            }

            @Test
            public void _契約中_お客様の転出待ち_契約解除料発生のときに解約不可能_契約解除料あり() throws Exception {

                // 事前準備
                tester.cleanInsertQuery(SIM_INFO.lte);
                tester.cleanInsertQuery(FixtureVoiceEngagementState.One.getForEngagement());
                tester.cleanInsertQuery(FixtureVoiceMnpOutState.One.getForNumbered());
                testcase.set("NOTEND");

                // 実行
                ResultActions resultActions = getValidResultActions(mockMvc, URI);

                // 確認：JSON（API固有部分）
                assertForCostPenaltyCharge(resultActions);
                resultActions.andExpect(jsonPath("$.disengagement.result").value("ng"));
                resultActions.andExpect(jsonPath("$.mnp_out_completion_and_disengagement.result").value("ok"));
                MultiCheckApiResponseAssert.assertJsonPath(resultActions);
            }

            @Test
            public void _契約中_お客様の転出待ち_契約解除料発生しないときに解約不可能_契約解除料なし() throws Exception {

                // 事前準備
                tester.cleanInsertQuery(SIM_INFO.lte);
                tester.cleanInsertQuery(FixtureVoiceEngagementState.One.getForEngagement());
                tester.cleanInsertQuery(FixtureVoiceMnpOutState.One.getForNumbered());
                testcase.set("END");

                // 実行
                ResultActions resultActions = getValidResultActions(mockMvc, URI);

                // 確認：JSON（API固有部分）
                assertForNotCostPenaltyCharge(resultActions);
                resultActions.andExpect(jsonPath("$.disengagement.result").value("ng"));
                resultActions.andExpect(jsonPath("$.mnp_out_completion_and_disengagement.result").value("ok"));
                MultiCheckApiResponseAssert.assertJsonPath(resultActions);
            }
        }

        @RunWith(SpringJUnit4ClassRunner.class)
        @WebAppConfiguration
        @ContextConfiguration(locations = {"classpath:context.xml"})
        public static class _契約中以外 {

            @Autowired
            public DbUnitTester tester;

            @Autowired
            private WebApplicationContext wac;
            private MockMvc mockMvc;

            @Before
            public void setup() throws IOException, DatabaseUnitException, SQLException {
                mockMvc = webAppContextSetup(wac).build();

                testcase.set("DEFAULT");

                // 処理前にテーブルをクリアする
                tester.executeAllClearTableAndSeq();

            }

            @After
            public void tearDown() throws IOException {
                testcase.unset();
            }

            @Test
            public void _申し込み中のときに解除不可能() throws Exception {

                // 事前準備
                tester.cleanInsertQuery(SIM_INFO.lte);
                tester.cleanInsertQuery(FixtureVoiceEngagementState.One.getDefaultData());
                testcase.set("NOTEXIST");

                // 実行
                ResultActions resultActions = getValidResultActions(mockMvc, URI);

                // 確認：JSON（API固有部分）
                assertForNotExistCostPenaltyCharge(resultActions);
                resultActions.andExpect(jsonPath("$.disengagement.result").value("ng"));
                resultActions.andExpect(jsonPath("$.mnp_out_completion_and_disengagement.result").value("ng"));
                MultiCheckApiResponseAssert.assertJsonPath(resultActions);
            }

            @Test
            public void _音声オプション契約がないときに解除不可能() throws Exception {

                // 事前準備
                tester.cleanInsertQuery(SIM_INFO.lte);
                tester.cleanInsertQuery(FixtureVoiceEngagementState.One.getDefaultData());
                testcase.set("NOTEXIST");

                // 実行
                ResultActions resultActions = mockMvc.perform(post(URI)
                        .param("equipmentNumberForm", "00000002"));

                // 確認：JSON（API固有部分）
                assertForNotExistCostPenaltyCharge(resultActions);
                resultActions.andExpect(jsonPath("$.disengagement.result").value("ng"));
                resultActions.andExpect(jsonPath("$.mnp_out_completion_and_disengagement.result").value("ng"));
                MultiCheckApiResponseAssert.assertJsonPath(resultActions);
            }

            @Test
            public void _キャンセル済みのときに解除不可能() throws Exception {

                // 事前準備
                tester.cleanInsertQuery(SIM_INFO.lte);
                tester.cleanInsertQuery(FixtureVoiceEngagementState.One.getForCancelForUserRequest());
                testcase.set("END");

                // 実行
                ResultActions resultActions = getValidResultActions(mockMvc, URI);

                // 確認：JSON（API固有部分）
                assertForNotCostPenaltyCharge(resultActions);
                resultActions.andExpect(jsonPath("$.disengagement.result").value("ng"));
                resultActions.andExpect(jsonPath("$.mnp_out_completion_and_disengagement.result").value("ng"));
                MultiCheckApiResponseAssert.assertJsonPath(resultActions);
            }

            @Test
            public void _キャンセル済み_転出済みのときに解除不可能() throws Exception {

                // 事前準備
                tester.cleanInsertQuery(SIM_INFO.lte);
                tester.cleanInsertQuery(FixtureVoiceEngagementState.One.getForCancelForUserRequest());
                tester.cleanInsertQuery(FixtureVoiceMnpOutState.One.getForCompletion());
                testcase.set("END");

                // 実行
                ResultActions resultActions = getValidResultActions(mockMvc, URI);

                // 確認：JSON（API固有部分）
                assertForNotCostPenaltyCharge(resultActions);
                resultActions.andExpect(jsonPath("$.disengagement.result").value("ng"));
                resultActions.andExpect(jsonPath("$.mnp_out_completion_and_disengagement.result").value("ng"));
                MultiCheckApiResponseAssert.assertJsonPath(resultActions);
            }

        }

    }

    @RunWith(Enclosed.class)
    public static class _バリデーションエラー {

        @RunWith(SpringJUnit4ClassRunner.class)
        @WebAppConfiguration
        @ContextConfiguration(locations = {"classpath:context.xml"})
        public static class _equipmentNumberForm {

            @Autowired
            private WebApplicationContext wac;
            private MockMvc mockMvc;

            @Before
            public void setup() throws IOException, DatabaseUnitException, SQLException {
                mockMvc = webAppContextSetup(wac).build();

                testcase.set("DEFAULT");

            }

            @After
            public void tearDown() throws IOException {
                testcase.unset();
            }

            @Test
            public void _equipmentNumberFormがない() throws Exception {

                // 事前準備
                testcase.set("NOTEXIST");

                // 実行
                ResultActions resultActions = mockMvc.perform(post(URI));

                // 確認：JSON（API固有部分）
                ValidationErrorResponseAssert.assertJsonPath(resultActions, "equipmentNumberForm");
            }

            @Test
            public void _equipmentNumberFormに不正な文字() throws Exception {

                // 事前準備
                testcase.set("NOTEXIST");

                // 実行
                ResultActions resultActions = mockMvc.perform(post(URI)
                        .param("equipmentNumberForm", "ZZZZZZZ1"));

                // 確認：JSON（API固有部分）
                ValidationErrorResponseAssert.assertJsonPath(resultActions, "equipmentNumberForm.value");
            }

        }

    }

}
