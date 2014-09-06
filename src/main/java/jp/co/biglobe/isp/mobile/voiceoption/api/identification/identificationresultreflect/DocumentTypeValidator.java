package jp.co.biglobe.isp.mobile.voiceoption.api.identification.identificationresultreflect;


import jp.co.biglobe.isp.mobile.extension.validator.OptionalValueObjectValidator;
import jp.co.biglobe.isp.mobile.voiceoption.api.form.identificationresult.identificationdocuments.IdentificationDocumentTypeForm;
import jp.co.biglobe.isp.mobile.voiceoption.domain.identification.IdentificationStatusEvent;
import jp.co.biglobe.isp.mobile.voiceoption.domain.identification.identificationresult.identificationdocuments.IdentificationDocumentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * 本人確認結果の反映において、本人確認結果がOKの時に
 * 本人確認書類種別がInvalidでない事を検証するバリデーター
 */

@Component
public class DocumentTypeValidator implements Validator {

    @Autowired
    OptionalValueObjectValidator optionalValueObjectValidator;

    @Override
    public boolean supports(Class<?> clazz) {
        return IdentificationResultReflectRequest.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

        IdentificationResultReflectRequest identificationResultReflectRequest = (IdentificationResultReflectRequest) target;

        // Validationが不要ならここで終わり
        if (validatorUnnecessary(identificationResultReflectRequest, errors)) {
            return;
        }

        // OKのときはDocumentTypeがInvalidでない事を検証
        documentTypeFormValidation(identificationResultReflectRequest.getIdentificationDocumentTypeForm(), errors);
    }

    private boolean validatorUnnecessary(IdentificationResultReflectRequest identificationResultReflectRequest, Errors errors) {


        // IdentificationStatusEventFormにバリデーションエラーがあったら以降の検証はしない
        if (isErrorInIdentificationStatusEventForm(errors)) {
            return true;
        }

        // DocumentTypeにバリデーションエラーがあったら以降の検証はしない
        if (isErrorInIdentificationDocumentTypeForm(errors)) {
            return true;
        }

        // 本人確認結果がOKの時に、残りのバリデーション処理をする
        IdentificationStatusEvent identificationStatusEvent
                = identificationResultReflectRequest.getIdentificationStatusEventForm().getValueObject();
        return identificationStatusEvent.documentTypeValidationUnnecessary();
    }

    private boolean isErrorInIdentificationStatusEventForm(Errors errors) {
        return (errors.hasFieldErrors("identificationStatusEventForm")
                || errors.hasFieldErrors("identificationStatusEventForm.value"));
    }

    private boolean isErrorInIdentificationDocumentTypeForm(Errors errors) {
        return (errors.hasFieldErrors("identificationDocumentTypeForm")
                || errors.hasFieldErrors("identificationDocumentTypeForm.value"));
    }

    private void documentTypeFormValidation(IdentificationDocumentTypeForm identificationDocumentTypeForm, Errors errors) {

        String fieldName = "identificationDocumentTypeForm";

        String addFieldName = ".value";

        if (identificationDocumentTypeForm.getValueObject() == IdentificationDocumentType.INVALID) {
            errors.rejectValue(fieldName + addFieldName,
                    "notMatching.registration." + fieldName + addFieldName,
                    null, fieldName + addFieldName + " Not match.");
        }
    }
}
