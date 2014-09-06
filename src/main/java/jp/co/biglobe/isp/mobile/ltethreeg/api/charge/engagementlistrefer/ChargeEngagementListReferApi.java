package jp.co.biglobe.isp.mobile.ltethreeg.api.charge.engagementlistrefer;

import jp.co.biglobe.isp.mobile.extension.paging.PageNumber;
import jp.co.biglobe.isp.mobile.extension.paging.PageSize;
import jp.co.biglobe.isp.mobile.extension.paging.Paging;
import jp.co.biglobe.isp.mobile.ltethreeg.api.charge.engagementdetailrefer.ChargeEngagementDetailReferRequest;
import jp.co.biglobe.isp.mobile.ltethreeg.api.charge.engagementdetailrefer.ChargeEngagementDetailReferResponse;
import jp.co.biglobe.isp.mobile.ltethreeg.option.charge.domain.view.chargeengagementlistrefer.ChargeEngagementEntityList;
import jp.co.biglobe.isp.mobile.ltethreeg.service.charge.ChargeEngagementDetailReferService;
import jp.co.biglobe.isp.mobile.ltethreeg.service.charge.ChargeEngagementListReferService;
import jp.co.biglobe.lib.plugin.validationverifier.AlarmValidationVerifier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.util.Map;

@Controller
public class ChargeEngagementListReferApi {

    @Autowired
    private AlarmValidationVerifier alarmValidationVerifier;

    @Autowired
    private ChargeEngagementListReferService chargeEngagementListReferService;

    @Autowired
    private ChargeEngagementListReferResponse chargeEngagementListReferResponse;

    @RequestMapping(value = "/lte3g/skip/option/charge/engagement/list/refer", method = RequestMethod.POST)
    @ResponseBody
    public Map invoke(@Valid ChargeEngagementListReferRequest request, Errors errors, Pageable pageable) {

        alarmValidationVerifier.verify(errors);

        Paging paging = new Paging(new PageNumber(pageable.getPageNumber()), new PageSize(pageable.getPageSize()));
        ChargeEngagementListReferService.ChargeEngagementListReferContainer chargeEngagementListReferContainer =
                chargeEngagementListReferService.refer(request.getLteThreeGEngagementNumberForm().getValueObject(), paging);

        return chargeEngagementListReferResponse.build(chargeEngagementListReferContainer);
    }

}
