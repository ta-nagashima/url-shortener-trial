package jp.co.biglobe.isp.mobile.voiceoption.datasource.identificationfiles.sftp;

import jp.co.biglobe.isp.mobile.voiceoption.domain.identification.IdentificationReceiptNumber;
import jp.co.biglobe.isp.mobile.voiceoption.domain.identificationfiles.IdentificationFiles;
import jp.co.biglobe.isp.mobile.voiceoption.domain.identificationfiles.NullIdentificationFile;
import jp.co.biglobe.isp.mobile.voiceoption.domain.identificationfiles.ValidIdentificationFile;
import jp.co.biglobe.isp.mobile.voiceoption.service.identification.IdentificationUploadTestFile;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Enclosed;

import java.io.File;
import java.io.IOException;
import java.util.GregorianCalendar;

import static org.junit.Assert.assertEquals;

@RunWith(Enclosed.class)
public class IdentificationFilesRepositorySftpTest {
    public static class normal {

        @Test
        public void getDirectoryName_正常() {
            String receiptNumber = "250701_1234";

            IdentificationFiles identificationFiles = new IdentificationFiles(
                    new IdentificationReceiptNumber(receiptNumber),
                    new GregorianCalendar(2016, 2 - 1, 29, 23, 59, 57).getTime(),
                    new NullIdentificationFile(),
                    new NullIdentificationFile(),
                    new NullIdentificationFile());
            String sftpDirectoryName = IdentificationFilesRepositorySftp.getMakeDirectoryName(identificationFiles);

            assertEquals(receiptNumber + "_" + "20160229235957", sftpDirectoryName);
        }

        @Test
        public void getFileName_正常() throws IOException {
            String receiptNumber = "250701_1234";
            String fileName = "./src/test/resources/images/1pixel.jpg";

            ValidIdentificationFile validIdentificationFile = new ValidIdentificationFile(3, new IdentificationUploadTestFile(new File(fileName)));
            IdentificationFiles identificationFiles = new IdentificationFiles(
                    new IdentificationReceiptNumber(receiptNumber),
                    new GregorianCalendar(2016, 2 - 1, 29, 23, 59, 57).getTime(),
                    new NullIdentificationFile(),
                    new NullIdentificationFile(),
                    validIdentificationFile);
            String sftpFileName = IdentificationFilesRepositorySftp.getFileName(identificationFiles, validIdentificationFile);
            assertEquals(receiptNumber + "_" + "20160229_03.jpg.pgp", sftpFileName);
        }
    }
}
