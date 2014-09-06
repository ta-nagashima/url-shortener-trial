package jp.co.biglobe.isp.auth.api.userauthapply;

import jp.co.biglobe.isp.auth.api.request.UserAuthRequest;
import jp.co.biglobe.isp.auth.domain.user.ValidUserAuth;
import jp.co.biglobe.isp.auth.service.UserAuthService;
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
public class UserAuthApplyApi {

    @Autowired
    private AlarmValidationVerifier alarmValidationVerifier;

    @Autowired
    private UserAuthService userAuthService;

    @Autowired
    private UserAuthApplyResponse userAuthApplyResponse;


    @RequestMapping(value = "/auth/user/userauthapply", method = RequestMethod.POST)
    @ResponseBody
    public Map refer(@Valid UserAuthRequest request, Errors errors) {

        alarmValidationVerifier.verify(errors);

        ValidUserAuth validUserAuth = userAuthService.auth(request.getUserAuthManagement());

        return userAuthApplyResponse.build(validUserAuth);
    }
}
