package jp.co.biglobe.isp.mobile.voiceoption.datasource.view.voiceengagementdetailcontainer;

import jp.co.biglobe.isp.mobile.ltethreeg.domain.ltethreegengagement.LteThreeGEngagementNumber;
import jp.co.biglobe.isp.mobile.voiceoption.datasource.view.voiceengagementdetailcontainer.db.VoiceEngagementDetailContainerQueryMapper;
import jp.co.biglobe.isp.mobile.voiceoption.domain.view.voiceengagementdetailcontainer.VoiceEngagementDetailContainer;
import jp.co.biglobe.isp.mobile.voiceoption.domain.view.voiceengagementdetailcontainer.VoiceEngagementDetailContainerList;
import jp.co.biglobe.isp.mobile.voiceoption.domain.view.voiceengagementdetailcontainer.VoiceEngagementDetailContainerRepository;
import jp.co.biglobe.lib.plugin.event.RequestEventProcess;
import jp.co.biglobe.lib.plugin.event.RequestEventTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class VoiceEngagementDetailContainerRepositoryDb implements VoiceEngagementDetailContainerRepository {


    @Autowired
    private VoiceEngagementDetailContainerQueryMapper voiceEngagementDetailContainerQueryMapper;

    @Autowired
    private RequestEventTime requestEventTime;

    @Autowired
    private RequestEventProcess requestEventProcess;


    @Override
    public VoiceEngagementDetailContainerList findAllByLteThreeGEngagementNumber
            (LteThreeGEngagementNumber lteThreeGEngagementNumber){

        List<VoiceEngagementDetailContainer> voiceEngagementDetailContainerList =
                voiceEngagementDetailContainerQueryMapper.findByLteThreeGEngagementNumberForAll(lteThreeGEngagementNumber);

        return new VoiceEngagementDetailContainerList(voiceEngagementDetailContainerList);
    }




}
