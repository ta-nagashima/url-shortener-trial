package jp.co.biglobe.isp.mobile.voiceoption.api.neworder.withmnpdatatovoiceapply;


import jp.co.biglobe.isp.mobile.extension.validator.OptionalValueObjectValidator;
import jp.co.biglobe.isp.mobile.voiceoption.api.form.MnpGenderOptionForm;
import jp.co.biglobe.isp.mobile.voiceoption.api.neworder.withmnpapply.VoiceNewOrderWithMnpApplyRequest;
import jp.co.biglobe.isp.mobile.voiceoption.domain.identification.ReceiptStatus;
import jp.co.biglobe.isp.mobile.voiceoption.domain.mnppersonalinfo.MnpGender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class MnpPersonalInfoForDataToVoiceValidator implements Validator {

    @Autowired
    OptionalValueObjectValidator optionalValueObjectValidator;

    @Override
    public boolean supports(Class<?> clazz) {
        return VoiceNewOrderWithMnpApplyRequest.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

        DataToVoiceOrderWithMnpApplyRequest dataToVoiceOrderWithMnpApplyRequest = (DataToVoiceOrderWithMnpApplyRequest)target;

        if(validatorUnnecessary(dataToVoiceOrderWithMnpApplyRequest, errors)){
            return;
        }

        mnpFullNameFormValidation(errors);
        mnpFullNameKanaFormValidation(errors);
        mnpGenderFormValidation(dataToVoiceOrderWithMnpApplyRequest.getMnpGenderForm(), errors);
        mnpBirthdayFormValidation(errors);


    }

    private boolean validatorUnnecessary(DataToVoiceOrderWithMnpApplyRequest voiceNewOrderWithMnpApplyRequest,  Errors errors){

        if(errors.hasFieldErrors("receiptStatusForm") || errors.hasFieldErrors("receiptStatusForm.value")){
            return true;
        }

        ReceiptStatus receiptStatus = voiceNewOrderWithMnpApplyRequest.getReceiptStatusForm().getValueObject();
        return receiptStatus.validatorMnpInputItemsUnnecessary();
    }

    private void mnpFullNameFormValidation(Errors errors){

        String fieldName = "mnpFullNameForm";

        optionalValueObjectValidator.run(fieldName, errors);
        if(optionalValueObjectValidator.hasErrors(fieldName, errors)){
            return;
        }


    }

    private void mnpFullNameKanaFormValidation(Errors errors){

        String fieldName = "mnpFullNameKanaForm";

        optionalValueObjectValidator.run(fieldName, errors);
        if(optionalValueObjectValidator.hasErrors(fieldName, errors)){
            return;
        }


    }

    private void mnpGenderFormValidation(MnpGenderOptionForm mnpGenderOptionForm, Errors errors){

        String fieldName = "mnpGenderForm";

        optionalValueObjectValidator.run(fieldName, errors);
        if(optionalValueObjectValidator.hasErrors(fieldName, errors)){
            return;
        }

        optionalValueObjectValidator.rejectIfEnumNoApiValue(MnpGender.class, mnpGenderOptionForm.getValue(), errors, fieldName);
    }

    private void mnpBirthdayFormValidation(Errors errors){

        String fieldName = "mnpBirthdayForm";

        optionalValueObjectValidator.run(fieldName, errors);
        if(optionalValueObjectValidator.hasErrors(fieldName, errors)){
            return;
        }
    }
}
