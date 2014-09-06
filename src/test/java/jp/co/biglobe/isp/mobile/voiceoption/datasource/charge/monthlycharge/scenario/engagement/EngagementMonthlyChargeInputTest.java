package jp.co.biglobe.isp.mobile.voiceoption.datasource.charge.monthlycharge.scenario.engagement;

import jp.co.biglobe.isp.auth.domain.user.UserId;
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
public class EngagementMonthlyChargeInputTest {

    @Test
    public void INパラ確認() throws Exception {

        UserId userId = new UserId("abc12345");
        String dateTimeStr = "20140401235959";
        VoiceEngagementNumber voiceEngagementNumber = new VoiceEngagementNumber(1);

        // 実行
        EngagementMonthlyChargeInput sut = createRadiusContractInput(userId, dateTimeStr, voiceEngagementNumber);
        Property2BobioConverter actual = new Property2BobioConverter(sut);

        // 確認
        BobioOutputData expected = createBobioOutputData();
        assertThat(actual.convert().buildBobioData(), is(expected.buildBobioData()));
    }

    private EngagementMonthlyChargeInput createRadiusContractInput(
            UserId userId,
            String dateTimeStr,
            VoiceEngagementNumber voiceEngagementNumber) {

        return new EngagementMonthlyChargeInput(
                userId,
                dateTimeStr,
                voiceEngagementNumber
        );

    }

    private BobioOutputData createBobioOutputData() {

        BobioOutputData expected = new BobioOutputData();
        expected.put("contract_auth_type[1]", "0");
        expected.put("contract_auth_type[6]", "0");
        expected.put("contract_auth_type[8]", "0");
        expected.put("contract_auth_type[9]", "0");
        expected.put("contract_auth_type[10]", "0");
        expected.put("contract_auth_type[13]", "0");
        expected.put("contract_auth_type[16]", "9");
        expected.put("contract_auth_type[17]", "1");
        expected.put("contract_auth_type[24]", "0");
        expected.put("sosaid", "SYSTEM");
        expected.put("hi_sosaid", "abc12345");
        expected.put("shohin_gc", "0AA0002989");
        expected.put("keiyaku_num", "1");
        expected.put("moushikomi_ymdh", "20140401235959");
        expected.put("apsv_key", "LTE");
        expected.put("apsv_own_info[1]", "1");

        return expected;

    }

}