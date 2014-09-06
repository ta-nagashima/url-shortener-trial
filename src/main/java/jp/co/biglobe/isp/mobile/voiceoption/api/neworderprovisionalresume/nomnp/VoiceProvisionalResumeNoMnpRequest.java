package jp.co.biglobe.isp.mobile.voiceoption.api.neworderprovisionalresume.nomnp;

import jp.co.biglobe.lib.publication.validation.ValueObjectNotEmpty;
import jp.co.biglobe.isp.mobile.ltethreeg.api.form.ltethreegengagement.LteThreeGEngagementNumberForm;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.Valid;

@ToString(includeFieldNames = false)
public class VoiceProvisionalResumeNoMnpRequest {
    @Getter
    @Setter
    @ValueObjectNotEmpty
    @Valid
    private LteThreeGEngagementNumberForm lteThreeGEngagementNumberForm;
}
