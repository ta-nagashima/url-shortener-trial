package jp.co.biglobe.isp.mobile.voiceoption.api.form;

import jp.co.biglobe.lib.publication.validation.DateFormat;
import jp.co.biglobe.isp.mobile.voiceoption.domain.voiceengagement.VoiceUserOrderDate;
import jp.co.biglobe.lib.publication.form.FormToValueObject;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.hibernate.validator.constraints.NotBlank;

@ToString(includeFieldNames = false)
@EqualsAndHashCode
@AllArgsConstructor
public class VoiceUserOrderDateForm implements FormToValueObject<VoiceUserOrderDate> {

    @Getter
    @NotBlank
    @DateFormat
    private String value;

    @Override
    public VoiceUserOrderDate getValueObject(){
        return new VoiceUserOrderDate(value);
    }
}
