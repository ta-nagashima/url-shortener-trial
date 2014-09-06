package jp.co.biglobe.isp.mobile.voiceoption.testdata.api.mnpout.mnpreservationnumberwaiting;

import jp.co.biglobe.isp.mobile.voiceoption.domain.container.VoiceEngagementDetailReferContainer;
import jp.co.biglobe.isp.mobile.voiceoption.domain.voiceengagement.ValidVoiceEngagement;
import jp.co.biglobe.isp.mobile.voiceoption.domain.voiceengagement.VoiceEngagementNumber;
import jp.co.biglobe.isp.mobile.voiceoption.service.engagement.VoiceEngagementDetailReferService;
import jp.co.biglobe.isp.mobile.voiceoption.service.mnpout.MnpOutRequestService;
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
public class MnpReservationNumberWaitingCreateApi {

    @Autowired
    private AlarmValidationVerifier alarmValidationVerifier;

    @Autowired
    private MnpReservationNumberWaitingCreateResponse response;

    @Autowired
    private VoiceEngagementDetailReferService voiceEngagementDetailReferService;

    @Autowired
    private MnpOutRequestService mnpOutRequestService;

    @RequestMapping(value = "/voiceoption/inside/skip/testdata/mnpout/mnpreservationnumberwaiting/create", method = RequestMethod.POST)
    @ResponseBody
    public Map invoke(@Valid MnpReservationNumberWaitingCreateRequest request, Errors errors) throws Exception {
        alarmValidationVerifier.verify(errors);

        // 音声契約番号の取得
        VoiceEngagementDetailReferContainer voiceEngagementDetailReferContainer = voiceEngagementDetailReferService.refer(request.getEquipmentNumberForm().getValueObject());
        ValidVoiceEngagement validVoiceEngagement = (ValidVoiceEngagement) voiceEngagementDetailReferContainer.getVoiceEngagement();
        VoiceEngagementNumber voiceEngagementNumber = validVoiceEngagement.getVoiceEngagementNumber();

        // MNP転出
        mnpOutRequestService.update(voiceEngagementNumber);

        return response.build("ok");
    }
}
