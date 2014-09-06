package jp.co.biglobe.isp.mobile.biglobedenwa.service.linkage;

import jp.co.biglobe.isp.mobile.biglobedenwa.domain.linkage.*;
import jp.co.biglobe.lib.plugin.event.RequestEventTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class BiglobeDenwaLinkageApplyFromBatchService {

    @Autowired
    private BiglobeDenwaLinkageRepository linkageRepository;

    @Autowired
    private RequestEventTime requestEventTime;

    public void apply(BiglobeDenwaMsisdn biglobeDenwaMsisdn, BiglobeDenwaApplyChannel biglobeDenwaApplyChannel) {

        BiglobeDenwaLinkage before = linkageRepository.findByMsisdn(biglobeDenwaMsisdn);
        ValidBiglobeDenwaLinkage after = before.applyFromBatch(
                biglobeDenwaMsisdn,
                new BiglobeDenwaStatusChangeDateTime(requestEventTime.getDate()),
                biglobeDenwaApplyChannel);

        linkageRepository.applyFromBatch(after);

    }

}
