package jp.co.biglobe.isp.mobile.voiceoption.api.identification.identificationresultreflect;

import jp.co.biglobe.isp.mobile.voiceoption.datasource.identification.identificationresultid.IdentificationResultIdQueryMapper;
import jp.co.biglobe.isp.mobile.voiceoption.domain.voicesendmail.VoiceSendMailStatus;
import jp.co.biglobe.isp.mobile.voiceoption.service.identification.IdentificationResultReflectService;
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
public class IdentificationResultReflectApi {

    @Autowired
    private NgReasonsValidator ngReasonsValidator;

    @Autowired
    private MnpInActivationDueDateValidator mnpInActivationDueDateValidator;

    @Autowired
    private DocumentTypeValidator documentTypeValidator;

    @Autowired
    private AlarmValidationVerifier alarmValidationVerifier;

    @Autowired
    private IdentificationResultReflectService identificationResultReflectService;

    @Autowired
    private IdentificationResultReflectResponse identificationResultReflectResponse;

    @Autowired
    private IdentificationResultIdQueryMapper identificationResultIdQueryMapper;

    @RequestMapping(value = "/voiceoption/skip/identificationresultreflect/submit", method = RequestMethod.POST)
    @ResponseBody
    public Map invoke(@Valid IdentificationResultReflectRequest request, Errors errors) {

        // リクエスト取得＆バリデーションエラーのチェック
        ngReasonsValidator.validate(request, errors);
        documentTypeValidator.validate(request, errors);
        mnpInActivationDueDateValidator.validate(request, errors);
        alarmValidationVerifier.verify(errors);

        VoiceSendMailStatus voiceSendMailStatus = identificationResultReflectService.reflect(
                request.getIdentificationStatusEventForm().getValueObject(),
                request.getIdentificationReceiptNumberForm().getValueObject(),
                request.getValidIdentificationResult(identificationResultIdQueryMapper.create()),
                request.getMnpInActivationDueDate(),
                mnpInActivationDueDateValidator.getMnpIn(request.getIdentificationReceiptNumberForm().getValueObject())
        );

        return identificationResultReflectResponse.build(voiceSendMailStatus);

    }

}
