package jp.co.biglobe.isp.mobile.biglobedenwa.api.form;

import jp.co.biglobe.isp.mobile.biglobedenwa.domain.rocky.RockySuccessFile;
import jp.co.biglobe.lib.publication.form.FormToValueObject;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.hibernate.validator.constraints.NotBlank;

@ToString(includeFieldNames = false)
@EqualsAndHashCode
@AllArgsConstructor
public class RockySuccessFileForm implements FormToValueObject<RockySuccessFile> {
    @Getter
    @NotBlank
    private String value;

    @Override
    public RockySuccessFile getValueObject() {
        return new RockySuccessFile(value);
    }
}
