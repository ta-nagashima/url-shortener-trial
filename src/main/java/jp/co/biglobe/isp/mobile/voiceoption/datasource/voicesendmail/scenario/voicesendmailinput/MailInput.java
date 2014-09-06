package jp.co.biglobe.isp.mobile.voiceoption.datasource.voicesendmail.scenario.voicesendmailinput;

import jp.co.biglobe.lib.publication.scenario.annotation.Mapping;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class MailInput {

    @Mapping("send_mail_kbn")
    private final Integer sendMailKbn = new Integer(1);

    @Mapping("to_address_kbn")
    private final Integer toAddressKbn = new Integer(1);

    @Mapping("umekomi_kbn")
    private final Integer umekomiKbn = new Integer(1);
}
