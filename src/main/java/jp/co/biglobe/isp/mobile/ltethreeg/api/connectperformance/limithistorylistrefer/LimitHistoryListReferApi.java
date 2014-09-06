package jp.co.biglobe.isp.mobile.ltethreeg.api.connectperformance.limithistorylistrefer;

import jp.co.biglobe.isp.mobile.extension.paging.PageNumber;
import jp.co.biglobe.isp.mobile.extension.paging.PageSize;
import jp.co.biglobe.isp.mobile.extension.paging.Paging;
import jp.co.biglobe.isp.mobile.ltethreeg.service.LimitHistoryListReferService;
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
public class LimitHistoryListReferApi {

    @Autowired
    private AlarmValidationVerifier alarmValidationVerifier;

    @Autowired
    private LimitHistoryListReferService limitHistoryListReferService;

    @Autowired
    private LimitHistoryListReferResponse limitHistoryListReferResponse;

    @RequestMapping(value = "/lte3g/skip/connectperformance/limithistorylist/refer", method = RequestMethod.POST)
    @ResponseBody
    public Map invoke(@Valid LimitHistoryListReferRequest request, Errors errors, Pageable pageable) {

        alarmValidationVerifier.verify(errors);

        Paging paging = new Paging(new PageNumber(pageable.getPageNumber()), new PageSize(pageable.getPageSize()));

        LimitHistoryListReferService.LimitHistoryListReferContainer limitHistoryListReferContainer = limitHistoryListReferService.refer(
                request.getLteThreeGEngagementNumberForm().getValueObject(),
                paging);

        return limitHistoryListReferResponse.build(limitHistoryListReferContainer);
    }

}
