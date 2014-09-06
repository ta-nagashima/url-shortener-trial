package jp.co.biglobe.isp.mobile.voiceoption.datasource.charge.monthlycharge.scenario.engagement;

import jp.co.biglobe.isp.auth.domain.operatornouser.DefaultOperatorId;
import jp.co.biglobe.isp.auth.domain.operatornouser.OperatorId;
import jp.co.biglobe.isp.auth.domain.user.UserId;
import jp.co.biglobe.isp.mobile.voiceoption.domain.charge.monthlycharge.VoiceMonthlyItemCode;
import jp.co.biglobe.isp.mobile.voiceoption.domain.voiceengagement.VoiceEngagementNumber;
import jp.co.biglobe.lib.publication.scenario.annotation.Mapping;
import jp.co.biglobe.lib.publication.scenario.annotation.MappingObject;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class EngagementMonthlyChargeInput {

    /**
     * 【注意事項】
     * 「認証なし」を使っているが、sosaidやhi_sosaidなどのインパラは必要。
     * 　↑このあたりの情報は、API仕様書の「3-1.入力パラメータ（認証なし）」の参照のこと
     */

    /**
     * 契約開始日時（keiyaku_ymdh_str）は設定しない
     */

    @MappingObject
    private final EngagementInput engagementInput = new EngagementInput();

    // 操作者ID（SYSTEM固定）
    @Mapping("sosaid")
    private final DefaultOperatorId operatorId = new DefaultOperatorId();

    // 被操作者ID
    @Mapping("hi_sosaid")
    private UserId userId;

    //商品グループコード
    @Mapping("shohin_gc")
    private final VoiceMonthlyItemCode voiceMonthlyItemCode = new VoiceMonthlyItemCode();

    //契約個数
    @Mapping("keiyaku_num")
    private final String keiyaku_num = "1";

    //申込日時
    @Mapping("moushikomi_ymdh")
    private String moushikomi_ymdh;

    //APサーバキー
    @Mapping("apsv_key")
    private final String apsv_key = "LTE";

    //APサーバ固有情報１(音声オプション契約番号)
    @Mapping("apsv_own_info[1]")
    private VoiceEngagementNumber apsv_own_info1;


}
