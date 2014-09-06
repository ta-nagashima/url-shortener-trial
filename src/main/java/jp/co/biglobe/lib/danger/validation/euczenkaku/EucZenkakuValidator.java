package jp.co.biglobe.lib.danger.validation.euczenkaku;

import jp.co.biglobe.lib.danger.charset.EucJpExtensionSingleton;
import jp.co.biglobe.lib.publication.validation.EucZenkaku;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

public class EucZenkakuValidator implements ConstraintValidator<EucZenkaku, String> {

    @Override
    public void initialize(EucZenkaku required) {}

    @Override
    public boolean isValid(final String beforeValue, ConstraintValidatorContext context) {

        if (beforeValue == null) {
            return true;
        }

        // ASCII文字、半角カタカナはエラーにする
        if (Pattern.compile("[\u0000-\u007F\uFF61-\uFF9F]").matcher(beforeValue).find()) {
            return false;
        }
        // 一部文字を除く、HalfWidth 文字である ISO/IEC8859-1 Latin-1 はエラーにする
        if (Pattern.compile("[’±×÷°§¶]").matcher(beforeValue).find() == false
                && Pattern.compile("[\u0080-\u024F]").matcher(beforeValue).find()) {
            return false;
        }

        final String beforeValue2 = replace(beforeValue);

        String afterValue = afterUtf8(beforeValue);
        return afterValue.equals(beforeValue2);

    }

    /**
     * 同じ文字だが、EUCとUTF-8で文字コードが違う文字がいる。
     * そいつらの文字がきても、バリデーションはOKにするので、
     * バリデーションがOKになる文字に変換している
     */
    private String replace(final String beforeValue){
        return beforeValue.replaceAll("〜", "～")     // 波ダッシュ("〜",\u301C)は、全角チルダ("～",\uFF5E)で救済されます。
             .replaceAll("\u2014", "\u2015")         // EM ダッシュ("—",\u2014)は、水平線バー("―",\u2015)で救済されます。
             .replaceAll("\u2212", "\uFF0D")         // マイナスサイン("−",\u2212)は、全角ハイフンマイナス("－",\uFF0D)で救済されます。
             .replaceAll("\u2016", "\u2225");        // 二重縦線("‖",\u2016)は、PARALLEL TO("∥",\u2225)で救済されます。
    }

    private String afterUtf8(final String beforeValue){
        return byteToUtf8(utf8ToByte(beforeValue));
    }

    private byte[] utf8ToByte(final String utf8String){
        return utf8String.getBytes(EucJpExtensionSingleton.INSTANCE.getInstance());
    }

    private String byteToUtf8(byte[] b) {
        return new String(b, EucJpExtensionSingleton.INSTANCE.getInstance());
    }

}