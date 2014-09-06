package jp.co.biglobe.isp.mobile.ltethreeg.api.speedcharge.purchase;

import jp.co.biglobe.isp.mobile.ltethreeg.service.charge.SpeedChargeEngagementPurchaseService;
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
public class SpeedChargeEngagementPurchaseApi {

    @Autowired
    private AlarmValidationVerifier alarmValidationVerifier;

    @Autowired
    private SpeedChargeEngagementPurchaseService speedChargeEngagementPurchaseService;

    @Autowired
    private UpdateApiResponse updateApiResponse;

    @RequestMapping(value = "/lte3g/skip/option/speedcharge/engagement/purchase/submit", method = RequestMethod.POST)
    @ResponseBody
    public Map invoke(@Valid SpeedChargeEngagementPurchaseRequest request, Errors errors) {

        alarmValidationVerifier.verify(errors);

        speedChargeEngagementPurchaseService.purchase(
                request.getLteThreeGEngagementNumberForm().getValueObject(),
                request.getSpeedChargePurchasedVolumeNumberForm().getValueObject());

        return updateApiResponse.build();
    }

}
