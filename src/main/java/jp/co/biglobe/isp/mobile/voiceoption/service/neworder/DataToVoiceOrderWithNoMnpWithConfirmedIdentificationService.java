package jp.co.biglobe.isp.mobile.voiceoption.service.neworder;

import jp.co.biglobe.isp.mobile.voiceoption.domain.identification.IdentificationInitialRequestDataForVoiceClerkRequestUnnecessary;
import jp.co.biglobe.isp.mobile.voiceoption.domain.identification.IdentificationReceiptNumber;
import jp.co.biglobe.isp.mobile.voiceoption.domain.identification.ValidIdentification;
import jp.co.biglobe.isp.mobile.voiceoption.domain.voiceengagement.ValidVoiceEngagement;
import jp.co.biglobe.isp.mobile.voiceoption.domain.voiceengagement.VoiceEngagementInitialRequestData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DataToVoiceOrderWithNoMnpWithConfirmedIdentificationService {

    @Autowired
    private DataToVoiceEngagementApplyService dataToVoiceEngagementApplyService;

    @Autowired
    private IdentificationApplyService identificationApplyService;


    public IdentificationReceiptNumber newApply(
            VoiceEngagementInitialRequestData voiceEngagementInitialRequestData,
            IdentificationInitialRequestDataForVoiceClerkRequestUnnecessary identificationInitialRequestDataForVoiceClerkRequestUnnecessary) {
        ValidVoiceEngagement validVoiceEngagement = dataToVoiceEngagementApplyService.newApply(voiceEngagementInitialRequestData);

        ValidIdentification validIdentification = identificationApplyService.newApplyOKForVoiceClerkRequestUnnecessary(
                identificationInitialRequestDataForVoiceClerkRequestUnnecessary, validVoiceEngagement
        );
        return validIdentification.getIdentificationReceiptNumber();
    }
}
