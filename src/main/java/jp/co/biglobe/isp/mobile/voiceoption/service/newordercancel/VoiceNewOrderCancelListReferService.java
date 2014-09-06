package jp.co.biglobe.isp.mobile.voiceoption.service.newordercancel;

import jp.co.biglobe.isp.mobile.ltethreeg.domain.ltethreegengagement.LteThreeGEngagementRepository;
import jp.co.biglobe.isp.mobile.ltethreeg.domain.ltethreegengagement.ValidLteThreeGEngagementEntity;
import jp.co.biglobe.isp.mobile.voiceoption.domain.container.VoiceNewOrderCancelListReferContainer;
import jp.co.biglobe.isp.mobile.voiceoption.domain.identification.IdentificationRepository;
import jp.co.biglobe.isp.mobile.voiceoption.domain.identification.ValidIdentification;
import jp.co.biglobe.isp.mobile.voiceoption.domain.voiceengagement.ValidVoiceEngagement;
import jp.co.biglobe.isp.mobile.voiceoption.domain.voiceengagement.VoiceEngagementNumber;
import jp.co.biglobe.isp.mobile.voiceoption.domain.voiceengagement.VoiceEngagementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VoiceNewOrderCancelListReferService {

    @Autowired
    private LteThreeGEngagementRepository lteThreeGEngagementRepository;

    @Autowired
    private IdentificationRepository identificationRepository;

    @Autowired
    private VoiceEngagementRepository voiceEngagementRepository;

    public VoiceNewOrderCancelListReferContainer refer(VoiceEngagementNumber voiceEngagementNumber) {

        ValidVoiceEngagement validVoiceEngagement
                = voiceEngagementRepository.findByVoiceEngagementNumberForValid(voiceEngagementNumber);

        ValidIdentification validIdentification
                = identificationRepository.findByLteThreeGEngagementNumberForValid(
                validVoiceEngagement.getVoiceEngagementLinkage().getLteThreeGEngagementNumber());

        ValidLteThreeGEngagementEntity lteThreeGEngagement
                = lteThreeGEngagementRepository.findByLteThreeGEngagementNumberForValidForEnable(
                validVoiceEngagement.getVoiceEngagementLinkage().getLteThreeGEngagementNumber());

        ValidVoiceEngagement outputted = validVoiceEngagement.outputCancelList(validIdentification);

        voiceEngagementRepository.update(outputted);

        return new VoiceNewOrderCancelListReferContainer(outputted, validIdentification, lteThreeGEngagement);
    }
}
