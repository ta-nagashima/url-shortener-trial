package jp.co.biglobe.isp.auth.api.operatornouserauthapply;

import jp.co.biglobe.isp.auth.api.form.OptionalOperatorIdForm;
import jp.co.biglobe.isp.auth.api.form.SessionIdForm;
import jp.co.biglobe.isp.auth.api.request.OperatorNoUserAuthRequest;
import jp.co.biglobe.lib.publication.validation.ValueObjectNotEmpty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@ToString(includeFieldNames=false)
public class OperatorNoUserAuthRequestSample {

    @Getter
    @Setter
    @NotNull
    @Valid
    private OperatorNoUserAuthRequest operatorNoUserAuthRequest;

    @Getter
    @Setter
    @ValueObjectNotEmpty
    @Valid
    private SessionIdForm sessionIdForm;

    @Getter
    @Setter
    @ValueObjectNotEmpty
    @Valid
    private OptionalOperatorIdForm operatorIdForm;


}
