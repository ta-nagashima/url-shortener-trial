package jp.co.biglobe.isp.mobile.callhistory.domain.detail;

import jp.co.biglobe.isp.mobile.extension.valueobject.ValueObjectConvertForApi;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.text.SimpleDateFormat;
import java.util.Date;

@ToString(includeFieldNames = false)
@EqualsAndHashCode
@AllArgsConstructor
public class StartDateTime implements ValueObjectConvertForApi {


    @Getter
    private final Date value;


    @Override
    public String getApiValue() {


        // DBから取得したyyyy-MM-dd hh:mm:ss.sの形式の時刻をMM/ddの形式で返す ((それぞれ一桁の時は先頭に半角スペースを入れる)
        StringBuilder month = new StringBuilder(new SimpleDateFormat("M").format(value));
        StringBuilder day = new StringBuilder(new SimpleDateFormat("d").format(value));
        StringBuilder time = new StringBuilder(new SimpleDateFormat("HH:mm:ss").format(value));

        if (month.length() < 2) {
            month.insert(0, " ");
        }
        if (day.length() < 2) {
            day.insert(0, " ");
        }

        String apiValue = month.append("/").append(day).append(" ").append(time).toString();

        return apiValue;

    }
}
