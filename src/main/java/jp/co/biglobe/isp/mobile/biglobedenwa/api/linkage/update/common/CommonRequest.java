package jp.co.biglobe.isp.mobile.biglobedenwa.api.linkage.update.common;

import jp.co.biglobe.isp.mobile.biglobedenwa.api.form.BiglobeDenwaMsisdnForm;
import jp.co.biglobe.lib.publication.validation.ValueObjectNotEmpty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.Valid;

@ToString(includeFieldNames = false)
public class CommonRequest {

    @Getter
    @Setter
    @ValueObjectNotEmpty
    @Valid
    private BiglobeDenwaMsisdnForm biglobeDenwaMsisdnForm;
}
