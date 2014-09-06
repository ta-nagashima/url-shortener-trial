package jp.co.biglobe.isp.mobile.voiceoption.testdata.api.bundlemnpout.requestwaiting;

import jp.co.biglobe.isp.mobile.ltethreeg.domain.ltethreegengagement.LteThreeGEngagementNumber;
import jp.co.biglobe.isp.mobile.ltethreeg.domain.sim.EquipmentNumber;
import jp.co.biglobe.isp.mobile.voiceoption.domain.identification.IdentificationRepository;
import jp.co.biglobe.isp.mobile.voiceoption.domain.identification.ValidIdentification;
import jp.co.biglobe.isp.mobile.voiceoption.domain.mnpout.MnpOutMsisdn;
import jp.co.biglobe.isp.mobile.voiceoption.domain.view.voiceengagementdetailcontainer.VoiceEngagementDetailContainer;
import jp.co.biglobe.isp.mobile.voiceoption.domain.view.voiceengagementdetailcontainer.VoiceEngagementDetailContainerList;
import jp.co.biglobe.isp.mobile.voiceoption.domain.voiceengagement.ValidVoiceEngagement;
import jp.co.biglobe.isp.mobile.voiceoption.domain.voiceengagement.VoiceEngagementNumber;
import jp.co.biglobe.isp.mobile.voiceoption.domain.voiceengagement.VoiceEngagementStartDate;
import jp.co.biglobe.isp.mobile.voiceoption.service.engagement.VoiceEngagementDetailListReferService;
import jp.co.biglobe.isp.mobile.voiceoption.service.engagement.VoiceEngagementService;
import jp.co.biglobe.isp.mobile.voiceoption.service.mnpout.MnpOutApplyService;
import jp.co.biglobe.isp.mobile.voiceoption.testdata.api.bundlemnpout.mnpoutanddisengagement.BundleMnpOutAndDisengagementCreateResponse;
import jp.co.biglobe.isp.mobile.voiceoption.testdata.service.IdentificationCreateService;
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
public class BundleRequestWaitingCreateApi {

    @Autowired
    private AlarmValidationVerifier alarmValidationVerifier;

    @Autowired
    private BundleMnpOutAndDisengagementCreateResponse response;

    @Autowired
    private VoiceEngagementDetailListReferService VoiceEngagementDetailListReferService;

    @Autowired
    private IdentificationRepository identificationRepository;

    @Autowired
    private VoiceEngagementService voiceEngagementService;

    @Autowired
    private MnpOutApplyService mnpOutApplyService;

    @Autowired
    private IdentificationCreateService identificationCreateService;

    @RequestMapping(value = "/voiceoption/inside/skip/testdata/bundle/mnpout/requestwaiting/create", method = RequestMethod.POST)
    @ResponseBody
    public Map invoke(@Valid BundleRequestWaitingCreateRequest request, Errors errors) throws Exception {
        alarmValidationVerifier.verify(errors);

        // 現在の日付の取得
        final String TODAY = new DateTime().toString("yyyyMMdd");

        // 音声契約番号の取得
        LteThreeGEngagementNumber lteThreeGEngagementNumber = request.getLteThreeGEngagementNumberForm().getValueObject();
        ValidIdentification validIdentification = identificationRepository.findByLteThreeGEngagementNumberForValid(lteThreeGEngagementNumber);
        VoiceEngagementDetailContainerList voiceEngagementDetailContainerList = VoiceEngagementDetailListReferService.refer(lteThreeGEngagementNumber);
        VoiceEngagementDetailContainer voiceEngagementDetailContainer = voiceEngagementDetailContainerList.getList().get(0);
        ValidVoiceEngagement validVoiceEngagement = voiceEngagementDetailContainer.getValidVoiceEngagement();
        VoiceEngagementNumber voiceEngagementNumber = validVoiceEngagement.getVoiceEngagementNumber();
        EquipmentNumber equipmentNumber = validVoiceEngagement.getVoiceEngagementLinkage().getEquipmentNumber();

        // 本人確認処理
        identificationCreateService.reflect(validIdentification);

        // 契約開始
        if(!(request.getEngagement() == null)) {
            voiceEngagementService.engage(
                    equipmentNumber,
                    new VoiceEngagementStartDate(TODAY)
            );
        }

        // MNP転出申込
        mnpOutApplyService.apply(
                equipmentNumber,
                new MnpOutMsisdn("090-1234-5678")
        );

        return response.build(voiceEngagementNumber.getApiValue());
    }
}
