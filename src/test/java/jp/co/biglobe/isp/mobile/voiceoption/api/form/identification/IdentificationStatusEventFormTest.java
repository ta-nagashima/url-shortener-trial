package jp.co.biglobe.isp.mobile.voiceoption.api.form.identification;

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
public class IdentificationStatusEventFormTest {

    public static class _正常系のテスト {

        ValidatorFactory validatorFactory;

        Validator validator;

        IdentificationStatusEventForm sut;

        @Before
        public void setup() throws IOException, DatabaseUnitException, SQLException {
            validatorFactory = Validation.buildDefaultValidatorFactory();
            validator = validatorFactory.getValidator();
        }

        @Test
        public void _本人確認状態がOK() throws Exception {

            sut = new IdentificationStatusEventForm("ok");

            Set<ConstraintViolation<IdentificationStatusEventForm>> violations = validator.validate(sut);
            assertThat(violations.size(), is(0));

        }

        @Test
        public void _本人確認状態がNG() throws Exception {

            sut = new IdentificationStatusEventForm("ng");

            Set<ConstraintViolation<IdentificationStatusEventForm>> violations = validator.validate(sut);
            assertThat(violations.size(), is(0));

        }

    }

    public static class _エラー系のテスト {

        ValidatorFactory validatorFactory;

        Validator validator;

        IdentificationStatusEventForm sut;

        @Before
        public void setup() throws IOException, DatabaseUnitException, SQLException {
            validatorFactory = Validation.buildDefaultValidatorFactory();
            validator = validatorFactory.getValidator();
        }

        /**
         * okとng以外はすべてバリデーションエラー
         * @throws Exception
         */

        @Test
        public void _本人確認状態が受付中() throws Exception {

            sut = new IdentificationStatusEventForm("order");

            Set<ConstraintViolation<IdentificationStatusEventForm>> violations = validator.validate(sut);
            assertThat(violations.size(), is(1));

        }

        @Test
        public void _本人確認状態が確認待ち() throws Exception {

            sut = new IdentificationStatusEventForm("receive_document");

            Set<ConstraintViolation<IdentificationStatusEventForm>> violations = validator.validate(sut);
            assertThat(violations.size(), is(1));

        }

        @Test
        public void _空白のみ() throws Exception {

            sut = new IdentificationStatusEventForm(" ");

            Set<ConstraintViolation<IdentificationStatusEventForm>> violations = validator.validate(sut);
            assertThat(violations.size(), is(3));

        }

        @Test
        public void _規定外() throws Exception {

            sut = new IdentificationStatusEventForm("HOLD");

            Set<ConstraintViolation<IdentificationStatusEventForm>> violations = validator.validate(sut);
            assertThat(violations.size(), is(2));

        }
    }

}