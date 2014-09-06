package jp.co.biglobe.isp.mobile.voiceoption.domain.identification.identificationresult;

import jp.co.biglobe.isp.auth.domain.operatornouser.OperatorId;
import jp.co.biglobe.isp.mobile.extension.domain.CommonValidEntity;
import jp.co.biglobe.isp.mobile.voiceoption.domain.executiondate.ExecutionDate;
import jp.co.biglobe.isp.mobile.voiceoption.domain.identification.IdentificationReceiptNumber;
import jp.co.biglobe.isp.mobile.voiceoption.domain.identification.identificationresult.identificationdocuments.IdentificationDocuments;
import jp.co.biglobe.isp.mobile.voiceoption.domain.identification.identificationresult.ngreasons.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@ToString(includeFieldNames = false)
@EqualsAndHashCode
@AllArgsConstructor
public class ValidIdentificationResult implements IdentificationResult, CommonValidEntity<ValidIdentificationResult> {

    @Getter
    private final IdentificationResultId identificationResultId;

    @Getter
    private final IdentificationReceiptNumber identificationReceiptNumber;

    @Getter
    private final OperatorId operatorId;

    @Getter
    private final ExecutionDate executionDate;

    @Getter
    private final IdentificationDocuments identificationDocuments;

    @Getter
    private final NgReasons ngReasons;

    public ValidIdentificationResult(
            IdentificationResultId identificationResultId,
            IdentificationReceiptNumber identificationReceiptNumber,
            OperatorId operatorId,
            ExecutionDate executionDate,
            IdentificationDocuments identificationDocuments,
            ValidNgReasons validNgReasons) {
        this.identificationResultId = identificationResultId;
        this.identificationReceiptNumber = identificationReceiptNumber;
        this.operatorId = operatorId;
        this.executionDate = executionDate;
        this.identificationDocuments = identificationDocuments;
        if (validNgReasons == null) {
            this.ngReasons = new NotExistNgReasons();
        } else {
            this.ngReasons = validNgReasons;
        }
    }

    @Override
    public boolean isExist() {
        return true;
    }

    @Override
    public String getIdentificationDocumentsTypeForApiValue() {return identificationDocuments.getIdentificationDocumentType().getApiValue();}

    @Override
    public String getIdentificationSubDocumentTypeForApiValue() {return identificationDocuments.getIdentificationSubDocumentType().getApiValue();}

    @Override
    public String getIdentificationDocumentAcceptanceMeansForApiValue() {return identificationDocuments.getDocumentAcceptanceMeans().getApiValue();}

    @Override
    public String getNgReasonForApiValue() {return ngReasons.getNgReasonForApiValue();}

    @Override
    public String getNgReasonDetailForApiValue() {return ngReasons.getNgReasonDetailForApiValue();}

    @Override
    public String getExcutionDateForApiValue() {
        return executionDate.getApiValue();
    }

    /**
     * データ変更時に、Update でなく、Delete & Insert する必要があるか、判定する（外部参照キーが違えば true）
     */
    @Override
    public boolean requiredDeleteAndInsert(ValidIdentificationResult validIdentificationResult) {
        // 主キーか外部参照キーが変わっているか判定する
        return !this.identificationReceiptNumber.equals(validIdentificationResult.getIdentificationReceiptNumber()) ||
               !this.identificationResultId.equals(validIdentificationResult.getIdentificationResultId());
    }

    /**
     * 子エンティティを除く値が同じか
     */
    @Override
    public boolean equalsExcludeChild(ValidIdentificationResult o) {
        ValidIdentificationResult x = new ValidIdentificationResult(
                this.identificationResultId,
                this.identificationReceiptNumber,
                this.operatorId,
                this.executionDate,
                this.identificationDocuments,
                new NotExistNgReasons());
        ValidIdentificationResult y = new ValidIdentificationResult(
                o.getIdentificationResultId(),
                o.getIdentificationReceiptNumber(),
                o.getOperatorId(),
                o.getExecutionDate(),
                o.getIdentificationDocuments(),
                new NotExistNgReasons());
        return x.equals(y);
    }
}
