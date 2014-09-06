package jp.co.biglobe.isp.mobile.voiceoption.api.form.identificationresult.ngreasons;

import jp.co.biglobe.isp.mobile.voiceoption.domain.identification.identificationresult.ngreasons.NgReasonDetail;
import jp.co.biglobe.lib.publication.form.FormToValueObject;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@ToString(includeFieldNames = false)
@EqualsAndHashCode
@AllArgsConstructor
public class NgReasonDetailForm implements FormToValueObject<NgReasonDetail> {

    @Getter
    @NotNull
    @Size(min=0, max=256)
    private String value;

    @Override
    public NgReasonDetail getValueObject(){

        String str = SpaceCharTrimmer.convertToDefaultString(value, "データ未登録");

        return new NgReasonDetail(str);
    }
}
