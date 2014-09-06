package jp.co.biglobe.isp.mobile.voiceoption.service.neworder;

import jp.co.biglobe.isp.mobile.voiceoption.domain.identification.IdentificationInitialRequestData;
import jp.co.biglobe.isp.mobile.voiceoption.domain.identification.IdentificationReceiptNumber;
import jp.co.biglobe.isp.mobile.voiceoption.domain.identification.ValidIdentification;
import jp.co.biglobe.isp.mobile.voiceoption.domain.mnpin.MnpInInitialRequestData;
import jp.co.biglobe.isp.mobile.voiceoption.domain.voiceengagement.ValidVoiceEngagement;
import jp.co.biglobe.isp.mobile.voiceoption.domain.voiceengagement.VoiceEngagementInitialRequestData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * MNPあり新規申込
 */
@Service
public class DataToVoiceOrderWithMnpApplyService {

    @Autowired
    private DataToVoiceEngagementApplyService dataToVoiceEngagementApplyService;

    @Autowired
    private IdentificationApplyService identificationApplyService;

    @Autowired
    private MnpInApplyService mnpInApplyService;

    public IdentificationReceiptNumber newApply(
            VoiceEngagementInitialRequestData voiceEngagementInitialRequestData,
            IdentificationInitialRequestData identificationInitialRequestData,
            MnpInInitialRequestData mnpInInitialRequestData) {

        ValidVoiceEngagement validVoiceEngagement = dataToVoiceEngagementApplyService.newApply(voiceEngagementInitialRequestData);

        ValidIdentification validIdentification = identificationApplyService.newApply(
                identificationInitialRequestData, validVoiceEngagement);

        mnpInApplyService.newApply(mnpInInitialRequestData, validVoiceEngagement);

        return validIdentification.getIdentificationReceiptNumber();
    }
}
