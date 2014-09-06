package jp.co.biglobe.isp.mobile.ltethreeg.datasource.connectauth.scenario.connectauthrefer;

import jp.co.biglobe.isp.auth.domain.operatornouser.DefaultOperatorId;
import jp.co.biglobe.isp.mobile.ltethreeg.domain.connectauth.AfterBuyChargeFreeId;
import jp.co.biglobe.isp.mobile.ltethreeg.domain.connectauth.ConnectAuthItemCode;
import jp.co.biglobe.isp.mobile.ltethreeg.domain.connectauth.InternationalMsisdn;
import jp.co.biglobe.isp.mobile.ltethreeg.domain.connectauth.bandcontroldevice.ApplicationDestinationPolicyId;
import jp.co.biglobe.isp.mobile.ltethreeg.domain.sim.Msisdn;
import jp.co.biglobe.lib.publication.scenario.annotation.Mapping;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ConnectAuthChargeFreeReferInput {

    //商品G
    @Mapping("shohin_gc")
    private final ConnectAuthItemCode connectAuthItemCode = new ConnectAuthItemCode();

    //被操作者ID
    @Mapping("sosaid")
    private final DefaultOperatorId defaultOperatorId = new DefaultOperatorId();

    //被操作者ID
    @Mapping("hi_sosaid")
    private final AfterBuyChargeFreeId afterBuyChargeFreeId;

    //APサーバキー
    /*
    1MSISDNにつき商品契約が3レコード存在するが、その3レコードの内容が等しいことは
    受注管理基盤で保証されているため、認証エンティティの情報を参照するという観点では、
    LTEAの1レコードを決めうちで参照する
     */
    @Mapping("apsv_key")
    private final ApplicationDestinationPolicyId applicationDestinationPolicyId;

    //MSISDN - APサーバ固有情報1
    @Mapping("apsv_own_info[1]")
    private InternationalMsisdn internationalMsisdn;

    //契約申込日の降順
    @Mapping("sort_order_c")
    private final String sortOrderC = "2";

    public ConnectAuthChargeFreeReferInput(Msisdn msisdn, AfterBuyChargeFreeId afterBuyChargeFreeId){
        this(afterBuyChargeFreeId,ApplicationDestinationPolicyId.LTEA,msisdn.getInternationalMsisdn());
    };

    public ConnectAuthChargeFreeReferInput(Msisdn msisdn, AfterBuyChargeFreeId afterBuyChargeFreeId, ApplicationDestinationPolicyId applicationDestinationPolicyId){
        this(afterBuyChargeFreeId,applicationDestinationPolicyId,msisdn.getInternationalMsisdn());
    };

}
