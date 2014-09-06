package jp.co.biglobe.isp.mobile.ltethreeg.api.speedcharge.purchasecheck;

import jp.co.biglobe.isp.mobile.ltethreeg.api.speedcharge.complete.SpeedChargeEngagementCompleteRequest;
import jp.co.biglobe.isp.mobile.ltethreeg.option.charge.domain.speedchargeengagement.SpeedChargePurchaseCheckStatus;
import jp.co.biglobe.isp.mobile.ltethreeg.service.charge.SpeedChargeEngagementPurchaseService;
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
public class SpeedChargeEngagementPurchaseCheckApi {

    @Autowired
    private AlarmValidationVerifier alarmValidationVerifier;

    @Autowired
    private SpeedChargeEngagementPurchaseService speedChargeEngagementPurchaseService;

    @Autowired
    private SpeedChargeEngagementPurchaseCheckResponse speedChargeEngagementPurchaseCheckResponse;

    @RequestMapping(value = "/lte3g/skip/option/speedcharge/engagement/purchase/check", method = RequestMethod.POST)
    @ResponseBody
    public Map invoke(@Valid SpeedChargeEngagementCompleteRequest request, Errors errors) {

        alarmValidationVerifier.verify(errors);

        SpeedChargePurchaseCheckStatus speedChargePurchaseCheckStatus = speedChargeEngagementPurchaseService.check(request.getLteThreeGEngagementNumberForm().getValueObject());

        return speedChargeEngagementPurchaseCheckResponse.build(speedChargePurchaseCheckStatus);
    }

}
