package jp.co.biglobe.isp.mobile.voiceoption.domain.identification.identificationresult;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public class NotExistIdentificationResult implements IdentificationResult {

    @Override
    public boolean isExist() {
        return false;
    }

    @Override
    public String getIdentificationDocumentsTypeForApiValue() {return "";}

    @Override
    public String getIdentificationSubDocumentTypeForApiValue() {return "";}

    @Override
    public String getIdentificationDocumentAcceptanceMeansForApiValue() {return "";}

    @Override
    public String getNgReasonForApiValue() {return "";}

    @Override
    public String getNgReasonDetailForApiValue() {return "";}

    @Override
    public String getExcutionDateForApiValue() {
        return "";
    }

}
