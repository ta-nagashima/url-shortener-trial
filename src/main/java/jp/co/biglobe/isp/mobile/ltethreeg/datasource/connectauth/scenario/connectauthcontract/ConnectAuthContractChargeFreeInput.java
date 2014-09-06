package jp.co.biglobe.isp.mobile.ltethreeg.datasource.connectauth.scenario.connectauthcontract;


import jp.co.biglobe.isp.auth.domain.operatornouser.DefaultOperatorId;
import jp.co.biglobe.isp.auth.domain.user.UserId;
import jp.co.biglobe.isp.mobile.biglobemember.domain.corporation.CorporationNumberForScenario;
import jp.co.biglobe.isp.mobile.ltethreeg.domain.connectauth.AfterBuyChargeFreeId;
import jp.co.biglobe.isp.mobile.ltethreeg.domain.connectauth.ConnectAuthItemCode;
import jp.co.biglobe.isp.mobile.ltethreeg.domain.connectauth.InternationalMsisdn;
import jp.co.biglobe.isp.mobile.ltethreeg.domain.connectauth.LteThreeGConnectAuthUserEntity;
import jp.co.biglobe.isp.mobile.ltethreeg.domain.connectauth.bandcontroldevice.ApplicationDestinationPolicyId;
import jp.co.biglobe.isp.mobile.ltethreeg.domain.connectauth.bandcontroldevice.LineApplicationDestinationPolicyId;
import jp.co.biglobe.isp.mobile.ltethreeg.domain.connectauth.vpncorporation.ValidVpnCorporation;
import jp.co.biglobe.isp.mobile.ltethreeg.domain.ltethreegengagement.LteThreeGEngagementNumber;
import jp.co.biglobe.isp.mobile.ltethreeg.domain.ltethreegengagement.serviceplan.LteThreeGServicePlanCode;
import jp.co.biglobe.isp.mobile.ltethreeg.domain.ltethreegengagement.serviceplan.LteThreeGServicePlanSwitchingDateTime;
import jp.co.biglobe.isp.mobile.ltethreeg.option.vpn.domain.engagement.VpnOptionCode;
import jp.co.biglobe.lib.plugin.event.RequestEventTime;
import jp.co.biglobe.lib.publication.scenario.annotation.Mapping;
import jp.co.biglobe.lib.publication.scenario.annotation.MappingObject;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ConnectAuthContractChargeFreeInput {

    @MappingObject
    private final ContractInput contractInput = new ContractInput();

    // 操作者ID
    @Mapping("sosaid")
    private final DefaultOperatorId operatorId = new DefaultOperatorId();

    // 被操作者ID
    @Mapping("hi_sosaid")
    private final AfterBuyChargeFreeId afterBuyChargeFreeId;

    //商品グループコード
    @Mapping("shohin_gc")
    private final ConnectAuthItemCode connectAuthItemCode = new ConnectAuthItemCode();

    //契約個数
    @Mapping("keiyaku_num")
    private final String keiyaku_num = "1";

    //申込日時
    @Mapping("moushikomi_ymdh")
    private final String moushikomi_ymdh;

    //APサーバキー
    @Mapping("apsv_key")
    private final ApplicationDestinationPolicyId applicationDestinationPolicyId;

    //APサーバ固有情報１
    @Mapping("apsv_own_info[1]")
    private final InternationalMsisdn internationalMsisdn;

    //汎用情報項目１(切替後サービスプランコード)
    @Mapping("hanyo_info1")
    private final LteThreeGServicePlanCode afterLteThreeGServicePlanCode;

    //制御ポリシー
    @Mapping("hanyo_info4")
    private final String connectControlPolicy;

    //ポリシー適用先ID
    @Mapping("hanyo_info5")
    private final LineApplicationDestinationPolicyId lineApplicationDestinationPolicyId;

}
