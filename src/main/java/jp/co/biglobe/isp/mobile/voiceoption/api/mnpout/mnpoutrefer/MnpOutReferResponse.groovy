package jp.co.biglobe.isp.mobile.voiceoption.api.mnpout.mnpoutrefer

import jp.co.biglobe.isp.mobile.voiceoption.domain.mnpout.MnpOut
import jp.co.biglobe.lib.plugin.view.JsonTemplate
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

/**
 * MNP転出参照
 */
@Component
class MnpOutReferResponse {

    @Autowired
    private JsonTemplate template;

    public Map build(MnpOut mnpOut) {
        template.build([
                "status" : mnpOut.getMnpOutStatus().getReferApiValue(),
        ]);
    }
}
