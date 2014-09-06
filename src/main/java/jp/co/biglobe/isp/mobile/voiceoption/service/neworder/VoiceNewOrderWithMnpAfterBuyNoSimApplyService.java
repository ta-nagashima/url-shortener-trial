package jp.co.biglobe.isp.mobile.voiceoption.service.neworder;

import jp.co.biglobe.isp.mobile.voiceoption.domain.identification.IdentificationInitialRequestDataForVoiceClerkRequestUnnecessary;
import jp.co.biglobe.isp.mobile.voiceoption.domain.identification.IdentificationReceiptNumber;
import jp.co.biglobe.isp.mobile.voiceoption.domain.identification.ValidIdentification;
import jp.co.biglobe.isp.mobile.voiceoption.domain.mnpin.MnpInInitialRequestData;
import jp.co.biglobe.isp.mobile.voiceoption.domain.voiceengagement.ValidVoiceEngagement;
import jp.co.biglobe.isp.mobile.voiceoption.domain.voiceengagement.VoiceEngagementInitialRequestData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * MNPありSIMなし新規申込
 *
 * 以下のAPIから利用
 * - MNPあり後からスキームSIMなし新規申込
 * - MNPあり提携スマホWebSIMなし新規申込
 */
@Service
public class VoiceNewOrderWithMnpAfterBuyNoSimApplyService {

    @Autowired
    private VoiceEngagementApplyService voiceEngagementApplyService;

    @Autowired
    private IdentificationApplyService identificationApplyService;

    @Autowired
    private MnpInApplyService mnpInApplyService;

    public IdentificationReceiptNumber newApply(
            VoiceEngagementInitialRequestData voiceEngagementInitialRequestData,
            IdentificationInitialRequestDataForVoiceClerkRequestUnnecessary identificationInitialRequestDataForVoiceClerkRequestUnnecessary,
            MnpInInitialRequestData mnpInInitialRequestData) {

        ValidVoiceEngagement validVoiceEngagement = voiceEngagementApplyService.newApply(voiceEngagementInitialRequestData);

        ValidIdentification validIdentification = identificationApplyService.newApplyInitStatusForVoiceClerkRequestUnnecessary(
                identificationInitialRequestDataForVoiceClerkRequestUnnecessary, validVoiceEngagement);

        mnpInApplyService.newApply(mnpInInitialRequestData, validVoiceEngagement);

        return validIdentification.getIdentificationReceiptNumber();
    }


}
