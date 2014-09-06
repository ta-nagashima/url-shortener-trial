package jp.co.biglobe.isp.mobile.voiceoption.domain.identificationfiles;

/**
 * Created by k-kawamura on 2014/04/24.
 */
public interface FileSuffix {
    public String getValueLowerCaseWithDot();
    public boolean isJpeg();
    public boolean isGif();
    public boolean isPng();
}
