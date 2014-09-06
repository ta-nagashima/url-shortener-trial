package jp.co.biglobe.isp.mobile.voiceoption.api.disengagement.immediatedisengagementrefer;

import jp.co.biglobe.isp.mobile.ltethreeg.datasource.SIM_INFO;
import jp.co.biglobe.isp.mobile.voiceoption.datasource.mnpout.db.FixtureVoiceMnpOutState;
import jp.co.biglobe.isp.mobile.voiceoption.datasource.voiceengagement.db.FixtureVoiceEngagementState;
import jp.co.biglobe.test.util.dbunit.DbUnitTester;
import jp.co.biglobe.test.util.response.ReferApiResponseAssert;
import jp.co.biglobe.test.util.response.ValidationErrorResponseAssert;
import org.dbunit.DatabaseUnitException;
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
public class VoiceImmediateDisengagementReferApiTest {

    private static final String URI = "/voiceoption/outside/skip/immediatedisengagement/refer";

    @RunWith(SpringJUnit4ClassRunner.class)
    @WebAppConfiguration
    @ContextConfiguration(locations = {"classpath:context.xml"})
    public static class _転出申込なしの場合 {

        @Autowired
        private WebApplicationContext wac;
        private MockMvc mockMvc;

        @Autowired
        public DbUnitTester tester;

        @Before
        public void setup() throws IOException, DatabaseUnitException, SQLException {
            // 処理前にテーブルをクリアする
            tester.executeAllClearTableAndSeq();

            mockMvc = webAppContextSetup(wac).build();
        }

        @Test
        public void _正常系_転出申込なしの場合() throws Exception {

            // 事前準備
            tester.cleanInsertQuery(SIM_INFO.regist);
            tester.cleanInsertQuery(FixtureVoiceEngagementState.One.getForEngagement());

            // 実行
            ResultActions resultActions = mockMvc.perform(post(URI)
                    .param("equipmentNumberForm", "00000001")
            );

            // 確認：JSON（API固有部分）
            resultActions.andExpect(jsonPath("$.status").value("mnp_out_not_ordered"));
            ReferApiResponseAssert.assertJsonPath(resultActions);
        }

        @Test
        public void _正常系_転出申込キャンセルの場合() throws Exception {

            // 事前準備
            tester.cleanInsertQuery(SIM_INFO.regist);
            tester.cleanInsertQuery(FixtureVoiceEngagementState.One.getForEngagement());
            tester.cleanInsertQuery(FixtureVoiceMnpOutState.One.getForCancelFromNumbered());

            // 実行
            ResultActions resultActions = mockMvc.perform(post(URI)
                            .param("equipmentNumberForm", "00000001")
            );

            // 確認：JSON（API固有部分）
            resultActions.andExpect(jsonPath("$.status").value("mnp_out_not_ordered"));
            ReferApiResponseAssert.assertJsonPath(resultActions);
        }
    }

    @RunWith(SpringJUnit4ClassRunner.class)
    @WebAppConfiguration
    @ContextConfiguration(locations = {"classpath:context.xml"})
    public static class _転出申込中の場合 {

        @Autowired
        private WebApplicationContext wac;
        private MockMvc mockMvc;

        @Autowired
        public DbUnitTester tester;

        @Before
        public void setup() throws IOException, DatabaseUnitException, SQLException {
            // 処理前にテーブルをクリアする
            tester.executeAllClearTableAndSeq();

            mockMvc = webAppContextSetup(wac).build();
        }

        @Test
        public void _正常系_事務局にMNP予約番号発行を依頼済みの場合() throws Exception {

            // 事前準備
            tester.cleanInsertQuery(SIM_INFO.regist);
            tester.cleanInsertQuery(FixtureVoiceEngagementState.One.getForEngagement());
            tester.cleanInsertQuery(FixtureVoiceMnpOutState.One.getForRequestedWaiting());

            // 実行
            ResultActions resultActions = mockMvc.perform(post(URI)
                            .param("equipmentNumberForm", "00000001")
            );

            // 確認：JSON（API固有部分）
            resultActions.andExpect(jsonPath("$.status").value("mnp_out_processing"));
            ReferApiResponseAssert.assertJsonPath(resultActions);
        }

        @Test
        public void _正常系_事務局がALADINでMNP予約番号発行を依頼済みの場合() throws Exception {

            // 事前準備
            tester.cleanInsertQuery(SIM_INFO.regist);
            tester.cleanInsertQuery(FixtureVoiceEngagementState.One.getForEngagement());
            tester.cleanInsertQuery(FixtureVoiceMnpOutState.One.getForRequested());

            // 実行
            ResultActions resultActions = mockMvc.perform(post(URI)
                            .param("equipmentNumberForm", "00000001")
            );

            // 確認：JSON（API固有部分）
            resultActions.andExpect(jsonPath("$.status").value("mnp_out_processing"));
            ReferApiResponseAssert.assertJsonPath(resultActions);
        }

        @Test
        public void _正常系_事務局がMNP予約番号を発行済みの場合() throws Exception {

            // 事前準備
            tester.cleanInsertQuery(SIM_INFO.regist);
            tester.cleanInsertQuery(FixtureVoiceEngagementState.One.getForEngagement());
            tester.cleanInsertQuery(FixtureVoiceMnpOutState.One.getForNumbered());

            // 実行
            ResultActions resultActions = mockMvc.perform(post(URI)
                            .param("equipmentNumberForm", "00000001")
            );

            // 確認：JSON（API固有部分）
            resultActions.andExpect(jsonPath("$.status").value("mnp_out_waiting"));
            ReferApiResponseAssert.assertJsonPath(resultActions);
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
        public void _equipmentNumberFormがない() throws Exception {
            // 実行
            ResultActions resultActions = mockMvc.perform(post(URI));

            // 確認：JSON（API固有部分）
            ValidationErrorResponseAssert.assertJsonPath(resultActions, "equipmentNumberForm");
        }

        @Test
        public void _equipmentNumberFormの桁数が多い() throws Exception {
            // 実行
            ResultActions resultActions = mockMvc.perform(post(URI)
                            .param("equipmentNumberForm", "000000012")
            );

            // 確認：JSON（API固有部分）
            ValidationErrorResponseAssert.assertJsonPath(resultActions, "equipmentNumberForm.value");
        }
    }

    @RunWith(SpringJUnit4ClassRunner.class)
    @WebAppConfiguration
    @ContextConfiguration(locations = {"classpath:context.xml"})
    public static class _転出完了の場合 {

        @Autowired
        private WebApplicationContext wac;
        private MockMvc mockMvc;

        @Autowired
        public DbUnitTester tester;

        @Before
        public void setup() throws IOException, DatabaseUnitException, SQLException {
            // 処理前にテーブルをクリアする
            tester.executeAllClearTableAndSeq();

            mockMvc = webAppContextSetup(wac).build();
        }

        @Test
        public void _正常系_転出完了済みの場合() throws Exception {

            // 事前準備
            tester.cleanInsertQuery(SIM_INFO.regist);
            tester.cleanInsertQuery(FixtureVoiceEngagementState.One.getForEngagement());
            tester.cleanInsertQuery(FixtureVoiceMnpOutState.One.getForCompletion());

            // 実行
            ResultActions resultActions = mockMvc.perform(post(URI)
                            .param("equipmentNumberForm", "00000001")
            );

            // 確認：JSON（API固有部分）
            resultActions.andExpect(jsonPath("$.status").value("mnp_out_completion"));
            ReferApiResponseAssert.assertJsonPath(resultActions);
        }

    }

}
