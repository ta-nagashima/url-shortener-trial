package jp.co.biglobe.isp.mobile.voiceoption.service.neworder;

import jp.co.biglobe.isp.mobile.voiceoption.domain.identification.*;
import jp.co.biglobe.isp.mobile.voiceoption.domain.voiceengagement.ValidVoiceEngagement;
import jp.co.biglobe.isp.mobile.voiceoption.domain.voiceengagement.VoiceEngagementInitialRequestData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * MNPなしSIMなし新規申込
 *
 * 以下のAPIから利用
 * - MNPなし後からスキームSIMなし新規申込
 * - MNPなし提携スマホWebSIMなし新規申込
 */
@Service
public class VoiceNewOrderNoMnpAfterBuyNoSimApplyService {

    @Autowired
    private VoiceEngagementApplyService voiceEngagementApplyService;

    @Autowired
    private IdentificationApplyService identificationApplyService;

    public IdentificationReceiptNumber newApply(
            VoiceEngagementInitialRequestData voiceEngagementInitialRequestData,
            IdentificationInitialRequestDataForVoiceClerkRequestUnnecessary identificationInitialRequestDataForVoiceClerkRequestUnnecessary) {

        ValidVoiceEngagement validVoiceEngagement = voiceEngagementApplyService.newApply(voiceEngagementInitialRequestData);

        ValidIdentification validIdentification = identificationApplyService.newApplyInitStatusForVoiceClerkRequestUnnecessary(
                identificationInitialRequestDataForVoiceClerkRequestUnnecessary, validVoiceEngagement);

        return validIdentification.getIdentificationReceiptNumber();
    }

}
