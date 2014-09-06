package jp.co.biglobe.isp.mobile.biglobedenwa.domain.auth;


import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@ToString(includeFieldNames=false)
@EqualsAndHashCode
@AllArgsConstructor
public class BiglobeDenwaBirthday {

    private final String value;

    public String getApiValue() { return value;}


}
