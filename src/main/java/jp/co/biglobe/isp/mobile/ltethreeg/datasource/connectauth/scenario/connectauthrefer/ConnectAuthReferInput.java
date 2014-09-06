package jp.co.biglobe.isp.mobile.ltethreeg.datasource.connectauth.scenario.connectauthrefer;

import jp.co.biglobe.isp.auth.domain.operatornouser.DefaultOperatorId;
import jp.co.biglobe.isp.auth.domain.user.UserId;
import jp.co.biglobe.isp.mobile.ltethreeg.domain.connectauth.ConnectAuthItemCode;
import jp.co.biglobe.isp.mobile.ltethreeg.domain.connectauth.InternationalMsisdn;
import jp.co.biglobe.isp.mobile.ltethreeg.domain.connectauth.bandcontroldevice.ApplicationDestinationPolicyId;
import jp.co.biglobe.isp.mobile.ltethreeg.domain.sim.Msisdn;
import jp.co.biglobe.lib.publication.scenario.annotation.Mapping;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ConnectAuthReferInput {

    //商品G
    @Mapping("shohin_gc")
    private final ConnectAuthItemCode connectAuthItemCode = new ConnectAuthItemCode();

    //被操作者ID
    @Mapping("sosaid")
    private final DefaultOperatorId defaultOperatorId = new DefaultOperatorId();

    //被操作者ID
    @Mapping("hi_sosaid")
    private final UserId userId;

    //APサーバキー
    @Mapping("apsv_key")
    private final ApplicationDestinationPolicyId applicationDestinationPolicyId;

    //MSISDN - APサーバ固有情報1
    @Mapping("apsv_own_info[1]")
    private InternationalMsisdn internationalMsisdn;

    //契約申込日の降順
    @Mapping("sort_order_c")
    private final String sortOrderC = "2";

}
