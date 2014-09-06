package jp.co.biglobe.isp.mobile.voiceoption.domain.identificationfiles;

import jp.co.biglobe.isp.mobile.voiceoption.domain.identification.IdentificationReceiptNumber;
import jp.co.biglobe.isp.mobile.voiceoption.service.identification.IdentificationUploadTestFile;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Enclosed;
import org.springframework.mock.web.MockMultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(Enclosed.class)
public class IdentificationFilesTest {
    public static class normal {
        @Test
        public void get_正常_データ取り出し1() throws IOException {

            ValidIdentificationFile file1 = new ValidIdentificationFile(1, new MockMultipartFile("abc.csv", new byte[]{'A', 'B'}));
            ValidIdentificationFile file3 = new ValidIdentificationFile(3, new MockMultipartFile("def.pdf", new byte[]{'X', 'Y', 'Z'}));

            IdentificationFiles identificationFiles = new IdentificationFiles(new IdentificationReceiptNumber("abc"),
                    new Date(), file1, new NullIdentificationFile(), file3);

            ValidIdentificationFile[] after = new ValidIdentificationFile[]{file1, file3};

            assertEquals(after, identificationFiles.getIdentificationFiles());
        }

        @Test
        public void get_正常_データ取り出し2() throws IOException {

            ValidIdentificationFile file2 = new ValidIdentificationFile(2, new MockMultipartFile("abc.txt", new byte[]{'A', 'B'}));

            IdentificationFiles identificationFiles = new IdentificationFiles(new IdentificationReceiptNumber("abc"),
                    new Date(), new NullIdentificationFile(), file2, new NullIdentificationFile());

            ValidIdentificationFile[] after = new ValidIdentificationFile[]{file2};

            assertEquals(after, identificationFiles.getIdentificationFiles());
        }
    }
}
