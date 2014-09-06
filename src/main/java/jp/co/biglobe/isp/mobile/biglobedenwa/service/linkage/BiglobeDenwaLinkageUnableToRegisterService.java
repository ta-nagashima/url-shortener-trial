package jp.co.biglobe.isp.mobile.biglobedenwa.service.linkage;

import jp.co.biglobe.isp.mobile.biglobedenwa.domain.linkage.*;
import jp.co.biglobe.lib.plugin.event.RequestEventTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * BIGLOBEでんわのステータスをUNABLE_TO_REGISTERに更新するサービス。
 * ユースケース的に、MNPINがないときは呼ばれないが、
 * そこのチェックは入れていない。
 * （呼び出しもとのAPIで判断）
 */

@Service
public class BiglobeDenwaLinkageUnableToRegisterService {

    @Autowired
    private RequestEventTime requestEventTime;

    @Autowired
    private BiglobeDenwaLinkageRepository linkageRepository;

    public void update(BiglobeDenwaMsisdn biglobeDenwaMsisdn) {

        BiglobeDenwaLinkage before = linkageRepository.findByMsisdnForUpdate(biglobeDenwaMsisdn);

        ValidBiglobeDenwaLinkage after = before.updateUnableToRegister(
                new BiglobeDenwaStatusChangeDateTime(requestEventTime.getDate()));

        linkageRepository.updateUnableToRegister(after);
    }

}
