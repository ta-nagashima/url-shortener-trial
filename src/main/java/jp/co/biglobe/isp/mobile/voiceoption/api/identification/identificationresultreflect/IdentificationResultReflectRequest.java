package jp.co.biglobe.isp.mobile.voiceoption.api.identification.identificationresultreflect;

import jp.co.biglobe.isp.mobile.voiceoption.api.form.VoiceOperatorIdForm;
import jp.co.biglobe.lib.publication.validation.ValueObjectNotEmpty;
import jp.co.biglobe.isp.mobile.voiceoption.api.form.MnpInActivationDueDateForm;
import jp.co.biglobe.isp.mobile.voiceoption.api.form.ExecutionDateForm;
import jp.co.biglobe.isp.mobile.voiceoption.api.form.identification.IdentificationReceiptNumberForm;
import jp.co.biglobe.isp.mobile.voiceoption.api.form.identification.IdentificationStatusEventForm;
import jp.co.biglobe.isp.mobile.voiceoption.api.form.identificationresult.identificationdocuments.IdentificationDocumentAcceptanceMeansForm;
import jp.co.biglobe.isp.mobile.voiceoption.api.form.identificationresult.identificationdocuments.IdentificationDocumentTypeForm;
import jp.co.biglobe.isp.mobile.voiceoption.api.form.identificationresult.identificationdocuments.IdentificationSubDocumentTypeForm;
import jp.co.biglobe.isp.mobile.voiceoption.api.form.identificationresult.ngreasons.NgReasonDetailForm;
import jp.co.biglobe.isp.mobile.voiceoption.api.form.identificationresult.ngreasons.NgReasonForm;
import jp.co.biglobe.isp.mobile.voiceoption.domain.identification.identificationresult.IdentificationResultId;
import jp.co.biglobe.isp.mobile.voiceoption.domain.identification.identificationresult.ValidIdentificationResult;
import jp.co.biglobe.isp.mobile.voiceoption.domain.identification.identificationresult.ValidIdentificationResultFactory;
import jp.co.biglobe.isp.mobile.voiceoption.domain.mnpin.mnpinactivationduedate.MnpInActivationDueDate;
import jp.co.biglobe.isp.mobile.voiceoption.domain.mnpin.mnpinactivationduedate.MnpInActivationDueDateFactory;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@ToString(includeFieldNames = false)
public class IdentificationResultReflectRequest {

    @Getter
    @Setter
    @ValueObjectNotEmpty
    @Valid
    private IdentificationReceiptNumberForm identificationReceiptNumberForm;

    @Getter
    @Setter
    @ValueObjectNotEmpty
    @Valid
    private VoiceOperatorIdForm operatorIdForm;

    @Getter
    @Setter
    @ValueObjectNotEmpty
    @Valid
    private ExecutionDateForm executionDateForm;

    @Getter
    @Setter
    @ValueObjectNotEmpty
    @Valid
    private IdentificationDocumentTypeForm identificationDocumentTypeForm;

    @Getter
    @Setter
    @ValueObjectNotEmpty
    @Valid
    private IdentificationSubDocumentTypeForm identificationSubDocumentTypeForm;

    @Getter
    @Setter
    @ValueObjectNotEmpty
    @Valid
    private IdentificationDocumentAcceptanceMeansForm identificationDocumentAcceptanceMeansForm;

    @Getter
    @Setter
    @ValueObjectNotEmpty
    @Valid
    private IdentificationStatusEventForm identificationStatusEventForm;

    /**
     * ここまで必須項目。以下は条件付きの任意項目
     */

    @Getter
    @Setter
    @NotNull
    @Valid
    private NgReasonForm ngReasonForm;

    @Getter
    @Setter
    @NotNull
    @Valid
    private NgReasonDetailForm ngReasonDetailForm;

    @Getter
    @Setter
    @NotNull
    @Valid
    private MnpInActivationDueDateForm activationDueDateForm;

    ValidIdentificationResult getValidIdentificationResult(IdentificationResultId identificationResultId) {

        return ValidIdentificationResultFactory.create(
                identificationResultId,
                identificationReceiptNumberForm.getValueObject(),
                operatorIdForm.getValueObject(),
                executionDateForm.getValueObject(),
                identificationDocumentAcceptanceMeansForm.getValueObject(),
                identificationDocumentTypeForm.getValueObject(),
                identificationSubDocumentTypeForm.getValueObject(),
                identificationStatusEventForm.getValueObject(),
                ngReasonForm.getValueObject(),
                ngReasonDetailForm.getValueObject()
        );
    }

    public MnpInActivationDueDate getMnpInActivationDueDate() {
        return MnpInActivationDueDateFactory.create(
                identificationStatusEventForm.getValueObject(),
                activationDueDateForm.getValueObject()
        );
    }

}
