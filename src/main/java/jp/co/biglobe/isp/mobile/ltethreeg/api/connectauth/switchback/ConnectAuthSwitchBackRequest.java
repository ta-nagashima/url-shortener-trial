package jp.co.biglobe.isp.mobile.ltethreeg.api.connectauth.switchback;

import jp.co.biglobe.isp.mobile.ltethreeg.api.form.ltethreegengagement.LteThreeGEngagementNumberForm;
import jp.co.biglobe.isp.mobile.ltethreeg.api.form.ltethreegengagement.MsisdnForm;
import jp.co.biglobe.lib.publication.validation.ValueObjectNotEmpty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.Valid;

@ToString(includeFieldNames = false)
public class ConnectAuthSwitchBackRequest {

    @Getter
    @Setter
    @ValueObjectNotEmpty
    @Valid
    private MsisdnForm msisdnForm;

    @Getter
    @Setter
    @ValueObjectNotEmpty
    @Valid
    private LteThreeGEngagementNumberForm lteThreeGEngagementNumberForm;

}
