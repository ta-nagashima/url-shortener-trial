package jp.co.biglobe.isp.mobile.ltethreeg.api.limitrelease.speedlimit72hour;

import jp.co.biglobe.isp.mobile.ltethreeg.service.ConnectSpeedLimitRelease72HourService;
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
public class ConnectSpeedLimitRelease72HourApi {

    @Autowired
    private AlarmValidationVerifier alarmValidationVerifier;

    @Autowired
    private ConnectSpeedLimitRelease72HourService connectSpeedLimitRelease72HourService;

    @Autowired
    private UpdateApiResponse updateApiResponse;

    @RequestMapping(value = "/lte3g/skip/connectperformance/limitrelease/72hour/submit", method = RequestMethod.POST)
    @ResponseBody
    public Map invoke(@Valid ConnectSpeedLimitRelease72HourRequest request, Errors errors) {

        alarmValidationVerifier.verify(errors);

        connectSpeedLimitRelease72HourService.limit(request.getLteThreeGEngagementNumberForm().getValueObject());

        return updateApiResponse.build();
    }

}
