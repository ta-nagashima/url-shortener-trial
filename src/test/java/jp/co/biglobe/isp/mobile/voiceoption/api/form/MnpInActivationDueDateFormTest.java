package jp.co.biglobe.isp.mobile.voiceoption.api.form;

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
import static org.junit.Assert.*;

@RunWith(Enclosed.class)
public class MnpInActivationDueDateFormTest {

    public static class _正常系のテスト {

        ValidatorFactory validatorFactory;

        Validator validator;

        MnpInActivationDueDateForm sut;

        @Before
        public void setup() throws IOException, DatabaseUnitException, SQLException {
            validatorFactory = Validation.buildDefaultValidatorFactory();
            validator = validatorFactory.getValidator();
        }

        @Test
        public void _数字のみ8桁() throws Exception {

            sut = new MnpInActivationDueDateForm("20140601");

            Set<ConstraintViolation<MnpInActivationDueDateForm>> violations = validator.validate(sut);
            assertThat(violations.size(), is(0));

        }

        @Test
        public void _空文字() throws Exception {

            sut = new MnpInActivationDueDateForm("");

            Set<ConstraintViolation<MnpInActivationDueDateForm>> violations = validator.validate(sut);
            assertThat(violations.size(), is(0));

        }

    }

    public static class _エラー系のテスト {

        ValidatorFactory validatorFactory;

        Validator validator;

        MnpInActivationDueDateForm sut;

        @Before
        public void setup() throws IOException, DatabaseUnitException, SQLException {
            validatorFactory = Validation.buildDefaultValidatorFactory();
            validator = validatorFactory.getValidator();
        }

        @Test
        public void _空白のみ() throws Exception {

            sut = new MnpInActivationDueDateForm(" ");

            Set<ConstraintViolation<MnpInActivationDueDateForm>> violations = validator.validate(sut);
            assertThat(violations.size(), is(1));

        }


        @Test
        public void _英字() throws Exception {

            sut = new MnpInActivationDueDateForm("aaaaaaaa");

            Set<ConstraintViolation<MnpInActivationDueDateForm>> violations = validator.validate(sut);
            assertThat(violations.size(), is(1));

        }

        @Test
        public void _数字のみで7桁() throws Exception {

            sut = new MnpInActivationDueDateForm("2014060");

            Set<ConstraintViolation<MnpInActivationDueDateForm>> violations = validator.validate(sut);
            assertThat(violations.size(), is(1));

        }

        @Test
        public void _数字のみで9桁() throws Exception {

            sut = new MnpInActivationDueDateForm("201406011");

            Set<ConstraintViolation<MnpInActivationDueDateForm>> violations = validator.validate(sut);
            assertThat(violations.size(), is(1));

        }

        @Test
        public void _日付として不正な数値文字列() throws Exception {

            sut = new MnpInActivationDueDateForm("20140631");

            Set<ConstraintViolation<MnpInActivationDueDateForm>> violations = validator.validate(sut);
            assertThat(violations.size(), is(1));

        }
    }
}