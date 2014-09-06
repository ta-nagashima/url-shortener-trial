package jp.co.biglobe.isp.auth.api.operatornouserauthapply;

import jp.co.biglobe.isp.auth.domain.operatornouser.ValidOperatorNoUserAuth;
import jp.co.biglobe.isp.auth.service.OperatorNoUserAuthService;
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
public class OperatorNoUserAuthApplyApi {

    @Autowired
    private AlarmValidationVerifier alarmValidationVerifier;

    @Autowired
    private OperatorNoUserAuthService operatorNoUserAuthService;

    @Autowired
    private OperatorNoUserAuthApplyResponse operatorNoUserAuthApplyResponse;


    @RequestMapping(value = "/auth/operator/operatornouserauthapply", method = RequestMethod.POST)
    @ResponseBody
    public Map refer(@Valid OperatorNoUserAuthRequestSample request, Errors errors) {

        alarmValidationVerifier.verify(errors);

        ValidOperatorNoUserAuth validOperatorNoUserAuth = operatorNoUserAuthService.auth(request.getOperatorNoUserAuthRequest().getOperatorNoUserAuthManagement());

        return operatorNoUserAuthApplyResponse.build(validOperatorNoUserAuth);
    }
}
