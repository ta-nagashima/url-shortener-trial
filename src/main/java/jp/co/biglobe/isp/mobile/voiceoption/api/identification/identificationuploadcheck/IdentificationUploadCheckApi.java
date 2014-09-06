package jp.co.biglobe.isp.mobile.voiceoption.api.identification.identificationuploadcheck;

import jp.co.biglobe.isp.auth.domain.user.UserId;
import jp.co.biglobe.isp.auth.domain.user.ValidUserAuth;
import jp.co.biglobe.isp.auth.service.UserAuthService;
import jp.co.biglobe.isp.mobile.biglobemember.domain.BiglobeMember;
import jp.co.biglobe.isp.mobile.biglobemember.domain.NotExistBiglobeMember;
import jp.co.biglobe.isp.mobile.voiceoption.domain.policy.UploadCheckStatus;
import jp.co.biglobe.isp.mobile.voiceoption.service.identification.IdentificationBiglobeMemberReferService;
import jp.co.biglobe.isp.mobile.voiceoption.service.identification.IdentificationUploadCheckService;
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
public class IdentificationUploadCheckApi {

    @Autowired
    private AlarmValidationVerifier alarmValidationVerifier;

    @Autowired
    private UserAuthService userAuthService;

    @Autowired
    private IdentificationUploadCheckService identificationUploadCheckService;

    @Autowired
    private IdentificationUploadCheckResponse identificationUploadCheckResponse;

    @Autowired
    private IdentificationBiglobeMemberReferService identificationBiglobeMemberReferService;

    @RequestMapping(value = "/voiceoption/outside/user/identificationupload/check", method = RequestMethod.POST)
    @ResponseBody
    public Map invoke(@Valid IdentificationUploadCheckRequest request, Errors errors) {
        // リクエスト取得＆バリデーションエラーのチェック
        alarmValidationVerifier.verify(errors);

        // セッションIDによる認証
        ValidUserAuth validUserAuth = userAuthService.auth(request.getSessionIdForm().getUserAuthManagement());

        // サービスの呼び出し
        UploadCheckStatus uploadCheckStatus = identificationUploadCheckService.check(validUserAuth.getUserId());

        BiglobeMember BiglobeMember = biglobeMemberRefer(uploadCheckStatus, validUserAuth.getUserId());

        // Viewへの返却
        return identificationUploadCheckResponse.build(uploadCheckStatus, BiglobeMember);
    }

    private BiglobeMember biglobeMemberRefer(UploadCheckStatus uploadCheckStatus, UserId userId) {
        if (uploadCheckStatus.isOk()) {
            return identificationBiglobeMemberReferService.refer(userId);
        } else {
            return new NotExistBiglobeMember();
        }
    }
}
