package jp.co.biglobe.isp.mobile.voiceoption.domain.identification.identificationresult;

import jp.co.biglobe.isp.auth.domain.operatornouser.OperatorId;
import jp.co.biglobe.isp.mobile.voiceoption.domain.executiondate.ExecutionDate;
import jp.co.biglobe.isp.mobile.voiceoption.domain.identification.IdentificationReceiptNumber;
import jp.co.biglobe.isp.mobile.voiceoption.domain.identification.identificationresult.identificationdocuments.IdentificationSubDocumentType;
import jp.co.biglobe.isp.mobile.voiceoption.domain.identification.identificationresult.identificationdocuments.DocumentAcceptanceMeans;
import jp.co.biglobe.isp.mobile.voiceoption.domain.identification.identificationresult.identificationdocuments.IdentificationDocumentType;
import jp.co.biglobe.isp.mobile.voiceoption.domain.identification.identificationresult.identificationdocuments.IdentificationDocuments;
import jp.co.biglobe.isp.mobile.voiceoption.domain.identification.identificationresult.ngreasons.*;
import org.joda.time.DateTime;

public class ValidIdentificationResultBuilder {

    private IdentificationResultId identificationResultId = new IdentificationResultId(1);
    private IdentificationReceiptNumber identificationReceiptNumber = new IdentificationReceiptNumber("140101_0001");
    private OperatorId operatorId = new OperatorId("xyz12345");
    private ExecutionDate executionDate = new ExecutionDate(new DateTime().toString("yyyyMMdd"));
    private IdentificationDocuments identificationDocuments = new IdentificationDocuments(
            DocumentAcceptanceMeans.WEB,
            IdentificationDocumentType.BASIC_RESIDENT_REGISTER_CARD,
            IdentificationSubDocumentType.FAMILY_REGISTER
    );
    private NgReasons ngReasons = new NotExistNgReasons();

    public ValidIdentificationResultBuilder identificationDocumentsWeb(){
        this.identificationDocuments = new IdentificationDocuments(
                DocumentAcceptanceMeans.WEB,
                IdentificationDocumentType.LICENSE,
                IdentificationSubDocumentType.NOTHING
        );
        return this;
    }

    public ValidIdentificationResultBuilder executionDate(DateTime dt){
        this.executionDate = new ExecutionDate(dt.toString("yyyyMMdd"));
        return this;
    }


    public ValidIdentificationResultBuilder identificationDocumentsFax(){
        this.identificationDocuments = new IdentificationDocuments(
                DocumentAcceptanceMeans.FAX,
                IdentificationDocumentType.LICENSE,
                IdentificationSubDocumentType.NOTHING
        );
        return this;
    }

    public ValidIdentificationResultBuilder validNgReason(){
        this.ngReasons = new ValidNgReasons(
                identificationResultId,
                NgReason.INCOMPLETE_DOCUMENT,
                new NgReasonDetail("未登録")
        );
        return this;
    }


    public ValidIdentificationResult build(){
        return new ValidIdentificationResult(
                identificationResultId,
                identificationReceiptNumber,
                operatorId,
                executionDate,
                identificationDocuments,
                ngReasons
        );
    }

}