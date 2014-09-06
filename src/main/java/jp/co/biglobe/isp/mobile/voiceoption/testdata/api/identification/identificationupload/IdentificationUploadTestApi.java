package jp.co.biglobe.isp.mobile.voiceoption.testdata.api.identification.identificationupload;

import jp.co.biglobe.isp.auth.domain.user.UserId;
import jp.co.biglobe.isp.auth.domain.user.ValidUserAuth;
import jp.co.biglobe.isp.auth.service.UserAuthService;
import jp.co.biglobe.isp.mobile.voiceoption.api.identification.identificationupload.IdentificationUploadResponse;
import jp.co.biglobe.isp.mobile.voiceoption.domain.identificationfiles.IdentificationFile;
import jp.co.biglobe.isp.mobile.voiceoption.domain.identificationfiles.NullIdentificationFile;
import jp.co.biglobe.isp.mobile.voiceoption.domain.identificationfiles.ValidIdentificationFile;
import jp.co.biglobe.isp.mobile.voiceoption.service.identification.IdentificationUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.Date;
import java.util.Map;

@Controller
public class IdentificationUploadTestApi {

    @Autowired
    private UserAuthService userAuthService;

    @Autowired
    private IdentificationUploadService identificationUploadService;

    @Autowired
    private IdentificationUploadResponse identificationUploadResponse;

    @RequestMapping(value = "/voiceoption/outside/user/identificationuploadtest/submit", method = RequestMethod.POST)
    @ResponseBody
    public Map invoke(@Valid IdentificationUploadTestRequest request, Errors errors) {
        // sessionIdForm が指定されたら sessionIdForm から取得
        UserId userId;
        if(request.getSessionIdForm() != null && request.getSessionIdForm().getValue() != null) {
            // セッションIDによる認証
            ValidUserAuth validUserAuth = userAuthService.auth(request.getSessionIdForm().getUserAuthManagement());
            userId = validUserAuth.getUserId();
        } else {
            userId = request.getUserIdForm().getValueObject();
        }

        // サービスの呼び出し
        identificationUploadService.upload(
                userId,
                new Date(),
                newIdentificationFile(1, new IdentificationUploadTestFile()),
                newIdentificationFile(2, new IdentificationUploadTestFile()),
                newIdentificationFile(3, new IdentificationUploadTestFile())
        );

        return identificationUploadResponse.build();
    }

    private IdentificationFile newIdentificationFile(int number, MultipartFile multipartFile) {
        if (multipartFile == null || multipartFile.isEmpty()) {
            return new NullIdentificationFile();
        } else {
            return new ValidIdentificationFile(number, multipartFile);
        }
    }
}
