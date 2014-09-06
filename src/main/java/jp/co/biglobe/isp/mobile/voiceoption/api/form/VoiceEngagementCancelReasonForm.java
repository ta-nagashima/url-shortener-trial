package jp.co.biglobe.isp.mobile.voiceoption.api.form;

import jp.co.biglobe.lib.publication.validation.EnumForApiValue;
import jp.co.biglobe.isp.mobile.voiceoption.domain.voiceengagement.cancel.VoiceEngagementCancelReason;
import jp.co.biglobe.lib.publication.form.EnumApiValueConverter;
import jp.co.biglobe.lib.publication.form.FormToValueObject;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.hibernate.validator.constraints.NotBlank;

@ToString(includeFieldNames=false)
@EqualsAndHashCode
@AllArgsConstructor
public class VoiceEngagementCancelReasonForm implements FormToValueObject<VoiceEngagementCancelReason> {

    @Getter
    @NotBlank
    @EnumForApiValue(VoiceEngagementCancelReason.class)
    private String value;

    @Override
    public VoiceEngagementCancelReason getValueObject(){
        return EnumApiValueConverter.convert(VoiceEngagementCancelReason.class, value);
    }
}
