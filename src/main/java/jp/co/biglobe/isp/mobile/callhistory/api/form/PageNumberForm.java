package jp.co.biglobe.isp.mobile.callhistory.api.form;

import jp.co.biglobe.isp.mobile.callhistory.domain.paging.PageNumber;
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
public class PageNumberForm implements FormToValueObject<PageNumber> {

    @Getter
    @NotBlank
    @Pattern(regexp = "^[1-9][0-9]{0,8}$")
    private String value;

    @Override
    public PageNumber getValueObject() {

        /**
         * todo : 長さ０の文字列がわたってきたときに、ValidationErrorになるが、
         * ValidationErrorの内容をJSONにシリアライズする際にNumberに変換できず、
         * 例外になる。
         *
         * とりあえず回避策としてtry-catchで囲んでみた。
         */

        try {
            return new PageNumber(Integer.valueOf(value));
        } catch (NumberFormatException ex) {
            return null;
        }
    }
}
