package jp.co.biglobe.isp.mobile.voiceoption.api.form.identificationresult.ngreasons;

import org.dbunit.DatabaseUnitException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.experimental.runners.Enclosed;

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
public class NgReasonFormTest {

    public static class _正常系のテスト {

        ValidatorFactory validatorFactory;

        Validator validator;

        NgReasonForm sut;

        @Before
        public void setup() throws IOException, DatabaseUnitException, SQLException {
            validatorFactory = Validation.buildDefaultValidatorFactory();
            validator = validatorFactory.getValidator();
        }

        @Test
        public void _incomplete_document() throws Exception {

            sut = new NgReasonForm("incomplete_document");

            Set<ConstraintViolation<NgReasonForm>> violations = validator.validate(sut);
            assertThat(violations.size(), is(0));

        }

        @Test
        public void _inconsistent_information() throws Exception {

            sut = new NgReasonForm("inconsistent_information");

            Set<ConstraintViolation<NgReasonForm>> violations = validator.validate(sut);
            assertThat(violations.size(), is(0));

        }

        @Test
        public void _inconsistent_mnp_information() throws Exception {

            sut = new NgReasonForm("inconsistent_mnp_information");

            Set<ConstraintViolation<NgReasonForm>> violations = validator.validate(sut);
            assertThat(violations.size(), is(0));

        }

        @Test
        public void _incomplete_application_document() throws Exception {

            sut = new NgReasonForm("incomplete_application_document");

            Set<ConstraintViolation<NgReasonForm>> violations = validator.validate(sut);
            assertThat(violations.size(), is(0));

        }

        @Test
        public void _etc() throws Exception {

            sut = new NgReasonForm("etc");

            Set<ConstraintViolation<NgReasonForm>> violations = validator.validate(sut);
            assertThat(violations.size(), is(0));

        }

        @Test
        public void _空文字() throws Exception {

            sut = new NgReasonForm("");

            Set<ConstraintViolation<NgReasonForm>> violations = validator.validate(sut);
            assertThat(violations.size(), is(0));

        }

    }

    public static class _エラー系のテスト {

        ValidatorFactory validatorFactory;

        Validator validator;

        NgReasonForm sut;

        @Before
        public void setup() throws IOException, DatabaseUnitException, SQLException {
            validatorFactory = Validation.buildDefaultValidatorFactory();
            validator = validatorFactory.getValidator();
        }

        @Test
        public void _空白のみ() throws Exception {

            sut = new NgReasonForm(" ");

            Set<ConstraintViolation<NgReasonForm>> violations = validator.validate(sut);
            assertThat(violations.size(), is(1));

        }


        @Test
        public void _enumのApiValueではない() throws Exception {

            sut = new NgReasonForm("hogehoge");

            Set<ConstraintViolation<NgReasonForm>> violations = validator.validate(sut);
            assertThat(violations.size(), is(1));

        }
    }
}
