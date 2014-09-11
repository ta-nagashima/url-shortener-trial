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
public class ReceiptStatusFormTest {

    public static class _正常系のテスト {

        ValidatorFactory validatorFactory;

        Validator validator;

        ReceiptStatusForm sut;

        @Before
        public void setup() throws IOException, DatabaseUnitException, SQLException {
            validatorFactory = Validation.buildDefaultValidatorFactory();
            validator = validatorFactory.getValidator();
        }

        @Test
        public void _設定あり() throws Exception {

            sut = new ReceiptStatusForm("constancy");

            Set<ConstraintViolation<ReceiptStatusForm>> violations = validator.validate(sut);
            assertThat(violations.size(), is(0));

        }
    }

    public static class _エラー系のテスト {

        ValidatorFactory validatorFactory;

        Validator validator;

        ReceiptStatusForm sut;

        @Before
        public void setup() throws IOException, DatabaseUnitException, SQLException {
            validatorFactory = Validation.buildDefaultValidatorFactory();
            validator = validatorFactory.getValidator();
        }

        @Test
        public void _空白のみ() throws Exception {

            sut = new ReceiptStatusForm(" ");

            Set<ConstraintViolation<ReceiptStatusForm>> violations = validator.validate(sut);
            assertThat(violations.size(), is(2));

        }

        @Test
        public void _ひらがなが入ってる() throws Exception {

            sut = new ReceiptStatusForm("あ");

            Set<ConstraintViolation<ReceiptStatusForm>> violations = validator.validate(sut);
            assertThat(violations.size(), is(1));

        }

    }

}
