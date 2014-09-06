package jp.co.biglobe.isp.mobile.voiceoption.datasource.charge.monthlycharge;

import jp.co.biglobe.isp.mobile.voiceoption.datasource.charge.monthlycharge.scenario.disengagement.DisengagementMonthlyChargeScenario;
import jp.co.biglobe.isp.mobile.voiceoption.datasource.charge.monthlycharge.scenario.disengagementcancel.DisengagementMonthlyChargeCancelScenario;
import jp.co.biglobe.isp.mobile.voiceoption.datasource.charge.monthlycharge.scenario.engagement.EngagementMonthlyChargeScenario;
import jp.co.biglobe.isp.mobile.voiceoption.domain.charge.monthlycharge.VoiceMonthlyChargeRepository;
import jp.co.biglobe.isp.mobile.voiceoption.domain.voiceengagement.ValidVoiceEngagement;
import jp.co.biglobe.isp.mobile.voiceoption.domain.voiceengagement.VoiceEngagementEndDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class VoiceMonthlyChargeRepositoryScenario implements VoiceMonthlyChargeRepository {

    @Autowired
    private EngagementMonthlyChargeScenario engagementMonthlyChargeScenario;

    @Autowired
    private DisengagementMonthlyChargeScenario disengagementMonthlyChargeScenario;

    @Autowired
    private DisengagementMonthlyChargeCancelScenario disengagementMonthlyChargeCancelScenario;

    @Override
    public void register(ValidVoiceEngagement validVoiceEngagement) {

        engagementMonthlyChargeScenario.register(
                validVoiceEngagement.getVoiceEngagementLinkage().getUserId(),
                validVoiceEngagement.getNewOrderInfo().getVoiceSystemReceiptDateTime(),
                validVoiceEngagement.getVoiceEngagementNumber());

    }

    @Override
    public void delete(
            VoiceEngagementEndDateTime voiceEngagementEndDateTime,
            ValidVoiceEngagement validVoiceEngagement) {

        disengagementMonthlyChargeScenario.delete(
                validVoiceEngagement.getVoiceEngagementLinkage().getUserId(),
                validVoiceEngagement.getVoiceEngagementNumber(),
                voiceEngagementEndDateTime);

    }

    @Override
    public void update(ValidVoiceEngagement validVoiceEngagement) {

        disengagementMonthlyChargeCancelScenario.update(
                validVoiceEngagement.getVoiceEngagementLinkage().getUserId(),
                validVoiceEngagement.getVoiceEngagementNumber());

    }

}
