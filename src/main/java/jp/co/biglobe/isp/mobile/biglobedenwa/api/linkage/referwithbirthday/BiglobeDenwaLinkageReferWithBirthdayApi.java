package jp.co.biglobe.isp.mobile.biglobedenwa.api.linkage.referwithbirthday;

import jp.co.biglobe.isp.auth.service.authentication.OperatorAuthByIdAndPasswordService;
import jp.co.biglobe.isp.mobile.biglobedenwa.api.linkage.refer.BiglobeDenwaLinkageReferResponse;
import jp.co.biglobe.isp.mobile.biglobedenwa.domain.linkage.BiglobeDenwaLinkage;
import jp.co.biglobe.isp.mobile.biglobedenwa.domain.linkage.BiglobeDenwaMsisdn;
import jp.co.biglobe.isp.mobile.biglobedenwa.domain.policy.BiglobeDenwaBirthDayCheckStatus;
import jp.co.biglobe.isp.mobile.biglobedenwa.service.linkage.BiglobeDenwaBirthdayAuthService;
import jp.co.biglobe.isp.mobile.biglobedenwa.service.linkage.BiglobeDenwaLinkageReferService;
import jp.co.biglobe.isp.mobile.ltethreeg.domain.ltethreegengagement.LteThreeGEngagementEntity;
import jp.co.biglobe.isp.mobile.ltethreeg.domain.ltethreegengagement.LteThreeGEngagementRepository;
import jp.co.biglobe.lib.plugin.validationverifier.AlarmValidationVerifier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.util.Map;

@Controller
public class BiglobeDenwaLinkageReferWithBirthdayApi {

    @Autowired
    private AlarmValidationVerifier alarmValidationVerifier;

    @Autowired
    OperatorAuthByIdAndPasswordService operatorAuthByIdAndPasswordService;

    @Autowired
    private LteThreeGEngagementRepository lteThreeGEngagementRepository;

    @Autowired
    private BiglobeDenwaLinkageReferService biglobeDenwaLinkageReferService;

    @Autowired
    private BiglobeDenwaBirthdayAuthService biglobeDenwaBirthdayAuthService;

    @Autowired
    private BiglobeDenwaLinkageReferWithBirthdayResponse biglobeDenwaLinkageReferWithBirthdayResponse;

    @RequestMapping(value = "/biglobedenwa/operator/linkage/withbirthday/refer")
    @ResponseBody
    public Map invoke(
            @Valid BiglobeDenwaLinkageReferWithBirthdayRequest request,
            Errors errors
    ) {
        alarmValidationVerifier.verify(errors);


        // 担当者認証
        operatorAuthByIdAndPasswordService.auth(
                request.getOperatorIdForm().getValueObject(),
                request.getOperatorPasswordForm().getValueObject()
        );

        BiglobeDenwaLinkage biglobeDenwaLinkage =
                biglobeDenwaLinkageReferService.refer(
                        request.getBiglobeDenwaMsisdnForm().getValueObject()
                );

        BiglobeDenwaBirthDayCheckStatus biglobeDenwaBirthdayCheckStatus =
                biglobeDenwaBirthdayAuthService.auth(
                        new BiglobeDenwaMsisdn(request.getBiglobeDenwaMsisdnForm().getValue()),
                        request.getBiglobeDenwaBirthdayForm().getValueObject(),
                        biglobeDenwaLinkage
                );

        biglobeDenwaBirthdayCheckStatus.verifyForBiglobeDenwaBirthDayAuthResult();



        return biglobeDenwaLinkageReferWithBirthdayResponse.build(biglobeDenwaBirthdayCheckStatus,biglobeDenwaLinkage);

    }

}