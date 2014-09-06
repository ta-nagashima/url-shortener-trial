package jp.co.biglobe.isp.mobile.ltethreeg.api.planchange.reservation;

import jp.co.biglobe.isp.mobile.ltethreeg.service.PlanChangeReservationService;
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
public class LteThreeGPlanChangeReservationApi {

    @Autowired
    private AlarmValidationVerifier alarmValidationVerifier;

    @Autowired
    private PlanChangeReservationService planChangeReservationService;

    @Autowired
    private UpdateApiResponse updateApiResponse;

    @RequestMapping(value = "/lte3g/skip/engagement/planchange/reservation/submit", method = RequestMethod.POST)
    @ResponseBody
    public Map invoke(@Valid LteThreeGPlanChangeReservationRequest request, Errors errors) {

        alarmValidationVerifier.verify(errors);

        planChangeReservationService.planChange(
                request.getLteThreeGEngagementNumberForm().getValueObject(),
                request.getLteThreeGServicePlan());

        return updateApiResponse.build();
    }

}
