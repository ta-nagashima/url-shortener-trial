package jp.co.biglobe.isp.mobile.voiceoption.datasource.voicesendmail.scenario.voicesendmailinput;

import jp.co.biglobe.isp.auth.domain.user.UserId;
import jp.co.biglobe.isp.mobile.voiceoption.datasource.voicesendmail.AddressName;
import jp.co.biglobe.lib.publication.scenario.annotation.Mapping;
import jp.co.biglobe.lib.publication.scenario.annotation.MappingObject;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class IdentificationResultVoiceSendMailInput implements VoiceSendMailInput {

    @MappingObject
    private final MailInput mailInput = new MailInput();

    @Mapping("biglobeid")
    private UserId userId;

    @Mapping("mail_template")
    private final MailTemplates mailTemplates = MailTemplates.BLTE_CONFIRM_RESULT;

    /**
     * ここから埋め込みパラメーター
     */

    @Mapping("item_free4")
    private String day;


}
