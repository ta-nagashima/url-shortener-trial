package jp.co.biglobe.isp.mobile.voiceoption.domain.identificationfiles;

import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.regex.Pattern;

/**
 * ファイル拡張子（拡張子あり）
 */
@ToString(includeFieldNames=false)
@EqualsAndHashCode
public class ValidFileSuffix implements FileSuffix {
    private final String lowerCaseValue;

    public ValidFileSuffix(String value) {
        this.lowerCaseValue = value.toLowerCase();
    }

    @Override
    public String getValueLowerCaseWithDot() {
        return "." + this.lowerCaseValue;
    }

    @Override
    public boolean isJpeg() {
        return this.lowerCaseValue.equals("jpg") || this.lowerCaseValue.equals("jpeg");
    }

    @Override
    public boolean isGif() {
        return this.lowerCaseValue.equals("gif");
    }

    @Override
    public boolean isPng() {
        return this.lowerCaseValue.equals("png");
    }

    public static FileSuffix createFileSuffixByFileName(String fileName) {
        // ピリオドがファイル名の途中であること
        int indexSuffix = fileName.lastIndexOf('.');
        if (indexSuffix <= 0 || indexSuffix >= fileName.length() - 1) {
            return new NullFileSuffix();
        }

        // 拡張子が英数字のみでない場合は、拡張子として扱わない
        String suffix = fileName.substring(indexSuffix + 1);
        if (Pattern.compile("^[0-9A-Za-z]+$").matcher(suffix).find() == false) {
            return new NullFileSuffix();
        }

        return new ValidFileSuffix(suffix);
    }
}
