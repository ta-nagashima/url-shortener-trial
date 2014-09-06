package jp.co.biglobe.isp.auth.api.operatorwithuserauthapply

import jp.co.biglobe.isp.auth.domain.operatorwithuser.OperatorWithUserAuth
import jp.co.biglobe.lib.plugin.view.JsonTemplate
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class OperatorWithUserAuthApplyResponse {

    @Autowired
    private JsonTemplate template;

    public Map build(OperatorWithUserAuth operatorWithUserAuth){
        template.build([
                "userId" : operatorWithUserAuth.getSimpleUserAuth().getUserId().getValue(),
                "userFullName" : operatorWithUserAuth.getSimpleUserAuth().getFullName().getValue(),
                "operatorId" : operatorWithUserAuth.getValidOperatorNoUserAuth().getOperatorId().getValue(),
                "operatorFullName" : operatorWithUserAuth.getValidOperatorNoUserAuth().getFullName().getValue()
        ])
    }
}
