package jp.co.biglobe.isp.mobile.voiceoption.domain.voiceengagement.disengage;


import jp.co.biglobe.isp.mobile.extension.date.SystemDateTime;
import jp.co.biglobe.isp.mobile.voiceoption.domain.voiceengagement.VoiceEngagementNumber;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@ToString(includeFieldNames = false)
@EqualsAndHashCode
public class NotExistVoiceEngagementDisengage implements VoiceEngagementDisengage{

    @Override
    public ValidVoiceEngagementDisengage disengage(
            VoiceEngagementNumber voiceEngagementNumber,
            VoiceEngagementDisengageReason voiceEngagementDisengageReason,
            VoiceEngagementDisengageOrderDate voiceEngagementDisengageOrderDate) {
        return new ValidVoiceEngagementDisengage(
                voiceEngagementNumber,
                voiceEngagementDisengageReason,
                new VoiceEngagementDisengageSystemReceiptDateTime(new SystemDateTime().getValue()),
                voiceEngagementDisengageOrderDate);
    }

    @Override
    public boolean isExist(){
        return false;
    }
}
