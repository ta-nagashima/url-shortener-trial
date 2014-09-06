package jp.co.biglobe.isp.mobile.voiceoption.datasource.charge.disengagementcharge;

import jp.co.biglobe.isp.mobile.ltethreeg.domain.ltethreegengagement.LteThreeGEngagementNumber;
import jp.co.biglobe.isp.mobile.ltethreeg.domain.sim.EquipmentNumber;
import jp.co.biglobe.isp.mobile.voiceoption.datasource.charge.disengagementcharge.refer.VoiceDisengagementChargeReferOutput;
import jp.co.biglobe.isp.mobile.voiceoption.datasource.charge.disengagementcharge.refer.VoiceDisengagementChargeReferScenario;
import jp.co.biglobe.isp.mobile.voiceoption.datasource.charge.disengagementcharge.register.VoiceDisengagementChargeAddScenario;
import jp.co.biglobe.isp.mobile.voiceoption.datasource.voiceengagement.db.VoiceEngagementMapper;
import jp.co.biglobe.isp.mobile.voiceoption.domain.charge.disengagementcharge.DisengagementCharge;
import jp.co.biglobe.isp.mobile.voiceoption.domain.charge.disengagementcharge.NotExistDisengagementCharge;
import jp.co.biglobe.isp.mobile.voiceoption.domain.charge.disengagementcharge.ValidDisengagementCharge;
import jp.co.biglobe.isp.mobile.voiceoption.domain.charge.disengagementcharge.VoiceDisengagementChargeRepository;
import jp.co.biglobe.isp.mobile.voiceoption.domain.voiceengagement.ValidVoiceEngagement;
import jp.co.biglobe.isp.mobile.voiceoption.domain.voiceengagement.VoiceEngagement;
import jp.co.biglobe.isp.mobile.voiceoption.domain.voiceengagement.VoiceEngagementEndDateTime;
import jp.co.biglobe.isp.mobile.voiceoption.domain.voiceengagement.VoiceEngagementStatus;
import jp.co.biglobe.lib.plugin.event.RequestEventTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class VoiceDisengagementChargeRepositoryScenario implements VoiceDisengagementChargeRepository {

    @Autowired
    private VoiceDisengagementChargeAddScenario voiceDisengagementChargeAddScenario;

    @Autowired
    private VoiceDisengagementChargeReferScenario voiceDisengagementChargeReferScenario;

    @Autowired
    private VoiceEngagementMapper voiceEngagementMapper;

    @Autowired
    private RequestEventTime requestEventTime;

    @Override
    public void register(ValidVoiceEngagement validVoiceEngagement) {

        voiceDisengagementChargeAddScenario.register(
                validVoiceEngagement.getVoiceEngagementLinkage().getUserId(),
                validVoiceEngagement.getVoiceEngagementStartDate(),
                validVoiceEngagement.getVoiceEngagementNumber());
    }

    /**
     * LTE3G契約番号による確認
     *
     * @param lteThreeGEngagementNumber
     * @return
     */
    @Override
    public DisengagementCharge findByVoiceEngagementNumberUnderValid(LteThreeGEngagementNumber lteThreeGEngagementNumber) {
        java.util.Date date = requestEventTime.getDate();
        ValidVoiceEngagement validVoiceEngagement = voiceEngagementMapper.findByLteThreeGEngagementNumberUnderValid(
                lteThreeGEngagementNumber,
                new VoiceEngagementEndDateTime(date),
                VoiceEngagementStatus.ORDERED,
                VoiceEngagementStatus.ENGAGED,
                VoiceEngagementStatus.DISENGAGED);

        if (isNotEngaged(validVoiceEngagement)) {
            return new NotExistDisengagementCharge();
        }

        VoiceDisengagementChargeReferOutput voiceDisengagementChargeReferOutput = voiceDisengagementChargeReferScenario.refer(
                validVoiceEngagement.getVoiceEngagementLinkage().getUserId(),
                validVoiceEngagement.getVoiceEngagementNumber());

        if (isNotExistPenaltyCharge(voiceDisengagementChargeReferOutput)) {
            return new NotExistDisengagementCharge();
        }

        return new ValidDisengagementCharge(validVoiceEngagement.getVoiceEngagementNumber(),
                voiceDisengagementChargeReferOutput.getVoiceDisengagementChargeEndMonth(),
                voiceDisengagementChargeReferOutput.getVoiceDisengagementChargeAmount());
    }

    /**
     * 機器番号による確認
     *
     * @param equipmentNumber
     * @return
     */
    @Override
    public DisengagementCharge findByEquipmentNumber(EquipmentNumber equipmentNumber) {

        ValidVoiceEngagement validVoiceEngagement = voiceEngagementMapper.findByEquipmentNumber(equipmentNumber);

        if (isNotEngaged(validVoiceEngagement)) {
            return new NotExistDisengagementCharge();
        }

        VoiceDisengagementChargeReferOutput voiceDisengagementChargeReferOutput = voiceDisengagementChargeReferScenario.refer(
                validVoiceEngagement.getVoiceEngagementLinkage().getUserId(),
                validVoiceEngagement.getVoiceEngagementNumber());

        if (isNotExistPenaltyCharge(voiceDisengagementChargeReferOutput)) {
            return new NotExistDisengagementCharge();
        }

        return new ValidDisengagementCharge(validVoiceEngagement.getVoiceEngagementNumber(),
                voiceDisengagementChargeReferOutput.getVoiceDisengagementChargeEndMonth(),
                voiceDisengagementChargeReferOutput.getVoiceDisengagementChargeAmount());
    }

    /**
     * 音声オプション契約による確認
     *
     * @param voiceEngagement
     * @return
     */
    @Override
    public DisengagementCharge findByVoiceEngagement(VoiceEngagement voiceEngagement) {

        if (voiceEngagement.isNotExist()) {
            return new NotExistDisengagementCharge();
        }

        ValidVoiceEngagement validVoiceEngagement = (ValidVoiceEngagement) voiceEngagement;
        VoiceDisengagementChargeReferOutput voiceDisengagementChargeReferOutput = voiceDisengagementChargeReferScenario.refer(
                validVoiceEngagement.getVoiceEngagementLinkage().getUserId(),
                validVoiceEngagement.getVoiceEngagementNumber());

        if (isNotExistPenaltyCharge(voiceDisengagementChargeReferOutput)) {
            return new NotExistDisengagementCharge();
        }

        return new ValidDisengagementCharge(validVoiceEngagement.getVoiceEngagementNumber(),
                voiceDisengagementChargeReferOutput.getVoiceDisengagementChargeEndMonth(),
                voiceDisengagementChargeReferOutput.getVoiceDisengagementChargeAmount());
    }

    private boolean isNotEngaged(ValidVoiceEngagement validVoiceEngagement) {
        return validVoiceEngagement == null;
    }

    private boolean isNotExistPenaltyCharge(VoiceDisengagementChargeReferOutput voiceDisengagementChargeReferOutput) {
        return voiceDisengagementChargeReferOutput.getVoiceDisengagementChargeEndMonth() == null &&
                voiceDisengagementChargeReferOutput.getVoiceDisengagementChargeEndMonth() == null;

    }

}
