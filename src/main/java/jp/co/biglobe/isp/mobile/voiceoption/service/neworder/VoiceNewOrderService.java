package jp.co.biglobe.isp.mobile.voiceoption.service.neworder;

import jp.co.biglobe.isp.mobile.voiceoption.domain.identification.IdentificationInitialRequestData;
import jp.co.biglobe.isp.mobile.voiceoption.domain.identification.IdentificationReceiptNumber;
import jp.co.biglobe.isp.mobile.voiceoption.domain.identification.ValidIdentification;
import jp.co.biglobe.isp.mobile.voiceoption.domain.voiceengagement.ValidVoiceEngagement;
import jp.co.biglobe.isp.mobile.voiceoption.domain.voiceengagement.VoiceEngagementInitialRequestData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * MNPなし新規申込
 */
@Service
public class VoiceNewOrderService {

    @Autowired
    private VoiceEngagementApplyService voiceEngagementApplyService;

    @Autowired
    private IdentificationApplyService identificationApplyService;

    public IdentificationReceiptNumber newApply(
            VoiceEngagementInitialRequestData voiceEngagementInitialRequestData,
            IdentificationInitialRequestData identificationInitialRequestData) {

        ValidVoiceEngagement validVoiceEngagement = voiceEngagementApplyService.newApply(voiceEngagementInitialRequestData);

        ValidIdentification validIdentification = identificationApplyService.newApply(
                identificationInitialRequestData, validVoiceEngagement);

        return validIdentification.getIdentificationReceiptNumber();

    }

}
