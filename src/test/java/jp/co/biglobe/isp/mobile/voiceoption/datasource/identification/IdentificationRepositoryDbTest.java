package jp.co.biglobe.isp.mobile.voiceoption.datasource.identification;

import jp.co.biglobe.isp.auth.domain.user.UserId;
import jp.co.biglobe.isp.mobile.ltethreeg.datasource.SIM_INFO;
import jp.co.biglobe.isp.mobile.ltethreeg.domain.ltethreegengagement.LteThreeGEngagementNumber;
import jp.co.biglobe.isp.mobile.voiceoption.database.VoiceIdentificationStateAssert;
import jp.co.biglobe.isp.mobile.voiceoption.database.VoiceIdentificationUploadEventAssert;
import jp.co.biglobe.isp.mobile.voiceoption.database.VoiceIdentificationUploadStateAssert;
import jp.co.biglobe.isp.mobile.voiceoption.datasource.identification.db.FixtureVoiceIdentificationState;
import jp.co.biglobe.isp.mobile.voiceoption.datasource.identification.upload.db.FixtureVoiceIdentificationUploadE;
import jp.co.biglobe.isp.mobile.voiceoption.datasource.identification.upload.db.FixtureVoiceIdentificationUploadS;
import jp.co.biglobe.isp.mobile.voiceoption.datasource.voiceengagement.db.FixtureVoiceEngagementState;
import jp.co.biglobe.isp.mobile.voiceoption.domain.identification.*;
import jp.co.biglobe.isp.mobile.voiceoption.domain.voiceengagement.VoiceEngagementNumber;
import jp.co.biglobe.test.util.dbunit.DbUnitTester;
import org.dbunit.DatabaseUnitException;
import org.joda.time.DateTime;
import org.joda.time.DateTimeUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.io.IOException;
import java.sql.SQLException;

import static org.junit.Assert.assertEquals;

@RunWith(Enclosed.class)
public class IdentificationRepositoryDbTest {

    @RunWith(SpringJUnit4ClassRunner.class)
    @WebAppConfiguration
    @ContextConfiguration(locations = {"classpath:context.xml"})
    public static class _update {

        @Autowired
        private DbUnitTester tester;

        @Autowired
        private IdentificationRepository identificationRepository;

        public final DateTime FIXATION_DATE = new DateTime(2014, 1, 1, 0, 0, 0);

        @Before
        public void setup() throws IOException, DatabaseUnitException, SQLException {
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
        public void _get_読めているかテスト1() {
            //テストケースの設定
            tester.cleanInsertQuery(SIM_INFO.lte);
            tester.cleanInsertQuery(FixtureVoiceEngagementState.One.getDefaultData());
            tester.cleanInsertQuery(FixtureVoiceIdentificationState.One.getDefaultData());

            ValidIdentification getValue = identificationRepository.findByLteThreeGEngagementNumberForValid(new LteThreeGEngagementNumber("00000001"));

            ValidIdentification validIdentification = ValidIdentification.create(
                    new IdentificationReceiptNumber("140101_0001"),
                    new IdentificationInitialRequestData(
                            new UserId("abc12345"),
                            new LteThreeGEngagementNumber("00000001"),
                            ReceiptStatus.CONSTANCY),
                    new VoiceEngagementNumber(1)
                    );

            assertEquals(validIdentification, getValue);
        }

        @Test
        public void _get_読めているかテスト2() {
            //テストケースの設定
            tester.cleanInsertQuery(SIM_INFO.lte);
            tester.cleanInsertQuery(FixtureVoiceEngagementState.One.getDefaultData());
            tester.cleanInsertQuery(FixtureVoiceIdentificationState.One.getForFirstUploaded());
            //tester.cleanInsertQuery(FixtureVoiceIdentificationUploadS.One.getForFirstUploaded());

            ValidIdentification getValue = identificationRepository.findByLteThreeGEngagementNumberForValid(new LteThreeGEngagementNumber("00000001"));

            ValidIdentification validIdentification = ValidIdentification.create(
                    new IdentificationReceiptNumber("140101_0001"),
                    new IdentificationInitialRequestData(
                            new UserId("abc12345"),
                            new LteThreeGEngagementNumber("00000001"),
                            ReceiptStatus.CONSTANCY),
                    new VoiceEngagementNumber(1)).uploaded();

            assertEquals(validIdentification, getValue);
        }

        @Test
        public void _upload_アップロード済１回からもう１回アップロード() throws Exception {
            //テストケースの設定
            tester.cleanInsertQuery(SIM_INFO.lte);
            tester.cleanInsertQuery(FixtureVoiceEngagementState.One.getDefaultData());
            tester.cleanInsertQuery(FixtureVoiceIdentificationState.One.getForFirstUploaded());
            //tester.cleanInsertQuery(FixtureVoiceIdentificationUploadS.One.getForFirstUploaded());

            ValidIdentification validIdentification = identificationRepository.findByLteThreeGEngagementNumberForValid(new LteThreeGEngagementNumber("00000001"));

            // 本人確認 Entity 更新
            identificationRepository.update(validIdentification.uploaded());

            // 確認
//            VoiceIdentificationEventAssert voiceIdentificationEventAssert = new VoiceIdentificationEventAssert(tester);
//            voiceIdentificationEventAssert.assertTableWithAllColumns(FixtureVoiceIdentificationEvent.Nothing.getDefaultData());

            VoiceIdentificationStateAssert voiceIdentificationStateAssert = new VoiceIdentificationStateAssert(tester);
            voiceIdentificationStateAssert.assertTableWithAllColumns(FixtureVoiceIdentificationState.One.getForFirstUploaded());

            VoiceIdentificationUploadEventAssert voiceIdentificationUploadEventAssert = new VoiceIdentificationUploadEventAssert(tester);
            voiceIdentificationUploadEventAssert.assertTableWithAllColumns(FixtureVoiceIdentificationUploadE.One.getForSecondUploaded());

            VoiceIdentificationUploadStateAssert voiceIdentificationUploadStateAssert = new VoiceIdentificationUploadStateAssert(tester);
            voiceIdentificationUploadStateAssert.assertTableWithAllColumns(FixtureVoiceIdentificationUploadS.One.getForSecondUploaded());
        }
    }
}
