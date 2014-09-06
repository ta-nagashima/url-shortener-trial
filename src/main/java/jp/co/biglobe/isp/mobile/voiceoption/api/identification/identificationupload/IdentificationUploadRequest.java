package jp.co.biglobe.isp.mobile.voiceoption.api.identification.identificationupload;

import jp.co.biglobe.isp.auth.api.form.SessionIdForm;
import jp.co.biglobe.lib.publication.validation.ValueObjectNotEmpty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;

@ToString(includeFieldNames = false)
public class IdentificationUploadRequest {

    @Getter
    @Setter
    @ValueObjectNotEmpty
    @Valid
    private SessionIdForm sessionIdForm;

    @Getter
    @Setter
    @Valid
    private MultipartFile identificationDocument1;

    @Getter
    @Setter
    @Valid
    private MultipartFile identificationDocument2;

    @Getter
    @Setter
    @Valid
    private MultipartFile identificationDocument3;
}
