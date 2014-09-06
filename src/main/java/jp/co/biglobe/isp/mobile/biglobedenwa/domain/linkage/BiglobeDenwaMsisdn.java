package jp.co.biglobe.isp.mobile.biglobedenwa.domain.linkage;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@ToString(includeFieldNames=false)
@EqualsAndHashCode
@AllArgsConstructor
public class BiglobeDenwaMsisdn {
    private final String value;

    public String getApiValue(){
        return value;
    }
}
