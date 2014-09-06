package jp.co.biglobe.isp.mobile.callhistory.api.history.summayrefer;

import jp.co.biglobe.isp.mobile.callhistory.api.form.TargetMonthForm;
import lombok.Setter;
import lombok.ToString;
import org.joda.time.DateTime;

import javax.validation.Valid;

@ToString(includeFieldNames = false)
public class CallHistorySummaryReferRequest {

    @Setter
    @Valid
    private TargetMonthForm targetMonth;


    public TargetMonthForm getTargetMonthForm(){

        if(targetMonth == null) {
            String dtStr = new DateTime().minusMonths(1).toString("yyyyMM");
            return new TargetMonthForm(dtStr);
        }

        return targetMonth;
    }
}
