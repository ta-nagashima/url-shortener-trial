package jp.co.biglobe.isp.mobile.voiceoption.api.disengagement.mnpoutcompletionanddisengagementapply;

import jp.co.biglobe.isp.mobile.ltethreeg.datasource.SIM_INFO;
import jp.co.biglobe.isp.mobile.voiceoption.database.VoiceEngagementStateAssert;
import jp.co.biglobe.isp.mobile.voiceoption.database.VoiceMnpOutChargeEventAssert;
import jp.co.biglobe.isp.mobile.voiceoption.database.VoiceMnpOutStateAssert;
import jp.co.biglobe.isp.mobile.voiceoption.datasource.charge.mnpoutcharge.db.FixtureVoiceMnpOutChargeEvent;
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
import java.util.Map;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

/**
 * MNP転出確定兼即時解約
 */

@RunWith(Enclosed.class)
public class VoiceMnpOutCompletionAndDisengagementApplyApiTest {

    private static final String URI = "/voiceoption/outside/skip/mnpoutcompletionanddisengagementapply/submit";

    @RunWith(SpringJUnit4ClassRunner.class)
    @WebAppConfiguration
    @ContextConfiguration(locations = {"classpath:context.xml"})
    public static class 正常系のテスト {

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
        public void test_正常系_解約日時がAM() throws Exception {
            // テストケースの設定
            tester.cleanInsertQuery(SIM_INFO.regist);
            tester.cleanInsertQuery(FixtureVoiceEngagementState.One.getForEngagement());
            tester.cleanInsertQuery(FixtureVoiceMnpOutState.One.getForNumbered());

            // 実行
            ResultActions resultActions = mockMvc.perform(post(URI)
                    .param("equipmentNumberForm", "00000001")
                    .param("mnpOutCompletionConfirmedDateForm", "20140401")
                    .param("voiceEngagementEndDateTimeForm", "20140101000000")
                    .param("voiceEngagementDisengageOrderDateForm", "20140101"));

            // 確認・JASON
            UpdateApiResponseAssert.assertJsonPath(resultActions);

            assertVoiceEngagement(FixtureVoiceEngagementState.One.getForImmediateDisEngagement());
            assertMnpOut();
            assertMnpOutCharge();

        }

        @Test
        public void test_正常系_解約日時がPM() throws Exception {
            // テストケースの設定
            tester.cleanInsertQuery(SIM_INFO.regist);
            tester.cleanInsertQuery(FixtureVoiceEngagementState.One.getForEngagement());
            tester.cleanInsertQuery(FixtureVoiceMnpOutState.One.getForNumbered());

            // 実行
            ResultActions resultActions = mockMvc.perform(post(URI)
                    .param("equipmentNumberForm", "00000001")
                    .param("mnpOutCompletionConfirmedDateForm", "20140401")
                    .param("voiceEngagementEndDateTimeForm", "20140101150000")
                    .param("voiceEngagementDisengageOrderDateForm", "20140101"));

            // 確認・JASON
            UpdateApiResponseAssert.assertJsonPath(resultActions);

            assertVoiceEngagement(FixtureVoiceEngagementState.One.getForImmediateDisEngagement2());
            assertMnpOut();
            assertMnpOutCharge();

        }

        private void assertVoiceEngagement(Map fixture) throws Exception{

            VoiceEngagementStateAssert voiceEngagementStateAssert = new VoiceEngagementStateAssert(tester);
            voiceEngagementStateAssert.assertTableWithAllColumns(fixture);
        }


        private void assertMnpOut() throws Exception{

            VoiceMnpOutStateAssert voiceMnpOutStateAssert = new VoiceMnpOutStateAssert(tester);
            voiceMnpOutStateAssert.assertTableWithAllColumns(FixtureVoiceMnpOutState.One.getForCompletion());
        }

        private void assertMnpOutCharge() throws Exception{

            VoiceMnpOutChargeEventAssert voiceMnpOutChargeEventAssert = new VoiceMnpOutChargeEventAssert(tester);
            voiceMnpOutChargeEventAssert.assertTableWithExcludeColumns(FixtureVoiceMnpOutChargeEvent.One.getDefaultData());
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
            ResultActions resultActions = mockMvc.perform(post(URI)
                    .param("mnpOutCompletionConfirmedDateForm", "20140401")
                    .param("voiceEngagementEndDateTimeForm", new DateTime().toString("yyyyMMddhhmmss"))
                    .param("voiceEngagementDisengageOrderDateForm", "20140101"));

            // 確認・JASON
            ValidationErrorResponseAssert.assertJsonPath(resultActions, "equipmentNumberForm");
        }

        @Test
        public void _equipmentNumberFormの桁間違い() throws Exception {

            // 実行
            ResultActions resultActions = mockMvc.perform(post(URI)
                    .param("equipmentNumberForm", "000001")
                    .param("mnpOutCompletionConfirmedDateForm", "20140401")
                    .param("voiceEngagementEndDateTimeForm", new DateTime().toString("yyyyMMddhhmmss"))
                    .param("voiceEngagementDisengageOrderDateForm", "20140101"));

            // 確認・JASON
            ValidationErrorResponseAssert.assertJsonPath(resultActions, "equipmentNumberForm.value");
        }

        @Test
        public void _mnpOutCompletionConfirmedDateFormがない() throws Exception {

            // 実行
            ResultActions resultActions = mockMvc.perform(post(URI)
                    .param("equipmentNumberForm", "00000001")
                    .param("voiceEngagementEndDateTimeForm", new DateTime().toString("yyyyMMddhhmmss"))
                    .param("voiceEngagementDisengageOrderDateForm", "20140101"));

            // 確認・JASON
            ValidationErrorResponseAssert.assertJsonPath(resultActions, "mnpOutCompletionConfirmedDateForm");
        }

        @Test
        public void _mnpOutCompletionConfirmedDateFormの値間違い() throws Exception {

            // 実行
            ResultActions resultActions = mockMvc.perform(post(URI)
                    .param("equipmentNumberForm", "00000001")
                    .param("mnpOutCompletionConfirmedDateForm", "2014048801")
                    .param("voiceEngagementEndDateTimeForm", new DateTime().toString("yyyyMMddhhmmss"))
                    .param("voiceEngagementDisengageOrderDateForm", "20140101"));

            // 確認・JASON
            ValidationErrorResponseAssert.assertJsonPath(resultActions, "mnpOutCompletionConfirmedDateForm.value");
        }

        @Test
        public void _voiceEngagementEndDateTimeFormがない() throws Exception {

            // 実行
            ResultActions resultActions = mockMvc.perform(post(URI)
                    .param("equipmentNumberForm", "00000001")
                    .param("mnpOutCompletionConfirmedDateForm", "20140401")
                    .param("voiceEngagementDisengageOrderDateForm", "20140101"));

            // 確認・JASON
            ValidationErrorResponseAssert.assertJsonPath(resultActions, "voiceEngagementEndDateTimeForm");
        }

        @Test
        public void _voiceEngagementEndDateTimeFormの値間違い() throws Exception {

            // 実行
            ResultActions resultActions = mockMvc.perform(post(URI)
                    .param("equipmentNumberForm", "00000001")
                    .param("mnpOutCompletionConfirmedDateForm", "20140401")
                    .param("voiceEngagementEndDateTimeForm", "yyyyMMddhhmmss")
                    .param("voiceEngagementDisengageOrderDateForm", "20140101"));

            // 確認・JASON
            ValidationErrorResponseAssert.assertJsonPath(resultActions, "voiceEngagementEndDateTimeForm.value");
        }


        @Test
        public void _voiceEngagementDisengageOrderDateFormがない() throws Exception {

            // 実行
            ResultActions resultActions = mockMvc.perform(post(URI)
                    .param("equipmentNumberForm", "00000001")
                    .param("mnpOutCompletionConfirmedDateForm", "20140401")
                    .param("voiceEngagementEndDateTimeForm", new DateTime().toString("yyyyMMddhhmmss")));

            // 確認・JASON
            ValidationErrorResponseAssert.assertJsonPath(resultActions, "voiceEngagementDisengageOrderDateForm");
        }

        @Test
        public void _voiceEngagementDisengageOrderDateFormの値間違い() throws Exception {

            // 実行
            ResultActions resultActions = mockMvc.perform(post(URI)
                    .param("equipmentNumberForm", "00000001")
                    .param("mnpOutCompletionConfirmedDateForm", "20140401")
                    .param("voiceEngagementEndDateTimeForm", new DateTime().toString("yyyyMMddhhmmss"))
                    .param("voiceEngagementDisengageOrderDateForm", "2014000101"));

            // 確認・JASON
            ValidationErrorResponseAssert.assertJsonPath(resultActions, "voiceEngagementDisengageOrderDateForm.value");
        }

    }

}