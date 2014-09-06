package jp.co.biglobe.isp.mobile.voiceoption.api.form.identificationresult.identificationdocuments;

import org.dbunit.DatabaseUnitException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Enclosed;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Set;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

@RunWith(Enclosed.class)
public class IdentificationDocumentTypeFormTest {


    public static class _正常系のテスト {

        ValidatorFactory validatorFactory;

        Validator validator;

        IdentificationDocumentTypeForm sut;

        @Before
        public void setup() throws IOException, DatabaseUnitException, SQLException {
            validatorFactory = Validation.buildDefaultValidatorFactory();
            validator = validatorFactory.getValidator();
        }

        @Test
        public void _本人確認書類種別が運転免許証() throws Exception {

            sut = new IdentificationDocumentTypeForm("license");

            Set<ConstraintViolation<IdentificationDocumentTypeForm>> violations = validator.validate(sut);
            assertThat(violations.size(), is(0));

        }

        @Test
        public void _本人確認書類種別がパスポート() throws Exception {

            sut = new IdentificationDocumentTypeForm("passport");

            Set<ConstraintViolation<IdentificationDocumentTypeForm>> violations = validator.validate(sut);
            assertThat(violations.size(), is(0));

        }

        @Test
        public void _本人確認書類種別が外国人登録証明書() throws Exception {

            sut = new IdentificationDocumentTypeForm("foreign_registration_card");

            Set<ConstraintViolation<IdentificationDocumentTypeForm>> violations = validator.validate(sut);
            assertThat(violations.size(), is(0));

        }

        @Test
        public void _本人確認書類種別が在留カード() throws Exception {

            sut = new IdentificationDocumentTypeForm("residence_card");

            Set<ConstraintViolation<IdentificationDocumentTypeForm>> violations = validator.validate(sut);
            assertThat(violations.size(), is(0));

        }

        @Test
        public void _本人確認書類種別が特別永住者証明書() throws Exception {

            sut = new IdentificationDocumentTypeForm("special_permanent_resident_certificate");

            Set<ConstraintViolation<IdentificationDocumentTypeForm>> violations = validator.validate(sut);
            assertThat(violations.size(), is(0));

        }

        @Test
        public void _本人確認書類種別が住民基本台帳カード() throws Exception {

            sut = new IdentificationDocumentTypeForm("basic_resident_register_card");

            Set<ConstraintViolation<IdentificationDocumentTypeForm>> violations = validator.validate(sut);
            assertThat(violations.size(), is(0));

        }

        @Test
        public void _本人確認書類種別が健康保険証() throws Exception {

            sut = new IdentificationDocumentTypeForm("health_insurance_card");

            Set<ConstraintViolation<IdentificationDocumentTypeForm>> violations = validator.validate(sut);
            assertThat(violations.size(), is(0));

        }

        @Test
        public void _本人確認書類種別が本人確認書類無効() throws Exception {

            sut = new IdentificationDocumentTypeForm("invalid");

            Set<ConstraintViolation<IdentificationDocumentTypeForm>> violations = validator.validate(sut);
            assertThat(violations.size(), is(0));

        }

    }

    public static class _エラー系のテスト {

        ValidatorFactory validatorFactory;

        Validator validator;

        IdentificationDocumentTypeForm sut;

        @Before
        public void setup() throws IOException, DatabaseUnitException, SQLException {
            validatorFactory = Validation.buildDefaultValidatorFactory();
            validator = validatorFactory.getValidator();
        }

        @Test
        public void _空白のみ() throws Exception {

            sut = new IdentificationDocumentTypeForm(" ");

            Set<ConstraintViolation<IdentificationDocumentTypeForm>> violations = validator.validate(sut);
            assertThat(violations.size(), is(2));

        }

        @Test
        public void _規定外() throws Exception {

            sut = new IdentificationDocumentTypeForm("手形");

            Set<ConstraintViolation<IdentificationDocumentTypeForm>> violations = validator.validate(sut);
            assertThat(violations.size(), is(1));

        }
    }

}