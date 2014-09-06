package jp.co.biglobe.isp.mobile.ltethreeg.api.charge.engagementdetailrefer;

import jp.co.biglobe.isp.mobile.ltethreeg.service.charge.ChargeEngagementDetailReferService;
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
public class ChargeEngagementDetailReferApi {

    @Autowired
    private AlarmValidationVerifier alarmValidationVerifier;

    @Autowired
    private ChargeEngagementDetailReferService chargeEngagementDetailReferService;

    @Autowired
    private ChargeEngagementDetailReferResponse chargeEngagementDetailReferResponse;

    @RequestMapping(value = "/lte3g/skip/option/charge/engagement/detail/refer", method = RequestMethod.POST)
    @ResponseBody
    public Map invoke(@Valid ChargeEngagementDetailReferRequest request, Errors errors) {

        alarmValidationVerifier.verify(errors);

        ChargeEngagementDetailReferService.ChargeEngagementDetailReferContainer chargeEngagementDetailReferContainer =
                chargeEngagementDetailReferService.refer(request.getLteThreeGEngagementNumberForm().getValueObject());

        return chargeEngagementDetailReferResponse.build(chargeEngagementDetailReferContainer);
    }

}
