package jp.co.biglobe.isp.mobile.voiceoption.datasource.charge.disengagementcharge.refer;

import jp.co.biglobe.isp.auth.domain.operatornouser.DefaultOperatorId;
import jp.co.biglobe.isp.auth.domain.operatornouser.OperatorId;
import jp.co.biglobe.isp.auth.domain.user.UserId;
import jp.co.biglobe.isp.mobile.voiceoption.domain.voiceengagement.VoiceEngagementNumber;
import jp.co.biglobe.lib.publication.scenario.annotation.Mapping;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class VoiceDisengagementChargeReferInput {

    // 操作者ID（SYSTEM固定）
    @Mapping("sosaid")
    private final DefaultOperatorId operatorId = new DefaultOperatorId();

    // 被操作者ID
    @Mapping("hi_sosaid")
    private UserId userId;

    // 参照モード（1:ユーザー用　2:運用者用）
    @Mapping("ref_mode")
    private final String refMode = "1";

    // ステータスコード（10:継続中 20:対象外 30:免除 40:通常完了 50:課金完了 51:課金判断中）
    // 【補足】ここでの「継続中」とは、契約解除料の期限内のものと期限を過ぎたものを含む
    @Mapping("status")
    private final String status = "10";

    // 違約金コード（音声はLT020001）
    @Mapping("iyakukin_c")
    private final String iyakukinC = "LT020001";

    // APサーバーキー：ここで音声契約番号を指定
    @Mapping("apsv_key")
    private VoiceEngagementNumber apsvKey;

    // 免除フラグ（契約解除料が免除される場合は検索結果に含まない）
    @Mapping("menjyo_f")
    private final String menjyoF = "0";

}
