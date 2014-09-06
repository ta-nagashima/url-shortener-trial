package jp.co.biglobe.isp.mobile.voiceoption.api.mnpout.mnpoutcancel;

import jp.co.biglobe.isp.mobile.voiceoption.service.mnpout.MnpOutCancelService;
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
public class MnpOutCancelApi {

    @Autowired
    private AlarmValidationVerifier alarmValidationVerifier;

    @Autowired
    private MnpOutCancelService mnpOutCancelService;

    @Autowired
    private MnpOutCancelResponse mnpOutCancelResponse;

    @RequestMapping(value = "/voiceoption/outside/skip/mnpoutcancel/submit", method = RequestMethod.POST)
    @ResponseBody
    public Map invoke(@Valid MnpOutCancelRequest mnpOutCancelRequest, Errors errors) {

        alarmValidationVerifier.verify(errors);

        // 転出情報の登録
        mnpOutCancelService.cancel(
                mnpOutCancelRequest.getEquipmentNumberForm().getValueObject(),
                mnpOutCancelRequest.getMnpOutCancelReasonForm().getValueObject()
        );

        return mnpOutCancelResponse.build();
    }

}
