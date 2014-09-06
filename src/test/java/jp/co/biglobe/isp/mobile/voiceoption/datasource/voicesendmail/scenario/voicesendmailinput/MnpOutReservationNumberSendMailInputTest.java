package jp.co.biglobe.isp.mobile.voiceoption.datasource.voicesendmail.scenario.voicesendmailinput;

import jp.co.biglobe.isp.auth.domain.user.UserId;
import jp.co.biglobe.isp.mobile.voiceoption.domain.mnpin.MnpReservationNumber;
import jp.co.biglobe.lib.danger.scenario.bobio.BobioOutputData;
import jp.co.biglobe.lib.danger.scenario.converter.Property2BobioConverter;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class MnpOutReservationNumberSendMailInputTest {

    @Test
    public void INパラ確認() throws Exception {

        UserId userId = new UserId("abc12345");

        // 実行
        MnpOutReservationNumberSendMailInput sut = createRadiusContractInput(
                userId,
                "20140101",
                new MnpReservationNumber("11-111-11111"));
        Property2BobioConverter actual = new Property2BobioConverter(sut);

        // 確認
        BobioOutputData expected = createBobioOutputData();
        assertThat(actual.convert().buildBobioData(), is(expected.buildBobioData()));
    }

    private MnpOutReservationNumberSendMailInput createRadiusContractInput(
            UserId userId,
            String day,
            MnpReservationNumber mnpReservationNumber) {

        return new MnpOutReservationNumberSendMailInput(
                userId,
                day,
                mnpReservationNumber
        );

    }

    private BobioOutputData createBobioOutputData() {

        BobioOutputData expected = new BobioOutputData();
        expected.put("send_mail_kbn", "1");
        expected.put("to_address_kbn", "1");
        expected.put("umekomi_kbn", "1");
        expected.put("biglobeid", "abc12345");
        expected.put("mail_template", "BLTE_telsim_confirm_num");
        expected.put("item_free4", "20140101");
        expected.put("item_free5", "11-111-11111");
        return expected;

    }

}