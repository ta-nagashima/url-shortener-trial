package jp.co.biglobe.isp.mobile.voiceoption.domain.identification;


import jp.co.biglobe.isp.auth.domain.user.UserId;
import jp.co.biglobe.isp.mobile.ltethreeg.domain.ltethreegengagement.LteThreeGEngagementNumber;
import jp.co.biglobe.isp.mobile.voiceoption.domain.voiceengagement.ValidVoiceEngagement;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

/**
 * 音声事務局への本人確認依頼が不要な場合の初期データ
 */
@ToString(includeFieldNames=false)
@EqualsAndHashCode
@AllArgsConstructor
public class IdentificationInitialRequestDataForVoiceClerkRequestUnnecessary {

    @Getter
    private UserId userId;

    @Getter
    private LteThreeGEngagementNumber lteThreeGEngagementNumber;

}
