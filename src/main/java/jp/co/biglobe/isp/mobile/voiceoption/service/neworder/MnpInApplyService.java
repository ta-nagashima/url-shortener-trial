package jp.co.biglobe.isp.mobile.voiceoption.service.neworder;

import jp.co.biglobe.isp.mobile.voiceoption.domain.mnpin.MnpInFactory;
import jp.co.biglobe.isp.mobile.voiceoption.domain.mnpin.MnpInInitialRequestData;
import jp.co.biglobe.isp.mobile.voiceoption.domain.mnpin.MnpInRepository;
import jp.co.biglobe.isp.mobile.voiceoption.domain.mnpin.ValidMnpIn;
import jp.co.biglobe.isp.mobile.voiceoption.domain.voiceengagement.ValidVoiceEngagement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
class MnpInApplyService {

    @Autowired
    private MnpInFactory mnpInFactory;

    @Autowired
    private MnpInRepository mnpInRepository;

    void newApply(
            MnpInInitialRequestData mnpInInitialRequestData,
            ValidVoiceEngagement validVoiceEngagement) {

        ValidMnpIn validMnpIn = mnpInFactory.create(validVoiceEngagement.getVoiceEngagementNumber(), mnpInInitialRequestData);
        mnpInRepository.insert(validMnpIn);

    }

}
