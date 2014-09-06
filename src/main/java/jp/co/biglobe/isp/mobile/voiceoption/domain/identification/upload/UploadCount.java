package jp.co.biglobe.isp.mobile.voiceoption.domain.identification.upload;

import jp.co.biglobe.isp.mobile.extension.valueobject.ValueObjectConvertForApi;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@ToString(includeFieldNames=false)
@EqualsAndHashCode
@AllArgsConstructor
class UploadCount implements ValueObjectConvertForApi {

    private static final int MAX_UPLOAD_LIMIT_COUNT = 1;

    private final Integer value;

    boolean isOverLimitCount(){
        return value >= MAX_UPLOAD_LIMIT_COUNT;
    }

    public UploadCount countUp() {
        return new UploadCount(this.value + 1);
    }

    @Override
    public String getApiValue() {return String.valueOf(this.value);}

}
