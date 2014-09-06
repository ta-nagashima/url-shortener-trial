package jp.co.biglobe.isp.mobile.voiceoption.api.form.identificationresult.ngreasons;

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
public class NgReasonDetailFormTest {

    public static class _正常系のテスト {

        ValidatorFactory validatorFactory;

        Validator validator;

        NgReasonDetailForm sut;

        @Before
        public void setup() throws IOException, DatabaseUnitException, SQLException {
            validatorFactory = Validation.buildDefaultValidatorFactory();
            validator = validatorFactory.getValidator();
        }

        @Test
        public void _256文字以下() throws Exception {

            sut = new NgReasonDetailForm("qqwertyuiopqwertyuiqwertyuioqwertyuiqwertyuiqwertyuiqaswedrftgyhujikoqawsedrftgyhqwertyuiopqwertyuiqwertyuioqwertyuiqwertyuiqwertyuiqaswedrftgyhujikoqawsedrftgyhqwertyuiopqwertyuiqwertyuioqwertyuiqwertyuiqwertyuiqaswedrftgyhujikoqawsedrftgyhqwertyuiopqwert");

            Set<ConstraintViolation<NgReasonDetailForm>> violations = validator.validate(sut);
            assertThat(violations.size(), is(0));

        }

        @Test
        public void _空文字() throws Exception {

            sut = new NgReasonDetailForm("");

            Set<ConstraintViolation<NgReasonDetailForm>> violations = validator.validate(sut);
            assertThat(violations.size(), is(0));

        }

    }

    public static class _エラー系のテスト {

        ValidatorFactory validatorFactory;

        Validator validator;

        NgReasonDetailForm sut;

        @Before
        public void setup() throws IOException, DatabaseUnitException, SQLException {
            validatorFactory = Validation.buildDefaultValidatorFactory();
            validator = validatorFactory.getValidator();
        }

        @Test
        public void _257文字() throws Exception {

            sut = new NgReasonDetailForm("qqwertyuiopqwertyuiqwertyuioqwertyuiqwertyuiqwertyuiqaswedrftgyhujikoqawsedrftgyhqwertyuiopqwertyuiqwertyuioqwertyuiqwertyuiqwertyuiqaswedrftgyhujikoqawsedrftgyhqwertyuiopqwertyuiqwertyuioqwertyuiqwertyuiqwertyuiqaswedrftgyhujikoqawsedrftgyhqwertyuiopqwerta");

            Set<ConstraintViolation<NgReasonDetailForm>> violations = validator.validate(sut);
            assertThat(violations.size(), is(1));

        }

    }




}