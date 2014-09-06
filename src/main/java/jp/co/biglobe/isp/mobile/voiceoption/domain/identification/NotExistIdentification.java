package jp.co.biglobe.isp.mobile.voiceoption.domain.identification;

import jp.co.biglobe.isp.mobile.voiceoption.domain.identification.identificationresult.IdentificationResult;
import jp.co.biglobe.isp.mobile.voiceoption.domain.identification.identificationresult.NotExistIdentificationResult;
import jp.co.biglobe.isp.mobile.voiceoption.domain.identification.upload.IdentificationUpload;
import jp.co.biglobe.isp.mobile.voiceoption.domain.identification.upload.NotExistIdentificationUpload;
import jp.co.biglobe.isp.mobile.voiceoption.domain.policy.UploadCheckStatus;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@ToString(includeFieldNames=false)
@EqualsAndHashCode
@AllArgsConstructor
public class NotExistIdentification implements Identification {

    @Override
    public UploadCheckStatus verifyUpload() {
        return UploadCheckStatus.UPLOAD_DISABLE_STATUS;
    }

    @Override
    public boolean isExist() { return false; }

    @Override
    public boolean isNotExist() { return true; }

    @Override
    public boolean isNotIdentificationStatusOK() {
        return true;
    }
}
