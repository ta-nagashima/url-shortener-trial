package jp.co.biglobe.isp.mobile.biglobedenwa.api.linkage.applyfrombatch;


import jp.co.biglobe.isp.mobile.biglobedenwa.api.form.BiglobeDenwaApplyChannelForm;
import jp.co.biglobe.isp.mobile.biglobedenwa.api.form.BiglobeDenwaMsisdnForm;
import jp.co.biglobe.lib.publication.validation.ValueObjectNotEmpty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.Valid;

@ToString(includeFieldNames = false)
public class BiglobeDenwaLinkageApplyFromBatchRequest {

    @Getter
    @Setter
    @ValueObjectNotEmpty
    @Valid
    private BiglobeDenwaMsisdnForm biglobeDenwaMsisdnForm;

    @Getter
    @Setter
    @ValueObjectNotEmpty
    @Valid
    private BiglobeDenwaApplyChannelForm biglobeDenwaApplyChannelForm;


}
