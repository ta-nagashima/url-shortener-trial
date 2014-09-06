package jp.co.biglobe.isp.mobile.ltethreeg.api.form.speedcharge;

import jp.co.biglobe.isp.mobile.ltethreeg.option.charge.domain.speedchargeengagement.SpeedChargePurchasedVolumeNumber;
import jp.co.biglobe.lib.publication.form.FormToValueObject;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Max;
import javax.validation.constraints.Pattern;

@ToString(includeFieldNames = false)
@EqualsAndHashCode
@AllArgsConstructor
public class SpeedChargePurchasedVolumeNumberForm implements FormToValueObject<SpeedChargePurchasedVolumeNumber> {

    @Getter
    @NotBlank
    @Pattern(regexp = "[0-9]*")
    @Max(10)
    private String value;

    @Override
    public SpeedChargePurchasedVolumeNumber getValueObject(){

        /**
         * todo : 長さ０の文字列がわたってきたときに、ValidationErrorになるが、
         * ValidationErrorの内容をJSONにシリアライズする際にNumberに変換できず、
         * 例外になる。
         *
         * とりあえず回避策としてtry-catchで囲んでみた。
         */

        try {
            return new SpeedChargePurchasedVolumeNumber(Integer.valueOf(value));
        } catch (NumberFormatException ex) {
            return null;
        }
    }

}
