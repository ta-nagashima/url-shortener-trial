package jp.co.biglobe.isp.mobile.voiceoption.domain.identification.identificationresult.identificationdocuments;

public class IdentificationDocumentsFactory {

    public static IdentificationDocuments create(
            DocumentAcceptanceMeans documentAcceptanceMeans,
            IdentificationDocumentType identificationDocumentType,
            IdentificationSubDocumentType identificationSubDocumentType){

        return new IdentificationDocuments(
                documentAcceptanceMeans,
                identificationDocumentType,
                identificationSubDocumentType);

    }

}


