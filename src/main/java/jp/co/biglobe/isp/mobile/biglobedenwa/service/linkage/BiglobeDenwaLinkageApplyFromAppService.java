package jp.co.biglobe.isp.mobile.biglobedenwa.service.linkage;

import jp.co.biglobe.isp.mobile.biglobedenwa.domain.linkage.*;
import jp.co.biglobe.lib.plugin.event.RequestEventTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BiglobeDenwaLinkageApplyFromAppService {

    @Autowired
    private BiglobeDenwaLinkageRepository linkageRepository;

    @Autowired
    private RequestEventTime requestEventTime;

    public void apply(BiglobeDenwaMsisdn biglobeDenwaMsisdn) {

        BiglobeDenwaLinkage before = linkageRepository.findByMsisdnForUpdate(biglobeDenwaMsisdn);

        ValidBiglobeDenwaLinkage after = before.applyFromApp(
                biglobeDenwaMsisdn,
                new BiglobeDenwaStatusChangeDateTime(requestEventTime.getDate()));

        linkageRepository.applyFromApp(before, after);
    }

}
