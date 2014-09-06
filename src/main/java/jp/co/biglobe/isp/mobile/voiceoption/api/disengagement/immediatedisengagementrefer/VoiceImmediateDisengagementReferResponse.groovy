package jp.co.biglobe.isp.mobile.voiceoption.api.disengagement.immediatedisengagementrefer

import jp.co.biglobe.isp.mobile.voiceoption.domain.mnpout.MnpOutStatus
import jp.co.biglobe.lib.plugin.view.JsonTemplate
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

/**
 * 即時解約参照
 */
@Component
public class VoiceImmediateDisengagementReferResponse {

    @Autowired
    private JsonTemplate template;

    public Map build(MnpOutStatus mnpOutStatus) {
        template.build([
                "status" : mnpOutStatus.getImmediateDisengagementReferApiValue(),
        ]);
    }
}
