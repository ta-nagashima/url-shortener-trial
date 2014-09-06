package jp.co.biglobe.isp.mobile.voiceoption.api.mnpout.mnpreservationnumberregister

import jp.co.biglobe.lib.plugin.response.normal.UpdateApiResponse
import jp.co.biglobe.isp.mobile.voiceoption.domain.voicesendmail.VoiceSendMailStatus
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class MnpReservationNumberRegisterResponse {

    @Autowired
    private UpdateApiResponse updateApiResponse;

    public Map build(VoiceSendMailStatus voiceSendMailStatus){

        Map resultMap = [ "mnpOutReservationNumberMailStatus" : voiceSendMailStatus.getApiValue()]

        updateApiResponse.build(resultMap);
    }

}