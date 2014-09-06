package jp.co.biglobe.isp.auth.api.operatornouserauthapply

import jp.co.biglobe.isp.auth.domain.operatornouser.ValidOperatorNoUserAuth
import jp.co.biglobe.lib.plugin.view.JsonTemplate
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class OperatorNoUserAuthApplyResponse {

    @Autowired
    private JsonTemplate template;

    public Map build(ValidOperatorNoUserAuth validOperatorNoUserAuth){
        template.build([
                "userId" : validOperatorNoUserAuth.getOperatorId().getValue(),
                "fullName" : validOperatorNoUserAuth.getFullName().getValue()
        ])
    }
}
