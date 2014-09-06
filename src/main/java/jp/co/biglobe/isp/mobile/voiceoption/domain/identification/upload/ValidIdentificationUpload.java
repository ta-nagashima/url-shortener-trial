package jp.co.biglobe.isp.mobile.voiceoption.domain.identification.upload;

import jp.co.biglobe.isp.mobile.extension.domain.CommonValidEntity;
import jp.co.biglobe.isp.mobile.voiceoption.domain.identification.IdentificationReceiptNumber;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@ToString(includeFieldNames = false)
@EqualsAndHashCode
@AllArgsConstructor
public class ValidIdentificationUpload implements IdentificationUpload, CommonValidEntity<ValidIdentificationUpload> {

    @Getter
    private final IdentificationReceiptNumber identificationReceiptNumber;

    private final FirstUploadDateTime firstUploadDateTime;

    private final UploadCount uploadCount;

    @Override
    public boolean isExist() {
        return true;
    }

    @Override
    public ValidIdentificationUpload uploaded(IdentificationReceiptNumber identificationReceiptNumber) {
        return new ValidIdentificationUpload(
                this.identificationReceiptNumber,
                this.firstUploadDateTime,
                this.uploadCount.countUp());
    }

    ValidIdentificationUpload uploaded() {
        return this.uploaded(this.identificationReceiptNumber);
    }

    @Override
    public boolean isUploaded() {
        return true;
    }

    @Override
    public boolean isOverLimitCount(){
        return uploadCount.isOverLimitCount();
    }

    @Override
    public String getUploadCountForApiValue() {return uploadCount.getApiValue();}

    @Override
    public String getFirstUploadDateTimeForApiValue() {return firstUploadDateTime.getApiValue();}

    @Override
    public NotExistIdentificationUpload reset() {
        return new NotExistIdentificationUpload();
    }

    /**
     * データ変更時に、Update でなく、Delete & Insert する必要があるか、判定する（外部参照キーが違えば true）
     */
    @Override
    public boolean requiredDeleteAndInsert(ValidIdentificationUpload validIdentificationUpload) {
        // 外部参照キーが変わっているか判定する
        return !this.identificationReceiptNumber.equals(validIdentificationUpload.getIdentificationReceiptNumber());
    }

    /**
     * 子エンティティを除く値が同じか
     */
    @Override
    public boolean equalsExcludeChild(ValidIdentificationUpload o) {
        return this.equals(o);
    }
}
