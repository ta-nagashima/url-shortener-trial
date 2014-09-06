package jp.co.biglobe.isp.mobile.voiceoption.domain.identification;

import jp.co.biglobe.isp.mobile.extension.domain.CommonEntity;
import jp.co.biglobe.isp.mobile.voiceoption.domain.identification.identificationresult.IdentificationResult;
import jp.co.biglobe.isp.mobile.voiceoption.domain.identification.upload.IdentificationUpload;
import jp.co.biglobe.isp.mobile.voiceoption.domain.policy.UploadCheckStatus;

public interface Identification extends CommonEntity {

    public boolean isNotExist();

    public UploadCheckStatus verifyUpload();

    public boolean isNotIdentificationStatusOK();
}
