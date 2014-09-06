package jp.co.biglobe.isp.mobile.voiceoption.domain.voiceengagement;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@ToString(includeFieldNames=false)
@EqualsAndHashCode
@AllArgsConstructor
public class NewOrderInfo {

    @Getter
    private final VoiceSystemReceiptDateTime voiceSystemReceiptDateTime;

    @Getter
    private final VoiceUserOrderDate voiceUserOrderDate;
    @Getter
    private final VoiceJoinCode voiceJoinCode;

    public NewOrderInfo(VoiceEngagementInitialRequestData voiceEngagementInitialRequestData){
        this.voiceSystemReceiptDateTime = new VoiceSystemReceiptDateTime();
        this.voiceUserOrderDate = voiceEngagementInitialRequestData.getVoiceUserOrderDate();
        this.voiceJoinCode = voiceEngagementInitialRequestData.getVoiceJoinCode();
    }

    boolean isIdentificationUploadDeadlineOverForMnpIn(){
        return voiceUserOrderDate.isIdentificationUploadDeadlineOverForMnpIn();
    }

    boolean isVoiceEngagementOrderDeadlineOver(){
        return voiceUserOrderDate.isVoiceEngagementOrderDeadlineOver();
    }


}
