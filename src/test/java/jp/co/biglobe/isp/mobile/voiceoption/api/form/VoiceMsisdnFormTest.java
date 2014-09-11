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
public class VoiceMsisdnFormTest {

    public static class _正常系のテスト {

        ValidatorFactory validatorFactory;

        Validator validator;

        VoiceMsisdnForm sut;

        @Before
        public void setup() throws IOException, DatabaseUnitException, SQLException {
            validatorFactory = Validation.buildDefaultValidatorFactory();
            validator = validatorFactory.getValidator();
        }

        @Test
        public void _完全にパターン一致() throws Exception {

            sut = new VoiceMsisdnForm("090-1234-5678");

            Set<ConstraintViolation<VoiceMsisdnForm>> violations = validator.validate(sut);
            assertThat(violations.size(), is(0));

        }
    }

    public static class _エラー系のテスト {

        ValidatorFactory validatorFactory;

        Validator validator;

        VoiceMsisdnForm sut;

        @Before
        public void setup() throws IOException, DatabaseUnitException, SQLException {
            validatorFactory = Validation.buildDefaultValidatorFactory();
            validator = validatorFactory.getValidator();
        }

        @Test
        public void _空白のみ() throws Exception {

            sut = new VoiceMsisdnForm(" ");

            Set<ConstraintViolation<VoiceMsisdnForm>> violations = validator.validate(sut);
            assertThat(violations.size(), is(2));

        }


        @Test
        public void _パターン不一致_ハイフンなし() throws Exception {

            sut = new VoiceMsisdnForm("09012345678");

            Set<ConstraintViolation<VoiceMsisdnForm>> violations = validator.validate(sut);
            assertThat(violations.size(), is(1));

        }

        @Test
        public void _パターン不一致_英字が存在() throws Exception {

            sut = new VoiceMsisdnForm("090-1234-5a78");

            Set<ConstraintViolation<VoiceMsisdnForm>> violations = validator.validate(sut);
            assertThat(violations.size(), is(1));

        }

    }

}
