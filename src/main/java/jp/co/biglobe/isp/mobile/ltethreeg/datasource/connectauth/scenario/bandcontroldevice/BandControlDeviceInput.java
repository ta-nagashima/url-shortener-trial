package jp.co.biglobe.isp.mobile.ltethreeg.datasource.connectauth.scenario.bandcontroldevice;

import jp.co.biglobe.isp.mobile.ltethreeg.domain.connectauth.InternationalMsisdn;
import jp.co.biglobe.isp.mobile.ltethreeg.domain.sim.Msisdn;
import jp.co.biglobe.lib.publication.scenario.annotation.Mapping;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class BandControlDeviceInput {

    //MSISDN
    @Mapping("msisdn")
    private final InternationalMsisdn internationalMsisdn;

    //制御ポリシー
    @Mapping("policy")
    private String connectControlPolicy;

    public BandControlDeviceInput(Msisdn msisdn,String connectControlPolicy){
        this(msisdn.getInternationalMsisdn(),connectControlPolicy);
    }

}
