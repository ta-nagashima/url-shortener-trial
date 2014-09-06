package jp.co.biglobe.isp.mobile.ltethreeg.domain.sim;

import jp.co.biglobe.isp.mobile.ltethreeg.domain.connectauth.InternationalMsisdn;

public interface Msisdn {

    public String getValue();

    public InternationalMsisdn getInternationalMsisdn();

    public boolean isExist();
}
