package jp.co.biglobe.isp.mobile.biglobedenwa.api.linkage.refer;


import jp.co.biglobe.isp.auth.service.authentication.OperatorAuthByIdAndPasswordService;
import jp.co.biglobe.isp.mobile.biglobedenwa.domain.linkage.BiglobeDenwaLinkage;
import jp.co.biglobe.isp.mobile.biglobedenwa.service.linkage.BiglobeDenwaLinkageReferService;
import jp.co.biglobe.isp.mobile.ltethreeg.domain.ltethreegengagement.LteThreeGEngagementEntity;
import jp.co.biglobe.isp.mobile.ltethreeg.domain.ltethreegengagement.LteThreeGEngagementRepository;
import jp.co.biglobe.isp.mobile.ltethreeg.domain.sim.ValidMsisdn;
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
public class BiglobeDenwaLinkageReferApi {

    @Autowired
    private AlarmValidationVerifier alarmValidationVerifier;

    @Autowired
    private OperatorAuthByIdAndPasswordService operatorAuthByIdAndPasswordService;

    @Autowired
    private LteThreeGEngagementRepository lteThreeGEngagementRepository;

    @Autowired
    private BiglobeDenwaLinkageReferService biglobeDenwaLinkageReferService;

    @Autowired
    private BiglobeDenwaLinkageReferResponse biglobeDenwaLinkageReferResponse;

    @RequestMapping(value = "/biglobedenwa/operator/linkage/refer", method = RequestMethod.POST)
    @ResponseBody
    public Map invoke(
            @Valid BiglobeDenwaLinkageReferRequest request,
            Errors errors) {
        alarmValidationVerifier.verify(errors);


        // 担当者認証
        operatorAuthByIdAndPasswordService.auth(
                request.getOperatorIdForm().getValueObject(),
                request.getOperatorPasswordForm().getValueObject()
        );

        LteThreeGEngagementEntity lteThreeGEngagementEntity =
                lteThreeGEngagementRepository.findVoiceSimEngagementByMsisdn(new ValidMsisdn(request.getBiglobeDenwaMsisdnForm().getValue()));

        BiglobeDenwaLinkage biglobeDenwaLinkage =
                biglobeDenwaLinkageReferService.refer(
                        request.getBiglobeDenwaMsisdnForm().getValueObject()
                );

        return biglobeDenwaLinkageReferResponse.build(lteThreeGEngagementEntity, biglobeDenwaLinkage);
    }
}
