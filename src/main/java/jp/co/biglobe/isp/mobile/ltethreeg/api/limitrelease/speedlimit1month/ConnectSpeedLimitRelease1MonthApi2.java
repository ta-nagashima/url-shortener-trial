package jp.co.biglobe.isp.mobile.ltethreeg.api.limitrelease.speedlimit1month;

import jp.co.biglobe.isp.mobile.extension.exception.SystemException;
import jp.co.biglobe.isp.mobile.ltethreeg.domain.alarm.LteThreegAlarmIdentifier;
import jp.co.biglobe.isp.mobile.ltethreeg.service.ConnectSpeedLimitRelease1MonthService;
import jp.co.biglobe.lib.essential.exceptionhandler.ExceptionHandlerImpl;
import jp.co.biglobe.lib.plugin.response.normal.UpdateApiResponse;
import jp.co.biglobe.lib.plugin.validationverifier.AlarmValidationVerifier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.util.Map;

@Controller
public class ConnectSpeedLimitRelease1MonthApi2 {

    @Autowired
    private AlarmValidationVerifier alarmValidationVerifier;

    @Autowired
    private ConnectSpeedLimitRelease1MonthService connectSpeedLimitRelease1MonthService;

    @Autowired
    private UpdateApiResponse updateApiResponse;

    // todo 実装クラスを直接autowiredしているから、interfaceに依存するように改修する
    @Autowired
    private ExceptionHandlerImpl exceptionHandler;

    @RequestMapping(value = "/lte3g/skip/connectperformance/limitrelease2/1month/submit", method = RequestMethod.POST)
    @ResponseBody
    public Map invoke(@Valid ConnectSpeedLimitRelease1MonthRequest request, Errors errors) {

        alarmValidationVerifier.verify(errors);

        connectSpeedLimitRelease1MonthService.limit(request.getLteThreeGEngagementNumberForm().getValueObject());

        throw new NullPointerException("aaaa");

        //return updateApiResponse.build();
    }

    @ExceptionHandler(Throwable.class)
    @ResponseBody
    public Map ThrowableHandler() {

        return exceptionHandler.handleAndResponse(new SystemException("aaaaa", LteThreegAlarmIdentifier.DEFAULT));

    }
}
