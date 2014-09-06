package jp.co.biglobe.isp.mobile.callhistory.domain.detail;

import java.math.BigDecimal;

public class CallDurationStringConverter {

    /**
     * 秒数を時分秒のint配列に変換するメソッド
     * 小数点以下切り捨てにしている
     */

    public static int[] splitToComponentTimes(BigDecimal biggy) {

        // ここで小数点以下切り捨てられる
        long longVal = biggy.longValue();

        //小数点1位まで
        int decimalCount = (int) Math.pow(10, 1);

        int hours = (int) longVal / 3600;
        int remainder = (int) longVal - hours * 3600;
        int mins = remainder / 60;
        remainder = remainder - mins * 60;
        int secs = remainder;

        BigDecimal integerPart = new BigDecimal(longVal);
        int decimalNumber = (biggy.subtract(integerPart)
                .multiply(new BigDecimal(decimalCount)))
                .intValue();

        int[] ints = {hours, mins, secs, decimalNumber};
        return ints;
    }
}
