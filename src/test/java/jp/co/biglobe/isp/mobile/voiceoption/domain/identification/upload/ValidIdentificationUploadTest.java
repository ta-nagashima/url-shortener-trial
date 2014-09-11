package jp.co.biglobe.isp.mobile.voiceoption.domain.identification.upload;

import jp.co.biglobe.isp.mobile.voiceoption.domain.identification.IdentificationReceiptNumber;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.experimental.runners.Enclosed;

import java.util.Date;

import static org.junit.Assert.assertTrue;

@RunWith(Enclosed.class)
public class ValidIdentificationUploadTest {

    public static class normal {
        @Test
        public void ok_uploaded() {
            long time = new Date().getTime();
            IdentificationReceiptNumber identificationReceiptNumber = new IdentificationReceiptNumber("160229_9876");

            ValidIdentificationUpload before = new ValidIdentificationUpload(identificationReceiptNumber, new FirstUploadDateTime(new Date(time)), new UploadCount(1));
            ValidIdentificationUpload after = new ValidIdentificationUpload(identificationReceiptNumber, new FirstUploadDateTime(new Date(time)), new UploadCount(2));
            ValidIdentificationUpload uploaded = before.uploaded();

            assertTrue(after.equals(uploaded));
        }
    }
}
