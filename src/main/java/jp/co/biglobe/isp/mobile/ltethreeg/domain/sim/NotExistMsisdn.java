package jp.co.biglobe.isp.mobile.ltethreeg.domain.sim;

import jp.co.biglobe.isp.mobile.ltethreeg.domain.connectauth.InternationalMsisdn;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@ToString(includeFieldNames = false)
@EqualsAndHashCode
@AllArgsConstructor
public class NotExistMsisdn implements Msisdn {

    @Override
    public String getValue(){
        return "";
    }

    @Override
    public InternationalMsisdn getInternationalMsisdn() {
        return new InternationalMsisdn("");
    }

    @Override
    public boolean isExist() {
        return false;
    }
}
