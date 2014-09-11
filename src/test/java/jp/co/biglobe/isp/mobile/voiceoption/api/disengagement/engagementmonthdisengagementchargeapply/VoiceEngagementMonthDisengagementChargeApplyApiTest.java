package jp.co.biglobe.isp.mobile.voiceoption.api.disengagement.engagementmonthdisengagementchargeapply;

import jp.co.biglobe.isp.mobile.ltethreeg.datasource.SIM_INFO;
import jp.co.biglobe.isp.mobile.voiceoption.database.VoiceEngagementMonthDisengagementChargeEventAssert;
import jp.co.biglobe.isp.mobile.voiceoption.datasource.voiceengagement.db.FixtureVoiceEngagementState;
import jp.co.biglobe.test.util.dbunit.DbUnitTester;
import jp.co.biglobe.test.util.response.UpdateApiResponseAssert;
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

/**
 * 契約月解約課金
 */

@RunWith(Enclosed.class)
public class VoiceEngagementMonthDisengagementChargeApplyApiTest {

    private static final String URI = "/voiceoption/outside/skip/engagementmonthdisengagementchargeapply/submit";

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

        public static void assertOk(ResultActions resultActions) throws Exception {
            resultActions.andExpect(jsonPath("$.result").value("ok"));
            UpdateApiResponseAssert.assertJsonPath(resultActions);
        }

        public static void assertError(ResultActions resultActions) throws Exception {
            resultActions.andExpect(jsonPath("$.result").value("error"));
            UpdateApiResponseAssert.assertJsonPath(resultActions);
        }

        public static void assertForBusinessCheckException(ResultActions resultActions, String message) throws Exception {
            resultActions.andExpect(jsonPath("$.header.statusCode").value("business_error"));
            resultActions.andExpect(jsonPath("$.error.type").value("BusinessException"));
            resultActions.andExpect(jsonPath("$.error.message").value(message));
        }

        @Test
        public void test_正常系_音声契約が契約月に解約した場合に契約月解約課金が行われる() throws Exception {
            // テストケースの設定
            tester.cleanInsertQuery(SIM_INFO.regist);
            tester.cleanInsertQuery(FixtureVoiceEngagementState.One.getForDisengaged(
                new DateTime(2013, 7, 1, 0, 0, 0),
                new DateTime(2013, 7, 31, 0, 0, 0)
            ));

            // 実行
            ResultActions resultActions = mockMvc.perform(post(URI)
                            .param("equipmentNumberForm", "00000001")
            );

            UpdateApiResponseAssert.assertJsonPath(resultActions);
            assertOk(resultActions);
        }

        @Test
        public void test_正常系_音声契約が契約月の翌月以降に解約した場合に契約月解約課金が行われない() throws Exception {
            // テストケースの設定
            tester.cleanInsertQuery(SIM_INFO.regist);
            tester.cleanInsertQuery(FixtureVoiceEngagementState.One.getForDisengaged(
                    new DateTime(2014, 7, 1, 0, 0, 0),
                    new DateTime(2014, 8, 1, 0, 0, 0)
            ));

            // 実行
            ResultActions resultActions = mockMvc.perform(post(URI)
                            .param("equipmentNumberForm", "00000001")
            );


            VoiceEngagementMonthDisengagementChargeEventAssert voiceEngagementMonthDisengagementChargeEventAssert = new VoiceEngagementMonthDisengagementChargeEventAssert(tester);
            voiceEngagementMonthDisengagementChargeEventAssert.assertTableNoCount();

            assertForBusinessCheckException(resultActions, "契約月解約課金できません");
        }

        @Test
        public void test_異常系が契約中の場合に契約月解約課金が行われない() throws Exception {
            // テストケースの設定
            tester.cleanInsertQuery(SIM_INFO.regist);
            tester.cleanInsertQuery(FixtureVoiceEngagementState.One.getForEngagement());

            // 実行
            ResultActions resultActions = mockMvc.perform(post(URI)
                            .param("equipmentNumberForm", "00000001")
            );

            assertForBusinessCheckException(resultActions, "契約月解約課金できません");
        }

    }

}
