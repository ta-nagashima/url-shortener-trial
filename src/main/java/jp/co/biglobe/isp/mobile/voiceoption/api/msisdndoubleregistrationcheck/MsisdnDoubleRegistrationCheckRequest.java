package jp.co.biglobe.isp.mobile.voiceoption.api.msisdndoubleregistrationcheck;

import jp.co.biglobe.isp.auth.api.form.OperatorIdForm;
import jp.co.biglobe.isp.auth.api.form.OperatorPasswordForm;
import jp.co.biglobe.lib.publication.validation.ValueObjectNotEmpty;
import jp.co.biglobe.isp.mobile.voiceoption.api.form.VoiceMsisdnForm;
import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;

public class MsisdnDoubleRegistrationCheckRequest {

    @Getter
    @Setter
    @ValueObjectNotEmpty
    @Valid
    private OperatorIdForm operatorIdForm;

    @Getter
    @Setter
    @ValueObjectNotEmpty
    @Valid
    private OperatorPasswordForm operatorPasswordForm;

    @Getter
    @Setter
    @ValueObjectNotEmpty
    @Valid
    private VoiceMsisdnForm voiceMsisdnForm;

}
