package jp.co.biglobe.isp.mobile.biglobedenwa.api.form;

import jp.co.biglobe.isp.mobile.biglobedenwa.domain.linkage.BiglobeDenwaApplyChannel;
import jp.co.biglobe.lib.publication.form.EnumApiValueConverter;
import jp.co.biglobe.lib.publication.form.FormToValueObject;
import jp.co.biglobe.lib.publication.validation.EnumForApiValue;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.hibernate.validator.constraints.NotBlank;

@ToString(includeFieldNames = false)
@EqualsAndHashCode
@AllArgsConstructor
public class BiglobeDenwaApplyChannelForm implements FormToValueObject<BiglobeDenwaApplyChannel> {

    @Getter
    @NotBlank
    @EnumForApiValue(BiglobeDenwaApplyChannel.class)
    private String value;

    @Override
    public BiglobeDenwaApplyChannel getValueObject() {
        return EnumApiValueConverter.convert(BiglobeDenwaApplyChannel.class, value);
    }
}