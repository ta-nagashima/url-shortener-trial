package jp.co.biglobe.isp.mobile.voiceoption.datasource.identification;

import jp.co.biglobe.isp.mobile.voiceoption.datasource.identification.identificationreceiptnumber.IdentificationReceiptNumberQueryMapper;
import jp.co.biglobe.isp.mobile.voiceoption.domain.identification.*;
import jp.co.biglobe.isp.mobile.voiceoption.domain.voiceengagement.VoiceEngagementNumber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class IdentificationFactoryDb implements IdentificationFactory {

    @Autowired
    IdentificationReceiptNumberQueryMapper identificationReceiptNumberQueryMapper;

    @Override
    public ValidIdentification create(
            IdentificationInitialRequestData identificationInitialRequestData,
            VoiceEngagementNumber voiceEngagementNumber) {

        IdentificationReceiptNumber identificationReceiptNumber = createIdentificationReceiptNumber();

        return ValidIdentification.create(
                identificationReceiptNumber,
                identificationInitialRequestData,
                voiceEngagementNumber
        );

    }

    @Override
    public ValidIdentification createInitStatusForVoiceClerkRequestRequested(
            IdentificationInitialRequestDataForVoiceClerkRequestUnnecessary identificationInitialRequestDataForVoiceClerkRequestUnnecessary,
            VoiceEngagementNumber voiceEngagementNumber) {

        IdentificationReceiptNumber identificationReceiptNumber = createIdentificationReceiptNumber();

        return ValidIdentification.createDocumentWaitingVoiceClerkRequestRequested(
                identificationReceiptNumber,
                identificationInitialRequestDataForVoiceClerkRequestUnnecessary,
                voiceEngagementNumber
        );

    }

    @Override
    public ValidIdentification createOKForVoiceClerkRequestRequested(
            IdentificationInitialRequestDataForVoiceClerkRequestUnnecessary identificationInitialRequestDataForVoiceClerkRequestUnnecessary,
            VoiceEngagementNumber voiceEngagementNumber) {

        IdentificationReceiptNumber identificationReceiptNumber = createIdentificationReceiptNumber();

        return ValidIdentification.createOKVoiceClerkRequestUnnecessary(
                identificationReceiptNumber,
                identificationInitialRequestDataForVoiceClerkRequestUnnecessary,
                voiceEngagementNumber
        );

    }

    @Override
    public ValidIdentification createOKForVoiceClerkRequestRequested(IdentificationInitialRequestData identificationInitialRequestData, VoiceEngagementNumber voiceEngagementNumber) {

        IdentificationReceiptNumber identificationReceiptNumber = createIdentificationReceiptNumber();

        return ValidIdentification.createOKVoiceClerkRequestUnnecessary(
                identificationReceiptNumber,
                identificationInitialRequestData,
                voiceEngagementNumber
        );
    }

    private IdentificationReceiptNumber createIdentificationReceiptNumber() {
        String serialNumber = identificationReceiptNumberQueryMapper.create();
        return IdentificationReceiptNumber.createIdentificationReceiptNumber(serialNumber);
    }

}
