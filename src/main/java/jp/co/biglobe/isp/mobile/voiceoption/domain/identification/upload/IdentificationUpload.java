package jp.co.biglobe.isp.mobile.voiceoption.domain.identification.upload;

import jp.co.biglobe.isp.mobile.extension.domain.CommonEntity;
import jp.co.biglobe.isp.mobile.voiceoption.domain.identification.IdentificationReceiptNumber;

public interface IdentificationUpload extends CommonEntity {

    public ValidIdentificationUpload uploaded(IdentificationReceiptNumber identificationReceiptNumber);

    public boolean isUploaded();

    public boolean isOverLimitCount();

    public String getUploadCountForApiValue();

    public String getFirstUploadDateTimeForApiValue();

    public NotExistIdentificationUpload reset();

}
