package jp.co.biglobe.isp.mobile.voiceoption.api.form;

import jp.co.biglobe.isp.auth.domain.operatornouser.OperatorId;
import jp.co.biglobe.lib.publication.form.FormToValueObject;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * 音声関連業務の担当者ID
 * ファイルIFの長さルールにそろえるために最小サイズをなくした。
 */
@ToString(includeFieldNames = false)
@EqualsAndHashCode
@AllArgsConstructor
public class VoiceOperatorIdForm implements FormToValueObject<OperatorId> {

    @Getter
    @NotBlank
    @Size(max = 8)
    @Pattern(regexp = "^[0-9a-zA-Z]+$")
    private String value;

    public boolean isValueBlank() {
        return value.length() == 0;
    }

    @Override
    public OperatorId getValueObject() {
        return new OperatorId(value);
    }
}
