package jp.co.biglobe.isp.mobile.voiceoption.api.mnpout.mnpoutrequest;

import jp.co.biglobe.lib.publication.validation.ValueObjectNotEmpty;
import jp.co.biglobe.isp.mobile.voiceoption.api.form.VoiceEngagementNumberForm;
import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;

/**
 * 転出依頼
 */

public class MnpOutRequestRequest {

    @Getter
    @Setter
    @ValueObjectNotEmpty
    @Valid
    private VoiceEngagementNumberForm voiceEngagementNumberForm;

}
