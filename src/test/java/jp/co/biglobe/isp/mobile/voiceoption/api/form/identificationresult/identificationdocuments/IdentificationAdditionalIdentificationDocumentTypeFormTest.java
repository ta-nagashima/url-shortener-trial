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
public class IdentificationAdditionalIdentificationDocumentTypeFormTest {

    public static class _正常系のテスト {

        ValidatorFactory validatorFactory;

        Validator validator;

        IdentificationSubDocumentTypeForm sut;

        @Before
        public void setup() throws IOException, DatabaseUnitException, SQLException {
            validatorFactory = Validation.buildDefaultValidatorFactory();
            validator = validatorFactory.getValidator();
        }

        @Test
        public void _補助書類が公共料金領収書() throws Exception {

            sut = new IdentificationSubDocumentTypeForm("utility_bill_receipt");

            Set<ConstraintViolation<IdentificationSubDocumentTypeForm>> violations = validator.validate(sut);
            assertThat(violations.size(), is(0));

        }

        @Test
        public void _補助書類が住民票() throws Exception {

            sut = new IdentificationSubDocumentTypeForm("resident_card");

            Set<ConstraintViolation<IdentificationSubDocumentTypeForm>> violations = validator.validate(sut);
            assertThat(violations.size(), is(0));

        }

        @Test
        public void _補助書類が戸籍謄本() throws Exception {

            sut = new IdentificationSubDocumentTypeForm("family_register");

            Set<ConstraintViolation<IdentificationSubDocumentTypeForm>> violations = validator.validate(sut);
            assertThat(violations.size(), is(0));

        }

        @Test
        public void _補助書類がその他() throws Exception {

            sut = new IdentificationSubDocumentTypeForm("other");

            Set<ConstraintViolation<IdentificationSubDocumentTypeForm>> violations = validator.validate(sut);
            assertThat(violations.size(), is(0));

        }

        @Test
        public void _補助書類が補助書類なし() throws Exception {

            sut = new IdentificationSubDocumentTypeForm("nothing");

            Set<ConstraintViolation<IdentificationSubDocumentTypeForm>> violations = validator.validate(sut);
            assertThat(violations.size(), is(0));

        }

    }

    public static class _エラー系のテスト {

        ValidatorFactory validatorFactory;

        Validator validator;

        IdentificationSubDocumentTypeForm sut;

        @Before
        public void setup() throws IOException, DatabaseUnitException, SQLException {
            validatorFactory = Validation.buildDefaultValidatorFactory();
            validator = validatorFactory.getValidator();
        }

        @Test
        public void _空白のみ() throws Exception {

            sut = new IdentificationSubDocumentTypeForm(" ");

            Set<ConstraintViolation<IdentificationSubDocumentTypeForm>> violations = validator.validate(sut);
            assertThat(violations.size(), is(2));

        }

        @Test
        public void _規定外() throws Exception {

            sut = new IdentificationSubDocumentTypeForm("納税証明書");

            Set<ConstraintViolation<IdentificationSubDocumentTypeForm>> violations = validator.validate(sut);
            assertThat(violations.size(), is(1));

        }
    }

}