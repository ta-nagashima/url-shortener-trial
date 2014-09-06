package jp.co.biglobe.isp.auth.api.request;

import jp.co.biglobe.isp.auth.api.form.OptionalOperatorIdForm;
import jp.co.biglobe.isp.auth.api.form.SessionIdForm;
import jp.co.biglobe.isp.auth.api.form.TantoSsoFlagForm;
import jp.co.biglobe.isp.auth.domain.operatornouser.OperatorNoUserAuthManagement;
import jp.co.biglobe.isp.auth.domain.operatornouser.TantoSsoFlag;
import jp.co.biglobe.lib.publication.validation.ValueObjectNotEmpty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.Valid;

@ToString(includeFieldNames=false)
public class OperatorNoUserAuthRequest {

    @Getter
    @Setter
    @ValueObjectNotEmpty
    @Valid
    private SessionIdForm sessionIdForm;

    @Getter
    @Setter
    @Valid
    private TantoSsoFlagForm tantoSsoFlagForm;

    @Getter
    @Setter
    @Valid
    private OptionalOperatorIdForm operatorIdForm;

    public OperatorNoUserAuthManagement getOperatorNoUserAuthManagement(){

        TantoSsoFlag tantoSsoFlag = tantoSsoFlagForm == null ?
                TantoSsoFlag.OTHER : tantoSsoFlagForm.getValueObject();

        return new OperatorNoUserAuthManagement(
                sessionIdForm.getValueObject(),
                tantoSsoFlag,
                operatorIdForm.getValueObject());
    }

}
