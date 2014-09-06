package jp.co.biglobe.isp.mobile.voiceoption.domain.identification.upload;

import jp.co.biglobe.isp.mobile.voiceoption.domain.identification.IdentificationReceiptNumber;
import org.joda.time.DateTime;

public class ValidUploadBuilder {

    /**
     * 初回アップロード日時は今日の0時0分0秒で作成
     */
    private final FirstUploadDateTime firstUploadDateTime
            = new FirstUploadDateTime(new DateTime().dayOfMonth().roundFloorCopy().toDate());
    private final UploadCount uploadCount = new UploadCount(1);


    public ValidIdentificationUpload build(IdentificationReceiptNumber identificationReceiptNumber) {
        return new ValidIdentificationUpload(identificationReceiptNumber, firstUploadDateTime, uploadCount);
    }

}