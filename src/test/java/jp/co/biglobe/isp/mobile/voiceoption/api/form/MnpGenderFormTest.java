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
import static org.junit.Assert.assertThat;

@RunWith(Enclosed.class)
public class MnpGenderFormTest {

    public static class _正常系のテスト {

        ValidatorFactory validatorFactory;

        Validator validator;

        MnpGenderForm sut;

        @Before
        public void setup() throws IOException, DatabaseUnitException, SQLException {
            validatorFactory = Validation.buildDefaultValidatorFactory();
            validator = validatorFactory.getValidator();
        }

        @Test
        public void _性別が男性() throws Exception {

            sut = new MnpGenderForm("male");

            Set<ConstraintViolation<MnpGenderForm>> violations = validator.validate(sut);
            assertThat(violations.size(), is(0));

        }

        @Test
        public void _性別が女性() throws Exception {

            sut = new MnpGenderForm("female");

            Set<ConstraintViolation<MnpGenderForm>> violations = validator.validate(sut);
            assertThat(violations.size(), is(0));

        }

        @Test
        public void _性別が不明() throws Exception {

            sut = new MnpGenderForm("unknown");

            Set<ConstraintViolation<MnpGenderForm>> violations = validator.validate(sut);
            assertThat(violations.size(), is(0));

        }

    }

    public static class _エラー系のテスト {

        ValidatorFactory validatorFactory;

        Validator validator;

        MnpGenderForm sut;

        @Before
        public void setup() throws IOException, DatabaseUnitException, SQLException {
            validatorFactory = Validation.buildDefaultValidatorFactory();
            validator = validatorFactory.getValidator();
        }

        @Test
        public void _nullのみ() throws Exception {

            sut = new MnpGenderForm(null);

            Set<ConstraintViolation<MnpGenderForm>> violations = validator.validate(sut);
            assertThat(violations.size(), is(1));

        }

        @Test
        public void _空文字のみ() throws Exception {

            sut = new MnpGenderForm("");

            Set<ConstraintViolation<MnpGenderForm>> violations = validator.validate(sut);
            assertThat(violations.size(), is(1));

        }


        @Test
        public void _空白のみ() throws Exception {

            sut = new MnpGenderForm(" ");

            Set<ConstraintViolation<MnpGenderForm>> violations = validator.validate(sut);
            assertThat(violations.size(), is(2));

        }

        @Test
        public void _規定外() throws Exception {

            sut = new MnpGenderForm("男性");

            Set<ConstraintViolation<MnpGenderForm>> violations = validator.validate(sut);
            assertThat(violations.size(), is(1));

        }
    }

}