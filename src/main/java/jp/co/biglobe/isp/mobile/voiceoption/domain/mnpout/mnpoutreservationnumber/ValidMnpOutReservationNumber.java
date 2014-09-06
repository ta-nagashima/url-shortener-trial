package jp.co.biglobe.isp.mobile.voiceoption.domain.mnpout.mnpoutreservationnumber;

import jp.co.biglobe.isp.auth.domain.operatornouser.OperatorId;
import jp.co.biglobe.isp.mobile.extension.domain.CommonValidEntity;
import jp.co.biglobe.isp.mobile.voiceoption.domain.executiondate.ExecutionDate;
import jp.co.biglobe.isp.mobile.voiceoption.domain.mnpin.MnpReservationNumber;
import jp.co.biglobe.isp.mobile.voiceoption.domain.mnpout.VoiceMnpOutId;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@ToString(includeFieldNames = false)
@EqualsAndHashCode
@AllArgsConstructor
public class ValidMnpOutReservationNumber implements MnpOutReservationNumber, CommonValidEntity<ValidMnpOutReservationNumber> {

    @Getter
    private final VoiceMnpOutId voiceMnpOutId;

    @Getter
    private final MnpReservationNumber mnpOutReservationNumber;

    private final ExpireDate expireDate;

    private final ExecutionDate executionDate;

    private final OperatorId operatorId;

    @Override
    public String getMnpReservationNumberForApiValue() {
        return mnpOutReservationNumber.getApiValue();
    }

    @Override
    public String getExpireDateForApiValue() {
        return expireDate.getApiValue();
    }


    @Override
    public String getExecutionDateForApiValue() {
        return executionDate.getApiValue();
    }

    @Override
    public boolean hasNumber() {
        return true;
    }

    @Override
    public boolean isExist() {
        return true;
    }

    @Override
    public boolean isNotExist() {
        return !(isExist());
    }

    @Override
    public boolean isValidReservationNumber() {
        return this.expireDate.isValid();
    }

    /**
     * データ変更時に、Update でなく、Delete & Insert する必要があるか、判定する（外部参照キーが違えば true）
     */
    @Override
    public boolean requiredDeleteAndInsert(ValidMnpOutReservationNumber validEntity) {
        return !this.voiceMnpOutId.equals(validEntity.getVoiceMnpOutId());
    }

    /**
     * 子エンティティを除く値が同じか
     */
    @Override
    public boolean equalsExcludeChild(ValidMnpOutReservationNumber validEntity) {
        return this.equals(validEntity);
    }
}
