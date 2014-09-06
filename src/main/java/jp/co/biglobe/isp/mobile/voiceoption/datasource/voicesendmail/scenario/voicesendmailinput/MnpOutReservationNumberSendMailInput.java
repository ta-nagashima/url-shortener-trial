package jp.co.biglobe.isp.mobile.voiceoption.datasource.voicesendmail.scenario.voicesendmailinput;

import jp.co.biglobe.isp.auth.domain.user.UserId;
import jp.co.biglobe.isp.mobile.voiceoption.domain.mnpin.MnpReservationNumber;
import jp.co.biglobe.lib.publication.scenario.annotation.Mapping;
import jp.co.biglobe.lib.publication.scenario.annotation.MappingObject;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class MnpOutReservationNumberSendMailInput implements VoiceSendMailInput {

    @MappingObject
    private final MailInput mailInput = new MailInput();

    @Mapping("biglobeid")
    private UserId userId;

    @Mapping("mail_template")
    private final MailTemplates mailTemplates = MailTemplates.BLTE_TELSIM_CONFIRM_NUM;

    /**
     * ここから埋め込みパラメーター
     */

    @Mapping("item_free4")
    private String day;

    @Mapping("item_free5")
    private MnpReservationNumber mnpReservationNumber;


}
