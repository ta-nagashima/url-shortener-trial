package jp.co.biglobe.isp.mobile.ltethreeg.datasource.connectauth.scenario.connectauthcontract;


import jp.co.biglobe.isp.auth.domain.operatornouser.DefaultOperatorId;
import jp.co.biglobe.isp.auth.domain.user.UserId;
import jp.co.biglobe.isp.mobile.biglobemember.domain.corporation.CorporationNumberForScenario;
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
public class ConnectAuthContractUserInput {

    @MappingObject
    private final ContractInput contractInput = new ContractInput();

    // 操作者ID
    @Mapping("sosaid")
    private final DefaultOperatorId operatorId = new DefaultOperatorId();

    // 被操作者ID
    @Mapping("hi_sosaid")
    private final UserId userId;

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

    //汎用情報項目２(切替前サービスプランコード)
    @Mapping("hanyo_info2")
    private final String beforeLteThreeGServicePlanCode;

    //汎用情報項目３(サービスプランコード切り替え日)
    @Mapping("hanyo_info3")
    private final LteThreeGServicePlanSwitchingDateTime lteThreeGServicePlanSwitchingDateTime;

    //制御ポリシー
    @Mapping("hanyo_info4")
    private final String connectControlPolicy;

    //ポリシー適用先ID
    @Mapping("hanyo_info5")
    private final LineApplicationDestinationPolicyId lineApplicationDestinationPolicyId;

    //契約番号
    @Mapping("hanyo_info6")
    private final LteThreeGEngagementNumber lteThreeGEngagementNumber;

    //オプションサービスコード（VPNオプションコード）
    @Mapping("hanyo_info6")
    private VpnOptionCode vpnOptionCode;

    //法人番号
    @Mapping("hanyo_info7")
    private CorporationNumberForScenario corporationNumber;

    public ConnectAuthContractUserInput(
            UserId userId,
            String moushikomi_ymdh,
            ApplicationDestinationPolicyId applicationDestinationPolicyId,
            InternationalMsisdn internationalMsisdn,
            LteThreeGServicePlanCode afterLteThreeGServicePlanCode,
            String beforeLteThreeGServicePlanCode,
            LteThreeGServicePlanSwitchingDateTime lteThreeGServicePlanSwitchingDateTime,
            String connectControlPolicy,
            LineApplicationDestinationPolicyId lineApplicationDestinationPolicyId,
            LteThreeGEngagementNumber lteThreeGEngagementNumber
    ){

        this.userId = userId;
        this.moushikomi_ymdh = moushikomi_ymdh;
        this.applicationDestinationPolicyId = applicationDestinationPolicyId;
        this.internationalMsisdn = internationalMsisdn;
        this.afterLteThreeGServicePlanCode = afterLteThreeGServicePlanCode;
        this.beforeLteThreeGServicePlanCode = beforeLteThreeGServicePlanCode;
        this.lteThreeGServicePlanSwitchingDateTime = lteThreeGServicePlanSwitchingDateTime;
        this.connectControlPolicy = connectControlPolicy;
        this.lineApplicationDestinationPolicyId = lineApplicationDestinationPolicyId;
        this.lteThreeGEngagementNumber = lteThreeGEngagementNumber;
    }

    public static ConnectAuthContractUserInput createCorporationVpnOn(
            LteThreeGConnectAuthUserEntity connectAuthEntity,
            ApplicationDestinationPolicyId applicationDestinationPolicyId,
            RequestEventTime requestEventTime){

        ValidVpnCorporation validVpnCorporation = (ValidVpnCorporation)connectAuthEntity.getVpnCorporation();
        return new ConnectAuthContractUserInput(
                connectAuthEntity.getUserId(),
                requestEventTime.get_yyyyMMddHHmmss(),
                applicationDestinationPolicyId,
                connectAuthEntity.getValidMsisdn().getInternationalMsisdn(),
                connectAuthEntity.getConnectType().getLteThreeGServicePlan().getAfterLteThreeGServicePlanCode(),
                connectAuthEntity.getConnectType().getLteThreeGServicePlan().getLteThreeGServicePlanChange().getBeforeServicePlanCodeScenarioValue(),
                connectAuthEntity.getConnectType().getLteThreeGServicePlan().getLteThreeGServicePlanChange().getSwitchingDateScenarioValue(),
                connectAuthEntity.getConnectControlPolicy().getScenarioValue(),
                applicationDestinationPolicyId.getLineApplicationDestinationPolicyId(),
                connectAuthEntity.getLteThreeGEngagementNumber(),
                validVpnCorporation.getVpnOptionCode(),
                validVpnCorporation.getCorporationNumber().getConnectAuthScenarioValue()
        );
    }

    public static ConnectAuthContractUserInput createPersonOrCorporationVpnOff(
            LteThreeGConnectAuthUserEntity connectAuthEntity,
            ApplicationDestinationPolicyId applicationDestinationPolicyId,
            RequestEventTime requestEventTime
    ){

        return new ConnectAuthContractUserInput(
                connectAuthEntity.getUserId(),
                requestEventTime.get_yyyyMMddHHmmss(),
                applicationDestinationPolicyId,
                connectAuthEntity.getValidMsisdn().getInternationalMsisdn(),
                connectAuthEntity.getConnectType().getLteThreeGServicePlan().getAfterLteThreeGServicePlanCode(),
                connectAuthEntity.getConnectType().getLteThreeGServicePlan().getLteThreeGServicePlanChange().getBeforeServicePlanCodeScenarioValue(),
                connectAuthEntity.getConnectType().getLteThreeGServicePlan().getLteThreeGServicePlanChange().getSwitchingDateScenarioValue(),
                connectAuthEntity.getConnectControlPolicy().getScenarioValue(),
                applicationDestinationPolicyId.getLineApplicationDestinationPolicyId(),
                connectAuthEntity.getLteThreeGEngagementNumber()
        );
    }


}
