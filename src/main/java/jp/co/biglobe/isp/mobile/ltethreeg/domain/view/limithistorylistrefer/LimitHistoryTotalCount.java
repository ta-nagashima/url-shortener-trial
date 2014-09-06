package jp.co.biglobe.isp.mobile.ltethreeg.domain.view.limithistorylistrefer;

import jp.co.biglobe.isp.mobile.extension.valueobject.ValueObjectConvertForApi;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.joda.time.DateTime;

import java.util.Date;

@ToString(includeFieldNames=false)
@EqualsAndHashCode
@AllArgsConstructor
public class LimitHistoryTotalCount implements ValueObjectConvertForApi {

    private final Integer value;

    @Override
    public String getApiValue(){
        return String.valueOf(value);
    }
}

