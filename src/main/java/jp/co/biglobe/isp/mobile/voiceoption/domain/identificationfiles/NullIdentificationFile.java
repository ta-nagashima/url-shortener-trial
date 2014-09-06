package jp.co.biglobe.isp.mobile.voiceoption.domain.identificationfiles;

import lombok.EqualsAndHashCode;
import lombok.ToString;

/*
 * 本人確認書類オブジェクト（null）
 */
@ToString(includeFieldNames=false)
@EqualsAndHashCode
public class NullIdentificationFile implements IdentificationFile {
    @Override
    public boolean isExist() {
        return false;
    }
}
