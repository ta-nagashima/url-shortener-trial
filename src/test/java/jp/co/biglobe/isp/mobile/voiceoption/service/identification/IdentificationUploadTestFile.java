package jp.co.biglobe.isp.mobile.voiceoption.service.identification;

import org.springframework.web.multipart.MultipartFile;

import java.io.*;

public class IdentificationUploadTestFile implements MultipartFile {
    private final String fileName;
    private final byte[] data;

    public IdentificationUploadTestFile() throws IOException {
        this(new File("./src/test/resources/images/1pixel.jpg"));
    }

    public IdentificationUploadTestFile(File file) throws IOException {
        if (file == null) {
            this.fileName = null;
            this.data = null;
        } else {
            this.fileName = file.getName();
            this.data = new byte[(int) file.length()];
            FileInputStream fs = new FileInputStream(file);
            fs.read(data);
            fs.close();
        }
    }

    @Override
    public String getName() {
        return fileName;
    }

    @Override
    public String getOriginalFilename() {
        return fileName;
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
        return data;
    }

    @Override
    public InputStream getInputStream() throws IOException {
        return new ByteArrayInputStream(getBytes());
    }

    @Override
    public void transferTo(File dest) throws IOException, IllegalStateException {

    }
}
