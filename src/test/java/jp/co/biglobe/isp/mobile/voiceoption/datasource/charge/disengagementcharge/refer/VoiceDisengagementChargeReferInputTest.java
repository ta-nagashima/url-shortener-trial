package jp.co.biglobe.isp.mobile.voiceoption.datasource.charge.disengagementcharge.refer;

import jp.co.biglobe.isp.auth.domain.user.UserId;
import jp.co.biglobe.isp.mobile.voiceoption.domain.voiceengagement.VoiceEngagementNumber;
import jp.co.biglobe.lib.danger.scenario.bobio.BobioOutputData;
import jp.co.biglobe.lib.danger.scenario.converter.Property2BobioConverter;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class VoiceDisengagementChargeReferInputTest {

    @Test
    public void INパラ確認() throws Exception {

        UserId userId = new UserId("abc12345");
        VoiceEngagementNumber voiceEngagementNumber = new VoiceEngagementNumber(1);

        // 実行
        VoiceDisengagementChargeReferInput sut = createRadiusContractInput(userId, voiceEngagementNumber);
        Property2BobioConverter actual = new Property2BobioConverter(sut);

        // 確認
        BobioOutputData expected = createBobioOutputData();
        assertThat(actual.convert().buildBobioData(), is(expected.buildBobioData()));
    }

    private VoiceDisengagementChargeReferInput createRadiusContractInput(
            UserId userId,
            VoiceEngagementNumber voiceEngagementNumber) {

        return new VoiceDisengagementChargeReferInput(
                userId,
                voiceEngagementNumber
        );

    }

    private BobioOutputData createBobioOutputData() {

        BobioOutputData expected = new BobioOutputData();
        expected.put("sosaid", "SYSTEM");
        expected.put("hi_sosaid", "abc12345");
        expected.put("ref_mode", "1");
        expected.put("status", "10");
        expected.put("iyakukin_c", "LT020001");
        expected.put("apsv_key", "1");
        expected.put("menjyo_f", "0");
        return expected;

    }

}