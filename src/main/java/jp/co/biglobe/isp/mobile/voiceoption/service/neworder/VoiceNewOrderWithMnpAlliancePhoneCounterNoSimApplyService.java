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
 * MNPあり提携スマホ店頭SIMなし新規申込
 */

@Service
public class VoiceNewOrderWithMnpAlliancePhoneCounterNoSimApplyService {

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

        // 契約オプションの登録
        ValidVoiceEngagement validVoiceEngagement = voiceEngagementApplyService.newApply(voiceEngagementInitialRequestData);

        // 本人確認エンティティの追加
        ValidIdentification validIdentification = identificationApplyService.newApplyOKForVoiceClerkRequestUnnecessary(
                identificationInitialRequestDataForVoiceClerkRequestUnnecessary, validVoiceEngagement);

        // 転入情報の登録
        mnpInApplyService.newApply(mnpInInitialRequestData, validVoiceEngagement);

        return validIdentification.getIdentificationReceiptNumber();
    }

}
