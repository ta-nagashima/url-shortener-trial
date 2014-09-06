package jp.co.biglobe.isp.mobile.voiceoption.domain.mnpout.mnpoutreservationnumber;

import jp.co.biglobe.isp.mobile.extension.valueobject.ValueObjectConvertForApi;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;

@ToString(includeFieldNames = false)
@EqualsAndHashCode
@AllArgsConstructor
public class ExpireDate implements ValueObjectConvertForApi {

    private final String value;

    public String getApiValue() {
        return value;
    }


    public boolean isValid() {

        DateTime today = new DateTime().dayOfMonth().roundFloorCopy();

        DateTime exDateTime = DateTimeFormat.forPattern("yyyyMMdd").parseDateTime(value);

        return today.equals(exDateTime) || today.isBefore(exDateTime);

    }
}
