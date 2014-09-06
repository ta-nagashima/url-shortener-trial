package jp.co.biglobe.isp.mobile.voiceoption.api.identification.identificationresultreflect;


import jp.co.biglobe.isp.mobile.extension.validator.OptionalValueObjectValidator;
import jp.co.biglobe.isp.mobile.voiceoption.api.form.MnpInActivationDueDateForm;
import jp.co.biglobe.isp.mobile.voiceoption.domain.identification.IdentificationReceiptNumber;
import jp.co.biglobe.isp.mobile.voiceoption.domain.identification.IdentificationStatusEvent;
import jp.co.biglobe.isp.mobile.voiceoption.domain.mnpin.MnpIn;
import jp.co.biglobe.isp.mobile.voiceoption.service.mnpin.MnpInCheckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * Mnp転入があるときにアクティベーション予定日が入力されている事を検証するバリデーション
 */

@Component
public class MnpInActivationDueDateValidator implements Validator {

    @Autowired
    private MnpInCheckService mnpInCheckService;

    @Autowired
    OptionalValueObjectValidator optionalValueObjectValidator;

    @Override
    public boolean supports(Class<?> clazz) {
        return IdentificationResultReflectRequest.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

        IdentificationResultReflectRequest identificationResultReflectRequest = (IdentificationResultReflectRequest) target;

        // バリデーションが不要ならここで終わり
        if (validatorUnnecessary(identificationResultReflectRequest, errors)) {
            return;
        }

        // 本人確認結果がOKであり、なおかつMNP転入があるときはアクティベーション予定日が必須（空文字の不可）であることを検証
        activationDueDateFormValidation(identificationResultReflectRequest.getActivationDueDateForm(), errors);
    }



    // バリデーションが不要かどうかを返す（true = 不要　false = 必要）
    private boolean validatorUnnecessary(IdentificationResultReflectRequest identificationResultReflectRequest, Errors errors) {

        // 本人確認番号にバリデーションエラーがあったら不要
        if (isErrorInIdentificationReceiptNumner(errors)) {
            return true;
        }

        // MNPステータスイベントにバリデーションエラーがあったら不要
        if (isErrorInIdentificationStatusEventForm(errors)) {
            return true;
        }

        // 本人確認結果イベントを取得
        IdentificationStatusEvent identificationStatusEvent
                = identificationResultReflectRequest.getIdentificationStatusEventForm().getValueObject();

        // アクティベーション日付のバリデーションが必要かどうかのフラグを取得（NGのとき不要）
        if(identificationStatusEvent.validatorActivationDueDateUnnecessary()){
            return true;
        }

        return getMnpIn(identificationResultReflectRequest.getIdentificationReceiptNumberForm().getValueObject()).isNotExist();
    }

    private boolean isErrorInIdentificationReceiptNumner(Errors errors) {
        return (errors.hasFieldErrors("identificationReceiptNumberForm")
                || errors.hasFieldErrors("identificationReceiptNumberForm.value"));
    }



    private boolean isErrorInIdentificationStatusEventForm(Errors errors) {
        return (errors.hasFieldErrors("identificationStatusEventForm")
                || errors.hasFieldErrors("identificationStatusEventForm.value"));
    }


    private void activationDueDateFormValidation(MnpInActivationDueDateForm mnpInActivationDueDateForm, Errors errors) {

        String fieldName = "activationDueDateForm";

        optionalValueObjectValidator.run(fieldName, errors);
        if (optionalValueObjectValidator.hasErrors(fieldName, errors)) {
            return;
        }
    }

    MnpIn getMnpIn(IdentificationReceiptNumber identificationReceiptNumber){

        return mnpInCheckService.check(identificationReceiptNumber);
    }

}
