package jp.co.biglobe.isp.mobile.biglobedenwa.api.rocky;

import jp.co.biglobe.isp.auth.api.form.OperatorIdForm;
import jp.co.biglobe.isp.auth.api.form.OperatorPasswordForm;
import jp.co.biglobe.isp.mobile.biglobedenwa.api.form.RockyErrorFileForm;
import jp.co.biglobe.isp.mobile.biglobedenwa.api.form.RockySuccessFileForm;
import jp.co.biglobe.lib.publication.validation.ValueObjectNotEmpty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.Valid;

@ToString(includeFieldNames = false)
public class BiglobeDenwaResultUploadRequest {
    @Getter
    @Setter
    @ValueObjectNotEmpty
    @Valid
    private RockySuccessFileForm rockySuccessFileForm;

    @Getter
    @Setter
    @ValueObjectNotEmpty
    @Valid
    private RockyErrorFileForm rockyErrorFileForm;

    @Getter
    @Setter
    @ValueObjectNotEmpty
    @Valid
    private OperatorIdForm operatorIdForm;

    @Getter
    @Setter
    @ValueObjectNotEmpty
    @Valid
    private OperatorPasswordForm operatorPasswordForm;
}
