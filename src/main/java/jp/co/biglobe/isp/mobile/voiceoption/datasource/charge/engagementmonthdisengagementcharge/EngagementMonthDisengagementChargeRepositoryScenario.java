package jp.co.biglobe.isp.mobile.voiceoption.datasource.charge.engagementmonthdisengagementcharge;

import jp.co.biglobe.isp.auth.domain.operatornouser.OperatorId;
import jp.co.biglobe.isp.mobile.voiceoption.datasource.charge.engagementmonthdisengagementcharge.db.EngagementMonthDisengagementChargeQueryMapper;
import jp.co.biglobe.isp.mobile.voiceoption.datasource.charge.engagementmonthdisengagementcharge.scenario.EngagementMonthDisengagementChargeScenario;
import jp.co.biglobe.isp.mobile.voiceoption.domain.charge.engagementmonthdisengagementcharge.*;
import jp.co.biglobe.isp.mobile.voiceoption.domain.voiceengagement.ValidVoiceEngagement;
import jp.co.biglobe.isp.mobile.voiceoption.domain.voiceengagement.VoiceEngagement;
import jp.co.biglobe.isp.mobile.voiceoption.domain.voiceengagement.VoiceEngagementNumber;
import jp.co.biglobe.isp.mobile.voiceoption.domain.voiceengagement.disengage.VoiceEngagementMonthDisengagementChargeDateTime;
import jp.co.biglobe.lib.essential.property.PropertyAccessor;
import jp.co.biglobe.lib.plugin.event.RequestEventProcess;
import jp.co.biglobe.lib.plugin.event.RequestEventTime;
import jp.co.biglobe.lib.publication.datasource.EventType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.Date;

@Repository
public class EngagementMonthDisengagementChargeRepositoryScenario implements EngagementMonthDisengagementChargeRepository {

    @Autowired
    private EngagementMonthDisengagementChargeScenario engagementMonthDisengagementChargeScenario;

    @Autowired
    private EngagementMonthDisengagementChargeQueryMapper engagementMonthDisengagementChargeQueryMapper;

    @Autowired
    private RequestEventTime requestEventTime;

    @Autowired
    private RequestEventProcess requestEventProcess;

    @Autowired
    private PropertyAccessor propertyAccessor;

    @Override
    public void register(ValidVoiceEngagement validVoiceEngagement) {

        EngagementMonthDisengagementItemCode engagementMonthDisengagementItemCode = EngagementMonthDisengagementItemCodeFactoryDb.create();
        EngagementMonthDisengagementFee engagementMonthDisengagementFee = new EngagementMonthDisengagementFeeFactoryDb().create();
        OperatorId operatorId = new OperatorId(propertyAccessor.getProperty("fixation.operator.id"));

        engagementMonthDisengagementChargeQueryMapper.insertEvent(
                EventType.INSERT,
                requestEventTime.getDate(),
                requestEventProcess.getValue(),
                validVoiceEngagement.getVoiceEngagementNumber(),
                operatorId,
                validVoiceEngagement.getVoiceEngagementLinkage().getUserId(),
                engagementMonthDisengagementItemCode,
                engagementMonthDisengagementFee
        );

        engagementMonthDisengagementChargeQueryMapper.insertState(
                validVoiceEngagement.getVoiceEngagementNumber(),
                operatorId,
                validVoiceEngagement.getVoiceEngagementLinkage().getUserId(),
                engagementMonthDisengagementItemCode,
                engagementMonthDisengagementFee,
                requestEventTime.getDate()
        );

        engagementMonthDisengagementChargeScenario.register(
                operatorId,
                validVoiceEngagement.getVoiceEngagementLinkage().getUserId(),
                engagementMonthDisengagementItemCode,
                engagementMonthDisengagementFee,
                requestEventTime
        );
    }

    @Override
    public EngagementMonthDisengagementCharge findByVoiceEngagement(VoiceEngagement voiceEngagement) {
        if (voiceEngagement.isNotExist()) {
            return new NotExistEngagementMonthDisengagementCharge();
        }

        ValidVoiceEngagement validVoiceEngagement = (ValidVoiceEngagement) voiceEngagement;

        return findByVoiceEngagement(validVoiceEngagement.getVoiceEngagementNumber());
    }

    @Override
    public EngagementMonthDisengagementCharge findByVoiceEngagement(VoiceEngagementNumber voiceEngagementNumber) {

        ValidEngagementMonthDisengagementCharge validEngagementMonthDisengagementCharge
                = engagementMonthDisengagementChargeQueryMapper.findByVoiceEngagementNumber(voiceEngagementNumber);

        if(validEngagementMonthDisengagementCharge == null){
            return new NotExistEngagementMonthDisengagementCharge();
        }

        return validEngagementMonthDisengagementCharge;
    }

}
