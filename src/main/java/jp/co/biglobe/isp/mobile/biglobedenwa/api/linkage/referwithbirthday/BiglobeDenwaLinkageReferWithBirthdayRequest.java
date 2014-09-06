package jp.co.biglobe.isp.mobile.biglobedenwa.api.linkage.referwithbirthday;


import jp.co.biglobe.isp.auth.api.form.OperatorIdForm;
import jp.co.biglobe.isp.auth.api.form.OperatorPasswordForm;
import jp.co.biglobe.isp.mobile.biglobedenwa.api.form.BiglobeDenwaBirthdayForm;
import jp.co.biglobe.isp.mobile.biglobedenwa.api.form.BiglobeDenwaMsisdnForm;
import jp.co.biglobe.lib.publication.validation.ValueObjectNotEmpty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.Valid;


@ToString(includeFieldNames = false)
public class BiglobeDenwaLinkageReferWithBirthdayRequest {

    @Getter
    @Setter
    @ValueObjectNotEmpty
    @Valid
    private BiglobeDenwaMsisdnForm biglobeDenwaMsisdnForm;

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

    @Getter
    @Setter
    @ValueObjectNotEmpty
    @Valid
    private BiglobeDenwaBirthdayForm biglobeDenwaBirthdayForm;
}
