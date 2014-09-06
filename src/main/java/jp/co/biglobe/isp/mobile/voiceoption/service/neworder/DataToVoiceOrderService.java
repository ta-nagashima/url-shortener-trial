package jp.co.biglobe.isp.mobile.voiceoption.service.neworder;

import jp.co.biglobe.isp.mobile.voiceoption.domain.identification.IdentificationInitialRequestData;
import jp.co.biglobe.isp.mobile.voiceoption.domain.identification.IdentificationReceiptNumber;
import jp.co.biglobe.isp.mobile.voiceoption.domain.identification.ValidIdentification;
import jp.co.biglobe.isp.mobile.voiceoption.domain.voiceengagement.ValidVoiceEngagement;
import jp.co.biglobe.isp.mobile.voiceoption.domain.voiceengagement.VoiceEngagementInitialRequestData;
import jp.co.biglobe.isp.mobile.voiceoption.service.neworder.DataToVoiceEngagementApplyService;
import jp.co.biglobe.isp.mobile.voiceoption.service.neworder.IdentificationApplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * MNPなし新規申込
 */
@Service
public class DataToVoiceOrderService {

    @Autowired
    private DataToVoiceEngagementApplyService dataToVoiceEngagementApplyService;

    @Autowired
    private IdentificationApplyService identificationApplyService;

    public IdentificationReceiptNumber newApply(
            VoiceEngagementInitialRequestData voiceEngagementInitialRequestData,
            IdentificationInitialRequestData identificationInitialRequestData) {

        ValidVoiceEngagement validVoiceEngagement = dataToVoiceEngagementApplyService.newApply(voiceEngagementInitialRequestData);

        ValidIdentification validIdentification = identificationApplyService.newApply(
                identificationInitialRequestData, validVoiceEngagement);

        return validIdentification.getIdentificationReceiptNumber();

    }

}
