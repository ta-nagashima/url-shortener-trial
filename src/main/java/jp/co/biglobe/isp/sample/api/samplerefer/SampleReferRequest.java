package jp.co.biglobe.isp.sample.api.samplerefer;


import jp.co.biglobe.isp.sample.api.form.SampleDateTimeForm;
import jp.co.biglobe.lib.publication.validation.ValueObjectNotEmpty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.Valid;

@ToString(includeFieldNames = false)
public class SampleReferRequest {
    @Getter
    @Setter
    @ValueObjectNotEmpty
    @Valid
    private SampleDateTimeForm sampleDateTimeForm;
}
