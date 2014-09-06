package jp.co.biglobe.isp.mobile.voiceoption.api.form;

import jp.co.biglobe.lib.publication.validation.EnumForApiValue;
import jp.co.biglobe.isp.mobile.voiceoption.domain.voiceengagement.disengage.VoiceEngagementDisengageReason;
import jp.co.biglobe.lib.publication.form.EnumApiValueConverter;
import jp.co.biglobe.lib.publication.form.FormToValueObject;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.hibernate.validator.constraints.NotBlank;

@ToString(includeFieldNames = false)
@EqualsAndHashCode
@AllArgsConstructor
public class VoiceEngagementDisengageReasonForm implements FormToValueObject<VoiceEngagementDisengageReason> {


    @Getter
    @NotBlank
    @EnumForApiValue(VoiceEngagementDisengageReason.class)
    private String value;

    @Override
    public VoiceEngagementDisengageReason getValueObject() {
        return EnumApiValueConverter.convert(VoiceEngagementDisengageReason.class, value);
    }

}
