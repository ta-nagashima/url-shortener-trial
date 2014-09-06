package jp.co.biglobe.isp.mobile.callhistory.api.history.summayrefer;

import jp.co.biglobe.isp.auth.api.form.UserIdForm;
import jp.co.biglobe.isp.mobile.callhistory.api.form.TargetMonthForm;
import jp.co.biglobe.lib.publication.validation.ValueObjectNotEmpty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.joda.time.DateTime;

import javax.validation.Valid;

/**
 *
 * 評価用にBiglobeIdを直接指定できるようにした。
 * 本番にはリリース不可
 *
 */

@ToString(includeFieldNames = false)
public class CallHistorySummaryReferRequestForTest {

    @Setter
    @Getter
    @ValueObjectNotEmpty
    private UserIdForm userIdForm;


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
