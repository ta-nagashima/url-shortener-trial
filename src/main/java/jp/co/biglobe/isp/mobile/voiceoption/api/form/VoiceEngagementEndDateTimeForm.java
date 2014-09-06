package jp.co.biglobe.isp.mobile.voiceoption.api.form;

import jp.co.biglobe.lib.publication.validation.DateTimeFormat;
import jp.co.biglobe.isp.mobile.voiceoption.domain.voiceengagement.VoiceEngagementEndDateTime;
import jp.co.biglobe.lib.publication.form.FormToValueObject;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.hibernate.validator.constraints.NotBlank;

@ToString(includeFieldNames = false)
@EqualsAndHashCode
@AllArgsConstructor

public class VoiceEngagementEndDateTimeForm implements FormToValueObject<VoiceEngagementEndDateTime> {

    @Getter
    @NotBlank
    @DateTimeFormat
    private String value;

    @Override
    public VoiceEngagementEndDateTime getValueObject() {
        return new VoiceEngagementEndDateTime(org.joda.time.format.DateTimeFormat.forPattern("yyyyMMddHHmmss").parseDateTime(value).toDate());

    }
}
