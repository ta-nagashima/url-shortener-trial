package jp.co.biglobe.isp.mobile.voiceoption.api.identificationdetailrefer

import jp.co.biglobe.isp.mobile.extension.view.StereotypedCharacters
import jp.co.biglobe.isp.mobile.voiceoption.domain.identification.Identification
import jp.co.biglobe.isp.mobile.voiceoption.domain.identification.ValidIdentification
import jp.co.biglobe.lib.plugin.view.JsonTemplate
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class IdentificationDetailReferResponse {
    @Autowired
    private JsonTemplate template;

    public Map build(Identification identification){
        final String IDENTIFICATION_RESULT = "result";

        if(identification.isNotExist()){
            return template.build([ (IDENTIFICATION_RESULT) :  StereotypedCharacters.NOT_EXIST ]);
        }

        ValidIdentification validIdentification = (ValidIdentification)identification;

        template.build(
                [
                        (IDENTIFICATION_RESULT) : StereotypedCharacters.EXIST,
                        "voiceEngagementNumber":validIdentification.getVoiceEngagementNumber().getApiValue(),
                        "lteThreeGEngagementNumber":validIdentification.getIdentificationLinkage().getLteThreeGEngagementNumber().getApiValue(),
                        "userId":validIdentification.getIdentificationLinkage().getUserId().getApiValue(),
                        "uploadCount":validIdentification.getIdentificationUpload().getUploadCountForApiValue(),
                        "uploadDate":validIdentification.getIdentificationUpload().getFirstUploadDateTimeForApiValue(),
                        "identificationReceiptNumber":validIdentification.getIdentificationReceiptNumber().getApiValue(),
                        "identificationDocumentType":validIdentification.getIdentificationResult().getIdentificationDocumentsTypeForApiValue(),
                        "identificationSubDocumentType":validIdentification.getIdentificationResult().getIdentificationSubDocumentTypeForApiValue(),
                        "identificationDocumentAcceptanceMeans":validIdentification.getIdentificationResult().getIdentificationDocumentAcceptanceMeansForApiValue(),
                        "identificationStatus":validIdentification.getIdentificationStatus().getRefApiValue(),
                        "ngReason":validIdentification.getIdentificationResult().getNgReasonForApiValue(),
                        "ngReasonDetail":validIdentification.getIdentificationResult().getNgReasonDetailForApiValue()
                ]
        )

    }

}
