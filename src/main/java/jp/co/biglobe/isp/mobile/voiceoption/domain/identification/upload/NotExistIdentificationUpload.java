package jp.co.biglobe.isp.mobile.voiceoption.domain.identification.upload;

import jp.co.biglobe.isp.mobile.extension.date.SystemDateTime;
import jp.co.biglobe.isp.mobile.voiceoption.domain.identification.IdentificationReceiptNumber;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@ToString(includeFieldNames = false)
@EqualsAndHashCode
@AllArgsConstructor
public class NotExistIdentificationUpload implements IdentificationUpload {

    @Override
    public boolean isExist() {
        return false;
    }

    @Override
    public ValidIdentificationUpload uploaded(IdentificationReceiptNumber identificationReceiptNumber) {
        return new ValidIdentificationUpload(
                identificationReceiptNumber,
                new FirstUploadDateTime(new SystemDateTime().getValue()),
                new UploadCount(1));
    }

    @Override
    public boolean isUploaded() {
        return false;
    }

    @Override
    public boolean isOverLimitCount(){
        return false;
    }

    @Override
    public String getUploadCountForApiValue() {return "";}

    @Override
    public String getFirstUploadDateTimeForApiValue() {return "";}

    @Override
    public NotExistIdentificationUpload reset() {
        return this;
    }

}
