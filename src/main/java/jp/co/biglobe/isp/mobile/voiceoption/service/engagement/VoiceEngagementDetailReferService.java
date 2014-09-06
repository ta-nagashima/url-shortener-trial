package jp.co.biglobe.isp.mobile.voiceoption.service.engagement;


import jp.co.biglobe.isp.mobile.ltethreeg.domain.sim.EquipmentNumber;
import jp.co.biglobe.isp.mobile.voiceoption.domain.charge.disengagementcharge.DisengagementCharge;
import jp.co.biglobe.isp.mobile.voiceoption.domain.charge.disengagementcharge.VoiceDisengagementChargeRepository;
import jp.co.biglobe.isp.mobile.voiceoption.domain.charge.engagementmonthdisengagementcharge.EngagementMonthDisengagementCharge;
import jp.co.biglobe.isp.mobile.voiceoption.domain.charge.engagementmonthdisengagementcharge.EngagementMonthDisengagementChargeRepository;
import jp.co.biglobe.isp.mobile.voiceoption.domain.container.VoiceEngagementDetailReferContainer;
import jp.co.biglobe.isp.mobile.voiceoption.domain.identification.Identification;
import jp.co.biglobe.isp.mobile.voiceoption.domain.identification.IdentificationRepository;
import jp.co.biglobe.isp.mobile.voiceoption.domain.mnpin.MnpIn;
import jp.co.biglobe.isp.mobile.voiceoption.domain.mnpin.MnpInRepository;
import jp.co.biglobe.isp.mobile.voiceoption.domain.mnpout.MnpOut;
import jp.co.biglobe.isp.mobile.voiceoption.domain.mnpout.MnpOutRepository;
import jp.co.biglobe.isp.mobile.voiceoption.domain.voiceengagement.VoiceEngagement;
import jp.co.biglobe.isp.mobile.voiceoption.domain.voiceengagement.VoiceEngagementRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VoiceEngagementDetailReferService {

    @Autowired
    VoiceEngagementRepository voiceEngagementRepository;

    @Autowired
    MnpInRepository mnpInRepository;


    @Autowired
    MnpOutRepository mnpOutRepository;

    @Autowired
    VoiceDisengagementChargeRepository voiceDisengagementChargeRepository;

    @Autowired
    IdentificationRepository identificationRepository;

    @Autowired
    EngagementMonthDisengagementChargeRepository engagementMonthDisengagementChargeRepository;

    public VoiceEngagementDetailReferContainer refer(EquipmentNumber equipmentNumber){

        VoiceEngagement voiceEngagement = voiceEngagementRepository.findByEquipmentNumber(equipmentNumber);

        return getVoiceEngagementDetailReferContainerByVoiceEngagement(voiceEngagement);
    }

    public VoiceEngagementDetailReferContainer getVoiceEngagementDetailReferContainerByVoiceEngagement(VoiceEngagement voiceEngagement) {

        Identification identification = identificationRepository.findByLteThreeGEngagementNumber(voiceEngagement);

        MnpIn mnpIn  = mnpInRepository.findByVoiceEngagementNumber(voiceEngagement);

        MnpOut mnpOut = mnpOutRepository.findByVoiceEngagementNumber(voiceEngagement);

        DisengagementCharge disengagementCharge = voiceDisengagementChargeRepository.findByVoiceEngagement(voiceEngagement);

        EngagementMonthDisengagementCharge engagementMonthDisengagementCharge = engagementMonthDisengagementChargeRepository.findByVoiceEngagement(voiceEngagement);

        return new VoiceEngagementDetailReferContainer(voiceEngagement, identification, mnpIn, mnpOut, disengagementCharge, engagementMonthDisengagementCharge);
    }
}
