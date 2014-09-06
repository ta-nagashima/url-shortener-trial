package jp.co.biglobe.isp.mobile.ltethreeg.domain.sim;

import jp.co.biglobe.isp.mobile.ltethreeg.domain.connectauth.InternationalMsisdn;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@ToString(includeFieldNames = false)
@EqualsAndHashCode
@AllArgsConstructor
public class ValidMsisdn implements Msisdn {

    @Getter
    private final String value;

    private final String COUNTRY_CODE_FOR_JAPAN = "81";

    @Override
    public InternationalMsisdn getInternationalMsisdn(){
        return new InternationalMsisdn(COUNTRY_CODE_FOR_JAPAN + value.substring(1).replaceAll("-", ""));
    }

    @Override
    public boolean isExist() {
        return true;
    }
}
