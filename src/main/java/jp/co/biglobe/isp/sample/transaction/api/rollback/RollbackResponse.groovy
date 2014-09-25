package jp.co.biglobe.isp.sample.transaction.api.rollback

import jp.co.biglobe.lib.plugin.response.normal.UpdateApiResponse
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class RollbackResponse {

    @Autowired
    private UpdateApiResponse updateApiResponse;

    public Map build() {
        updateApiResponse.build();
    }
}
