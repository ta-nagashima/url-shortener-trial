package jp.co.biglobe.isp.mobile.voiceoption.domain.container;

import groovy.transform.EqualsAndHashCode;
import jp.co.biglobe.isp.mobile.voiceoption.domain.mnpout.ValidMnpOut;
import jp.co.biglobe.isp.mobile.voiceoption.domain.voiceengagement.ValidVoiceEngagement;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
* Created by masahirodoi on 2014/07/07.
*/
@EqualsAndHashCode
@AllArgsConstructor
public class MnpOutRequestInfoReturnContainer {

    @Getter
    private final ValidVoiceEngagement voiceEngagement;

    @Getter
    private final ValidMnpOut validMnpOut;

}
