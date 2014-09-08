package jp.co.biglobe.isp.sample.api.samplecheck;

import jp.co.biglobe.isp.sample.domain.samplecheckstatus.SampleCheckStatus;
import jp.co.biglobe.lib.essential.property.PropertyAccessor;
import jp.co.biglobe.lib.plugin.validationverifier.AlarmValidationVerifier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.util.Map;

@Controller
public class SampleCheckApi {
    @Autowired
    private AlarmValidationVerifier alarmValidationVerifier;

//    @Autowired
//    private VoiceDisengagementCheckService voiceDisengagementCheckService;

    @Autowired
    private SampleCheckResponse sampleCheckResponse;

    @RequestMapping(value = "/sample/check", method = RequestMethod.POST)
    @ResponseBody
    public Map invoke(@Valid SampleCheckRequest sampleCheckRequest,
                      Errors errors) {

        alarmValidationVerifier.verify(errors);

        System.out.println("ほげほげぇええええええええええええ");
        System.out.println(sampleCheckRequest.getSampleDateTimeForm().getValueObject().getValue());

        return sampleCheckResponse.build(SampleCheckStatus.REGISTERED);
    }


    @RequestMapping(value = "/sample/value", method = RequestMethod.POST)
    @ResponseBody
    public String value(@Valid SampleCheckRequest sampleCheckRequest,
                        Errors errors) {
        return operatorId;
    }

    @Value("${sample.key.value}")
    private String operatorId;

    @RequestMapping(value = "/sample/property", method = RequestMethod.POST)
    @ResponseBody
    public String property(@Valid SampleCheckRequest sampleCheckRequest,
                           Errors errors) {
        return propertyAccessor.getProperty("sample.key.property.accessor");
    }

    @Autowired
    private PropertyAccessor propertyAccessor;
}
