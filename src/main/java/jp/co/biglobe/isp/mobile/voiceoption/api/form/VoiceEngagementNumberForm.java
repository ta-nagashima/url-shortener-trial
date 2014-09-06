package jp.co.biglobe.isp.mobile.voiceoption.api.form;

import jp.co.biglobe.isp.mobile.voiceoption.domain.voiceengagement.VoiceEngagementNumber;
import jp.co.biglobe.lib.publication.form.FormToValueObject;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Pattern;

@ToString(includeFieldNames = false)
@EqualsAndHashCode
@AllArgsConstructor
public class VoiceEngagementNumberForm implements FormToValueObject<VoiceEngagementNumber> {

    @Getter
    @NotBlank
    @Pattern(regexp = "^[1-9][0-9]{0,8}$")
    private String value;

    @Override
    public VoiceEngagementNumber getValueObject() {

        /**
         * todo : 長さ０の文字列がわたってきたときに、ValidationErrorになるが、
         * ValidationErrorの内容をJSONにシリアライズする際にNumberに変換できず、
         * 例外になる。
         *
         * とりあえず回避策としてtry-catchで囲んでみた。
         */

        try {
            return new VoiceEngagementNumber(Integer.valueOf(value));
        } catch (NumberFormatException ex) {
            return null;
        }
    }

}
