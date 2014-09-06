package jp.co.biglobe.isp.mobile.voiceoption.api.identification.identificationupload;

import jp.co.biglobe.isp.auth.domain.user.ValidUserAuth;
import jp.co.biglobe.isp.auth.service.UserAuthService;
import jp.co.biglobe.isp.mobile.voiceoption.domain.identificationfiles.IdentificationFile;
import jp.co.biglobe.isp.mobile.voiceoption.domain.identificationfiles.NullIdentificationFile;
import jp.co.biglobe.isp.mobile.voiceoption.domain.identificationfiles.ValidIdentificationFile;
import jp.co.biglobe.isp.mobile.voiceoption.service.identification.IdentificationUploadService;
import jp.co.biglobe.lib.plugin.event.RequestEventTime;
import jp.co.biglobe.lib.plugin.validationverifier.AlarmValidationVerifier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.Map;

@Controller
public class IdentificationUploadApi {
    @Autowired
    private RequestEventTime requestEventTime;

    @Autowired
    private IdentificationUploadValidator identificationUploadValidator;

    @Autowired
    private AlarmValidationVerifier alarmValidationVerifier;

    @Autowired
    private UserAuthService userAuthService;

    @Autowired
    private IdentificationUploadService identificationUploadService;

    @Autowired
    private IdentificationUploadResponse identificationUploadResponse;

    @RequestMapping(value = "/voiceoption/outside/user/identificationupload/submit", method = RequestMethod.POST)
    @ResponseBody
    public Map invoke(@Valid IdentificationUploadRequest request, Errors errors) {
        // リクエスト取得＆バリデーションエラーのチェック
        identificationUploadValidator.validate(request, errors);
        alarmValidationVerifier.verify(errors);

        // セッションIDによる認証
        ValidUserAuth validUserAuth = userAuthService.auth(request.getSessionIdForm().getUserAuthManagement());

        // サービスの呼び出し
        identificationUploadService.upload(
                validUserAuth.getUserId(),
                requestEventTime.getDate(),
                newIdentificationFile(1, request.getIdentificationDocument1()),
                newIdentificationFile(2, request.getIdentificationDocument2()),
                newIdentificationFile(3, request.getIdentificationDocument3())
        );

        return identificationUploadResponse.build();
    }

    private IdentificationFile newIdentificationFile(int number, MultipartFile multipartFile) {
        if (multipartFile == null) {
            return new NullIdentificationFile();
        } else if (multipartFile.isEmpty()) {
            return new NullIdentificationFile();
        } else {
            return new ValidIdentificationFile(number, multipartFile);
        }
    }
}
