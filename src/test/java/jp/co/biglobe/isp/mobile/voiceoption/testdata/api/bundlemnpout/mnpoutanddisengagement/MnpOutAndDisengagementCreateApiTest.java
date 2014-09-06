package jp.co.biglobe.isp.mobile.voiceoption.testdata.api.bundlemnpout.mnpoutanddisengagement;

import jp.co.biglobe.isp.mobile.ltethreeg.datasource.SIM_INFO;
import jp.co.biglobe.isp.mobile.voiceoption.datasource.identification.db.FixtureVoiceIdentificationState;
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
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@RunWith(Enclosed.class)
public class MnpOutAndDisengagementCreateApiTest {

    private static final String URI = "/voiceoption/inside/skip/testdata/bundle/mnpout/mnpoutanddisengagement/create";

    public static final DateTime FIXATION_DATE = new DateTime(2014, 1, 1, 0, 0, 0);

    @RunWith(SpringJUnit4ClassRunner.class)
    @WebAppConfiguration
    @ContextConfiguration(locations = {"classpath:context.xml"})
    public static class _コントローラの処理フローのテスト {

        @Autowired
        public DbUnitTester tester;

        @Autowired
        private WebApplicationContext wac;
        private MockMvc mockMvc;

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
        public void _MNP転入なしで実行() throws Exception {

            // 事前準備
            tester.cleanInsertQuery(SIM_INFO.lte);
            tester.cleanInsertQuery(FixtureVoiceEngagementState.One.getForEngagement());
            tester.cleanInsertQuery(FixtureVoiceIdentificationState.One.getDefaultData());

            // 実行
            ResultActions resultActions = mockMvc.perform(post(URI)
                            .param("LteThreeGEngagementNumberForm", "00000001")
            );

            //resultActions.andExpect(content().string(""));
            UpdateApiResponseAssert.assertJsonPath(resultActions);
        }
    }
}
