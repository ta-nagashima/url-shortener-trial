package jp.co.biglobe.isp.mobile.voiceoption.service.neworder;

import jp.co.biglobe.isp.mobile.voiceoption.domain.identification.*;
import jp.co.biglobe.isp.mobile.voiceoption.domain.voiceengagement.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * MNPなし提携スマホ店頭SIMあり新規申込
 *
 * 以下のAPIから利用
 * - MNPなし提携スマホ店頭SIMあり新規申込
 * - MNPなし提携スマホ郵送SIMなし新規申込
 */
@Service
public class VoiceNewOrderNoMnpAlliancePhoneCounterWithSimApplyService {

    @Autowired
    private VoiceEngagementApplyService voiceEngagementApplyService;

    @Autowired
    private IdentificationApplyService identificationApplyService;

    public IdentificationReceiptNumber newOrder(
            VoiceEngagementInitialRequestData voiceEngagementInitialRequestData,
            IdentificationInitialRequestDataForVoiceClerkRequestUnnecessary identificationInitialRequestDataForVoiceClerkRequestUnnecessary) {

        //音声オプション契約の登録
        ValidVoiceEngagement validVoiceEngagement = voiceEngagementApplyService.newApply(voiceEngagementInitialRequestData);


        // 本人確認エンティティの追加
        ValidIdentification validIdentification = identificationApplyService.newApplyOKForVoiceClerkRequestUnnecessary(
                identificationInitialRequestDataForVoiceClerkRequestUnnecessary, validVoiceEngagement);

        return validIdentification.getIdentificationReceiptNumber();
    }
}
