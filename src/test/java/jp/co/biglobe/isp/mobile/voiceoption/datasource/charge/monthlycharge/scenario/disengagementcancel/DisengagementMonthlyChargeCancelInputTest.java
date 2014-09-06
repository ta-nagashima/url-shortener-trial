package jp.co.biglobe.isp.mobile.voiceoption.datasource.charge.monthlycharge.scenario.disengagementcancel;

import jp.co.biglobe.isp.auth.domain.user.UserId;
import jp.co.biglobe.isp.mobile.voiceoption.domain.voiceengagement.VoiceEngagementNumber;
import jp.co.biglobe.lib.danger.scenario.bobio.BobioOutputData;
import jp.co.biglobe.lib.danger.scenario.converter.Property2BobioConverter;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class DisengagementMonthlyChargeCancelInputTest {

    @Test
    public void INパラ確認() throws Exception{

        UserId userId = new UserId("abc12345");

        VoiceEngagementNumber voiceEngagementNumber = new VoiceEngagementNumber(1);

        // 実行
        DisengagementMonthlyChargeCancelInput sut = new DisengagementMonthlyChargeCancelInput(
                userId, voiceEngagementNumber);

        Property2BobioConverter actual = new Property2BobioConverter(sut);

        // 確認
        BobioOutputData expected = createBobioOutputData();
        assertThat(actual.convert().buildBobioData(), is(expected.buildBobioData()));
    }

    private BobioOutputData createBobioOutputData(){

        BobioOutputData expected = new BobioOutputData();
        expected.put("sosaid", "SYSTEM");
        expected.put("hi_sosaid", "abc12345");
        expected.put("shohin_gc", "0AA0002989");
        expected.put("apsv_own_info[1]", "1");
        expected.put("keiyaku_ymdh_end", "29991231235959");
        expected.put("kyk_kigen_ymd", "29991231235959");
        expected.put("kyk_sts_c", "10");
        expected.put("shnkyk_hnk_yoyaku_umuflg", "1");
        expected.put("teikaiyaku_cd_kai", "07");
        return expected;

    }


}