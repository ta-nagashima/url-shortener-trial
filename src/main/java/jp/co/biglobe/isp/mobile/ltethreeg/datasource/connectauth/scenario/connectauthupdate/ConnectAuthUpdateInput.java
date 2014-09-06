package jp.co.biglobe.isp.mobile.ltethreeg.datasource.connectauth.scenario.connectauthupdate;

import jp.co.biglobe.isp.auth.domain.operatornouser.DefaultOperatorId;
import jp.co.biglobe.isp.auth.domain.user.UserId;
import jp.co.biglobe.isp.mobile.biglobemember.domain.corporation.CorporationNumberForScenario;
import jp.co.biglobe.isp.mobile.ltethreeg.datasource.connectauth.scenario.connectauthdelete.scenarioparam.*;
import jp.co.biglobe.isp.mobile.ltethreeg.domain.connectauth.ConnectAuthItemCode;
import jp.co.biglobe.isp.mobile.ltethreeg.domain.ltethreegengagement.LteThreeGEngagementNumber;
import jp.co.biglobe.isp.mobile.ltethreeg.domain.ltethreegengagement.serviceplan.LteThreeGServicePlanCode;
import jp.co.biglobe.isp.mobile.ltethreeg.domain.ltethreegengagement.serviceplan.LteThreeGServicePlanSwitchingDateTime;
import jp.co.biglobe.isp.mobile.ltethreeg.option.vpn.domain.engagement.VpnOptionCode;
import jp.co.biglobe.lib.publication.scenario.annotation.Mapping;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ConnectAuthUpdateInput {

    //操作者IDタイプ
    @Mapping("id_type")
    private final String idType = "1";

    //出力データ区分(出力項目のみ出力、入力項目は出力しない)
    @Mapping("output_type")
    private final String outputType = "1";

    //パスワード変換区分
    @Mapping("pwd_enc_type")
    private final String pwdEncType = "1";

    //担当者パスワード
//    @Mapping("taotopwd")
//    private final String tantopwd = "50415353303030";

    //チェックレベル
    @Mapping("op_auth_type")
    private final String opAuthType = "0000000000";

    //強制更新フラグ
    @Mapping("shnkyk_hnk_yoyaku_umuflg")
    private final String shnkykHnkYoyakuUmuflg = "1";

    //利用数量更新ログ発行対処F（0：発行しない）
    @Mapping("riyousuryo_updlog_flg")
    private final String riyousuryoUpdlogFlg = "0";

    //商品G
    @Mapping("shohin_gc")
    private final ConnectAuthItemCode connectAuthItemCode = new ConnectAuthItemCode();

    // 操作者ID（SYSTEM固定）
    @Mapping("sosaid")
    private final UserId sosaid;

    //被操作者ID
    @Mapping("hi_sosaid")
    private final UserId hi_sosaid;

    //利用口番号
    @Mapping("riyoguchi_no")
    private final RiyoguchiNo riyoguchiNo;

    //契約枝番
    @Mapping("keiyaku_edano")
    private final KeiyakuEdano keiyakuEdano;

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
    private final String bandControlLimitPolicy;

    //契約番号
    @Mapping("hanyo_info6")
    private final LteThreeGEngagementNumber lteThreeGEngagementNumber;

}
