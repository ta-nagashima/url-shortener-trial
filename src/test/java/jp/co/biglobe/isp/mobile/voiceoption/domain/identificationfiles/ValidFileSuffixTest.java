package jp.co.biglobe.isp.mobile.voiceoption.domain.identificationfiles;

import org.junit.Test;

import java.io.IOException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class ValidFileSuffixTest {
    @Test
    public void get_正常_拡張子取り出し() throws IOException {
        String fileName = "./src/test/resources/images/ファイル名.JPG";
        FileSuffix actual = ValidFileSuffix.createFileSuffixByFileName(fileName);

        assertThat(actual, is(ValidFileSuffix.createFileSuffixByFileName("abc.jpg")));
    }
}
