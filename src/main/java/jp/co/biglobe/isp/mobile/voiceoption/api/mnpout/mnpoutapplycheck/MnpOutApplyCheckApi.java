package jp.co.biglobe.isp.mobile.voiceoption.api.mnpout.mnpoutapplycheck;

import jp.co.biglobe.isp.mobile.voiceoption.api.mnpout.mnpoutrefer.MnpOutReferRequest;
import jp.co.biglobe.isp.mobile.voiceoption.domain.container.MnpOutApplyCheckStatusContainer;
import jp.co.biglobe.isp.mobile.voiceoption.service.mnpout.MnpOutApplyCheckService;
import jp.co.biglobe.lib.plugin.validationverifier.AlarmValidationVerifier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.util.Map;

/**
 * MNP転出申込可否チェック
 */
@Controller
public class MnpOutApplyCheckApi {

    @Autowired
    private AlarmValidationVerifier alarmValidationVerifier;

    @Autowired
    private MnpOutApplyCheckService mnpOutApplyCheckService;

    @Autowired
    private MnpOutApplyCheckResponse mnpOutApplyCheckResponse;

    @RequestMapping(value = "/voiceoption/outside/skip/mnpoutapply/check", method = RequestMethod.POST)
    @ResponseBody
    public Map invoke(@Valid MnpOutReferRequest request, Errors errors) {

        alarmValidationVerifier.verify(errors);
        MnpOutApplyCheckStatusContainer mnpOutApplyCheckStatusContainer = mnpOutApplyCheckService.check(
                request.getLteThreeGEngagementNumberForm().getValueObject()
        );

        return mnpOutApplyCheckResponse.build(mnpOutApplyCheckStatusContainer);

    }



}
