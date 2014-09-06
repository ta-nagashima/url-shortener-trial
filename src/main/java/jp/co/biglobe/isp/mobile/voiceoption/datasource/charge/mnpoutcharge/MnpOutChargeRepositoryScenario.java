package jp.co.biglobe.isp.mobile.voiceoption.datasource.charge.mnpoutcharge;

import jp.co.biglobe.isp.auth.domain.operatornouser.OperatorId;
import jp.co.biglobe.isp.mobile.voiceoption.datasource.charge.mnpoutcharge.db.MnpOutChargeQueryMapper;
import jp.co.biglobe.isp.mobile.voiceoption.datasource.charge.mnpoutcharge.scenario.MnpOutChargeScenario;
import jp.co.biglobe.isp.mobile.voiceoption.domain.charge.mnpoutcharge.MnpOutChargeRepository;
import jp.co.biglobe.isp.mobile.voiceoption.domain.charge.mnpoutcharge.MnpOutFee;
import jp.co.biglobe.isp.mobile.voiceoption.domain.charge.mnpoutcharge.MnpOutItemCode;
import jp.co.biglobe.isp.mobile.voiceoption.domain.voiceengagement.ValidVoiceEngagement;
import jp.co.biglobe.lib.essential.property.PropertyAccessor;
import jp.co.biglobe.lib.plugin.event.RequestEventProcess;
import jp.co.biglobe.lib.plugin.event.RequestEventTime;
import jp.co.biglobe.lib.publication.datasource.EventType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class MnpOutChargeRepositoryScenario implements MnpOutChargeRepository {

    @Autowired
    private MnpOutChargeScenario mnpOutChargeScenario;

    @Autowired
    private MnpOutChargeQueryMapper mnpOutChargeQueryMapper;

    @Autowired
    private RequestEventTime requestEventTime;

    @Autowired
    private RequestEventProcess requestEventProcess;

    @Autowired
    private PropertyAccessor propertyAccessor;

    @Override
    public void register(ValidVoiceEngagement validVoiceEngagement) {

        MnpOutItemCode mnpOutItemCode = new MnpOutItemCode();
        MnpOutFee mnpOutFee = new MnpOutFee();
        OperatorId operatorId = new OperatorId(propertyAccessor.getProperty("fixation.operator.id"));

        mnpOutChargeQueryMapper.insertEvent(
                EventType.INSERT,
                requestEventTime.getDate(),
                requestEventProcess.getValue(),
                validVoiceEngagement.getVoiceEngagementNumber(),
                operatorId,
                validVoiceEngagement.getVoiceEngagementLinkage().getUserId(),
                mnpOutItemCode,
                mnpOutFee
        );

        mnpOutChargeScenario.register(
                operatorId, validVoiceEngagement.getVoiceEngagementLinkage().getUserId(), mnpOutItemCode,
                mnpOutFee, requestEventTime);

    }

}
