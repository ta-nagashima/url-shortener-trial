package jp.co.biglobe.isp.mobile.voiceoption.api.form;

import jp.co.biglobe.isp.mobile.voiceoption.domain.mnpout.MnpOutMailSendStatus;
import jp.co.biglobe.isp.mobile.voiceoption.domain.mnpout.MnpOutMsisdn;
import jp.co.biglobe.lib.publication.form.EnumApiValueConverter;
import jp.co.biglobe.lib.publication.form.FormToValueObject;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Pattern;

@ToString(includeFieldNames = false)
@EqualsAndHashCode
@AllArgsConstructor
public class MnpOutMailSendStatusForm implements FormToValueObject<MnpOutMailSendStatus> {

    @Getter
    @NotBlank
    private String value;

    @Override
    public MnpOutMailSendStatus getValueObject(){
        return EnumApiValueConverter.convert(MnpOutMailSendStatus.class, value);
    }
}
