package jp.co.biglobe.isp.mobile.ltethreeg.api.limit.speedlimit1month;

import jp.co.biglobe.lib.publication.validation.ValueObjectNotEmpty;
import jp.co.biglobe.isp.mobile.ltethreeg.api.form.ltethreegengagement.LteThreeGEngagementNumberForm;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.Valid;

@ToString(includeFieldNames = false)
public class ConnectSpeedLimit1MonthRequest {

    @Getter
    @Setter
    @ValueObjectNotEmpty
    @Valid
    private LteThreeGEngagementNumberForm lteThreeGEngagementNumberForm;

}
