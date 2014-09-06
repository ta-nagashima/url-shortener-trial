package jp.co.biglobe.isp.mobile.voiceoption.api.newordercancel.llistrefer;

import jp.co.biglobe.lib.publication.validation.ValueObjectNotEmpty;
import jp.co.biglobe.isp.mobile.voiceoption.api.form.VoiceEngagementNumberForm;
import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;

/**
 * Created by takaosakamoto on 2014/07/11.
 */
public class VoiceCancelListReferOutputRequest {

    @Getter
    @Setter
    @ValueObjectNotEmpty
    @Valid
    private VoiceEngagementNumberForm voiceEngagementNumberForm;
}
