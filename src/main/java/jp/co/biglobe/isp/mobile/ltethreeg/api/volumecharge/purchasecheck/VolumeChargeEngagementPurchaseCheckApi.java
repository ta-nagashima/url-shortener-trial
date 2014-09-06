package jp.co.biglobe.isp.mobile.ltethreeg.api.volumecharge.purchasecheck;

import jp.co.biglobe.isp.mobile.ltethreeg.api.volumecharge.complete.VolumeChargeEngagementCompleteRequest;
import jp.co.biglobe.isp.mobile.ltethreeg.option.charge.domain.volumechargeengagement.VolumeChargePurchaseCheckStatus;
import jp.co.biglobe.isp.mobile.ltethreeg.service.charge.VolumeChargeEngagementPurchaseService;
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
public class VolumeChargeEngagementPurchaseCheckApi {

    @Autowired
    private AlarmValidationVerifier alarmValidationVerifier;

    @Autowired
    private VolumeChargeEngagementPurchaseService volumeChargeEngagementPurchaseService;

    @Autowired
    private VolumeChargeEngagementPurchaseCheckResponse volumeChargeEngagementPurchaseCheckResponse;

    @RequestMapping(value = "/lte3g/skip/option/volumecharge/engagement/purchase/check", method = RequestMethod.POST)
    @ResponseBody
    public Map invoke(@Valid VolumeChargeEngagementCompleteRequest request, Errors errors) {

        alarmValidationVerifier.verify(errors);

        VolumeChargePurchaseCheckStatus volumeChargePurchaseCheckStatus = volumeChargeEngagementPurchaseService.check(request.getLteThreeGEngagementNumberForm().getValueObject());

        return volumeChargeEngagementPurchaseCheckResponse.build(volumeChargePurchaseCheckStatus);
    }

}
