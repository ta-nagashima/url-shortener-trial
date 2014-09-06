package jp.co.biglobe.isp.mobile.voiceoption.service.neworder;

import jp.co.biglobe.isp.mobile.voiceoption.domain.identification.*;
import jp.co.biglobe.isp.mobile.voiceoption.domain.voiceengagement.ValidVoiceEngagement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
class IdentificationApplyService {

    @Autowired
    private IdentificationRepository identificationRepository;

    @Autowired
    private IdentificationFactory identificationFactory;


    ValidIdentification newApply(IdentificationInitialRequestData identificationInitialRequestData,
                                        ValidVoiceEngagement validVoiceEngagement) {

        ValidIdentification validIdentification = identificationFactory.create(
                identificationInitialRequestData,
                validVoiceEngagement.getVoiceEngagementNumber());

        return register(validIdentification);

    }

    ValidIdentification newApplyInitStatusForVoiceClerkRequestUnnecessary(
            IdentificationInitialRequestDataForVoiceClerkRequestUnnecessary identificationInitialRequestDataForVoiceClerkRequestUnnecessary,
            ValidVoiceEngagement validVoiceEngagement){

        ValidIdentification validIdentification = identificationFactory.createInitStatusForVoiceClerkRequestRequested(
                identificationInitialRequestDataForVoiceClerkRequestUnnecessary, validVoiceEngagement.getVoiceEngagementNumber());

        return register(validIdentification);
    }

    ValidIdentification newApplyOKForVoiceClerkRequestUnnecessary(
            IdentificationInitialRequestDataForVoiceClerkRequestUnnecessary identificationInitialRequestDataForVoiceClerkRequestUnnecessary,
            ValidVoiceEngagement validVoiceEngagement){

        ValidIdentification validIdentification = identificationFactory.createOKForVoiceClerkRequestRequested(
                identificationInitialRequestDataForVoiceClerkRequestUnnecessary, validVoiceEngagement.getVoiceEngagementNumber());

        return register(validIdentification);
    }

    ValidIdentification newApplyOKForVoiceClerkRequestUnnecessary(
            IdentificationInitialRequestData identificationInitialRequestData,
            ValidVoiceEngagement validVoiceEngagement){

        ValidIdentification validIdentification = identificationFactory.createOKForVoiceClerkRequestRequested(
                identificationInitialRequestData, validVoiceEngagement.getVoiceEngagementNumber());

        return register(validIdentification);
    }


    private ValidIdentification register(ValidIdentification validIdentification){

        identificationRepository.insert(validIdentification);

        return validIdentification;
    }
}
