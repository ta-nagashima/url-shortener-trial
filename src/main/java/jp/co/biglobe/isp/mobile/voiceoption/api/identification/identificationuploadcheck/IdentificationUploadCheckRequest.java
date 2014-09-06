package jp.co.biglobe.isp.mobile.voiceoption.api.identification.identificationuploadcheck;

import jp.co.biglobe.isp.auth.api.form.SessionIdForm;
import jp.co.biglobe.lib.publication.validation.ValueObjectNotEmpty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.Valid;

@ToString(includeFieldNames = false)
public class IdentificationUploadCheckRequest {


    @Getter
    @Setter
    @ValueObjectNotEmpty
    @Valid
    private SessionIdForm sessionIdForm;

}
