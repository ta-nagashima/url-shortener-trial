package jp.co.biglobe.isp.mobile.voiceoption.testdata.api.mnpout.mnpoutwaiting;

import jp.co.biglobe.isp.auth.domain.operatornouser.OperatorId;
import jp.co.biglobe.isp.mobile.voiceoption.domain.container.VoiceEngagementDetailReferContainer;
import jp.co.biglobe.isp.mobile.voiceoption.domain.executiondate.ExecutionDate;
import jp.co.biglobe.isp.mobile.voiceoption.domain.mnpin.MnpReservationNumber;
import jp.co.biglobe.isp.mobile.voiceoption.domain.mnpout.mnpoutreservationnumber.ExpireDate;
import jp.co.biglobe.isp.mobile.voiceoption.domain.voiceengagement.ValidVoiceEngagement;
import jp.co.biglobe.isp.mobile.voiceoption.domain.voiceengagement.VoiceEngagementNumber;
import jp.co.biglobe.isp.mobile.voiceoption.service.engagement.VoiceEngagementDetailReferService;
import jp.co.biglobe.isp.mobile.voiceoption.service.mnpout.MnpReservationNumberRegisterService;
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
public class MnpOutWaitingCreateApi {

    @Autowired
    private AlarmValidationVerifier alarmValidationVerifier;

    @Autowired
    private MnpOutWaitingCreateResponse response;

    @Autowired
    private VoiceEngagementDetailReferService voiceEngagementDetailReferService;

    @Autowired
    private MnpReservationNumberRegisterService mnpReservationNumberRegisterService;

    @RequestMapping(value = "/voiceoption/inside/skip/testdata/mnpout/mnpoutwaiting/create", method = RequestMethod.POST)
    @ResponseBody
    public Map invoke(@Valid MnpOutWaitingCreateRequest request, Errors errors) throws Exception {
        alarmValidationVerifier.verify(errors);

        // 現在の日付の取得
        final String TODAY = new DateTime().toString("yyyyMMdd");
        final String FIFTEEN_DAYS_AFTER = new DateTime().plusDays(15).toString("yyyyMMdd");

        // 音声契約番号の取得
        VoiceEngagementDetailReferContainer voiceEngagementDetailReferContainer = voiceEngagementDetailReferService.refer(request.getEquipmentNumberForm().getValueObject());
        ValidVoiceEngagement validVoiceEngagement = (ValidVoiceEngagement) voiceEngagementDetailReferContainer.getVoiceEngagement();
        VoiceEngagementNumber voiceEngagementNumber = validVoiceEngagement.getVoiceEngagementNumber();

        // MNP予約番号登録
        mnpReservationNumberRegisterService.register(
                voiceEngagementNumber,
                new MnpReservationNumber("11-222-33333"),
                new ExpireDate(FIFTEEN_DAYS_AFTER),
                new ExecutionDate(TODAY),
                new OperatorId("abc12345")
        );

        return response.build("ok");
    }
}
