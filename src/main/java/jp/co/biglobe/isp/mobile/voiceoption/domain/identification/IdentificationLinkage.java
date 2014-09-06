package jp.co.biglobe.isp.mobile.voiceoption.domain.identification;

import jp.co.biglobe.isp.auth.domain.user.UserId;
import jp.co.biglobe.isp.mobile.ltethreeg.domain.ltethreegengagement.LteThreeGEngagementNumber;
import jp.co.biglobe.isp.mobile.voiceoption.domain.voiceengagement.VoiceEngagementNumber;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@ToString(includeFieldNames=false)
@EqualsAndHashCode
@AllArgsConstructor
class IdentificationLinkage {

    @Getter
    private final UserId userId;

    @Getter
    private final LteThreeGEngagementNumber lteThreeGEngagementNumber;

    @Getter
    private final VoiceEngagementNumber voiceEngagementNumber;

}
