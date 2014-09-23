package jp.co.biglobe.isp.sample.user.api.samplecheck;


import jp.co.biglobe.isp.sample.user.api.form.SampleDateTimeForm;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.Valid;

@ToString(includeFieldNames = false)
public class SampleCheckRequest {
    @Getter
    @Setter
    //@ValueObjectNotEmpty
    @Valid
    private SampleDateTimeForm sampleDateTimeForm;
}
