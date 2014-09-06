package jp.co.biglobe.isp.mobile.voiceoption.service.neworderprovisionalresume;

import jp.co.biglobe.isp.mobile.ltethreeg.domain.ltethreegengagement.LteThreeGEngagementNumber;
import jp.co.biglobe.isp.mobile.voiceoption.domain.identification.IdentificationReceiptNumber;
import jp.co.biglobe.isp.mobile.voiceoption.domain.identification.IdentificationRepository;
import jp.co.biglobe.isp.mobile.voiceoption.domain.identification.ValidIdentification;
import jp.co.biglobe.isp.mobile.voiceoption.domain.mnpin.MnpInRepository;
import jp.co.biglobe.isp.mobile.voiceoption.domain.mnpin.ValidMnpIn;
import jp.co.biglobe.isp.mobile.voiceoption.domain.mnppersonalinfo.ValidMnpPersonalInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 仮受付復旧_MNPあり
 */

@Service
public class VoiceProvisionalResumeWithMnpService {

    @Autowired
    private IdentificationRepository identificationRepository;

    @Autowired
    private MnpInRepository mnpInRepository;

    public IdentificationReceiptNumber update(LteThreeGEngagementNumber lteThreeGEngagementNumber, ValidMnpPersonalInfo validMnpPersonalInfo) {

        ValidIdentification validIdentification = identificationRepository.findByLteThreeGEngagementNumberForUpdate(lteThreeGEngagementNumber);
        ValidIdentification askedValidIdentification = validIdentification.changeVoiceClerkRequest();

        ValidMnpIn validMnpIn = mnpInRepository.findByVoiceEngagementNumberForUpdate(validIdentification.getVoiceEngagementNumber());
        ValidMnpIn askedMnpIn = validMnpIn.inputMnpInPersonalItems(validMnpPersonalInfo);

        identificationRepository.update(askedValidIdentification);
        mnpInRepository.update(askedMnpIn);

        return askedValidIdentification.getIdentificationReceiptNumber();

    }
}
