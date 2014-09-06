package jp.co.biglobe.isp.mobile.voiceoption.api.simchange.simchangecheck

import jp.co.biglobe.lib.plugin.response.normal.CheckApiResponse
import jp.co.biglobe.isp.mobile.voiceoption.domain.policy.SimChangeCheckStatus
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

/**
 * Created by k-kawamura on 2014/04/19.
 */
@Component
class VoiceSimChangeCheckResponse {

    @Autowired
    private CheckApiResponse checkApiResponse;

    public Map build(SimChangeCheckStatus simSizeChangeCheckStatus) {
        checkApiResponse.build(simSizeChangeCheckStatus);
    }
}
