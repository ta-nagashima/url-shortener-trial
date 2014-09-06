package jp.co.biglobe.isp.auth.api.operatorwithuserauthapply;

import jp.co.biglobe.isp.auth.api.request.OperatorWithUserAuthRequest;
import jp.co.biglobe.isp.auth.domain.operatorwithuser.OperatorWithUserAuth;
import jp.co.biglobe.isp.auth.service.OperatorWithUserAuthService;
import jp.co.biglobe.lib.plugin.validationverifier.AlarmValidationVerifier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.util.Map;

@Controller
public class OperatorWithUserAuthApplyApi {

    @Autowired
    private AlarmValidationVerifier alarmValidationVerifier;

    @Autowired
    private OperatorWithUserAuthService operatorWithUserAuthService;

    @Autowired
    private OperatorWithUserAuthApplyResponse operatorWithUserAuthApplyResponse;


    @RequestMapping(value = "/auth/operator/operatorwithuserauthapply", method = RequestMethod.POST)
    @ResponseBody
    public Map refer(@Valid OperatorWithUserAuthRequest request, Errors errors) {

        alarmValidationVerifier.verify(errors);

        OperatorWithUserAuth operatorWithUserAuth = operatorWithUserAuthService.auth(request.getOperatorWithUserAuthManagement());

        return operatorWithUserAuthApplyResponse.build(operatorWithUserAuth);
    }
}
