package jp.co.biglobe.isp.mobile.voiceoption.api.identification.identificationresultreflect;


import jp.co.biglobe.isp.mobile.extension.validator.OptionalValueObjectValidator;
import jp.co.biglobe.isp.mobile.voiceoption.api.form.identificationresult.ngreasons.NgReasonForm;
import jp.co.biglobe.isp.mobile.voiceoption.domain.identification.IdentificationStatusEvent;
import jp.co.biglobe.isp.mobile.voiceoption.domain.identification.identificationresult.ngreasons.NgReason;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * 本人確認結果の反映において、本人確認結果がNGの時に
 * NG理由が具体値である事を検証するためにバリデーター
 */

@Component
public class NgReasonsValidator implements Validator {

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

        // NGのときはNgReasonが必須（空文字の不可）であることを検証
        ngReasonFormValidation(identificationResultReflectRequest.getNgReasonForm(), errors);
    }

    private boolean validatorUnnecessary(IdentificationResultReflectRequest identificationResultReflectRequest, Errors errors) {


        if (isErrorInIdentificationStatusEventForm(errors)) {
            return true;
        }

        // 本人確認結果がNGの時に、残りのバリデーション処理をする
        IdentificationStatusEvent identificationStatusEvent
                = identificationResultReflectRequest.getIdentificationStatusEventForm().getValueObject();
        return identificationStatusEvent.validatorNgReasonsUnnecessary();
    }

    private boolean isErrorInIdentificationStatusEventForm(Errors errors) {
        return (errors.hasFieldErrors("identificationStatusEventForm")
                || errors.hasFieldErrors("identificationStatusEventForm.value"));
    }


    private void ngReasonFormValidation(NgReasonForm ngReasonForm, Errors errors) {

        String fieldName = "ngReasonForm";

        optionalValueObjectValidator.rejectIfEnumNoApiValue(NgReason.class, ngReasonForm.getValue(), errors, fieldName);;
        if (optionalValueObjectValidator.hasErrors(fieldName, errors)) {
            return;
        }
    }
}
