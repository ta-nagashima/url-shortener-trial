package jp.co.biglobe.isp.sample.user.api.form;

import jp.co.biglobe.isp.sample.user.domain.sample.SampleDateTime;
import jp.co.biglobe.lib.publication.form.FormToValueObject;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.hibernate.validator.constraints.NotBlank;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;

@ToString(includeFieldNames = false)
@EqualsAndHashCode
@AllArgsConstructor
public class SampleDateTimeForm implements FormToValueObject<SampleDateTime> {

    @Getter
    @NotBlank
    //@DateTimeFormat
    private String value;

    @Override
    public SampleDateTime getValueObject() {
//        return new SampleDateTime(new Date());
        return new SampleDateTime(LocalDateTime.parse(value, DateTimeFormatter.ofPattern("uuuuMMddHHmmss").withResolverStyle(ResolverStyle.STRICT)));
    }
}
