package jp.co.biglobe.isp.mobile.biglobedenwa.api.linkage.applyfrombatch;

import jp.co.biglobe.isp.mobile.biglobedenwa.service.linkage.BiglobeDenwaLinkageApplyFromBatchService;
import jp.co.biglobe.lib.plugin.response.normal.UpdateApiResponse;
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
public class BiglobeDenwaLinkageApplyFromBatchApi {

    @Autowired
    private AlarmValidationVerifier alarmValidationVerifier;

    @Autowired
    private BiglobeDenwaLinkageApplyFromBatchService service;

    @Autowired
    private UpdateApiResponse response;


    @RequestMapping(value = "/biglobedenwa/skip/linkage/apply/submit", method = RequestMethod.POST)
    @ResponseBody
    public Map invoke(@Valid BiglobeDenwaLinkageApplyFromBatchRequest request, Errors errors) {

        alarmValidationVerifier.verify(errors);

        service.apply(
                request.getBiglobeDenwaMsisdnForm().getValueObject(),
                request.getBiglobeDenwaApplyChannelForm().getValueObject()
        );

        return response.build();
    }

}
