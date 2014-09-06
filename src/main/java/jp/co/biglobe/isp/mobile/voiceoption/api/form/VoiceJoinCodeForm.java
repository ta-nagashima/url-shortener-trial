package jp.co.biglobe.isp.mobile.voiceoption.api.form;

import jp.co.biglobe.isp.mobile.voiceoption.domain.voiceengagement.VoiceJoinCode;
import jp.co.biglobe.lib.publication.form.FormToValueObject;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@ToString(includeFieldNames=false)
@EqualsAndHashCode
@AllArgsConstructor
public class VoiceJoinCodeForm  implements FormToValueObject<VoiceJoinCode> {

    @Getter
    @NotBlank
    @Size(min = 1, max = 256)
    @Pattern(regexp = "^[0-9a-zA-Z]+$")
    private String value;

    @Override
    public VoiceJoinCode getValueObject(){
        return new VoiceJoinCode(value);
    }

}
