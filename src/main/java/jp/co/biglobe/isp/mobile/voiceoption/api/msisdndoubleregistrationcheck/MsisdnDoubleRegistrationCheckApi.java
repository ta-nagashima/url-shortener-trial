package jp.co.biglobe.isp.mobile.voiceoption.api.msisdndoubleregistrationcheck;

import jp.co.biglobe.isp.auth.service.authentication.OperatorAuthByIdAndPasswordService;
import jp.co.biglobe.isp.mobile.voiceoption.domain.policy.MsisdnDoubleRegistrationCheckStatus;
import jp.co.biglobe.isp.mobile.voiceoption.service.neworder.MsisdnDoubleRegistrationCheckService;
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
 * MSISDN二重登録チェック
 */

@Controller
public class MsisdnDoubleRegistrationCheckApi {

    @Autowired
    private AlarmValidationVerifier alarmValidationVerifier;

    @Autowired
    private OperatorAuthByIdAndPasswordService operatorAuthByIdAndPasswordService;

    @Autowired
    private MsisdnDoubleRegistrationCheckService msisdnDoubleRegistrationCheckService;

    @Autowired
    private MsisdnDoubleRegistrationCheckResponse msisdnDoubleRegistrationCheckResponse;

    @RequestMapping(value = "/voiceoption/outside/operator/msisdndoubleregistration/check", method = RequestMethod.POST)
    @ResponseBody
    public Map invoke(@Valid MsisdnDoubleRegistrationCheckRequest msisdnDoubleRegistrationCheckRequest, Errors errors) {

        alarmValidationVerifier.verify(errors);

        operatorAuthByIdAndPasswordService.auth(
                msisdnDoubleRegistrationCheckRequest.getOperatorIdForm().getValueObject(),
                msisdnDoubleRegistrationCheckRequest.getOperatorPasswordForm().getValueObject());

        MsisdnDoubleRegistrationCheckStatus msisdnDoubleRegistrationCheckStatus = msisdnDoubleRegistrationCheckService.check(
                msisdnDoubleRegistrationCheckRequest.getVoiceMsisdnForm().getValueObject());

        return msisdnDoubleRegistrationCheckResponse.build(msisdnDoubleRegistrationCheckStatus);
    }

}
