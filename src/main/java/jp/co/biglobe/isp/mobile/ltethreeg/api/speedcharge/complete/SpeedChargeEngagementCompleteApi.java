package jp.co.biglobe.isp.mobile.ltethreeg.api.speedcharge.complete;

import jp.co.biglobe.isp.mobile.ltethreeg.api.speedcharge.purchasecheck.SpeedChargeEngagementPurchaseCheckRequest;
import jp.co.biglobe.isp.mobile.ltethreeg.service.charge.SpeedChargeEngagementCompleteService;
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
public class SpeedChargeEngagementCompleteApi {

    @Autowired
    private AlarmValidationVerifier alarmValidationVerifier;

    @Autowired
    private SpeedChargeEngagementCompleteService speedChargeEngagementCompleteService;

    @Autowired
    private UpdateApiResponse updateApiResponse;

    @RequestMapping(value = "/lte3g/skip/option/speedcharge/engagement/complete/submit", method = RequestMethod.POST)
    @ResponseBody
    public Map invoke(@Valid SpeedChargeEngagementPurchaseCheckRequest request, Errors errors) {

        alarmValidationVerifier.verify(errors);

        speedChargeEngagementCompleteService.complete(request.getLteThreeGEngagementNumberForm().getValueObject());

        return updateApiResponse.build();
    }

}
