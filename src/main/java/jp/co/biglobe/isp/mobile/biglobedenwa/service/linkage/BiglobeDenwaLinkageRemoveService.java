package jp.co.biglobe.isp.mobile.biglobedenwa.service.linkage;

import jp.co.biglobe.isp.mobile.biglobedenwa.domain.linkage.*;

import jp.co.biglobe.lib.plugin.event.RequestEventTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BiglobeDenwaLinkageRemoveService {

    @Autowired
    private RequestEventTime requestEventTime;

    @Autowired
    private BiglobeDenwaLinkageRepository linkageRepository;

    public void remove(BiglobeDenwaMsisdn biglobeDenwaMsisdn) {

        BiglobeDenwaLinkage linkage = linkageRepository.findByMsisdnForUpdate(biglobeDenwaMsisdn);

        ValidBiglobeDenwaLinkage after = linkage.remove(new BiglobeDenwaStatusChangeDateTime(requestEventTime.getDate()));

        linkageRepository.remove(after);

    }

}
