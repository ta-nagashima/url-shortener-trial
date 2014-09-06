package jp.co.biglobe.isp.sample.api.samplecheck;


import jp.co.biglobe.isp.sample.api.form.SampleDateTimeForm;
import jp.co.biglobe.lib.publication.validation.ValueObjectNotEmpty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.Valid;

@ToString(includeFieldNames = false)
public class SampleCheckRequest {
    @Getter
    @Setter
    @ValueObjectNotEmpty
    @Valid
    private SampleDateTimeForm sampleDateTimeForm;
}
