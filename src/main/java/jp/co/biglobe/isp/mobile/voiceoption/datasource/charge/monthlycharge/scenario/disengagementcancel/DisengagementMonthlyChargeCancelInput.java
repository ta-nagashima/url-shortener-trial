package jp.co.biglobe.isp.mobile.voiceoption.datasource.charge.monthlycharge.scenario.disengagementcancel;

import jp.co.biglobe.isp.auth.domain.operatornouser.DefaultOperatorId;
import jp.co.biglobe.isp.auth.domain.user.UserId;
import jp.co.biglobe.lib.publication.date.ForeverDate;
import jp.co.biglobe.isp.mobile.voiceoption.domain.charge.monthlycharge.VoiceMonthlyItemCode;
import jp.co.biglobe.isp.mobile.voiceoption.domain.voiceengagement.VoiceEngagementNumber;
import jp.co.biglobe.lib.publication.scenario.annotation.Mapping;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class DisengagementMonthlyChargeCancelInput {

    // 操作者ID（SYSTEM固定）
    @Mapping("sosaid")
    private final DefaultOperatorId operatorId = new DefaultOperatorId();

    // 被操作者ID
    @Mapping("hi_sosaid")
    private UserId userId;

    //商品グループコード
    @Mapping("shohin_gc")
    private final VoiceMonthlyItemCode voiceMonthlyItemCode = new VoiceMonthlyItemCode();

    //APサーバ固有情報１(Wi-Fi専用ID)
    @Mapping("apsv_own_info[1]")
    private VoiceEngagementNumber voiceEngagementNumber;

    //契約終了日時
    @Mapping("keiyaku_ymdh_end")
    private final String keiyaku_ymdh_end = new ForeverDate().get_yyyyMMddHHmmss();

    //契約期限日
    @Mapping("kyk_kigen_ymd")
    private final String kyk_kigen_ymd = new ForeverDate().get_yyyyMMddHHmmss();

    //契約状態コード
    @Mapping("kyk_sts_c")
    private final String kyk_sts_c = "10";

    //強制更新フラグ
    @Mapping("shnkyk_hnk_yoyaku_umuflg")
    private final String shnkyk_hnk_yoyaku_umuflg = "1";

    //停止解約理由コード(解約)
    @Mapping("teikaiyaku_cd_kai")
    private final String teikaiyaku_cd_kai = "07";



}
