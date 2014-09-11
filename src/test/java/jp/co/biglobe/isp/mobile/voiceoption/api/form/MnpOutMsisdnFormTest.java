package jp.co.biglobe.isp.mobile.voiceoption.api.form;

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
public class MnpOutMsisdnFormTest {

    public static class _正常系のテスト {

        ValidatorFactory validatorFactory;

        Validator validator;

        MnpOutMsisdnForm sut;

        @Before
        public void setup() throws IOException, DatabaseUnitException, SQLException {
            validatorFactory = Validation.buildDefaultValidatorFactory();
            validator = validatorFactory.getValidator();
        }

        @Test
        public void _数字のみ8桁() throws Exception {

            sut = new MnpOutMsisdnForm("123-1234-1234");

            Set<ConstraintViolation<MnpOutMsisdnForm>> violations = validator.validate(sut);
            assertThat(violations.size(), is(0));

        }
    }

    public static class _エラー系のテスト {

        ValidatorFactory validatorFactory;

        Validator validator;

        MnpOutMsisdnForm sut;

        @Before
        public void setup() throws IOException, DatabaseUnitException, SQLException {
            validatorFactory = Validation.buildDefaultValidatorFactory();
            validator = validatorFactory.getValidator();
        }

        @Test
        public void _空白のみ() throws Exception {

            sut = new MnpOutMsisdnForm(" ");

            Set<ConstraintViolation<MnpOutMsisdnForm>> violations = validator.validate(sut);
            assertThat(violations.size(), is(2));

        }

        @Test
        public void _英字() throws Exception {

            sut = new MnpOutMsisdnForm("aaa-aaaa-aaaa");

            Set<ConstraintViolation<MnpOutMsisdnForm>> violations = validator.validate(sut);
            assertThat(violations.size(), is(1));

        }

        @Test
        public void _数字のみで10桁() throws Exception {

            sut = new MnpOutMsisdnForm("123-1234-123");

            Set<ConstraintViolation<MnpOutMsisdnForm>> violations = validator.validate(sut);
            assertThat(violations.size(), is(1));

        }

        @Test
        public void _数字のみで12桁() throws Exception {

            sut = new MnpOutMsisdnForm("123-1234-12345");

            Set<ConstraintViolation<MnpOutMsisdnForm>> violations = validator.validate(sut);
            assertThat(violations.size(), is(1));

        }
    }

}