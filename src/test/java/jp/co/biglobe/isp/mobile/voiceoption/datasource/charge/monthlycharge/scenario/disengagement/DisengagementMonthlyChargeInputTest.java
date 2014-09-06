package jp.co.biglobe.isp.mobile.voiceoption.datasource.charge.monthlycharge.scenario.disengagement;

import jp.co.biglobe.isp.auth.domain.operatornouser.OperatorId;
import jp.co.biglobe.isp.auth.domain.user.UserId;
import jp.co.biglobe.isp.mobile.voiceoption.datasource.charge.monthlycharge.scenario.engagement.EngagementMonthlyChargeInput;
import jp.co.biglobe.isp.mobile.voiceoption.domain.voiceengagement.VoiceEngagementNumber;
import jp.co.biglobe.lib.danger.scenario.bobio.BobioOutputData;
import jp.co.biglobe.lib.danger.scenario.converter.Property2BobioConverter;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;


@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = {"classpath:context.xml"})
public class DisengagementMonthlyChargeInputTest {

    @Test
    public void INパラ確認() throws Exception{

        UserId userId = new UserId("abc12345");
        String dateTimeStr = "20140401235959";
        VoiceEngagementNumber voiceEngagementNumber = new VoiceEngagementNumber(1);

        // 実行
        DisengagementMonthlyChargeInput sut = new DisengagementMonthlyChargeInput(
                userId, voiceEngagementNumber, dateTimeStr, dateTimeStr);

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
        expected.put("keiyaku_ymdh_end", "20140401235959");
        expected.put("kyk_kigen_ymd", "20140401235959");
        expected.put("kyk_sts_c", "25");
        expected.put("shnkyk_hnk_yoyaku_umuflg", "1");
        expected.put("teikaiyaku_cd_kai", "07");
        return expected;

    }

}