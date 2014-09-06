package jp.co.biglobe.isp.mobile.voiceoption.datasource.mnpout.scenario;

import java.util.HashMap;
import java.util.Map;

/**
 * 会員参照シナリオから取り出した会員の「かな」を、可能な限り半角カナに変換するクラス
 */
public class StringConverter {

    public static char zenkakuHiraganaToZenkakuKatakana(char c) {

        Character character = c;

        // 全角かなかどうかを判定
        if (character.compareTo(new Character((char) 0x3041)) >= 0
                && character.compareTo(new Character((char) 0x3093)) <= 0) {

            // 全角かな文字に0x0060を加算して全角カナ文字に変換
            Character x = new Character((char) (character.charValue() + (new Character((char) 0x0060)).charValue()));

            return x;
        }

        // 全角かなでなければ変換しない
        return c;

    }

    public static String convert(String inputString) {

        String s = replace(inputString);

        StringBuffer sb = new StringBuffer();

        for (int i = 0; i < s.length(); i++) {

            char originalChar = s.charAt(i);

            char zenkakuKatakanaOrZenkakuHankakuEisu = zenkakuHiraganaToZenkakuKatakana(originalChar);

            String str = convertCharactor(zenkakuKatakanaOrZenkakuHankakuEisu);

            sb.append(str);
        }
        return sb.toString();
    }


    /**
     * ハッシュマップを見て、変換できる場合は変換の文字列、変換できない場合はもとの文字列を返す。
     */

    public static String convertCharactor(char c){
        Character chara = c;
        Map map = getConvertMap();
        if(map.containsKey(chara)) {
            return (String) map.get(chara);
        }

        return String.valueOf(c);
    }


    public static Map getConvertMap(){
        Map map = new HashMap<Character, String>();
        map.put('‐', "ｰ");
        map.put('—', "ｰ");
        map.put('’', "\'");
        map.put('”', "\"");
        map.put('　', " ");
        map.put('、', "､");
        map.put('。', "｡");
        map.put('「', "｢");
        map.put('」', "｣");
        map.put('゛', "ﾞ");
        map.put('゜', "ﾟ");
        map.put('ァ', "ｧ");
        map.put('ア', "ｱ");
        map.put('ィ', "ｨ");
        map.put('イ', "ｲ");
        map.put('ゥ', "ｩ");
        map.put('ウ', "ｳ");
        map.put('ェ', "ｪ");
        map.put('エ', "ｴ");
        map.put('ォ', "ｫ");
        map.put('オ', "ｵ");
        map.put('カ', "ｶ");
        map.put('ガ', "ｶﾞ");
        map.put('キ', "ｷ");
        map.put('ギ', "ｷﾞ");
        map.put('ク', "ｸ");
        map.put('グ', "ｸﾞ");
        map.put('ケ', "ｹ");
        map.put('ゲ', "ｹﾞ");
        map.put('コ', "ｺ");
        map.put('ゴ', "ｺﾞ");
        map.put('サ', "ｻ");
        map.put('ザ', "ｻﾞ");
        map.put('シ', "ｼ");
        map.put('ジ', "ｼﾞ");
        map.put('ス', "ｽ");
        map.put('ズ', "ｽﾞ");
        map.put('セ', "ｾ");
        map.put('ゼ', "ｾﾞ");
        map.put('ソ', "ｿ");
        map.put('ゾ', "ｿﾞ");
        map.put('タ', "ﾀ");
        map.put('ダ', "ﾀﾞ");
        map.put('チ', "ﾁ");
        map.put('ヂ', "ﾁﾞ");
        map.put('ッ', "ｯ");
        map.put('ツ', "ﾂ");
        map.put('ヅ', "ﾂﾞ");
        map.put('テ', "ﾃ");
        map.put('デ', "ﾃﾞ");
        map.put('ト', "ﾄ");
        map.put('ド', "ﾄﾞ");
        map.put('ナ', "ﾅ");
        map.put('ニ', "ﾆ");
        map.put('ヌ', "ﾇ");
        map.put('ネ', "ﾈ");
        map.put('ノ', "ﾉ");
        map.put('ハ', "ﾊ");
        map.put('バ', "ﾊﾞ");
        map.put('パ', "ﾊﾟ");
        map.put('ヒ', "ﾋ");
        map.put('ビ', "ﾋﾞ");
        map.put('ピ', "ﾋﾟ");
        map.put('フ', "ﾌ");
        map.put('ブ', "ﾌﾞ");
        map.put('プ', "ﾌﾟ");
        map.put('ヘ', "ﾍ");
        map.put('ベ', "ﾍﾞ");
        map.put('ペ', "ﾍﾟ");
        map.put('ホ', "ﾎ");
        map.put('ボ', "ﾎﾞ");
        map.put('ポ', "ﾎﾟ");
        map.put('マ', "ﾏ");
        map.put('ミ', "ﾐ");
        map.put('ム', "ﾑ");
        map.put('メ', "ﾒ");
        map.put('モ', "ﾓ");
        map.put('ャ', "ｬ");
        map.put('ヤ', "ﾔ");
        map.put('ュ', "ｭ");
        map.put('ユ', "ﾕ");
        map.put('ョ', "ｮ");
        map.put('ヨ', "ﾖ");
        map.put('ラ', "ﾗ");
        map.put('リ', "ﾘ");
        map.put('ル', "ﾙ");
        map.put('レ', "ﾚ");
        map.put('ロ', "ﾛ");
        map.put('ワ', "ﾜ");
        map.put('ヲ', "ｦ");
        map.put('ン', "ﾝ");
        map.put('ヴ', "ｳﾞ");
        map.put('・', "･");
        map.put('ー', "ｰ");
        map.put('！', "!");
        map.put('＃', "#");
        map.put('＄', "$");
        map.put('％', "%");
        map.put('＆', "&");
        map.put('（', "(");
        map.put('）', ")");
        map.put('＊', "*");
        map.put('＋', "+");
        map.put('，', ",");
        map.put('−', "ｰ");
        map.put('．', ".");
        map.put('／', "/");
        map.put('０', "0");
        map.put('１', "1");
        map.put('２', "2");
        map.put('３', "3");
        map.put('４', "4");
        map.put('５', "5");
        map.put('６', "6");
        map.put('７', "7");
        map.put('８', "8");
        map.put('９', "9");
        map.put('：', ":");
        map.put('；', ";");
        map.put('＜', "<");
        map.put('＝', "=");
        map.put('＞', ">");
        map.put('？', "?");
        map.put('＠', "@");
        map.put('Ａ', "A");
        map.put('Ｂ', "B");
        map.put('Ｃ', "C");
        map.put('Ｄ', "D");
        map.put('Ｅ', "E");
        map.put('Ｆ', "F");
        map.put('Ｇ', "G");
        map.put('Ｈ', "H");
        map.put('Ｉ', "I");
        map.put('Ｊ', "J");
        map.put('Ｋ', "K");
        map.put('Ｌ', "L");
        map.put('Ｍ', "M");
        map.put('Ｎ', "N");
        map.put('Ｏ', "O");
        map.put('Ｐ', "P");
        map.put('Ｑ', "Q");
        map.put('Ｒ', "R");
        map.put('Ｓ', "S");
        map.put('Ｔ', "T");
        map.put('Ｕ', "U");
        map.put('Ｖ', "V");
        map.put('Ｗ', "W");
        map.put('Ｘ', "X");
        map.put('Ｙ', "Y");
        map.put('Ｚ', "Z");
        map.put('［', "[");
        map.put('＼', "＼");
        map.put('］', "]");
        map.put('＾', "^");
        map.put('＿', "_");
        map.put('｀', "`");
        map.put('ａ', "a");
        map.put('ｂ', "b");
        map.put('ｃ', "c");
        map.put('ｄ', "d");
        map.put('ｅ', "e");
        map.put('ｆ', "f");
        map.put('ｇ', "g");
        map.put('ｈ', "h");
        map.put('ｉ', "i");
        map.put('ｊ', "j");
        map.put('ｋ', "k");
        map.put('ｌ', "l");
        map.put('ｍ', "m");
        map.put('ｎ', "n");
        map.put('ｏ', "o");
        map.put('ｐ', "p");
        map.put('ｑ', "q");
        map.put('ｒ', "r");
        map.put('ｓ', "s");
        map.put('ｔ', "t");
        map.put('ｕ', "u");
        map.put('ｖ', "v");
        map.put('ｗ', "w");
        map.put('ｘ', "x");
        map.put('ｙ', "y");
        map.put('ｚ', "z");
        map.put('｛', "{");
        map.put('｜', "|");
        map.put('｝', "}");
        map.put('〜', "~");
        map.put('\uFF5E', "~");
        map.put('\u2015', "ｰ");
        map.put('\uFF0D', "ｰ");

        return map;
    }


    /**
     * EucStringValidatorから拝借
     * 波ダッシュとかマイナスサインを変換可能な文字に置き換える。
     * ※波ダッシュと二重縦棒はシナリオで使っている文字変換のperl部品のマッピングルールにないので、
     * 　あえて外した。
     */
    public static String replace(final String beforeValue){
        return beforeValue
                .replaceAll("\u2014", "\u2015")         // EM ダッシュ("—",\u2014)は、水平線バー("―",\u2015)で救済されます。
                .replaceAll("\u2212", "\uFF0D");       // マイナスサイン("−",\u2212)は、全角ハイフンマイナス("－",\uFF0D)で救済されます。
                //.replaceAll("\u301C", "\uFF5E")     // 波ダッシュ("〜",\u301C)は、全角チルダ("～",\uFF5E)で救済されます。
                //.replaceAll("\u2016", "\u2225");        // 二重縦線("‖",\u2016)は、PARALLEL TO("∥",\u2225)で救済されます。
    }

}
