package jp.co.biglobe.isp.mobile.voiceoption.domain.voiceengagement.cancel;


import jp.co.biglobe.isp.mobile.extension.date.SystemDateTime;
import jp.co.biglobe.isp.mobile.voiceoption.domain.voiceengagement.VoiceEngagementNumber;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@ToString(includeFieldNames = false)
@EqualsAndHashCode
public class NotExistVoiceEngagementCancel implements VoiceEngagementCancel{

    @Override
    public ValidVoiceEngagementCancel cancel(
            VoiceEngagementNumber voiceEngagementNumber,
            VoiceEngagementCancelReason voiceEngagementCancelReason,
            VoiceEngagementCancelOrderDate voiceEngagementCancelOrderDate) {
        return new ValidVoiceEngagementCancel(
                voiceEngagementNumber,
                voiceEngagementCancelReason,
                new VoiceEngagementCancelSystemReceiptDateTime(new SystemDateTime().getValue()),
                voiceEngagementCancelOrderDate);
    }

    @Override
    public boolean isExist(){
        return false;
    }
}
