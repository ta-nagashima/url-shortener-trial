package jp.co.biglobe.isp.mobile.voiceoption.domain.identificationfiles;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * ファイル拡張子（拡張子なし）
 */
@ToString(includeFieldNames=false)
@EqualsAndHashCode
@AllArgsConstructor
public class NullFileSuffix implements FileSuffix {
    @Override
    public String getValueLowerCaseWithDot() {
        return "";
    }

    @Override
    public boolean isJpeg() {
        return false;
    }

    @Override
    public boolean isGif() {
        return false;
    }

    @Override
    public boolean isPng() {
        return false;
    }
}
