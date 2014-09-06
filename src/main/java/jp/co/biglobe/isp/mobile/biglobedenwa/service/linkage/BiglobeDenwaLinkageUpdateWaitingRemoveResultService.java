package jp.co.biglobe.isp.mobile.biglobedenwa.service.linkage;

import jp.co.biglobe.isp.mobile.biglobedenwa.domain.linkage.*;
import jp.co.biglobe.lib.plugin.event.RequestEventTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BiglobeDenwaLinkageUpdateWaitingRemoveResultService {

    @Autowired
    private RequestEventTime requestEventTime;

    @Autowired
    private BiglobeDenwaLinkageRepository linkageRepository;

    public void update(BiglobeDenwaMsisdn biglobeDenwaMsisdn) {

        BiglobeDenwaLinkage before = linkageRepository.findByMsisdnForUpdate(biglobeDenwaMsisdn);

        ValidBiglobeDenwaLinkage after = before.updateWaitingRemoveResult(new BiglobeDenwaStatusChangeDateTime(requestEventTime.getDate()));

        linkageRepository.updateWaitingRemoveResult(after);

    }

}
