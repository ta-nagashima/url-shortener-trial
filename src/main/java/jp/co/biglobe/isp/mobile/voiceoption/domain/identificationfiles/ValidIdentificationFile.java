package jp.co.biglobe.isp.mobile.voiceoption.domain.identificationfiles;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.MemoryCacheImageInputStream;
import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;

/*
 * 本人確認書類オブジェクト
 */
@ToString(includeFieldNames = false)
@EqualsAndHashCode
public class ValidIdentificationFile implements IdentificationFile {
    @Getter
    private final String key;
    private final MultipartFile value;
    @Getter
    private final FileSuffix fileSuffix;

    public ValidIdentificationFile(int number, MultipartFile value) {
        this.key = new DecimalFormat("00").format(number);
        this.value = value;
        this.fileSuffix = ValidFileSuffix.createFileSuffixByFileName(value.getOriginalFilename());
    }

    @Override
    public boolean isExist() {
        return true;
    }

    /**
     * 本人確認書類データ
     */
    public byte[] getBytes() {
        try {
            return value.getBytes();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * ファイル拡張子を指定して、画像をグレースケールに変換する
     */
    private static byte[] convertGrayScale(FileSuffix fileSuffix, byte[] data) {
        try {
            if (fileSuffix.isJpeg()) {
                // JPEG の場合
                return convertGrayScale("jpeg", data);
            }
            if (fileSuffix.isGif()) {
                // GIF の場合
                return convertGrayScale("gif", data);
            }
            if (fileSuffix.isPng()) {
                // PNG の場合
                return convertGrayScale("png", data);
            }
        } catch (IOException e) {
            // No Operation
        }
        return data;
    }

    /**
     * 形式を明示して、画像をグレースケールに変換する
     */
    private static byte[] convertGrayScale(String formatName, byte[] data) throws IOException {
        // バイトデータから BufferedImage を作成する
        BufferedImage inputImage = getBufferedImage(formatName, data);
        // グレースケールに変換する
        BufferedImage grayImage = getBufferedImageGray(inputImage);
        // グレースケールからバイトデータを取得する
        byte[] grayData = getBytes(formatName, grayImage);
        // グレースケールができている、かつ、元データより小さくなっていたら、グレーデータを返す
        if (grayData != null && grayData.length > 0 && grayData.length < data.length) {
            return grayData;
        }
        // グレースケールできない、または、小さくならなかった場合、元のデータを返す
        return data;
    }

    /**
     * 形式を明示して BufferedImage に変換する
     */
    private static BufferedImage getBufferedImage(String formatName, byte[] data) throws IOException {
        MemoryCacheImageInputStream memoryCacheImageInputStream = new MemoryCacheImageInputStream(new ByteArrayInputStream(data));
        try {
            ImageReader imageReader = ImageIO.getImageReadersByFormatName(formatName).next();
            imageReader.setInput(memoryCacheImageInputStream);
            BufferedImage bufferedImage = imageReader.read(0);
            bufferedImage.flush();
            return bufferedImage;
        } finally {
            memoryCacheImageInputStream.close();
        }
    }

    /**
     * グレーに変換した BufferedImage を取得する
     */
    private static BufferedImage getBufferedImageGray(BufferedImage bufferedImage) {
        final int convertImageType = BufferedImage.TYPE_BYTE_GRAY;

        if (bufferedImage.getType() == convertImageType) {
            return bufferedImage;
        }

        final int width = bufferedImage.getWidth();
        final int height = bufferedImage.getHeight();

        BufferedImage grayImage = new BufferedImage(width, height, convertImageType);
        grayImage.createGraphics().drawImage(bufferedImage, 0, 0, width, height, null);
        grayImage.flush();
        return grayImage;
    }

    /**
     * 形式を明示して BufferedImage からバイト配列を取得する
     */
    private static byte[] getBytes(String formatName, BufferedImage bufferedImage) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(byteArrayOutputStream, 131072);
        ImageIO.write(bufferedImage, formatName, bufferedOutputStream);
        bufferedOutputStream.close();
        return byteArrayOutputStream.toByteArray();
    }
}
