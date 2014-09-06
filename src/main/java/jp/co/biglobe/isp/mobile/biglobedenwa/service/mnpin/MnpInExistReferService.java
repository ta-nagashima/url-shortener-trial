package jp.co.biglobe.isp.mobile.biglobedenwa.service.mnpin;

import jp.co.biglobe.isp.mobile.biglobedenwa.domain.linkage.BiglobeDenwaMsisdn;
import jp.co.biglobe.isp.mobile.voiceoption.domain.mnpin.MnpIn;
import jp.co.biglobe.isp.mobile.voiceoption.domain.mnpin.MnpInRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MnpInExistReferService {

    @Autowired
    private MnpInRepository mnpInRepository;

    public MnpIn refer(BiglobeDenwaMsisdn biglobeDenwaMsisdn){

        return mnpInRepository.findByBiglobeDenwaMsisdnUnderValid(biglobeDenwaMsisdn);
    }

}
