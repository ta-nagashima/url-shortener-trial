package jp.co.biglobe.isp.mobile.voiceoption.api.form;

import jp.co.biglobe.lib.publication.validation.Euc;
import jp.co.biglobe.isp.mobile.voiceoption.domain.mnppersonalinfo.MnpFullNameKana;
import jp.co.biglobe.lib.publication.form.FormToValueObject;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.hibernate.validator.constraints.NotBlank;

@ToString(includeFieldNames = false)
@EqualsAndHashCode
@AllArgsConstructor
public class MnpFullNameKanaForm implements FormToValueObject<MnpFullNameKana> {

    @Getter
    @NotBlank
    @Euc  // 会員管理のふりがなのバリデーションが何もしていないので、Eucで対応可能な文字列ならOKとする
    private String value;

    @Override
    public MnpFullNameKana getValueObject(){
        return new MnpFullNameKana(value);
    }
}
