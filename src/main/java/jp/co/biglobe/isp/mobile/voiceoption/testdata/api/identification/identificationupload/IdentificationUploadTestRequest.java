package jp.co.biglobe.isp.mobile.voiceoption.testdata.api.identification.identificationupload;

import jp.co.biglobe.isp.auth.api.form.SessionIdForm;
import jp.co.biglobe.isp.auth.api.form.UserIdForm;
import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;

public class IdentificationUploadTestRequest {
    @Getter
    @Setter
    @Valid
    private UserIdForm userIdForm;

    @Getter
    @Setter
    @Valid
    private SessionIdForm sessionIdForm;
}
