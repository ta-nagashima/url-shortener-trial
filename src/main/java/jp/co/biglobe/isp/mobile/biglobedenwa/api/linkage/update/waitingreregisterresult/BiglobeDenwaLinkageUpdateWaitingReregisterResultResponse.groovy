package jp.co.biglobe.isp.mobile.biglobedenwa.api.linkage.update.waitingreregisterresult

import jp.co.biglobe.isp.mobile.voiceoption.domain.mnpin.MnpIn
import jp.co.biglobe.lib.plugin.response.normal.UpdateApiResponse
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class BiglobeDenwaLinkageUpdateWaitingReregisterResultResponse {

    @Autowired
    private UpdateApiResponse updateApiResponse;

    public Map build(MnpIn mnpIn){

        def map;

        if(mnpIn.isExist()){
            map = [ "mnpInStatus" : "exist"]
        } else {
            map = [ "mnpInStatus" : "not_exist" ]
        }




        updateApiResponse.build(map)
    }


}
