package jp.co.biglobe.isp.mobile.voiceoption.service.neworderprovisionalresume;

import jp.co.biglobe.isp.mobile.ltethreeg.domain.ltethreegengagement.LteThreeGEngagementNumber;
import jp.co.biglobe.isp.mobile.voiceoption.domain.identification.Identification;
import jp.co.biglobe.isp.mobile.voiceoption.domain.identification.IdentificationReceiptNumber;
import jp.co.biglobe.isp.mobile.voiceoption.domain.identification.IdentificationRepository;
import jp.co.biglobe.isp.mobile.voiceoption.domain.identification.ValidIdentification;
import jp.co.biglobe.isp.mobile.voiceoption.domain.voiceengagement.VoiceEngagementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VoiceProvisionalResumeNoMnpService {
    @Autowired
    private IdentificationRepository identificationRepository;

    @Autowired
    private VoiceEngagementRepository voiceEngagementRepository;

    public IdentificationReceiptNumber update(LteThreeGEngagementNumber lteThreeGEngagementNumber){

        ValidIdentification validIdentification = identificationRepository.findByLteThreeGEngagementNumberForUpdate(lteThreeGEngagementNumber);
        ValidIdentification askedValidIdentification = validIdentification.changeVoiceClerkRequest();

        identificationRepository.update(askedValidIdentification);

        return askedValidIdentification.getIdentificationReceiptNumber();

    }

}
