package jp.co.biglobe.isp.mobile.voiceoption.domain.identification.identificationresult;

import jp.co.biglobe.isp.mobile.extension.domain.CommonEntity;
import jp.co.biglobe.isp.mobile.voiceoption.domain.identification.identificationresult.ngreasons.NgReasons;

public interface IdentificationResult extends CommonEntity {

    public String getIdentificationDocumentsTypeForApiValue();

    public String getIdentificationSubDocumentTypeForApiValue();

    public String getIdentificationDocumentAcceptanceMeansForApiValue();

    public String getNgReasonForApiValue();

    public String getNgReasonDetailForApiValue();

    public String getExcutionDateForApiValue();

}
