package jp.co.biglobe.isp.sample.api.samplerefer;

import jp.co.biglobe.isp.sample.datasource.sampleuser.SampleRepositoryDb;
import jp.co.biglobe.isp.sample.domain.samplecheckstatus.SampleCheckStatus;
import jp.co.biglobe.isp.sample.domain.sampleuser.SampleUser;
import jp.co.biglobe.isp.sample.domain.sampleuser.SampleUserId;
import jp.co.biglobe.lib.plugin.validationverifier.AlarmValidationVerifier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.util.Map;

@Controller
public class SampleReferApi {
    @Autowired
    private AlarmValidationVerifier alarmValidationVerifier;

    @Autowired
    private SampleRepositoryDb sampleRepositoryDb;

    @Autowired
    private SampleReferResponse sampleReferResponse;

    @RequestMapping(value = "/sample/refer", method = RequestMethod.POST)
    @ResponseBody
    public Map invoke(@Valid SampleReferRequest sampleReferRequest,
                      Errors errors) {

        alarmValidationVerifier.verify(errors);

        SampleUser sampleUser = sampleRepositoryDb.findBySampleUserId(new SampleUserId(1));

        return sampleReferResponse.build(SampleCheckStatus.REGISTERED);
    }
}
