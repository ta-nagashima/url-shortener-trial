package jp.co.biglobe.isp.mobile.voiceoption.domain.identification;

import jp.co.biglobe.isp.auth.domain.user.UserId;
import jp.co.biglobe.isp.mobile.ltethreeg.domain.ltethreegengagement.LteThreeGEngagementNumber;
import jp.co.biglobe.isp.mobile.voiceoption.domain.identification.upload.IdentificationUpload;
import jp.co.biglobe.isp.mobile.voiceoption.domain.identification.upload.NotExistIdentificationUpload;
import jp.co.biglobe.isp.mobile.voiceoption.domain.identification.upload.ValidUploadBuilder;
import jp.co.biglobe.isp.mobile.voiceoption.domain.identification.identificationresult.ValidIdentificationResultBuilder;
import jp.co.biglobe.isp.mobile.voiceoption.domain.identification.identificationresult.IdentificationResult;
import jp.co.biglobe.isp.mobile.voiceoption.domain.identification.identificationresult.NotExistIdentificationResult;
import jp.co.biglobe.isp.mobile.voiceoption.domain.voiceengagement.VoiceEngagementNumber;
import org.joda.time.DateTime;

public class ValidIdentificationBuilder {

    private IdentificationReceiptNumber identificationReceiptNumber = new IdentificationReceiptNumber("140101_0001");
    private IdentificationStatus identificationStatus = IdentificationStatus.DOCUMENT_WAITING;
    private IdentificationLinkage identificationLinkage = new IdentificationLinkage(
            new UserId("abc12345"),
            new LteThreeGEngagementNumber("00000001"),
            new VoiceEngagementNumber(1)
    );
    private VoiceClerkRequestStatus voiceClerkRequestStatus = VoiceClerkRequestStatus.REQUESTED;
    private IdentificationUpload identificationUpload = new NotExistIdentificationUpload();
    private IdentificationResult identificationResult = new NotExistIdentificationResult();

    public ValidIdentification build() {

        return new ValidIdentification(
                identificationReceiptNumber,
                identificationStatus,
                identificationLinkage,
                voiceClerkRequestStatus,
                identificationUpload,
                identificationResult
        );
    }

    public ValidIdentificationBuilder identificationStatus(IdentificationStatus newStatus){
        identificationStatus = newStatus;
        return this;
    }

    public ValidIdentificationBuilder validUpload(){
        identificationUpload = new ValidUploadBuilder().build(identificationReceiptNumber);
        return this;
    }

    public ValidIdentificationBuilder validIdentificationResult(){
        identificationResult = new ValidIdentificationResultBuilder().build();
        return this;
    }

    public ValidIdentificationBuilder validIdentificationResult(DateTime dt){
        identificationResult = new ValidIdentificationResultBuilder().executionDate(dt).build();
        return this;
    }

    public ValidIdentificationBuilder validIdentificationResultWeb(){
        identificationResult = new ValidIdentificationResultBuilder().identificationDocumentsWeb().build();
        return this;
    }

    public ValidIdentificationBuilder validIdentificationResultWeb(DateTime dt){
        identificationResult = new ValidIdentificationResultBuilder().identificationDocumentsWeb().executionDate(dt).build();
        return this;
    }

    public ValidIdentificationBuilder validIdentificationResultWebNG(){
        identificationResult = new ValidIdentificationResultBuilder().identificationDocumentsWeb().validNgReason().build();
        return this;
    }

    public ValidIdentificationBuilder validIdentificationResultFax(){
        identificationResult = new ValidIdentificationResultBuilder().identificationDocumentsFax().build();
        return this;
    }

    public ValidIdentificationBuilder validIdentificationResultFaxNG(){
        identificationResult = new ValidIdentificationResultBuilder().identificationDocumentsFax().validNgReason().build();
        return this;
    }
}
