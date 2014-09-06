package jp.co.biglobe.isp.mobile.voiceoption.testdata.api.identification.ok;

import jp.co.biglobe.isp.mobile.ltethreeg.domain.ltethreegengagement.LteThreeGEngagementNumber;
import jp.co.biglobe.isp.mobile.voiceoption.domain.identification.IdentificationRepository;
import jp.co.biglobe.isp.mobile.voiceoption.domain.identification.ValidIdentification;
import jp.co.biglobe.isp.mobile.voiceoption.testdata.service.IdentificationCreateService;
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
public class IdentificationOkCreateApi {

    @Autowired
    private AlarmValidationVerifier alarmValidationVerifier;

    @Autowired
    private IdentificationOkCreateResponse response;

    @Autowired
    private IdentificationRepository identificationRepository;

    @Autowired
    private IdentificationCreateService identificationCreateService;

    @RequestMapping(value = "/voiceoption/inside/skip/testdata/identification/ok/create", method = RequestMethod.POST)
    @ResponseBody
    public Map invoke(@Valid IdentificationOkCreateRequest request, Errors errors) throws Exception {
        alarmValidationVerifier.verify(errors);

        // 音声契約番号の取得
        LteThreeGEngagementNumber lteThreeGEngagementNumber = request.getLteThreeGEngagementNumberForm().getValueObject();
        ValidIdentification validIdentification = identificationRepository.findByLteThreeGEngagementNumberForValid(lteThreeGEngagementNumber);

        // 本人確認処理
        identificationCreateService.reflect(validIdentification);

        return response.build("ok");
    }
}
