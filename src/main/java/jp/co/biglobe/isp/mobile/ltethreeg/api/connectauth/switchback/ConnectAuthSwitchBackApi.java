package jp.co.biglobe.isp.mobile.ltethreeg.api.connectauth.switchback;

import jp.co.biglobe.isp.mobile.ltethreeg.service.ConnectAuthChangeService;
import jp.co.biglobe.lib.plugin.response.normal.UpdateApiResponse;
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
public class ConnectAuthSwitchBackApi {

    @Autowired
    private AlarmValidationVerifier alarmValidationVerifier;

    @Autowired
    private ConnectAuthChangeService connectAuthChangeService;

    @Autowired
    private UpdateApiResponse updateApiResponse;

    @RequestMapping(value = "/lte3g/skip/connectauth/switchback/submit", method = RequestMethod.POST)
    @ResponseBody
    public Map invoke(@Valid ConnectAuthSwitchBackRequest request,Errors errors){

        alarmValidationVerifier.verify(errors);

        connectAuthChangeService.changeUserToChargeFree(
                request.getMsisdnForm().getValueObject(),
                request.getLteThreeGEngagementNumberForm().getValueObject()
        );

        return updateApiResponse.build();
    }

}
