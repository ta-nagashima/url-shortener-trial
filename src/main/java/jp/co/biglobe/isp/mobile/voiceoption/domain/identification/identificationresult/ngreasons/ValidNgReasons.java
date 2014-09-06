package jp.co.biglobe.isp.mobile.voiceoption.domain.identification.identificationresult.ngreasons;

import jp.co.biglobe.isp.mobile.extension.domain.CommonValidEntity;
import jp.co.biglobe.isp.mobile.voiceoption.domain.identification.identificationresult.IdentificationResultId;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@ToString(includeFieldNames=false)
@EqualsAndHashCode
@AllArgsConstructor
public class ValidNgReasons implements NgReasons, CommonValidEntity<ValidNgReasons> {

    @Getter
    private final IdentificationResultId identificationResultId;

    private final NgReason ngReason;

    private final NgReasonDetail ngReasonDetail;

    @Override
    public boolean isExist() {
        return true;
    }

    @Override
    public String getNgReasonForApiValue() {return ngReason.getApiValue();}

    @Override
    public String getNgReasonDetailForApiValue() {return ngReasonDetail.getApiValue();}

    /**
     * データ変更時に、Update でなく、Delete & Insert する必要があるか、判定する（外部参照キーが違えば true）
     */
    @Override
    public boolean requiredDeleteAndInsert(ValidNgReasons validNgReasons) {
        // 外部参照キーが変わっているか判定する
        return !this.identificationResultId.equals(validNgReasons.getIdentificationResultId());
    }

    /**
     * 子エンティティを除く値が同じか
     */
    @Override
    public boolean equalsExcludeChild(ValidNgReasons validEntity) {
        return this.equals(validEntity);
    }
}
