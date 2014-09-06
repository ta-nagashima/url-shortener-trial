package jp.co.biglobe.isp.mobile.biglobedenwa.service.linkage;

import jp.co.biglobe.isp.mobile.biglobedenwa.domain.linkage.BiglobeDenwaLinkage;
import jp.co.biglobe.isp.mobile.biglobedenwa.domain.linkage.BiglobeDenwaLinkageRepository;
import jp.co.biglobe.isp.mobile.biglobedenwa.domain.linkage.BiglobeDenwaMsisdn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BiglobeDenwaLinkageReferService {

    @Autowired
    BiglobeDenwaLinkageRepository linkageRepository;

    public BiglobeDenwaLinkage refer(BiglobeDenwaMsisdn biglobeDenwaMsisdn){
        return linkageRepository.findByMsisdn(biglobeDenwaMsisdn);
    }

}
