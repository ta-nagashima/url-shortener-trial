package jp.co.biglobe.isp.mobile.voiceoption.api.mnpout.mnpoutapply
import jp.co.biglobe.lib.plugin.response.normal.UpdateApiResponse
import jp.co.biglobe.isp.mobile.voiceoption.domain.voicesendmail.VoiceSendMailStatus
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class MnpOutApplyResponse {

    @Autowired
    private UpdateApiResponse updateApiResponse;

    public Map build(VoiceSendMailStatus voiceSendMailStatus){

        Map resultMap = [ "mnpOutApplyMailStatus" : voiceSendMailStatus.getApiValue()]

        updateApiResponse.build(resultMap);
    }

}
