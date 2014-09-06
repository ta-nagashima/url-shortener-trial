package jp.co.biglobe.isp.mobile.voiceoption.api.identification.identificationupload;

import jp.co.biglobe.isp.mobile.voiceoption.domain.identification.IdentificationReceiptNumber;
import jp.co.biglobe.isp.mobile.voiceoption.domain.identificationfiles.*;
import jp.co.biglobe.isp.mobile.voiceoption.testdata.api.identification.identificationupload.IdentificationUploadTestFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.util.Map;

@Controller
public class IdentificationPutTestApi {

    @Autowired
    private IdentificationFilesFactory identificationFilesFactory;

    @Autowired
    private IdentificationFilesRepository identificationFilesRepository;

    @Autowired
    private IdentificationUploadResponse identificationUploadResponse;

    @RequestMapping(value = "/voiceoption/outside/user/identificationputtest/submit", method = RequestMethod.POST)
    @ResponseBody
    public Map invoke(@Valid IdentificationPutTestRequest request, Errors errors) {

        // ダミーデータを SFTP サーバに登録する
        IdentificationFiles identificationFiles = identificationFilesFactory.create(
                new IdentificationReceiptNumber("123456_7890"),
                new java.util.Date(),
                new ValidIdentificationFile(1, new IdentificationUploadTestFile()),
                new ValidIdentificationFile(2, new IdentificationUploadTestFile()),
                new ValidIdentificationFile(3, new IdentificationUploadTestFile())
        );
        identificationFilesRepository.register(identificationFiles);

        return identificationUploadResponse.build();
    }
}
