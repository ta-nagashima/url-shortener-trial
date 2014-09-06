package jp.co.biglobe.isp.mobile.biglobedenwa.api.rocky;

import jp.co.biglobe.isp.auth.service.authentication.OperatorAuthByIdAndPasswordService;
import jp.co.biglobe.isp.mobile.biglobedenwa.service.rocky.BiglobeDenwaResultUploadService;
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
public class BiglobeDenwaResultUploadApi {

    @Autowired
    private AlarmValidationVerifier alarmValidationVerifier;

    @Autowired
    private OperatorAuthByIdAndPasswordService operatorAuthByIdAndPasswordService;

    @Autowired
    private BiglobeDenwaResultUploadService biglobeDenwaResultUploadService;

    @Autowired
    private UpdateApiResponse response;

    @RequestMapping(value = "/biglobedenwa/operator/rocky/upload/submit" ,method = RequestMethod.POST)
    @ResponseBody
    public Map invoke(@Valid BiglobeDenwaResultUploadRequest request, Errors errors){
        alarmValidationVerifier.verify(errors);

        // 担当者認証
        operatorAuthByIdAndPasswordService.auth(
                request.getOperatorIdForm().getValueObject(),
                request.getOperatorPasswordForm().getValueObject()
        );

        biglobeDenwaResultUploadService.upload(
                request.getRockySuccessFileForm().getValueObject(),
                request.getRockyErrorFileForm().getValueObject()
        );

        return response.build();
    }
}
