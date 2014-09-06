package jp.co.biglobe.isp.mobile.voiceoption.domain.view.voiceengagementcountrefer;

import jp.co.biglobe.isp.mobile.ltethreeg.domain.ltethreegengagement.LteThreeGEngagementNumber;

public interface VoiceEngagementCountContainerRepository {

    public VoiceEngagementCountContainer findByLteThreeGEngagementNumberForCount(LteThreeGEngagementNumber lteThreeGEngagementNumber);

}
