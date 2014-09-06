package jp.co.biglobe.isp.mobile.voiceoption.datasource.view.voiceengagementcountcontainer.db;

import jp.co.biglobe.isp.mobile.ltethreeg.domain.ltethreegengagement.LteThreeGEngagementNumber;
import jp.co.biglobe.isp.mobile.voiceoption.domain.view.voiceengagementcountrefer.VoiceEngagementCountContainer;
import jp.co.biglobe.isp.mobile.voiceoption.domain.view.voiceengagementcountrefer.VoiceEngagementCount;
import jp.co.biglobe.isp.mobile.voiceoption.domain.view.voiceengagementcountrefer.VoiceEngagementCountContainerRepository;
import jp.co.biglobe.isp.mobile.voiceoption.domain.voiceengagement.VoiceEngagementEndDateTime;
import jp.co.biglobe.isp.mobile.voiceoption.domain.voiceengagement.VoiceEngagementStatus;
import jp.co.biglobe.lib.plugin.event.RequestEventTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class VoiceEngagementCountContainerRepositoryDb implements VoiceEngagementCountContainerRepository {

    @Autowired
    private VoiceEngagementCountContainerQueryMapper voiceEngagementCountContainerQueryMapper;

    @Autowired
    private RequestEventTime requestEventTime;

    @Override
    public VoiceEngagementCountContainer findByLteThreeGEngagementNumberForCount(LteThreeGEngagementNumber lteThreeGEngagementNumber) {

        //申込中のものを検索
        VoiceEngagementCount voiceEngagementCountOrdered =
                voiceEngagementCountContainerQueryMapper.findByLteThreeGEngagementNumberForOrderedCount(
                        lteThreeGEngagementNumber, VoiceEngagementStatus.ORDERED);

        //契約中のものを検索
        VoiceEngagementCount voiceEngagementCountEngaged =
                voiceEngagementCountContainerQueryMapper.findByLteThreeGEngagementNumberForEngagedCount(
                        lteThreeGEngagementNumber, VoiceEngagementStatus.ENGAGED);

        //解約予約中のものを検索
        java.util.Date date = requestEventTime.getDate();
        VoiceEngagementCount voiceEngagementCountDisengagedReserved =
                voiceEngagementCountContainerQueryMapper.findByLteThreeGEngagementNumberForDisengagedReservedCount(
                        lteThreeGEngagementNumber, VoiceEngagementStatus.DISENGAGED, new VoiceEngagementEndDateTime(date));

        return new VoiceEngagementCountContainer(voiceEngagementCountOrdered,voiceEngagementCountEngaged,voiceEngagementCountDisengagedReserved);

    }

}
