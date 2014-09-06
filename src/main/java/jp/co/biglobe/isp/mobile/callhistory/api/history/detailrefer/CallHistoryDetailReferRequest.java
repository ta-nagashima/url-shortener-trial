package jp.co.biglobe.isp.mobile.callhistory.api.history.detailrefer;

import jp.co.biglobe.isp.mobile.callhistory.api.form.DisplayCountForm;
import jp.co.biglobe.isp.mobile.callhistory.api.form.InternationalCallKindForm;
import jp.co.biglobe.isp.mobile.callhistory.api.form.PageNumberForm;
import jp.co.biglobe.isp.mobile.callhistory.api.form.TargetMonthForm;
import jp.co.biglobe.isp.mobile.ltethreeg.api.form.ltethreegengagement.MsisdnForm;
import jp.co.biglobe.lib.publication.validation.ValueObjectNotEmpty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;

public class CallHistoryDetailReferRequest {

    @Getter
    @Setter
    @ValueObjectNotEmpty
    @Valid
    private MsisdnForm msisdn;

    @Getter
    @Setter
    @ValueObjectNotEmpty
    @Valid
    private InternationalCallKindForm internationalCallKind;

    @Getter
    @Setter
    @ValueObjectNotEmpty
    @Valid
    private TargetMonthForm targetMonth;

    @Setter
    @Valid
    private DisplayCountForm displayCount;

    @Setter
    @Valid
    private PageNumberForm pageNumber;


    public DisplayCountForm getDisplayCountForm(){

        if(displayCount == null) {
            return new DisplayCountForm("500");
        }

        return displayCount;
    }

    public PageNumberForm getPageNumberForm(){

        if(pageNumber == null) {
            return new PageNumberForm("1");
        }

        return pageNumber;
    }









}
