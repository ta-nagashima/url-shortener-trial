package jp.co.biglobe.isp.mobile.voiceoption.datasource.mnpin.db;

import jp.co.biglobe.isp.mobile.extension.date.SystemDateTime;
import jp.co.biglobe.isp.mobile.ltethreeg.datasource.SIM_INFO;
import jp.co.biglobe.isp.mobile.voiceoption.database.VoiceMnpInPersonalInfoEventAssert;
import jp.co.biglobe.isp.mobile.voiceoption.database.VoiceMnpInPersonalInfoStateAssert;
import jp.co.biglobe.isp.mobile.voiceoption.datasource.voiceengagement.db.FixtureVoiceEngagementState;
import jp.co.biglobe.isp.mobile.voiceoption.domain.mnpin.ValidMnpIn;
import jp.co.biglobe.isp.mobile.voiceoption.domain.mnpin.ValidMnpInBuilder;
import jp.co.biglobe.isp.mobile.voiceoption.domain.mnpin.ValidMnpInPersonalInfo;
import jp.co.biglobe.lib.plugin.event.RequestEventProcess;
import jp.co.biglobe.lib.publication.datasource.EventType;
import jp.co.biglobe.test.util.dbunit.DbUnitTester;
import org.dbunit.DatabaseUnitException;
import org.joda.time.DateTime;
import org.joda.time.DateTimeUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.io.IOException;
import java.sql.SQLException;


@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = {"classpath:context.xml"})
public class MnpInPersonalInfoQueryMapperTest {

    @Autowired
    SqlSessionTemplate session;

    private MnpInPersonalInfoQueryMapper sut;

    @Autowired
    DbUnitTester tester;

    public final DateTime FIXATION_DATE = new DateTime(2014, 1, 1, 0, 0, 0);

    @Autowired
    private RequestEventProcess requestEventProcess;

    @Before
    public void setUp() throws DatabaseUnitException, SQLException, IOException {
        tester.executeAllClearTableAndSeq();
        sut = session.getMapper(MnpInPersonalInfoQueryMapper.class);

        // 「現在時刻」を固定化する
        DateTimeUtils.setCurrentMillisFixed(FIXATION_DATE.getMillis());
    }

    @After
    public void tearDown() {
        // 「現在時刻」をシステム日付に戻す
        DateTimeUtils.setCurrentMillisSystem();
    }

    @Test
    public void _insertState() throws Exception {
        // テストデータの準備
        tester.cleanInsertQuery(SIM_INFO.regist);
        tester.cleanInsertQuery(FixtureVoiceEngagementState.One.getDefaultData());
        tester.cleanInsertQuery(FixtureVoiceMnpInState.One.getForProvisional());

        // 準備
        ValidMnpIn validMnpIn = new ValidMnpInBuilder().buildKoike();

        // 実行
//        sut.insertState(validMnpIn.getVoiceEngagementNumber(), validMnpIn.getMnpInPersonalInfo());
        sut.insertState((ValidMnpInPersonalInfo) validMnpIn.getMnpInPersonalInfo());

        // 評価
        VoiceMnpInPersonalInfoStateAssert voiceMnpInPersonalInfoOutStateAssert = new VoiceMnpInPersonalInfoStateAssert(tester);
        voiceMnpInPersonalInfoOutStateAssert.assertTableWithAllColumns(FixtureVoiceMnpInPersonalInfoState.One.getDefaultData());

    }


    @Test
    public void _insertEvent() throws Exception {
        // テストデータの準備
        tester.cleanInsertQuery(SIM_INFO.regist);
        tester.cleanInsertQuery(FixtureVoiceEngagementState.One.getDefaultData());
        tester.cleanInsertQuery(FixtureVoiceMnpInState.One.getForProvisional());

        // 準備
        ValidMnpIn validMnpIn = new ValidMnpInBuilder().buildKoike();

        // 実行
        sut.insertEvent(
                EventType.UPDATE,
                new SystemDateTime().getValue(),
                requestEventProcess.getValue(),
                (ValidMnpInPersonalInfo) validMnpIn.getMnpInPersonalInfo()
        );

        // 評価
        VoiceMnpInPersonalInfoEventAssert voiceMnpInPersonalInfoEventAssert = new VoiceMnpInPersonalInfoEventAssert(tester);
        voiceMnpInPersonalInfoEventAssert.assertTableWithAllColumns(FixtureVoiceMnpInPersonalInfoEvent.One.getForUpdate());

    }

    @Test
    public void _deleteState() throws Exception {
        // テストデータの準備
        tester.cleanInsertQuery(SIM_INFO.regist);
        tester.cleanInsertQuery(FixtureVoiceEngagementState.Two.getDefaultData());
        tester.cleanInsertQuery(FixtureVoiceMnpInState.Two.getDefaultData());
        //tester.cleanInsertQuery(FixtureVoiceMnpInPersonalInfoState.Two.getDefaultData());

        // 準備
        ValidMnpIn validMnpIn = new ValidMnpInBuilder().buildYamada();

        // 実行
//        sut.deleteState(validMnpIn.getVoiceEngagementNumber());
        sut.deleteState((ValidMnpInPersonalInfo) validMnpIn.getMnpInPersonalInfo());

        // 評価
        VoiceMnpInPersonalInfoStateAssert voiceMnpInPersonalInfoStateAssert = new VoiceMnpInPersonalInfoStateAssert(tester);
        voiceMnpInPersonalInfoStateAssert.assertTableWithAllColumns(FixtureVoiceMnpInPersonalInfoState.One.getDefaultData());

    }

}