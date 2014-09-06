package jp.co.biglobe.isp.mobile.voiceoption.api.neworderprovisionalresume.withmnp;

import jp.co.biglobe.lib.publication.validation.ValueObjectNotEmpty;
import jp.co.biglobe.isp.mobile.ltethreeg.api.form.ltethreegengagement.LteThreeGEngagementNumberForm;
import jp.co.biglobe.isp.mobile.voiceoption.api.form.*;
import jp.co.biglobe.isp.mobile.voiceoption.domain.mnppersonalinfo.ValidMnpPersonalInfo;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.Valid;

@ToString(includeFieldNames = false)
public class VoiceProvisionalResumeWithMnpRequest {

    @Getter
    @Setter
    @ValueObjectNotEmpty
    @Valid
    private LteThreeGEngagementNumberForm lteThreeGEngagementNumberForm;

    @Getter
    @Setter
    @ValueObjectNotEmpty
    @Valid
    private MnpFullNameForm mnpFullNameForm;

    @Getter
    @Setter
    @ValueObjectNotEmpty
    @Valid
    private MnpFullNameKanaForm mnpFullNameKanaForm;

    @Getter
    @Setter
    @ValueObjectNotEmpty
    @Valid
    private MnpGenderForm mnpGenderForm;

    @Getter
    @Setter
    @ValueObjectNotEmpty
    @Valid
    private MnpBirthdayForm mnpBirthdayForm;


    public ValidMnpPersonalInfo getMnpInputItems() {
        return new ValidMnpPersonalInfo(
                mnpFullNameForm.getValueObject(),
                mnpFullNameKanaForm.getValueObject(),
                mnpGenderForm.getValueObject(),
                mnpBirthdayForm.getValueObject()
        );

    }
}

