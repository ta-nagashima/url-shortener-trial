package jp.co.biglobe.isp.mobile.voiceoption.service.identification;

import jp.co.biglobe.isp.auth.domain.user.UserId;
import jp.co.biglobe.isp.mobile.ltethreeg.domain.ltethreegengagement.LteThreeGEngagementEntity;
import jp.co.biglobe.isp.mobile.ltethreeg.domain.ltethreegengagement.LteThreeGEngagementRepository;
import jp.co.biglobe.isp.mobile.voiceoption.domain.identification.Identification;
import jp.co.biglobe.isp.mobile.voiceoption.domain.identification.IdentificationRepository;
import jp.co.biglobe.isp.mobile.voiceoption.domain.identification.upload.UploadManagement;
import jp.co.biglobe.isp.mobile.voiceoption.domain.mnpin.MnpIn;
import jp.co.biglobe.isp.mobile.voiceoption.domain.mnpin.MnpInRepository;
import jp.co.biglobe.isp.mobile.voiceoption.domain.policy.UploadCheckStatus;
import jp.co.biglobe.isp.mobile.voiceoption.domain.voiceengagement.VoiceEngagement;
import jp.co.biglobe.isp.mobile.voiceoption.domain.voiceengagement.VoiceEngagementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IdentificationUploadCheckService {

    @Autowired
    LteThreeGEngagementRepository lteThreeGEngagementRepository;

    @Autowired
    private IdentificationRepository identificationRepository;

    @Autowired
    private VoiceEngagementRepository voiceEngagementRepository;

    @Autowired
    private MnpInRepository mnpInRepository;

    public UploadCheckStatus check(UserId userId) {

        LteThreeGEngagementEntity lteThreeGEngagementEntity = lteThreeGEngagementRepository.findByUserId(userId);

        Identification identification = identificationRepository.findByLteThreeGEngagementNumber(lteThreeGEngagementEntity);

        VoiceEngagement voiceEngagement = voiceEngagementRepository.findByVoiceEngagementNumber(identification);

        MnpIn mnpIn = mnpInRepository.findByVoiceEngagementNumber(voiceEngagement);

        UploadManagement uploadManagement = new UploadManagement();
        return uploadManagement.verify(lteThreeGEngagementEntity, identification, voiceEngagement, mnpIn);

    }

}