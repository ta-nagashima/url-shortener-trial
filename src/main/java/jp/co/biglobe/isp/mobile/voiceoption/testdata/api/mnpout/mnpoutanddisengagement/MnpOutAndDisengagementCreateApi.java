package jp.co.biglobe.isp.mobile.voiceoption.testdata.api.mnpout.mnpoutanddisengagement;

import jp.co.biglobe.isp.mobile.ltethreeg.domain.sim.EquipmentNumber;
import jp.co.biglobe.isp.mobile.voiceoption.domain.mnpout.mnpoutcompletion.MnpOutCompletionConfirmedDate;
import jp.co.biglobe.isp.mobile.voiceoption.domain.voiceengagement.VoiceEngagementEndDateTime;
import jp.co.biglobe.isp.mobile.voiceoption.domain.voiceengagement.disengage.VoiceEngagementDisengageOrderDate;
import jp.co.biglobe.isp.mobile.voiceoption.service.disengagement.VoiceMnpOutCompletionAndDisengagementService;
import jp.co.biglobe.lib.plugin.validationverifier.AlarmValidationVerifier;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.util.Map;

@Controller
public class MnpOutAndDisengagementCreateApi {

    @Autowired
    private AlarmValidationVerifier alarmValidationVerifier;

    @Autowired
    private MnpOutAndDisengagementCreateResponse response;

    @Autowired
    private VoiceMnpOutCompletionAndDisengagementService voiceMnpOutCompletionAndDisengagementService;

    @RequestMapping(value = "/voiceoption/inside/skip/testdata/mnpout/mnpoutanddisengagement/create", method = RequestMethod.POST)
    @ResponseBody
    public Map invoke(@Valid MnpOutAndDisengagementCreateRequest request, Errors errors) throws Exception {
        alarmValidationVerifier.verify(errors);

        // 現在の日付の取得
        final String TODAY = new DateTime().toString("yyyyMMdd");

        // 音声契約番号の取得
        EquipmentNumber equipmentNumber = request.getEquipmentNumberForm().getValueObject();

        // MNP転出確定兼解約
        voiceMnpOutCompletionAndDisengagementService.disengage(
                equipmentNumber,
                new MnpOutCompletionConfirmedDate(TODAY),
                new VoiceEngagementEndDateTime(new DateTime().toDate()),
                new VoiceEngagementDisengageOrderDate(TODAY));

        return response.build("ok");
    }
}
