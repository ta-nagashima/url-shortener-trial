package jp.co.biglobe.isp.mobile.voiceoption.datasource.charge.disengagementcharge.register;

import jp.co.biglobe.isp.auth.domain.operatornouser.DefaultOperatorId;
import jp.co.biglobe.isp.auth.domain.operatornouser.OperatorId;
import jp.co.biglobe.isp.auth.domain.user.UserId;
import jp.co.biglobe.isp.mobile.voiceoption.domain.voiceengagement.VoiceEngagementNumber;
import jp.co.biglobe.isp.mobile.voiceoption.domain.voiceengagement.VoiceEngagementStartDate;
import jp.co.biglobe.lib.publication.scenario.annotation.Mapping;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class VoiceDisengagementChargeAddInput {

    // 操作者ID（SYSTEM固定）
    @Mapping("sosaid")
    private final DefaultOperatorId operatorId = new DefaultOperatorId();

    // 被操作者ID
    @Mapping("hi_sosaid")
    private UserId userId;

    // 処理モード
    @Mapping("syori_mode")
    private final String syoriMode = "1";

    // 違約金コード
    @Mapping("iyakukin_c")
    private final String iyakukinC = "LT020001";

    // 違約金コード枝版
    @Mapping("iyakukin_c_eda")
    private final String iyakukinCEda = "00000001";

    // 違約金開始日
    @Mapping("iyakukin_str_ymd")
    private VoiceEngagementStartDate iyakukinStrYmd;

    // APサーバキー
    @Mapping("apsv_key")
    private VoiceEngagementNumber apsv_key;

}
