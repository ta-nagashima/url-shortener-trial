package jp.co.biglobe.isp.mobile.voiceoption.api.msisdndoubleregistrationcheck

import jp.co.biglobe.lib.plugin.response.normal.CheckApiResponse
import jp.co.biglobe.isp.mobile.voiceoption.domain.policy.MsisdnDoubleRegistrationCheckStatus
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class MsisdnDoubleRegistrationCheckResponse {

    @Autowired
    private CheckApiResponse checkApiResponse;

    public Map build(MsisdnDoubleRegistrationCheckStatus msisdnDoubleRegistrationCheckStatus) {
        checkApiResponse.build(msisdnDoubleRegistrationCheckStatus);
    }
}
