package jp.co.biglobe.isp.mobile.biglobedenwa.api.linkage.applyfromapp;


import jp.co.biglobe.isp.auth.service.authentication.OperatorAuthByIdAndPasswordService;
import jp.co.biglobe.isp.mobile.biglobedenwa.domain.linkage.BiglobeDenwaMsisdn;
import jp.co.biglobe.isp.mobile.biglobedenwa.service.linkage.BiglobeDenwaLinkageApplyFromAppService;
import jp.co.biglobe.isp.mobile.ltethreeg.domain.ltethreegengagement.LteThreeGEngagementRepository;
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
public class BiglobeDenwaLinkageApplyFromAppApi {

    @Autowired
    private AlarmValidationVerifier alarmValidationVerifier;

    @Autowired
    private BiglobeDenwaLinkageApplyFromAppService biglobeDenwaLinkageApplyFromAppService;

    @Autowired
    private BiglobeDenwaLinkageApplyFromAppResponse biglobeDenwaLinkageApplyFromAppResponse;

    @Autowired
    private OperatorAuthByIdAndPasswordService operatorAuthByIdAndPasswordService;

    @Autowired
    private LteThreeGEngagementRepository lteThreeGEngagementRepository;

    @RequestMapping(value = "/biglobedenwa/operator/linkage/apply/submit" ,method = RequestMethod.POST)
    @ResponseBody
    public Map invoke(@Valid BiglobeDenwaLinkageApplyFromAppRequest biglobeDenwaLinkageApplyFromAppRequest, Errors errors){

        alarmValidationVerifier.verify(errors);

        // 担当者認証
        operatorAuthByIdAndPasswordService.auth(
                biglobeDenwaLinkageApplyFromAppRequest.getOperatorIdForm().getValueObject(),
                biglobeDenwaLinkageApplyFromAppRequest.getOperatorPasswordForm().getValueObject());

        BiglobeDenwaMsisdn biglobeDenwaMsisdn = biglobeDenwaLinkageApplyFromAppRequest.getBiglobeDenwaMsisdnForm().getValueObject();


        biglobeDenwaLinkageApplyFromAppService.apply(biglobeDenwaMsisdn);

        return biglobeDenwaLinkageApplyFromAppResponse.build();
    }


}
