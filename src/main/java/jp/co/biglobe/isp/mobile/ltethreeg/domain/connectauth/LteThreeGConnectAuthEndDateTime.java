package jp.co.biglobe.isp.mobile.ltethreeg.domain.connectauth;

import jp.co.biglobe.isp.mobile.extension.date.DateToString;
import jp.co.biglobe.isp.mobile.extension.date.SystemDateTime;
import jp.co.biglobe.isp.mobile.extension.valueobject.ValueObjectConvertForApi;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.Date;

@ToString(includeFieldNames = false)
@EqualsAndHashCode
@AllArgsConstructor
public class LteThreeGConnectAuthEndDateTime implements ValueObjectConvertForApi {
    private Date value;

    public LteThreeGConnectAuthEndDateTime connectAuthEnd(){
        return new LteThreeGConnectAuthEndDateTime( new SystemDateTime().getValue() );
    }

    @Override
    public String getApiValue(){
        return DateToString.get_yyyyMMddHHmmss(value);
    }
}
