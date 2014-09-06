package jp.co.biglobe.isp.mobile.ltethreeg.api.planchange.decision;

import jp.co.biglobe.isp.mobile.ltethreeg.service.PlanChangeDecisionService;
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
public class LteThreeGPlanChangeDecisionApi {

    @Autowired
    private AlarmValidationVerifier alarmValidationVerifier;

    @Autowired
    private PlanChangeDecisionService planChangeDecisionService;

    @Autowired
    private UpdateApiResponse updateApiResponse;

    @RequestMapping(value = "/lte3g/skip/engagement/planchange/decision/submit", method = RequestMethod.POST)
    @ResponseBody
    public Map invoke(@Valid LteThreeGPlanChangeDecisionRequest request, Errors errors) {

        alarmValidationVerifier.verify(errors);

        planChangeDecisionService.planChange(
                request.getLteThreeGEngagementNumberForm().getValueObject(),
                request.getLteThreeGServicePlan());

        return updateApiResponse.build();
    }

}
