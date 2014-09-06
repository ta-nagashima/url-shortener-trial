package jp.co.biglobe.isp.mobile.voiceoption.datasource.voicesendmail;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

/**
 * メールの「〜様」のところに埋め込む会員氏名
 */

@ToString(includeFieldNames=false)
@EqualsAndHashCode
@AllArgsConstructor
public class AddressName {

    @Getter
    private final String value;
}
