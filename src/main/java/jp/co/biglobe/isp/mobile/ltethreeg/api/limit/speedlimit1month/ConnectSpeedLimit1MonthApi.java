package jp.co.biglobe.isp.mobile.ltethreeg.api.limit.speedlimit1month;

import jp.co.biglobe.isp.mobile.ltethreeg.service.ConnectSpeedLimit1MonthService;
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
public class ConnectSpeedLimit1MonthApi {

    @Autowired
    private AlarmValidationVerifier alarmValidationVerifier;

    @Autowired
    private ConnectSpeedLimit1MonthService connectSpeedLimit1MonthService;

    @Autowired
    private UpdateApiResponse updateApiResponse;

    @RequestMapping(value = "/lte3g/skip/connectperformance/limit/1month/submit", method = RequestMethod.POST)
    @ResponseBody
    public Map invoke(@Valid ConnectSpeedLimit1MonthRequest request, Errors errors) {

        alarmValidationVerifier.verify(errors);

        connectSpeedLimit1MonthService.limit(request.getLteThreeGEngagementNumberForm().getValueObject());

        return updateApiResponse.build();
    }

}
