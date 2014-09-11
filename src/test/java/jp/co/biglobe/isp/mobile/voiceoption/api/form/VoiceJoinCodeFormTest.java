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
public class VoiceJoinCodeFormTest {

    public static class _正常系のテスト {

        ValidatorFactory validatorFactory;

        Validator validator;

        VoiceJoinCodeForm sut;

        @Before
        public void setup() throws IOException, DatabaseUnitException, SQLException {
            validatorFactory = Validation.buildDefaultValidatorFactory();
            validator = validatorFactory.getValidator();
        }

        @Test
        public void _1桁() throws Exception {

            sut = new VoiceJoinCodeForm("1");

            Set<ConstraintViolation<VoiceJoinCodeForm>> violations = validator.validate(sut);
            assertThat(violations.size(), is(0));

        }

        @Test
        public void _256桁() throws Exception {

            sut = new VoiceJoinCodeForm("1234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456");

            Set<ConstraintViolation<VoiceJoinCodeForm>> violations = validator.validate(sut);
            assertThat(violations.size(), is(0));

        }

        @Test
        public void _大文字_小文字_数字() throws Exception {

            sut = new VoiceJoinCodeForm("Aa1");

            Set<ConstraintViolation<VoiceJoinCodeForm>> violations = validator.validate(sut);
            assertThat(violations.size(), is(0));

        }
    }

    public static class _エラー系のテスト {

        ValidatorFactory validatorFactory;

        Validator validator;

        VoiceJoinCodeForm sut;

        @Before
        public void setup() throws IOException, DatabaseUnitException, SQLException {
            validatorFactory = Validation.buildDefaultValidatorFactory();
            validator = validatorFactory.getValidator();
        }

        @Test
        public void _未設定() throws Exception {

            sut = new VoiceJoinCodeForm("");

            Set<ConstraintViolation<VoiceJoinCodeForm>> violations = validator.validate(sut);
            assertThat(violations.size(), is(3));

        }

        @Test
        public void _空白のみ() throws Exception {

            sut = new VoiceJoinCodeForm(" ");

            Set<ConstraintViolation<VoiceJoinCodeForm>> violations = validator.validate(sut);
            assertThat(violations.size(), is(2));

        }

        @Test
        public void _257桁() throws Exception {

            sut = new VoiceJoinCodeForm("12345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567");

            Set<ConstraintViolation<VoiceJoinCodeForm>> violations = validator.validate(sut);
            assertThat(violations.size(), is(1));

        }

        @Test
        public void _ひらがな() throws Exception {

            sut = new VoiceJoinCodeForm("ああああ");

            Set<ConstraintViolation<VoiceJoinCodeForm>> violations = validator.validate(sut);
            assertThat(violations.size(), is(1));

        }

    }

}
