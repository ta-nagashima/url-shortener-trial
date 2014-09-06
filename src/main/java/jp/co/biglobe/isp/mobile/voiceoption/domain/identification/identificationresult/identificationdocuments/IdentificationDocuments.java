package jp.co.biglobe.isp.mobile.voiceoption.domain.identification.identificationresult.identificationdocuments;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@ToString(includeFieldNames=false)
@EqualsAndHashCode
@AllArgsConstructor
public class IdentificationDocuments {

    @Getter
    private final DocumentAcceptanceMeans documentAcceptanceMeans;
    @Getter
    private final IdentificationDocumentType identificationDocumentType;
    @Getter
    private final IdentificationSubDocumentType identificationSubDocumentType;

}
