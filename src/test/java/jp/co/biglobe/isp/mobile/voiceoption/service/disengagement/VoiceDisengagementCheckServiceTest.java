package jp.co.biglobe.isp.mobile.voiceoption.service.disengagement;

import jp.co.biglobe.isp.mobile.ltethreeg.datasource.SIM_INFO;
import jp.co.biglobe.isp.mobile.ltethreeg.domain.sim.EquipmentNumber;
import jp.co.biglobe.isp.mobile.voiceoption.datasource.mnpout.db.FixtureVoiceMnpOutState;
import jp.co.biglobe.isp.mobile.voiceoption.datasource.voiceengagement.db.FixtureVoiceEngagementState;
import jp.co.biglobe.isp.mobile.voiceoption.domain.policy.DisengagementCheckStatus;
import jp.co.biglobe.isp.mobile.voiceoption.domain.policy.MnpOutAndDisengagementCheckStatus;
import jp.co.biglobe.isp.mobile.voiceoption.domain.container.DisengagementCheckStatusContainer;
import jp.co.biglobe.test.util.dbunit.DbUnitTester;
import org.dbunit.DatabaseUnitException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.io.IOException;
import java.sql.SQLException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = {"classpath:context.xml"})
public class VoiceDisengagementCheckServiceTest {

    @Autowired
    private VoiceDisengagementCheckService sut;

    @Autowired
    public DbUnitTester tester;

    @Before
    public void setup() throws IOException, DatabaseUnitException, SQLException {
        // 処理前にテーブルをクリアする
        tester.executeAllClearTableAndSeq();
    }

    @Test
    public void check_音声契約のステータスが契約中で過去に転出キャンセルの実績なしで転出申し込みなしのときに解約可能() throws Exception {

        // テストケースの設定
        tester.cleanInsertQuery(SIM_INFO.regist);
        tester.cleanInsertQuery(FixtureVoiceEngagementState.One.getForEngagement());

        // 準備
        DisengagementCheckStatus expectedDisengagementCheckStatus = DisengagementCheckStatus.NO_MNP_OUT;
        MnpOutAndDisengagementCheckStatus expectedMnpOutAndDisengagementCheckStatus = MnpOutAndDisengagementCheckStatus.NO_MNP_OUT;

        // 実行
        DisengagementCheckStatusContainer actualDisengagementCheckStatusContainer = sut.check(new EquipmentNumber("00000001"));
        DisengagementCheckStatus actualDisengagementCheckStatus = actualDisengagementCheckStatusContainer.getDisengagementCheckStatus();
        MnpOutAndDisengagementCheckStatus actualMnpOutAndDisengagementCheckStatus = actualDisengagementCheckStatusContainer.getMnpOutAndDisengagementCheckStatus();

        // 評価
        assertThat(actualDisengagementCheckStatus, is(expectedDisengagementCheckStatus));
        assertThat(actualMnpOutAndDisengagementCheckStatus, is(expectedMnpOutAndDisengagementCheckStatus));
    }

    @Test
    public void check_音声契約のステータスが契約中で過去に転出キャンセルの実績ありで転出申し込みなしのときに解約可能() throws Exception {

        // テストケースの設定
        tester.cleanInsertQuery(SIM_INFO.regist);
        tester.cleanInsertQuery(FixtureVoiceEngagementState.One.getForEngagement());
        tester.cleanInsertQuery(FixtureVoiceMnpOutState.One.getForCancelFromNumbered());

        // 準備
        DisengagementCheckStatus expectedDisengagementCheckStatus = DisengagementCheckStatus.NO_MNP_OUT;
        MnpOutAndDisengagementCheckStatus expectedMnpOutAndDisengagementCheckStatus = MnpOutAndDisengagementCheckStatus.NO_MNP_OUT;

        // 実行
        DisengagementCheckStatusContainer actualDisengagementCheckStatusContainer = sut.check(new EquipmentNumber("00000001"));
        DisengagementCheckStatus actualDisengagementCheckStatus = actualDisengagementCheckStatusContainer.getDisengagementCheckStatus();
        MnpOutAndDisengagementCheckStatus actualMnpOutAndDisengagementCheckStatus = actualDisengagementCheckStatusContainer.getMnpOutAndDisengagementCheckStatus();

        // 評価
        assertThat(actualDisengagementCheckStatus, is(expectedDisengagementCheckStatus));
        assertThat(actualMnpOutAndDisengagementCheckStatus, is(expectedMnpOutAndDisengagementCheckStatus));

    }

    @Test
    public void check_音声契約のステータスが契約中で転出依頼待ちのときに解約可能() throws Exception {

        // テストケースの設定
        tester.cleanInsertQuery(SIM_INFO.regist);
        tester.cleanInsertQuery(FixtureVoiceEngagementState.One.getForEngagement());
        tester.cleanInsertQuery(FixtureVoiceMnpOutState.One.getForRequestedWaiting());

        // 準備
        DisengagementCheckStatus expectedDisengagementCheckStatus = DisengagementCheckStatus.REQUEST_WAITING;
        MnpOutAndDisengagementCheckStatus expectedMnpOutAndDisengagementCheckStatus = MnpOutAndDisengagementCheckStatus.REQUEST_WAITING;

        // 実行
        DisengagementCheckStatusContainer actualDisengagementCheckStatusContainer = sut.check(new EquipmentNumber("00000001"));
        DisengagementCheckStatus actualDisengagementCheckStatus = actualDisengagementCheckStatusContainer.getDisengagementCheckStatus();
        MnpOutAndDisengagementCheckStatus actualMnpOutAndDisengagementCheckStatus = actualDisengagementCheckStatusContainer.getMnpOutAndDisengagementCheckStatus();

        // 評価
        assertThat(actualDisengagementCheckStatus, is(expectedDisengagementCheckStatus));
        assertThat(actualMnpOutAndDisengagementCheckStatus, is(expectedMnpOutAndDisengagementCheckStatus));

    }

    @Test
    public void check_音声契約のステータスが契約中で転出番号発行待ちのときに解約可能() throws Exception {

        // テストケースの設定
        tester.cleanInsertQuery(SIM_INFO.regist);
        tester.cleanInsertQuery(FixtureVoiceEngagementState.One.getForEngagement());
        tester.cleanInsertQuery(FixtureVoiceMnpOutState.One.getForRequested());

        // 準備
        DisengagementCheckStatus expectedDisengagementCheckStatus = DisengagementCheckStatus.MNP_RESERVATION_NUMBER_WAITING;
        MnpOutAndDisengagementCheckStatus expectedMnpOutAndDisengagementCheckStatus = MnpOutAndDisengagementCheckStatus.MNP_RESERVATION_NUMBER_WAITING;

        // 実行
        DisengagementCheckStatusContainer actualDisengagementCheckStatusContainer = sut.check(new EquipmentNumber("00000001"));
        DisengagementCheckStatus actualDisengagementCheckStatus = actualDisengagementCheckStatusContainer.getDisengagementCheckStatus();
        MnpOutAndDisengagementCheckStatus actualMnpOutAndDisengagementCheckStatus = actualDisengagementCheckStatusContainer.getMnpOutAndDisengagementCheckStatus();

        // 評価
        assertThat(actualDisengagementCheckStatus, is(expectedDisengagementCheckStatus));
        assertThat(actualMnpOutAndDisengagementCheckStatus, is(expectedMnpOutAndDisengagementCheckStatus));

    }

    @Test
    public void check_音声契約のステータスが契約中で転出待ちのときに解約可能() throws Exception {

        // テストケースの設定
        tester.cleanInsertQuery(SIM_INFO.regist);
        tester.cleanInsertQuery(FixtureVoiceEngagementState.One.getForEngagement());
        tester.cleanInsertQuery(FixtureVoiceMnpOutState.One.getForNumbered());

        // 準備
        DisengagementCheckStatus expectedDisengagementCheckStatus = DisengagementCheckStatus.MNP_OUT_WAITING;
        MnpOutAndDisengagementCheckStatus expectedMnpOutAndDisengagementCheckStatus = MnpOutAndDisengagementCheckStatus.MNP_OUT_WAITING;

        // 実行
        DisengagementCheckStatusContainer actualDisengagementCheckStatusContainer = sut.check(new EquipmentNumber("00000001"));
        DisengagementCheckStatus actualDisengagementCheckStatus = actualDisengagementCheckStatusContainer.getDisengagementCheckStatus();
        MnpOutAndDisengagementCheckStatus actualMnpOutAndDisengagementCheckStatus = actualDisengagementCheckStatusContainer.getMnpOutAndDisengagementCheckStatus();


        // 評価
        assertThat(actualDisengagementCheckStatus, is(expectedDisengagementCheckStatus));
        assertThat(actualMnpOutAndDisengagementCheckStatus, is(expectedMnpOutAndDisengagementCheckStatus));

    }

    @Test
    public void check_音声契約のステータスが申し込み中の場合は解約できない() throws Exception {

        // テストケースの設定
        tester.cleanInsertQuery(SIM_INFO.regist);
        tester.cleanInsertQuery(FixtureVoiceEngagementState.One.getDefaultData());

        // 準備
        DisengagementCheckStatus expectedDisengagementCheckStatus = DisengagementCheckStatus.NO_VOICE_OPTION;
        MnpOutAndDisengagementCheckStatus expectedMnpOutAndDisengagementCheckStatus = MnpOutAndDisengagementCheckStatus.NO_VOICE_OPTION;

        // 実行
        DisengagementCheckStatusContainer actualDisengagementCheckStatusContainer = sut.check(new EquipmentNumber("00000001"));
        DisengagementCheckStatus actualDisengagementCheckStatus = actualDisengagementCheckStatusContainer.getDisengagementCheckStatus();
        MnpOutAndDisengagementCheckStatus actualMnpOutAndDisengagementCheckStatus = actualDisengagementCheckStatusContainer.getMnpOutAndDisengagementCheckStatus();


        // 評価
        assertThat(actualDisengagementCheckStatus, is(expectedDisengagementCheckStatus));
        assertThat(actualMnpOutAndDisengagementCheckStatus, is(expectedMnpOutAndDisengagementCheckStatus));

    }

    @Test
    public void check_音声契約のステータスが解約予約中のときに解約不可() throws Exception {

        // テストケースの設定
        tester.cleanInsertQuery(SIM_INFO.regist);
        tester.cleanInsertQuery(FixtureVoiceEngagementState.One.getForDisEngagementReserved());

        // 準備
        DisengagementCheckStatus expectedDisengagementCheckStatus = DisengagementCheckStatus.NO_VOICE_OPTION;
        MnpOutAndDisengagementCheckStatus expectedMnpOutAndDisengagementCheckStatus = MnpOutAndDisengagementCheckStatus.NO_VOICE_OPTION;

        // 実行
        DisengagementCheckStatusContainer actualDisengagementCheckStatusContainer = sut.check(new EquipmentNumber("00000001"));
        DisengagementCheckStatus actualDisengagementCheckStatus = actualDisengagementCheckStatusContainer.getDisengagementCheckStatus();
        MnpOutAndDisengagementCheckStatus actualMnpOutAndDisengagementCheckStatus = actualDisengagementCheckStatusContainer.getMnpOutAndDisengagementCheckStatus();


        // 評価
        assertThat(actualDisengagementCheckStatus, is(expectedDisengagementCheckStatus));
        assertThat(actualMnpOutAndDisengagementCheckStatus, is(expectedMnpOutAndDisengagementCheckStatus));

    }

    @Test
    public void check_音声契約のステータスが解約中のときに解約不可() throws Exception {

        // テストケースの設定
        tester.cleanInsertQuery(SIM_INFO.regist);
        tester.cleanInsertQuery(FixtureVoiceEngagementState.One.getForDisEngaged());

        // 準備
        DisengagementCheckStatus expectedDisengagementCheckStatus = DisengagementCheckStatus.NO_VOICE_OPTION;
        MnpOutAndDisengagementCheckStatus expectedMnpOutAndDisengagementCheckStatus = MnpOutAndDisengagementCheckStatus.NO_VOICE_OPTION;

        // 実行
        DisengagementCheckStatusContainer actualDisengagementCheckStatusContainer = sut.check(new EquipmentNumber("00000001"));
        DisengagementCheckStatus actualDisengagementCheckStatus = actualDisengagementCheckStatusContainer.getDisengagementCheckStatus();
        MnpOutAndDisengagementCheckStatus actualMnpOutAndDisengagementCheckStatus = actualDisengagementCheckStatusContainer.getMnpOutAndDisengagementCheckStatus();


        // 評価
        assertThat(actualDisengagementCheckStatus, is(expectedDisengagementCheckStatus));
        assertThat(actualMnpOutAndDisengagementCheckStatus, is(expectedMnpOutAndDisengagementCheckStatus));

    }

    @Test
    public void check_音声契約のステータスがキャンセル済みのときに解約不可() throws Exception {

        // テストケースの設定
        tester.cleanInsertQuery(SIM_INFO.regist);
        tester.cleanInsertQuery(FixtureVoiceEngagementState.One.getForCancelForUserRequest());

        // 準備
        DisengagementCheckStatus expectedDisengagementCheckStatus = DisengagementCheckStatus.NO_VOICE_OPTION;
        MnpOutAndDisengagementCheckStatus expectedMnpOutAndDisengagementCheckStatus = MnpOutAndDisengagementCheckStatus.NO_VOICE_OPTION;

        // 実行
        DisengagementCheckStatusContainer actualDisengagementCheckStatusContainer = sut.check(new EquipmentNumber("00000001"));
        DisengagementCheckStatus actualDisengagementCheckStatus = actualDisengagementCheckStatusContainer.getDisengagementCheckStatus();
        MnpOutAndDisengagementCheckStatus actualMnpOutAndDisengagementCheckStatus = actualDisengagementCheckStatusContainer.getMnpOutAndDisengagementCheckStatus();


        // 評価
        assertThat(actualDisengagementCheckStatus, is(expectedDisengagementCheckStatus));
        assertThat(actualMnpOutAndDisengagementCheckStatus, is(expectedMnpOutAndDisengagementCheckStatus));

    }

    @Test
    public void check_音声契約のステータスがキャンセル済みでMNP転出ありのときに解約不可() throws Exception {

        // テストケースの設定
        tester.cleanInsertQuery(SIM_INFO.regist);
        tester.cleanInsertQuery(FixtureVoiceEngagementState.One.getForCancelForUserRequest());
        tester.cleanInsertQuery(FixtureVoiceMnpOutState.One.getForCompletion());

        // 準備
        DisengagementCheckStatus expectedDisengagementCheckStatus = DisengagementCheckStatus.NO_VOICE_OPTION;
        MnpOutAndDisengagementCheckStatus expectedMnpOutAndDisengagementCheckStatus = MnpOutAndDisengagementCheckStatus.NO_VOICE_OPTION;

        // 実行
        DisengagementCheckStatusContainer actualDisengagementCheckStatusContainer = sut.check(new EquipmentNumber("00000001"));
        DisengagementCheckStatus actualDisengagementCheckStatus = actualDisengagementCheckStatusContainer.getDisengagementCheckStatus();
        MnpOutAndDisengagementCheckStatus actualMnpOutAndDisengagementCheckStatus = actualDisengagementCheckStatusContainer.getMnpOutAndDisengagementCheckStatus();


        // 評価
        assertThat(actualDisengagementCheckStatus, is(expectedDisengagementCheckStatus));
        assertThat(actualMnpOutAndDisengagementCheckStatus, is(expectedMnpOutAndDisengagementCheckStatus));

    }


    @Test
    public void check_音声契約のステータスが見つからないときに解約可能() throws Exception {

        // テストケースの設定
        tester.cleanInsertQuery(SIM_INFO.regist);
        tester.cleanInsertQuery(FixtureVoiceEngagementState.One.getForEngagement());

        // 準備
        DisengagementCheckStatus expectedDisengagementCheckStatus = DisengagementCheckStatus.NO_VOICE_OPTION;
        MnpOutAndDisengagementCheckStatus expectedMnpOutAndDisengagementCheckStatus = MnpOutAndDisengagementCheckStatus.NO_VOICE_OPTION;

        // 実行
        DisengagementCheckStatusContainer actualDisengagementCheckStatusContainer = sut.check(new EquipmentNumber("99999999"));
        DisengagementCheckStatus actualDisengagementCheckStatus = actualDisengagementCheckStatusContainer.getDisengagementCheckStatus();
        MnpOutAndDisengagementCheckStatus actualMnpOutAndDisengagementCheckStatus = actualDisengagementCheckStatusContainer.getMnpOutAndDisengagementCheckStatus();


        // 評価
        assertThat(actualDisengagementCheckStatus, is(expectedDisengagementCheckStatus));
        assertThat(actualMnpOutAndDisengagementCheckStatus, is(expectedMnpOutAndDisengagementCheckStatus));

    }
}
