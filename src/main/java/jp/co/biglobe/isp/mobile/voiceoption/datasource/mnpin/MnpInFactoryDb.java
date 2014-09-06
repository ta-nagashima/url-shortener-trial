package jp.co.biglobe.isp.mobile.voiceoption.datasource.mnpin;

import jp.co.biglobe.isp.mobile.voiceoption.domain.mnpin.*;
import jp.co.biglobe.isp.mobile.voiceoption.domain.voiceengagement.VoiceEngagementNumber;
import org.springframework.stereotype.Repository;

@Repository
public class MnpInFactoryDb implements MnpInFactory {

    public ValidMnpIn create(
            VoiceEngagementNumber voiceEngagementNumber,
            MnpInInitialRequestData mnpInInitialRequestData) {

        return new ValidMnpIn(
                voiceEngagementNumber,
                mnpInInitialRequestData
        );

    }

}
