package jp.co.biglobe.isp.mobile.callhistory.domain.detail;

import jp.co.biglobe.isp.mobile.extension.valueobject.ValueObjectConvertForApi;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.math.BigDecimal;

@ToString(includeFieldNames = false)
@EqualsAndHashCode
@AllArgsConstructor
public class CallDuration implements ValueObjectConvertForApi {

    /**
     * 秒数（小数点あり）
     * 画面描画の時にはH:mm:ss.SSSに変換する必要がある
     */

    private final BigDecimal value;

    @Override
    public String getApiValue() {

        int[] ints = CallDurationStringConverter.splitToComponentTimes(value);

        StringBuilder sb = new StringBuilder();

        sb.append(ints[0]);
        sb.append(":");

        if (ints[1] < 10) {
            sb.append("0");
        }

        sb.append(ints[1]);
        sb.append(":");

        if (ints[2] < 10) {
            sb.append("0");
        }

        sb.append(ints[2]);

        sb.append(".");
        if (ints[3] == 0) {
            sb.append("0");
        } else {
            sb.append(ints[3]);
        }

        return sb.toString();
    }


}
