package jp.co.biglobe.isp.mobile.voiceoption.api.form.identification;

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
public class IdentificationReceiptNumberFormTest {

    public static class _正常系のテスト {

        ValidatorFactory validatorFactory;

        Validator validator;

        IdentificationReceiptNumberForm sut;

        @Before
        public void setup() throws IOException, DatabaseUnitException, SQLException {
            validatorFactory = Validation.buildDefaultValidatorFactory();
            validator = validatorFactory.getValidator();
        }

        @Test
        public void _正常() throws Exception {

            sut = new IdentificationReceiptNumberForm("123456_1234");

            Set<ConstraintViolation<IdentificationReceiptNumberForm>> violations = validator.validate(sut);
            assertThat(violations.size(), is(0));

        }
    }

    public static class _エラー系のテスト {

        ValidatorFactory validatorFactory;

        Validator validator;

        IdentificationReceiptNumberForm sut;

        @Before
        public void setup() throws IOException, DatabaseUnitException, SQLException {
            validatorFactory = Validation.buildDefaultValidatorFactory();
            validator = validatorFactory.getValidator();
        }

        @Test
        public void _空白のみ() throws Exception {

            sut = new IdentificationReceiptNumberForm(" ");

            Set<ConstraintViolation<IdentificationReceiptNumberForm>> violations = validator.validate(sut);
            assertThat(violations.size(), is(2));

        }

        @Test
        public void _英字() throws Exception {

            sut = new IdentificationReceiptNumberForm("aaaaaa_aaaa");

            Set<ConstraintViolation<IdentificationReceiptNumberForm>> violations = validator.validate(sut);
            assertThat(violations.size(), is(1));

        }

        @Test
        public void _アンスコない() throws Exception {

            sut = new IdentificationReceiptNumberForm("1234561234");

            Set<ConstraintViolation<IdentificationReceiptNumberForm>> violations = validator.validate(sut);
            assertThat(violations.size(), is(1));

        }

        @Test
        public void _アンスコが違う文字() throws Exception {

            sut = new IdentificationReceiptNumberForm("123456-1234");

            Set<ConstraintViolation<IdentificationReceiptNumberForm>> violations = validator.validate(sut);
            assertThat(violations.size(), is(1));

        }

        @Test
        public void _前半桁が少ない() throws Exception {

            sut = new IdentificationReceiptNumberForm("12345_1234");

            Set<ConstraintViolation<IdentificationReceiptNumberForm>> violations = validator.validate(sut);
            assertThat(violations.size(), is(1));

        }

        @Test
        public void _前半桁が多い() throws Exception {

            sut = new IdentificationReceiptNumberForm("1234567_1234");

            Set<ConstraintViolation<IdentificationReceiptNumberForm>> violations = validator.validate(sut);
            assertThat(violations.size(), is(1));

        }

        @Test
        public void _後半桁が少ない() throws Exception {

            sut = new IdentificationReceiptNumberForm("123456_7");

            Set<ConstraintViolation<IdentificationReceiptNumberForm>> violations = validator.validate(sut);
            assertThat(violations.size(), is(1));

        }

        @Test
        public void _後半桁が多い() throws Exception {

            sut = new IdentificationReceiptNumberForm("123456_12345");

            Set<ConstraintViolation<IdentificationReceiptNumberForm>> violations = validator.validate(sut);
            assertThat(violations.size(), is(1));

        }
    }

}