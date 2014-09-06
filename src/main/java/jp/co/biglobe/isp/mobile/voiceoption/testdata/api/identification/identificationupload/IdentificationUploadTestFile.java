package jp.co.biglobe.isp.mobile.voiceoption.testdata.api.identification.identificationupload;

import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by k-kawamura on 2014/05/21.
 */
public class IdentificationUploadTestFile implements MultipartFile {
    @Override
    public String getName() {
        return "idenTificationDocument1";
    }

    @Override
    public String getOriginalFilename() {
        return "abc.txt";
    }

    @Override
    public String getContentType() {
        return null;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public long getSize() {
        try {
            return getBytes().length;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public byte[] getBytes() throws IOException {
        return new byte[3*1024*1024];
    }

    @Override
    public InputStream getInputStream() throws IOException {
        return new ByteArrayInputStream(getBytes());
    }

    @Override
    public void transferTo(File dest) throws IOException, IllegalStateException {

    }
}
