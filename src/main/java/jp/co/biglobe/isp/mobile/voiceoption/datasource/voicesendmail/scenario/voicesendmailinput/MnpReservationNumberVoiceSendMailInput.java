package jp.co.biglobe.isp.mobile.voiceoption.datasource.voicesendmail.scenario.voicesendmailinput;

import jp.co.biglobe.isp.auth.domain.user.FullName;
import jp.co.biglobe.isp.auth.domain.user.UserId;
import jp.co.biglobe.isp.mobile.voiceoption.domain.mnpin.MnpReservationNumber;
import jp.co.biglobe.lib.publication.scenario.annotation.Mapping;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class MnpReservationNumberVoiceSendMailInput implements VoiceSendMailInput {
    /*
     * 「省略時０」の記載があるパラメーターはint型にしている
     * 「省略可」の利用しないパラメータは省略している
     */

    @Mapping("send_mail_kbn")
    private final Integer sendMailKbn = new Integer(1);

    @Mapping("biglobeid")
    private UserId userId;

    @Mapping("mail_template")
    private final String mailTemplate = "blte_mnpout";

    @Mapping("to_address_kbn")
    private final Integer toAddressKbn = new Integer(1);

    @Mapping("item_free4")
    private String day;

    @Mapping("name")
    private FullName fullName;

    @Mapping("mnp_reservation_number")
    private MnpReservationNumber mnpReservationNumber;
}
