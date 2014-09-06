package jp.co.biglobe.isp.mobile.voiceoption.domain.identification;

import jp.co.biglobe.isp.mobile.extension.valueobject.ValueObjectConvertForApi;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.joda.time.DateTime;

@ToString(includeFieldNames=false)
@EqualsAndHashCode
@AllArgsConstructor
public class IdentificationReceiptNumber implements ValueObjectConvertForApi {
    @Getter
    private final String value;

    public static IdentificationReceiptNumber createIdentificationReceiptNumber(String serialNumber){
        String date = new DateTime().toString("yyMMdd");
        return new IdentificationReceiptNumber(date.concat("_").concat(serialNumber));
    }

    @Override
    public String getApiValue() {return value;}
}
