package jp.co.biglobe.isp.mobile.ltethreeg.api.connectauth.changeanddestinationlimitrelease;

import jp.co.biglobe.isp.mobile.ltethreeg.service.ConnectAuthChangeAndDestinationLimitReleaseService;
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
public class ConnectAuthChangeAndDestinationLimitReleaseApi {
    @Autowired
    private AlarmValidationVerifier alarmValidationVerifier;

    @Autowired
    private ConnectAuthChangeAndDestinationLimitReleaseService connectAuthChangeAndDestinationLimitReleaseService;

    @Autowired
    private UpdateApiResponse updateApiResponse;

    @RequestMapping(value = "/lte3g/skip/connectauth/change-destinationlimitrelease/submit", method = RequestMethod.POST)
    @ResponseBody
    public Map invoke(@Valid ConnectAuthChangeAndDestinationLimitReleaseRequest request,Errors errors){

        alarmValidationVerifier.verify(errors);

        connectAuthChangeAndDestinationLimitReleaseService.changeAndRelease(
                request.getMsisdnForm().getValueObject(),
                request.getLteThreeGEngagementNumberForm().getValueObject()
        );

        return updateApiResponse.build();
    }
}
