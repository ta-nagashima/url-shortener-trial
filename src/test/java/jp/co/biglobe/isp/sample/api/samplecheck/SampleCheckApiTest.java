package jp.co.biglobe.isp.sample.api.samplecheck;

import jp.co.biglobe.isp.mobile.ltethreeg.datasource.SIM_INFO;
import jp.co.biglobe.test.util.dbunit.DbUnitTester;
import jp.co.biglobe.test.util.usecase.BobioUseCase;
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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@RunWith(Enclosed.class)
public class SampleCheckApiTest {

    private static final String URI = "/sample/check";

    private static final String SCENARIO_NAME = "C_iyakukin_mo_ref";

    private static BobioUseCase testcase = new BobioUseCase(SCENARIO_NAME);


    private static ResultActions getValidResultActions(MockMvc mockMvc, String URI) throws Exception {
        return mockMvc.perform(post(URI)
                        .param("equipmentNumberForm", "00000001")
        );
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
        public void setup() throws Exception {
            mockMvc = webAppContextSetup(wac).build();

            testcase.set("DEFAULT");

            // 処理前にテーブルをクリアする
            tester.executeAllClearTableAndSeq();

        }

        @After
        public void tearDown() throws Exception {
            testcase.unset();
        }

        @Test
        public void _申し込み中のときに解除不可能() throws Exception {

            // 事前準備
            tester.cleanInsertQuery(SIM_INFO.lte);
            testcase.set("NOTEXIST");

            // 実行
            ResultActions resultActions = mockMvc.perform(post(URI)
                    .param("sampleDateTimeForm", "20140401000000"));

//            resultActions.andExpect(content().string(""));

            // 確認：JSON（API固有部分）
            resultActions.andExpect(jsonPath("$.result").value("ok"));
            resultActions.andExpect(jsonPath("$.detail.type").value("registered"));
            resultActions.andExpect(jsonPath("$.detail.message").value("登録済"));

            // 確認：JSON（API共通部分）
            resultActions.andExpect(jsonPath("$.header.statusCode").value("ok"));
            resultActions.andExpect(jsonPath("$.header.requestId").exists());
            resultActions.andExpect(jsonPath("$.header.hostName").exists());
            resultActions.andExpect(jsonPath("$.error").doesNotExist());
        }
    }

    /*
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
        }

    }
*/

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
            public void setup() throws Exception {
                mockMvc = webAppContextSetup(wac).build();
            }

            @After
            public void tearDown() throws Exception {
                testcase.unset();
            }

            @Test
            public void _sampleDateTimeFormがない() throws Exception {
                // 実行
                ResultActions resultActions = mockMvc.perform(post(URI));

                // 確認：JSON（API固有部分）
                resultActions.andExpect(jsonPath("$.header.statusCode").value("validation_error"));
                resultActions.andExpect(jsonPath("$.header.requestId").exists());
                resultActions.andExpect(jsonPath("$.header.hostName").exists());
                resultActions.andExpect(jsonPath("$.error.type").value("AlarmValidationException"));
                resultActions.andExpect(jsonPath("$.error.message").exists());
                resultActions.andExpect(jsonPath("$.detail").exists());
            }

            @Test
            public void _sampleDateTimeFormに不正な文字() throws Exception {
                // 実行
                ResultActions resultActions = mockMvc.perform(post(URI)
                        .param("sampleDateTimeForm", "invalid"));

                // 確認：JSON（API固有部分）
                resultActions.andExpect(jsonPath("$.header.statusCode").value("validation_error"));
                resultActions.andExpect(jsonPath("$.header.requestId").exists());
                resultActions.andExpect(jsonPath("$.header.hostName").exists());
                resultActions.andExpect(jsonPath("$.error.type").value("AlarmValidationException"));
                resultActions.andExpect(jsonPath("$.error.message").exists());
                resultActions.andExpect(jsonPath("$.detail").exists());
            }
        }
    }

}
