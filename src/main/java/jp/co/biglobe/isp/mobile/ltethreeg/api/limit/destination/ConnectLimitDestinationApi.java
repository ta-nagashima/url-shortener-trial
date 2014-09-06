package jp.co.biglobe.isp.mobile.ltethreeg.api.limit.destination;

import jp.co.biglobe.isp.mobile.ltethreeg.service.ConnectDestinationLimitService;
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
public class ConnectLimitDestinationApi {

    @Autowired
    private AlarmValidationVerifier alarmValidationVerifier;

    @Autowired
    private ConnectDestinationLimitService connectDestinationLimitService;

    @Autowired
    private UpdateApiResponse updateApiResponse;

    @RequestMapping(value = "/lte3g/skip/connectperformance/limit/destination/submit", method = RequestMethod.POST)
    @ResponseBody
    public Map invoke(@Valid ConnectLimitDestinationRequest request, Errors errors) {

        alarmValidationVerifier.verify(errors);

        connectDestinationLimitService.limit(request.getLteThreeGEngagementNumberForm().getValueObject());

        return updateApiResponse.build();
    }

}
