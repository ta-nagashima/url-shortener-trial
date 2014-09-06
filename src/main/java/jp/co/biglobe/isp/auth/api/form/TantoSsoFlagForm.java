package jp.co.biglobe.isp.auth.api.form;

import jp.co.biglobe.isp.auth.domain.operatornouser.TantoSsoFlag;
import jp.co.biglobe.lib.publication.form.EnumApiValueConverter;
import jp.co.biglobe.lib.publication.form.FormToValueObject;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@ToString(includeFieldNames = false)
@EqualsAndHashCode
@AllArgsConstructor
public class TantoSsoFlagForm  implements FormToValueObject<TantoSsoFlag> {

    @Getter
    private String value;

    @Override
    public TantoSsoFlag getValueObject() {

        if(!value.equals(TantoSsoFlag.SINGLE_SIGN_ON.getApiValue())){
            return EnumApiValueConverter.convert(TantoSsoFlag.class, "0");
        }
        return EnumApiValueConverter.convert(TantoSsoFlag.class, value);
    }

}
