package jp.co.biglobe.isp.mobile.biglobedenwa.api.form;

import jp.co.biglobe.isp.mobile.biglobedenwa.domain.rocky.RockyErrorFile;
import jp.co.biglobe.lib.publication.form.FormToValueObject;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.hibernate.validator.constraints.NotBlank;

@ToString(includeFieldNames = false)
@EqualsAndHashCode
@AllArgsConstructor
public class RockyErrorFileForm implements FormToValueObject<RockyErrorFile> {
    @Getter
    @NotBlank
    private String value;

    @Override
    public RockyErrorFile getValueObject() {
        return new RockyErrorFile(value);
    }
}
