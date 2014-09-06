package jp.co.biglobe.isp.mobile.voiceoption.testdata.api.mnpout.requestwaiting;

import jp.co.biglobe.isp.mobile.ltethreeg.domain.sim.EquipmentNumber;
import jp.co.biglobe.isp.mobile.voiceoption.domain.mnpout.MnpOutMsisdn;
import jp.co.biglobe.isp.mobile.voiceoption.service.mnpout.MnpOutApplyService;
import jp.co.biglobe.isp.mobile.voiceoption.testdata.api.mnpout.mnpoutanddisengagement.MnpOutAndDisengagementCreateResponse;
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
public class RequestWaitingCreateApi {

    @Autowired
    private AlarmValidationVerifier alarmValidationVerifier;

    @Autowired
    private MnpOutAndDisengagementCreateResponse response;

    @Autowired
    private MnpOutApplyService mnpOutApplyService;

    @RequestMapping(value = "/voiceoption/inside/skip/testdata/mnpout/requestwaiting/create", method = RequestMethod.POST)
    @ResponseBody
    public Map invoke(@Valid RequestWaitingCreateRequest request, Errors errors) throws Exception {
        alarmValidationVerifier.verify(errors);

        // 音声契約番号の取得
        EquipmentNumber equipmentNumber = request.getEquipmentNumberForm().getValueObject();

        // MNP転出申込
        mnpOutApplyService.apply(
                equipmentNumber,
                new MnpOutMsisdn("090-1234-5678")
        );

        return response.build("ok");
    }
}
