package jp.co.biglobe.isp.mobile.voiceoption.datasource.mnpout.db;

import jp.co.biglobe.isp.mobile.extension.date.SystemDateTime;
import jp.co.biglobe.isp.mobile.ltethreeg.datasource.SIM_INFO;
import jp.co.biglobe.isp.mobile.voiceoption.database.VoiceMnpOutNumberEventAssert;
import jp.co.biglobe.isp.mobile.voiceoption.database.VoiceMnpOutNumberStateAssert;
import jp.co.biglobe.isp.mobile.voiceoption.datasource.voiceengagement.db.FixtureVoiceEngagementState;
import jp.co.biglobe.isp.mobile.voiceoption.domain.mnpout.ValidMnpOut;
import jp.co.biglobe.isp.mobile.voiceoption.domain.mnpout.ValidMnpOutBuilder;
import jp.co.biglobe.isp.mobile.voiceoption.domain.mnpout.mnpoutreservationnumber.ValidMnpOutReservationNumber;
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
public class MnpOutNumberQueryMapperTest {

    @Autowired
    SqlSessionTemplate session;

    private MnpOutNumberQueryMapper sut;

    @Autowired
    DbUnitTester tester;

    public final DateTime FIXATION_DATE = new DateTime(2014, 1, 1, 0, 0, 0);

    @Autowired
    private RequestEventProcess requestEventProcess;

    @Before
    public void setUp() throws DatabaseUnitException, SQLException, IOException {
        tester.executeAllClearTableAndSeq();
        sut = session.getMapper(MnpOutNumberQueryMapper.class);

        // 「現在時刻」を固定化する
        DateTimeUtils.setCurrentMillisFixed(FIXATION_DATE.getMillis());
    }

    @After
    public void tearDown() {
        // 「現在時刻」をシステム日付に戻す
        DateTimeUtils.setCurrentMillisSystem();
    }

    @Test
    public void _insertEvent() throws Exception {
        // テストデータの準備
        tester.cleanInsertQuery(SIM_INFO.regist);
        tester.cleanInsertQuery(FixtureVoiceEngagementState.One.getDefaultData());
        tester.cleanInsertQuery(FixtureVoiceMnpOutState.One.getForRequested());

        // 準備
        ValidMnpOut validMnpOut = new ValidMnpOutBuilder()
                .mnpOutReservationNumber()
                .build();

        // 実行
        sut.insertEvent(
                EventType.INSERT,
                new SystemDateTime().getValue(),
                requestEventProcess.getValue(),
                (ValidMnpOutReservationNumber)validMnpOut.getMnpOutReservationNumber());

        // 評価
        VoiceMnpOutNumberEventAssert voiceMnpOutNumberEventAssert = new VoiceMnpOutNumberEventAssert(tester);
        voiceMnpOutNumberEventAssert.assertTableWithAllColumns(FixtureVoiceMnpOutNumberEvent.One.getDefaultData());
    }

    public MnpOutNumberQueryMapperTest() {
        super();
    }

    @Test
    public void _insertState() throws Exception {
        // テストデータの準備
        tester.cleanInsertQuery(SIM_INFO.regist);
        tester.cleanInsertQuery(FixtureVoiceEngagementState.One.getDefaultData());
        tester.cleanInsertQuery(FixtureVoiceMnpOutState.One.getForRequested());

        // 準備
        ValidMnpOut validMnpOut = new ValidMnpOutBuilder()
                .mnpOutReservationNumber()
                .build();

        // 実行
        sut.insertState((ValidMnpOutReservationNumber) validMnpOut.getMnpOutReservationNumber());

        // 評価
        VoiceMnpOutNumberStateAssert voiceMnpOutNumberStateAssert = new VoiceMnpOutNumberStateAssert(tester);
        voiceMnpOutNumberStateAssert.assertTableWithAllColumns(FixtureVoiceMnpOutNumberState.One.getDefaultData());
    }

}
