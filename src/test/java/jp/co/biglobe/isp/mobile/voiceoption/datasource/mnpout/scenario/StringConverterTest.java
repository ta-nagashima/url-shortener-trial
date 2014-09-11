package jp.co.biglobe.isp.mobile.voiceoption.datasource.mnpout.scenario;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.experimental.runners.Enclosed;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

@RunWith(Enclosed.class)
public class StringConverterTest {

    public static class Conversion {

        @Test
        public void _変換() {

            // 準備
            String str = "‐—’”　、。「」ぁあぃいぅうぇえぉおかがきぎくぐけげこごさざしじすずせぜそぞただちぢっつづてでとどなにぬねの" +
                    "はばぱひびぴふぶぷへべぺほぼぽまみむめもゃやゅゆょよらりるれろわをん゛゜ァアィイゥウェエォオカガキギクグケゲコゴサ" +
                    "ザシジスズセゼソゾタダチヂッツヅテデトドナニヌネノハバパヒビピフブプヘベペホボポマミムメモャヤュユョヨラリルレロワヲ" +
                    "ンヴ・ー！＃＄％＆（）＊＋，−．／０１２３４５６７８９：；＜＝＞？＠ＡＢＣＤＥＦＧＨＩＪＫＬＭＮＯＰＱＲＳＴＵＶＷＸＹＺ" +
                    "［＼］＾＿｀ａｂｃｄｅｆｇｈｉｊｋｌｍｎｏｐｑｒｓｔｕｖｗｘｙｚ｛｜｝〜―";

            // 準備
            String actual = StringConverter.convert(str);

            // 評価
            assertThat(actual, is("ｰｰ\'\" ､｡｢｣ｧｱｨｲｩｳｪｴｫｵｶｶﾞｷｷﾞｸｸﾞｹｹﾞｺｺﾞｻｻﾞｼｼﾞｽｽﾞｾｾﾞｿｿﾞﾀﾀﾞﾁﾁﾞｯﾂﾂﾞﾃﾃﾞﾄﾄﾞﾅﾆﾇﾈﾉﾊﾊﾞﾊﾟﾋﾋﾞﾋﾟﾌﾌﾞﾌﾟﾍﾍﾞﾍﾟﾎﾎﾞﾎﾟ" +
                    "ﾏﾐﾑﾒﾓｬﾔｭﾕｮﾖﾗﾘﾙﾚﾛﾜｦﾝﾞﾟｧｱｨｲｩｳｪｴｫｵｶｶﾞｷｷﾞｸｸﾞｹｹﾞｺｺﾞｻｻﾞｼｼﾞｽｽﾞｾｾﾞｿｿﾞﾀﾀﾞﾁﾁﾞｯﾂﾂﾞﾃﾃﾞﾄﾄﾞﾅﾆﾇﾈﾉﾊﾊﾞﾊﾟﾋﾋﾞﾋﾟﾌﾌﾞﾌﾟﾍﾍﾞﾍﾟﾎﾎﾞﾎﾟﾏﾐﾑﾒﾓｬﾔｭﾕ" +
                    "ｮﾖﾗﾘﾙﾚﾛﾜｦﾝｳﾞ･ｰ!#$%&()*+,ｰ./0123456789:;<=>?@ABCDEFGHIJKLMNOPQRSTUVWXYZ[＼]^_`abcdefghijklmnopqrstuvwxyz{|}~ｰ"));
        }


        /**
         * 以下、特殊文字のテスト
         * ※波ダッシュの全角チルダへの変換と二重縦棒の変換はシナリオで使っている文字変換のperl部品のマッピングルールにないので、
         * 　あえて外した。
         */



        @Test
        public void _変換_波ダッシュ() {

            // 準備
            String str = "\u301C";

            // 準備
            String actual = StringConverter.convert(str);

            // 評価
            assertThat(actual, is("~"));
        }

        /*

        @Test
        public void _変換_全角チルダ() {

            // 準備
            String str = "\uFF5E";

            // 準備
            String actual = StringConverter.convert(str);

            // 評価
            assertThat(actual, is("~"));
        }

        */

        @Test
        public void _変換_EMダッシュ() {

            // 準備
            String str = "\u2014";

            // 準備
            String actual = StringConverter.convert(str);

            // 評価
            assertThat(actual, is("ｰ"));
        }

        @Test
        public void _変換_水平バー() {

            // 準備
            String str = "\u2015";

            // 準備
            String actual = StringConverter.convert(str);

            // 評価
            assertThat(actual, is("ｰ"));
        }

        @Test
        public void _変換_マイナスサイン() {

            // 準備
            String str = "\u2212";

            // 準備
            String actual = StringConverter.convert(str);

            // 評価
            assertThat(actual, is("ｰ"));
        }

        @Test
        public void _変換_全角ハイフンマイナス() {

            // 準備
            String str = "\uFF0D";

            // 準備
            String actual = StringConverter.convert(str);

            // 評価
            assertThat(actual, is("ｰ"));
        }

        @Test
        public void _変換_漢字はそのまま() {

            // 準備
            String str = "ダルビッシュ・有　／？＆％＄＃￥｜〜＝ー＠";

            // 準備
            String actual = StringConverter.convert(str);

            // 評価
            assertThat(actual, is("ﾀﾞﾙﾋﾞｯｼｭ･有 /?&%$#￥|~=ｰ@"));
        }

    }


}
