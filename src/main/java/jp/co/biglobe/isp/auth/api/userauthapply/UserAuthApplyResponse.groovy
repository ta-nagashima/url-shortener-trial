package jp.co.biglobe.isp.auth.api.userauthapply

import jp.co.biglobe.isp.auth.domain.user.ValidUserAuth
import jp.co.biglobe.lib.plugin.view.JsonTemplate
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class UserAuthApplyResponse {

    @Autowired
    private JsonTemplate template;

    public Map build(ValidUserAuth validUserAuth){
        template.build([
                "userId" : validUserAuth.getUserId().getValue(),
                "fullName" : validUserAuth.getFullName().getValue(),
                "contractType" : validUserAuth.getContractType().getApiValue()
        ])
    }
}
