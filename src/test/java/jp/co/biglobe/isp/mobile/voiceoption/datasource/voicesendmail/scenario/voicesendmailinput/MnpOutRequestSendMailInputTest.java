package jp.co.biglobe.isp.mobile.voiceoption.datasource.voicesendmail.scenario.voicesendmailinput;

import jp.co.biglobe.isp.auth.domain.user.UserId;
import jp.co.biglobe.lib.danger.scenario.bobio.BobioOutputData;
import jp.co.biglobe.lib.danger.scenario.converter.Property2BobioConverter;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class MnpOutRequestSendMailInputTest {

    @Test
    public void INパラ確認() throws Exception {

        UserId userId = new UserId("abc12345");

        // 実行
        MnpOutRequestSendMailInput sut = createRadiusContractInput(userId, "20140101");
        Property2BobioConverter actual = new Property2BobioConverter(sut);

        // 確認
        BobioOutputData expected = createBobioOutputData();
        assertThat(actual.convert().buildBobioData(), is(expected.buildBobioData()));
    }

    private MnpOutRequestSendMailInput createRadiusContractInput(
            UserId userId,
            String day) {

        return new MnpOutRequestSendMailInput(
                userId,
                day
        );

    }

    private BobioOutputData createBobioOutputData() {

        BobioOutputData expected = new BobioOutputData();
        expected.put("send_mail_kbn", "1");
        expected.put("to_address_kbn", "1");
        expected.put("umekomi_kbn", "1");
        expected.put("biglobeid", "abc12345");
        expected.put("mail_template", "BLTE_telsim_recept_end_mnp");
        expected.put("item_free4", "20140101");
        return expected;

    }

}