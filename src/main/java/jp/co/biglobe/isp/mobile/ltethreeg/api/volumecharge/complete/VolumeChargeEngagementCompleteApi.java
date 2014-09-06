package jp.co.biglobe.isp.mobile.ltethreeg.api.volumecharge.complete;

import jp.co.biglobe.isp.mobile.ltethreeg.service.charge.VolumeChargeEngagementCompleteService;
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
public class VolumeChargeEngagementCompleteApi {

    @Autowired
    private AlarmValidationVerifier alarmValidationVerifier;

    @Autowired
    private VolumeChargeEngagementCompleteService volumeChargeEngagementCompleteService;

    @Autowired
    private UpdateApiResponse updateApiResponse;

    @RequestMapping(value = "/lte3g/skip/option/volumecharge/engagement/complete/submit", method = RequestMethod.POST)
    @ResponseBody
    public Map invoke(@Valid VolumeChargeEngagementCompleteRequest request, Errors errors) {

        alarmValidationVerifier.verify(errors);

        volumeChargeEngagementCompleteService.complete(request.getLteThreeGEngagementNumberForm().getValueObject());

        return updateApiResponse.build();
    }

}
