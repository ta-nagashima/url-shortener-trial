package jp.co.biglobe.isp.mobile.voiceoption.domain.view.voiceengagementdetailcontainer;

import jp.co.biglobe.isp.mobile.ltethreeg.domain.ltethreegengagement.LteThreeGEngagementNumber;

public interface VoiceEngagementDetailContainerRepository {

    public VoiceEngagementDetailContainerList findAllByLteThreeGEngagementNumber
            (LteThreeGEngagementNumber lteThreeGEngagementNumber);
}
