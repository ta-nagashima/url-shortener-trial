package jp.co.biglobe.isp.mobile.biglobedenwa.api.linkage.applyfromapp

import jp.co.biglobe.isp.mobile.biglobedenwa.domain.linkage.BiglobeDenwaLinkageStatus
import jp.co.biglobe.lib.plugin.response.normal.UpdateApiResponse
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class BiglobeDenwaLinkageApplyFromAppResponse {

    @Autowired
    private UpdateApiResponse updateApiResponse;

    public Map build(){
        updateApiResponse.build()
    }


}
