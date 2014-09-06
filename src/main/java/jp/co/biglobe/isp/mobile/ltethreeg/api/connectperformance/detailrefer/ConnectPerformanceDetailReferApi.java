package jp.co.biglobe.isp.mobile.ltethreeg.api.connectperformance.detailrefer;

import jp.co.biglobe.isp.mobile.ltethreeg.domain.connectperformance.ConnectPerformanceEntity;
import jp.co.biglobe.isp.mobile.ltethreeg.domain.connectperformance.ValidConnectPerformanceEntity;
import jp.co.biglobe.isp.mobile.ltethreeg.service.ConnectPerformanceDetailReferService;
import jp.co.biglobe.lib.plugin.validationverifier.AlarmValidationVerifier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.util.Map;

/**
 * 通信実績参照
 */
@Controller
public class ConnectPerformanceDetailReferApi {
    @Autowired
    private AlarmValidationVerifier alarmValidationVerifier;

    @Autowired
    private ConnectPerformanceDetailReferService connectPerformanceDetailReferService;

    @Autowired
    private ConnectPerformanceDetailReferResponse connectPerformanceDetailReferResponse;


    @RequestMapping(value = "/lte3g/skip/connectperformance/detail/refer", method = RequestMethod.POST)
    @ResponseBody
    public Map invoke(@Valid ConnectPerformanceDetailReferRequest request, Errors errors) {

        alarmValidationVerifier.verify(errors);

        ConnectPerformanceEntity connectPerformanceEntity = connectPerformanceDetailReferService.refer(request.getLteThreeGEngagementNumberForm().getValueObject());

        return connectPerformanceDetailReferResponse.build(connectPerformanceEntity);

    }
}
