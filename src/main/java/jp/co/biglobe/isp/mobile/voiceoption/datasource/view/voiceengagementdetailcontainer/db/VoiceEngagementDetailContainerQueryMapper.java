package jp.co.biglobe.isp.mobile.voiceoption.datasource.view.voiceengagementdetailcontainer.db;

import jp.co.biglobe.isp.mobile.ltethreeg.domain.ltethreegengagement.LteThreeGEngagementNumber;
import jp.co.biglobe.isp.mobile.voiceoption.domain.view.voiceengagementdetailcontainer.VoiceEngagementDetailContainer;
import jp.co.biglobe.isp.mobile.voiceoption.domain.view.voiceengagementdetailcontainer.VoiceEngagementDetailContainerList;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface VoiceEngagementDetailContainerQueryMapper {

    public List<VoiceEngagementDetailContainer> findByLteThreeGEngagementNumberForAll
            (@Param("lteThreeGEngagementNumber") LteThreeGEngagementNumber lteThreeGEngagementNumber);


}
