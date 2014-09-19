package jp.co.biglobe.isp.sample.user.api.samplerefer

import jp.co.biglobe.lib.plugin.response.normal.CheckApiResponse
import jp.co.biglobe.lib.publication.enumeration.businessstatus.BusinessStatusForApi
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class SampleReferResponse {

    @Autowired
    private CheckApiResponse checkApiResponse;

    public Map build(BusinessStatusForApi businessStatusForApi) {
        checkApiResponse.build(businessStatusForApi);
    }
}
