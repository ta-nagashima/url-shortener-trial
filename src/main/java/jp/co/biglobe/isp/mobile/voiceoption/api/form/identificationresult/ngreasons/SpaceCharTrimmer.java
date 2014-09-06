package jp.co.biglobe.isp.mobile.voiceoption.api.form.identificationresult.ngreasons;

/**
 * NG理由詳細がスペースのみで構成されているときに
 * 空文字に変換する機能を提供する。
 */
public class SpaceCharTrimmer {

    public static String trimSpace(String orgStr){

        char[] value = orgStr.toCharArray();
        int len = value.length;
        int st = 0;
        char[] val = value;

        while ((st < len) && (val[st] <= ' ' || val[st] == '　')) {
            st++;
        }
        while ((st < len) && (val[len - 1] <= ' ' || val[len - 1] == '　')) {
            len--;
        }

        return ((st>0) || (len<value.length)) ? orgStr.substring(st,len):orgStr;
    }

    public static boolean isSpaceOnly(String orgStr) {

        String str = trimSpace(orgStr);

        return (str.equals(""));
    }

    public static String convertToDefaultString(String orgStr, String defStr) {

        return isSpaceOnly(orgStr) ? defStr : orgStr;
    }



}
