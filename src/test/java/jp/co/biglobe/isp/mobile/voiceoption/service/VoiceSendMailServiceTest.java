package jp.co.biglobe.isp.mobile.voiceoption.service;

import jp.co.biglobe.isp.auth.domain.user.FullName;
import jp.co.biglobe.isp.auth.domain.user.UserId;
import jp.co.biglobe.isp.mobile.voiceoption.datasource.voicesendmail.VoiceSendMailDate;
import jp.co.biglobe.isp.mobile.voiceoption.datasource.voicesendmail.scenario.VoiceSendMailOutput;
import jp.co.biglobe.isp.mobile.voiceoption.datasource.voicesendmail.scenario.VoiceSendMailScenario;
import jp.co.biglobe.isp.mobile.voiceoption.datasource.voicesendmail.scenario.voicesendmailinput.MnpReservationNumberVoiceSendMailInput;
import jp.co.biglobe.isp.mobile.voiceoption.domain.mnpin.MnpReservationNumber;
import jp.co.biglobe.lib.plugin.event.RequestEventTime;
import jp.co.biglobe.test.util.usecase.BobioUseCase;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = {"classpath:context.xml"})
public class VoiceSendMailServiceTest {

    @Autowired
    private VoiceSendMailScenario sut;

    @Autowired
    RequestEventTime requestEventTime;

    private static final String SCENARIO_NAME = "M_JKBS_Mail_send";
    private BobioUseCase testcase = new BobioUseCase(SCENARIO_NAME);

    @Test
    public void registerSendMail_正常終了() throws Exception {

        //テストケースの設定
        testcase.set("DEFAULT");

        // 準備
        VoiceSendMailDate voiceSendMailDate = new VoiceSendMailDate(requestEventTime.getDate());

        // 実行
        MnpReservationNumberVoiceSendMailInput mnpReservationNumberVoiceSendMailInput = new MnpReservationNumberVoiceSendMailInput(
                new UserId("nrz68284"),
                voiceSendMailDate.getDay(),
                new FullName("小池　直樹"),
                new MnpReservationNumber("11-222-33333") {
                }
        );
        VoiceSendMailOutput actual = sut.send(mnpReservationNumberVoiceSendMailInput);

        // 評価
        assertEquals("0", actual.getBobioHeader().getBOCLCode().getValue());

    }

    @After
    public void tearDown() throws IOException {
        testcase.unset();
    }

}
