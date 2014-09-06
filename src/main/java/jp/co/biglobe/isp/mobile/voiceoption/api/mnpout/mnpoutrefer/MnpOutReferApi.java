package jp.co.biglobe.isp.mobile.voiceoption.api.mnpout.mnpoutrefer;

import jp.co.biglobe.isp.mobile.voiceoption.domain.mnpout.MnpOut;
import jp.co.biglobe.isp.mobile.voiceoption.service.mnpout.MnpOutReferService;
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
 * MNP転出参照
 */
@Controller
public class MnpOutReferApi {

    @Autowired
    private AlarmValidationVerifier alarmValidationVerifier;

    @Autowired
    private MnpOutReferService mnpOutReferService;

    @Autowired
    private MnpOutReferResponse mnpOutReferResponse;

    @RequestMapping(value = "/voiceoption/outside/skip/mnpout/refer", method = RequestMethod.POST)
    @ResponseBody
    public Map invoke(@Valid MnpOutReferRequest request, Errors errors) {

        alarmValidationVerifier.verify(errors);

        MnpOut mnpOut = mnpOutReferService.refer(request.getLteThreeGEngagementNumberForm().getValueObject());

        return mnpOutReferResponse.build(mnpOut);

    }



}
