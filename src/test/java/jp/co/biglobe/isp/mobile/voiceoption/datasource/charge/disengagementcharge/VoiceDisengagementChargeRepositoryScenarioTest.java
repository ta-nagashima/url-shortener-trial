package jp.co.biglobe.isp.mobile.voiceoption.datasource.charge.disengagementcharge;

import jp.co.biglobe.isp.mobile.ltethreeg.datasource.SIM_INFO;
import jp.co.biglobe.isp.mobile.ltethreeg.domain.sim.EquipmentNumber;
import jp.co.biglobe.isp.mobile.voiceoption.datasource.voiceengagement.db.FixtureVoiceEngagementState;
import jp.co.biglobe.isp.mobile.voiceoption.domain.charge.disengagementcharge.DisengagementCharge;
import jp.co.biglobe.isp.mobile.voiceoption.domain.charge.disengagementcharge.NotExistDisengagementCharge;
import jp.co.biglobe.isp.mobile.voiceoption.domain.charge.disengagementcharge.ValidPenaltyChargeBuilder;
import jp.co.biglobe.isp.mobile.voiceoption.domain.charge.disengagementcharge.VoiceDisengagementChargeRepository;
import jp.co.biglobe.test.util.dbunit.DbUnitTester;
import jp.co.biglobe.test.util.usecase.BobioUseCase;
import org.dbunit.DatabaseUnitException;
import org.junit.After;
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
public class VoiceDisengagementChargeRepositoryScenarioTest {

    @Autowired
    private VoiceDisengagementChargeRepository sut;

    private static final String SCENARIO_NAME = "C_iyakukin_mo_ref";

    private BobioUseCase testcase = new BobioUseCase(SCENARIO_NAME);

    private EquipmentNumber EQUIPMENT_NUMBER = new EquipmentNumber("00000001");

    @Autowired
    public DbUnitTester tester;

    @Before
    public void setUp() throws DatabaseUnitException, SQLException, IOException {
        tester.executeAllClearTableAndSeq();
    }

    @After
    public void tearDown() throws IOException {
        testcase.unset();
    }

    @Test
    public void findのテスト_音声契約が見つからないときNotExistPenaltyCharge() throws IOException {
        // テストケースの設定
        testcase.set("DEFAULT");

        // テストデータの準備
        //tester.cleanInsertQuery(SIM_INFO.lte);

        // 準備
        DisengagementCharge expected = new NotExistDisengagementCharge();

        // 実行
        DisengagementCharge actual = sut.findByEquipmentNumber(EQUIPMENT_NUMBER);

        // 評価
        assertThat(actual, is(expected));
    }

    @Test
    public void findのテスト_音声契約が存在するが契約解除料が登録されていないときにNotExistPenaltyCharge() throws IOException {
        // テストケースの設定
        testcase.set("NOTEXIST");

        // テストデータの準備
        tester.cleanInsertQuery(SIM_INFO.lte);
        tester.cleanInsertQuery(FixtureVoiceEngagementState.One.getDefaultData());

        // 準備
        DisengagementCharge expected = new NotExistDisengagementCharge();

        // 実行
        DisengagementCharge actual = sut.findByEquipmentNumber(EQUIPMENT_NUMBER);

        // 評価
        assertThat(actual, is(expected));
    }

    @Test
    public void findのテスト_音声契約が存在し期限切れの契約解除料が登録されている() throws IOException {
        // テストケースの設定
        testcase.set("END");

        // テストデータの準備
        tester.cleanInsertQuery(SIM_INFO.lte);
        tester.cleanInsertQuery(FixtureVoiceEngagementState.One.getDefaultData());

        // 準備
        DisengagementCharge expected = new ValidPenaltyChargeBuilder().build();

        // 実行
        DisengagementCharge actual = sut.findByEquipmentNumber(EQUIPMENT_NUMBER);

        // 評価
        assertThat(actual, is(expected));
    }

    @Test
    public void findのテスト_音声契約が存在し期限切れではない契約解除料が登録されている() throws IOException {
        // テストケースの設定
        testcase.set("NOTEND");

        // テストデータの準備
        tester.cleanInsertQuery(SIM_INFO.lte);
        tester.cleanInsertQuery(FixtureVoiceEngagementState.One.getDefaultData());

        // 準備
        DisengagementCharge expected = new ValidPenaltyChargeBuilder().costedCharge().build();

        // 実行
        DisengagementCharge actual = sut.findByEquipmentNumber(EQUIPMENT_NUMBER);

        // 評価
        assertThat(actual, is(expected));
    }




}
