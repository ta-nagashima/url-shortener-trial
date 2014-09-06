package jp.co.biglobe.isp.mobile.voiceoption.api.form.identificationresult.ngreasons;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class SpaceCharTrimmerTest {

    @Test
    public void _trimSpace_半角() {

        String str = "   aaa       ";

        String actual = SpaceCharTrimmer.trimSpace(str);

        assertThat(actual, is("aaa"));
    }

    @Test
    public void _trimSpace_全角() {

        String str = "　　　あああ　　　　";

        String actual = SpaceCharTrimmer.trimSpace(str);

        assertThat(actual, is("あああ"));
    }

    @Test
    public void _trimSpace_半角スペースのみ() {

        String str = "          ";

        String actual = SpaceCharTrimmer.trimSpace(str);

        assertThat(actual, is(""));
    }


    @Test
    public void _trimSpace_全角スペースのみ() {

        String str = "　　　　　　　";

        String actual = SpaceCharTrimmer.trimSpace(str);

        assertThat(actual, is(""));
    }

    @Test
    public void _trimSpace_空文字() {

        String str = "";

        String actual = SpaceCharTrimmer.trimSpace(str);

        assertThat(actual, is(""));
    }

    @Test
    public void _trimSpace_全角半角スペースのみ() {

        String str1 = "      ";
        String str2 = "　　　　　　　";

        String actual = SpaceCharTrimmer.trimSpace(str1 + str2);

        assertThat(actual, is(""));
    }

    @Test
    public void _isSpaceOnly_スペースのみ() {

        String str = "　　　　　　　";

        boolean actual = SpaceCharTrimmer.isSpaceOnly(str);

        assertTrue(actual);
    }

    @Test
    public void _isSpaceOnly_スペース以外を含む() {

        String str = "　　ああああ　　　　　";

        boolean actual = SpaceCharTrimmer.isSpaceOnly(str);

        assertFalse(actual);
    }

    @Test
    public void _convertToDefaultString_スペースのみ() {

        String str = "     　　　　　";

        String actual = SpaceCharTrimmer.convertToDefaultString(str, "データ未登録");

        assertThat(actual, is("データ未登録"));
    }

    @Test
    public void _convertToDefaultString_スペース以外を含む() {

        String str = "　　ああああ　　　　　";

        String actual = SpaceCharTrimmer.convertToDefaultString(str, "データ未登録");

        assertThat(actual, is(str));
    }

}