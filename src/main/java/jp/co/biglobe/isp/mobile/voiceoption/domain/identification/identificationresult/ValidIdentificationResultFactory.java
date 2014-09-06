package jp.co.biglobe.isp.mobile.voiceoption.domain.identification.identificationresult;

import jp.co.biglobe.isp.auth.domain.operatornouser.OperatorId;
import jp.co.biglobe.isp.mobile.voiceoption.domain.executiondate.ExecutionDate;
import jp.co.biglobe.isp.mobile.voiceoption.domain.identification.IdentificationReceiptNumber;
import jp.co.biglobe.isp.mobile.voiceoption.domain.identification.IdentificationStatusEvent;
import jp.co.biglobe.isp.mobile.voiceoption.domain.identification.identificationresult.identificationdocuments.DocumentAcceptanceMeans;
import jp.co.biglobe.isp.mobile.voiceoption.domain.identification.identificationresult.identificationdocuments.IdentificationDocumentType;
import jp.co.biglobe.isp.mobile.voiceoption.domain.identification.identificationresult.identificationdocuments.IdentificationDocumentsFactory;
import jp.co.biglobe.isp.mobile.voiceoption.domain.identification.identificationresult.identificationdocuments.IdentificationSubDocumentType;
import jp.co.biglobe.isp.mobile.voiceoption.domain.identification.identificationresult.ngreasons.NgReason;
import jp.co.biglobe.isp.mobile.voiceoption.domain.identification.identificationresult.ngreasons.NgReasonDetail;
import jp.co.biglobe.isp.mobile.voiceoption.domain.identification.identificationresult.ngreasons.NgReasonsFactory;

public class ValidIdentificationResultFactory {

    public static ValidIdentificationResult create(
            IdentificationResultId identificationResultId,
            IdentificationReceiptNumber identificationReceiptNumber,
            OperatorId operatorId,
            ExecutionDate executionDate,
            DocumentAcceptanceMeans documentAcceptanceMeans,
            IdentificationDocumentType identificationDocumentType,
            IdentificationSubDocumentType identificationSubDocumentType,
            IdentificationStatusEvent identificationStatusEvent,
            NgReason ngReason,
            NgReasonDetail ngReasonDetail){

        return new ValidIdentificationResult(
                identificationResultId,
                identificationReceiptNumber,
                operatorId,
                executionDate,
                IdentificationDocumentsFactory.create(
                        documentAcceptanceMeans,
                        identificationDocumentType,
                        identificationSubDocumentType),
                NgReasonsFactory.create(
                        identificationStatusEvent,
                        identificationResultId,
                        ngReason,
                        ngReasonDetail)
        );
    }
}
