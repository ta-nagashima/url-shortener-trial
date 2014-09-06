package jp.co.biglobe.isp.mobile.voiceoption.api.identification.identificationupload;

import jp.co.biglobe.isp.mobile.extension.validator.OptionalValueObjectValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Component
public class IdentificationUploadValidator implements Validator {

    @Autowired
    OptionalValueObjectValidator optionalValueObjectValidator;

    @Override
    public boolean supports(Class<?> clazz) {
        return IdentificationUploadRequest.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        IdentificationUploadRequest identificationUploadRequest = (IdentificationUploadRequest) target;

        if (alternativeRequired(identificationUploadRequest)) {
            String fieldName = "identificationDocument";
            if (errors.hasFieldErrors(fieldName)) {
                return;
            }
            errors.reject("AlternativeRequired." + fieldName, fieldName + " alternative required.");
        }
    }

    /**
     * 添付ファイルがすべて空なら true
     */
    private boolean alternativeRequired(IdentificationUploadRequest identificationUploadRequest) {
        try {
            boolean identificationDocument1 = isNull(identificationUploadRequest.getIdentificationDocument1());
            boolean identificationDocument2 = isNull(identificationUploadRequest.getIdentificationDocument2());
            boolean identificationDocument3 = isNull(identificationUploadRequest.getIdentificationDocument3());
            return identificationDocument1 && identificationDocument2 && identificationDocument3;
        } catch (IOException e) {
            return true;
        }
    }

    /**
     * 添付ファイルが空か？
     */
    private boolean isNull(MultipartFile identificationDocument) throws IOException {
        if (identificationDocument == null) {
            return true;
        }
        if (identificationDocument.isEmpty()) {
            return true;
        }
        if (identificationDocument.getBytes().length <= 0) {
            return true;
        }
        return false;
    }
}
