package jp.co.biglobe.isp.mobile.biglobedenwa.api.linkage.outsidelinkagerefer;

import jp.co.biglobe.isp.auth.api.form.TotalAuthRequest;
import jp.co.biglobe.isp.auth.service.authentication.totalauth.TotalAuthService;
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

/**
 * (参照-01)外部向け連携状態参照
 */
@Controller
public class OutsideLinkageReferApi {

    @Autowired
    private AlarmValidationVerifier alarmValidationVerifier;

    @Autowired
    private TotalAuthService totalAuthService;

    @Autowired
    private LteThreeGEngagementRepository lteThreeGEngagementRepository;

    @Autowired
    private BiglobeDenwaLinkageReferService biglobeDenwaLinkageReferService;

    @Autowired
    private OutsideLinkageReferResponse outisideCooperationStatusReferResponse;

    @RequestMapping(value = "/biglobedenwa/outside/user/linkage/refer", method = RequestMethod.POST)
    @ResponseBody
    public Map invoke(
            @Valid OutsideLinkageReferRequest request,Errors errors,
            @Valid TotalAuthRequest totalAuthRequest, Errors authRequestErrors){

        alarmValidationVerifier.verify(errors);
        alarmValidationVerifier.verify(authRequestErrors);

        totalAuthService.auth(totalAuthRequest.getTotalAuthForm());

        LteThreeGEngagementEntity lteThreeGEngagementEntity =
                lteThreeGEngagementRepository.findVoiceSimEngagementByMsisdn(new ValidMsisdn(request.getBiglobeDenwaMsisdnForm().getValue()));

        BiglobeDenwaLinkage biglobeDenwaLinkage = biglobeDenwaLinkageReferService.refer(request.getBiglobeDenwaMsisdnForm().getValueObject());

        return outisideCooperationStatusReferResponse.build(lteThreeGEngagementEntity, biglobeDenwaLinkage);
    }
}
