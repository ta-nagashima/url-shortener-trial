package jp.co.biglobe.isp.mobile.biglobedenwa.domain.rocky;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@ToString(includeFieldNames=false)
@EqualsAndHashCode
@AllArgsConstructor
public class RockySuccessFile {
    private final String value;

    public String getApiValue(){
        return value;
    }
}
