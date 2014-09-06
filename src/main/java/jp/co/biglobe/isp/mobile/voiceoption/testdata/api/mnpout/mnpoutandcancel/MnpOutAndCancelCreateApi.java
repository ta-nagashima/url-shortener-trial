package jp.co.biglobe.isp.mobile.voiceoption.testdata.api.mnpout.mnpoutandcancel;

import jp.co.biglobe.isp.mobile.ltethreeg.domain.sim.EquipmentNumber;
import jp.co.biglobe.isp.mobile.voiceoption.domain.mnpout.mnpoutcompletion.MnpOutCompletionConfirmedDate;
import jp.co.biglobe.isp.mobile.voiceoption.domain.voiceengagement.cancel.VoiceEngagementCancelOrderDate;
import jp.co.biglobe.isp.mobile.voiceoption.service.newordercancel.VoiceMnpOutCompletionAndNewOrderCancelService;
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
public class MnpOutAndCancelCreateApi {

    @Autowired
    private AlarmValidationVerifier alarmValidationVerifier;

    @Autowired
    private MnpOutAndCancelCreateResponse response;

    @Autowired
    private VoiceMnpOutCompletionAndNewOrderCancelService voiceMnpOutCompletionAndNewOrderCancelService;

    @RequestMapping(value = "/voiceoption/inside/skip/testdata/mnpout/mnpoutandcancel/create", method = RequestMethod.POST)
    @ResponseBody
    public Map invoke(@Valid MnpOutAndCancelCreateRequest request, Errors errors) throws Exception {
        alarmValidationVerifier.verify(errors);

        // 現在の日付の取得
        final String TODAY = new DateTime().toString("yyyyMMdd");

        // 音声契約番号の取得
        EquipmentNumber equipmentNumber = request.getEquipmentNumberForm().getValueObject();

        // MNP転出確定兼新規申込キャンセル
        voiceMnpOutCompletionAndNewOrderCancelService.cancel(
                equipmentNumber,
                new MnpOutCompletionConfirmedDate(TODAY),
                new VoiceEngagementCancelOrderDate(TODAY));

        return response.build("ok");
    }
}
