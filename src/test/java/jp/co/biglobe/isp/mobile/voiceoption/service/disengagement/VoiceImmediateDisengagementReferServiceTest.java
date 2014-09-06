package jp.co.biglobe.isp.mobile.voiceoption.service.disengagement;

import jp.co.biglobe.isp.mobile.ltethreeg.datasource.SIM_INFO;
import jp.co.biglobe.isp.mobile.ltethreeg.domain.sim.EquipmentNumber;
import jp.co.biglobe.isp.mobile.voiceoption.datasource.mnpout.db.FixtureVoiceMnpOutState;
import jp.co.biglobe.isp.mobile.voiceoption.datasource.voiceengagement.db.FixtureVoiceEngagementState;
import jp.co.biglobe.isp.mobile.voiceoption.domain.mnpout.MnpOutStatus;
import jp.co.biglobe.test.util.dbunit.DbUnitTester;
import org.dbunit.DatabaseUnitException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Enclosed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.io.IOException;
import java.sql.SQLException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

@RunWith(Enclosed.class)
public class VoiceImmediateDisengagementReferServiceTest {

    @RunWith(SpringJUnit4ClassRunner.class)
    @WebAppConfiguration
    @ContextConfiguration(locations = {"classpath:context.xml"})
    public static class _refer_転出申込なしの場合のテスト {

        @Autowired
        VoiceImmediateDisengagementReferService sut;

        @Autowired
        public DbUnitTester tester;

        @Before
        public void setup() throws IOException, DatabaseUnitException, SQLException {
            // 処理前にテーブルをクリアする
            tester.executeAllClearTableAndSeq();
        }

        @Test
        public void _転出申込なしの場合() throws Exception {

            // 事前準備
            tester.cleanInsertQuery(SIM_INFO.regist);
            tester.cleanInsertQuery(FixtureVoiceEngagementState.One.getForEngagement());

            // 実行
            MnpOutStatus actual = sut.refer(new EquipmentNumber("00000001"));

            // 確認
            assertThat(actual, is(MnpOutStatus.NO_REQUEST));
            assertThat(actual.getImmediateDisengagementReferApiValue(), is("mnp_out_not_ordered"));
        }

        @Test
        public void _転出申込キャンセルの場合() throws Exception {

            // 事前準備
            tester.cleanInsertQuery(SIM_INFO.regist);
            tester.cleanInsertQuery(FixtureVoiceEngagementState.One.getForCancelForUserRequest());
            tester.cleanInsertQuery(FixtureVoiceMnpOutState.One.getForCancelFromNumbered());

            // 実行
            MnpOutStatus actual = sut.refer(new EquipmentNumber("00000001"));

            // 確認
            assertThat(actual, is(MnpOutStatus.MNP_OUT_CANCEL));
            assertThat(actual.getImmediateDisengagementReferApiValue(), is("mnp_out_not_ordered"));
        }

    }

    @RunWith(SpringJUnit4ClassRunner.class)
    @WebAppConfiguration
    @ContextConfiguration(locations = {"classpath:context.xml"})
    public static class _refer_転出申込中の場合のテスト {

        @Autowired
        VoiceImmediateDisengagementReferService sut;

        @Autowired
        public DbUnitTester tester;

        @Before
        public void setup() throws IOException, DatabaseUnitException, SQLException {
            // 処理前にテーブルをクリアする
            tester.executeAllClearTableAndSeq();
        }

        private void assertReferApiValue(MnpOutStatus actual) {
            assertThat(actual.getImmediateDisengagementReferApiValue(), is("mnp_out_processing"));
        }

        @Test
        public void _事務局にMNP予約番号発行を依頼済みの場合() throws Exception {

            // 事前準備
            tester.cleanInsertQuery(SIM_INFO.regist);
            tester.cleanInsertQuery(FixtureVoiceEngagementState.One.getForEngagement());
            tester.cleanInsertQuery(FixtureVoiceMnpOutState.One.getForRequestedWaiting());

            // 実行
            MnpOutStatus actual = sut.refer(new EquipmentNumber("00000001"));

            // 確認
            assertThat(actual, is(MnpOutStatus.REQUEST_WAITING));
            assertReferApiValue(actual);
        }

        @Test
        public void _事務局がALADINでMNP予約番号発行を依頼済みの場合() throws Exception {

            // 事前準備
            tester.cleanInsertQuery(SIM_INFO.regist);
            tester.cleanInsertQuery(FixtureVoiceEngagementState.One.getForEngagement());
            tester.cleanInsertQuery(FixtureVoiceMnpOutState.One.getForRequested());

            // 実行
            MnpOutStatus actual = sut.refer(new EquipmentNumber("00000001"));

            // 確認
            assertThat(actual, is(MnpOutStatus.MNP_RESERVATION_NUMBER_WAITING));
            assertReferApiValue(actual);
        }

    }

    @RunWith(SpringJUnit4ClassRunner.class)
    @WebAppConfiguration
    @ContextConfiguration(locations = {"classpath:context.xml"})
    public static class _refer_MNP_OUT_WAITING {

        @Autowired
        VoiceImmediateDisengagementReferService sut;

        @Autowired
        public DbUnitTester tester;

        @Before
        public void setup() throws IOException, DatabaseUnitException, SQLException {
            // 処理前にテーブルをクリアする
            tester.executeAllClearTableAndSeq();
        }

        private void assertReferApiValue(MnpOutStatus actual) {
            assertThat(actual.getImmediateDisengagementReferApiValue(), is("mnp_out_waiting"));
        }

        @Test
        public void _事務局がMNP予約番号を発行済みの場合() throws Exception {

            // 事前準備
            tester.cleanInsertQuery(SIM_INFO.regist);
            tester.cleanInsertQuery(FixtureVoiceEngagementState.One.getForEngagement());
            tester.cleanInsertQuery(FixtureVoiceMnpOutState.One.getForNumbered());

            // 実行
            MnpOutStatus actual = sut.refer(new EquipmentNumber("00000001"));

            // 確認
            assertThat(actual, is(MnpOutStatus.MNP_OUT_WAITING));
            assertReferApiValue(actual);
        }

    }

    @RunWith(SpringJUnit4ClassRunner.class)
    @WebAppConfiguration
    @ContextConfiguration(locations = {"classpath:context.xml"})
    public static class _refer_MNP_OUT_COMPLETION {

        @Autowired
        VoiceImmediateDisengagementReferService sut;

        @Autowired
        public DbUnitTester tester;

        @Before
        public void setup() throws IOException, DatabaseUnitException, SQLException {
            // 処理前にテーブルをクリアする
            tester.executeAllClearTableAndSeq();
        }

        @Test
        public void _転出完了済みの場合() throws Exception {

            // 事前準備
            tester.cleanInsertQuery(SIM_INFO.regist);
            tester.cleanInsertQuery(FixtureVoiceEngagementState.One.getForEngagement());
            tester.cleanInsertQuery(FixtureVoiceMnpOutState.One.getForCompletion());

            // 実行
            MnpOutStatus actual = sut.refer(new EquipmentNumber("00000001"));

            // 確認
            assertThat(actual, is(MnpOutStatus.MNP_OUT_COMPLETION));
            assertThat(actual.getImmediateDisengagementReferApiValue(), is("mnp_out_completion"));
        }

    }

}