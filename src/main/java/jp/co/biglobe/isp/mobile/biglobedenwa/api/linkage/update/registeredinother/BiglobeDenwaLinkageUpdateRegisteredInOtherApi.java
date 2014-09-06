package jp.co.biglobe.isp.mobile.biglobedenwa.api.linkage.update.registeredinother;


import jp.co.biglobe.isp.auth.service.authentication.OperatorAuthByIdAndPasswordService;
import jp.co.biglobe.isp.mobile.biglobedenwa.service.linkage.BiglobeDenwaLinkageUpdateRegisteredInOtherService;
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
public class BiglobeDenwaLinkageUpdateRegisteredInOtherApi {

    @Autowired
    private AlarmValidationVerifier alarmValidationVerifier;

    @Autowired
    private BiglobeDenwaLinkageUpdateRegisteredInOtherService biglobeDenwaLinkageUpdateRegisteredInOtherService;

    @Autowired
    private UpdateApiResponse response;

    @Autowired
    private OperatorAuthByIdAndPasswordService service;

    @RequestMapping(value = "/biglobedenwa/operator/linkage/update/registeredinother/submit" ,method = RequestMethod.POST)
    @ResponseBody
    public Map invoke(@Valid BiglobeDenwaLinkageUpdateRegisteredInOtherRequest request, Errors errors){

        alarmValidationVerifier.verify(errors);

        // 担当者認証
        service.auth(
                request.getOperatorIdForm().getValueObject(),
                request.getOperatorPasswordForm().getValueObject());

        biglobeDenwaLinkageUpdateRegisteredInOtherService.update(
                request.getBiglobeDenwaMsisdnForm().getValueObject()
        );

        return response.build();
    }


}
