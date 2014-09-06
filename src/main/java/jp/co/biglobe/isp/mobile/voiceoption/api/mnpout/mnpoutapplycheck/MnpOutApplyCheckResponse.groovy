package jp.co.biglobe.isp.mobile.voiceoption.api.mnpout.mnpoutapplycheck

import jp.co.biglobe.isp.mobile.extension.response.normal.MultiCheckApiResponse
import jp.co.biglobe.isp.mobile.voiceoption.domain.container.MnpOutApplyCheckStatusContainer;
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

/**
 * MNP転出申込可否チェック
 */
@Component
class MnpOutApplyCheckResponse {

    @Autowired
    private MultiCheckApiResponse multiCheckApiResponse;

    public Map build(MnpOutApplyCheckStatusContainer mnpOutApplyCheckStatusContainer) {

        Map statusSet = [
                "mnp_out" : mnpOutApplyCheckStatusContainer.getMnpOutApplyCheckStatus(),
                "mnp_out_cancel" : mnpOutApplyCheckStatusContainer.getMnpOutApplyCancelCheckStatus()
        ]
        multiCheckApiResponse.build(statusSet);
    }
}
