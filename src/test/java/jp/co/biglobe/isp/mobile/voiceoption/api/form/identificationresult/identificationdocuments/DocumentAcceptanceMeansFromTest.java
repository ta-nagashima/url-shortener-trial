package jp.co.biglobe.isp.mobile.voiceoption.api.form.identificationresult.identificationdocuments;

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
public class DocumentAcceptanceMeansFromTest {

    public static class _正常系のテスト {

        ValidatorFactory validatorFactory;

        Validator validator;

        IdentificationDocumentAcceptanceMeansForm sut;

        @Before
        public void setup() throws IOException, DatabaseUnitException, SQLException {
            validatorFactory = Validation.buildDefaultValidatorFactory();
            validator = validatorFactory.getValidator();
        }

        @Test
        public void _本人確認受付経路がFAX() throws Exception {

            sut = new IdentificationDocumentAcceptanceMeansForm("fax");

            Set<ConstraintViolation<IdentificationDocumentAcceptanceMeansForm>> violations = validator.validate(sut);
            assertThat(violations.size(), is(0));

        }

        @Test
        public void _本人確認受付経路がWEB() throws Exception {

            sut = new IdentificationDocumentAcceptanceMeansForm("web");

            Set<ConstraintViolation<IdentificationDocumentAcceptanceMeansForm>> violations = validator.validate(sut);
            assertThat(violations.size(), is(0));

        }

        @Test
        public void _本人確認受付経路がPOST() throws Exception {

            sut = new IdentificationDocumentAcceptanceMeansForm("post");

            Set<ConstraintViolation<IdentificationDocumentAcceptanceMeansForm>> violations = validator.validate(sut);
            assertThat(violations.size(), is(0));

        }

    }

    public static class _エラー系のテスト {

        ValidatorFactory validatorFactory;

        Validator validator;

        IdentificationDocumentAcceptanceMeansForm sut;

        @Before
        public void setup() throws IOException, DatabaseUnitException, SQLException {
            validatorFactory = Validation.buildDefaultValidatorFactory();
            validator = validatorFactory.getValidator();
        }

        @Test
        public void _空白のみ() throws Exception {

            sut = new IdentificationDocumentAcceptanceMeansForm(" ");

            Set<ConstraintViolation<IdentificationDocumentAcceptanceMeansForm>> violations = validator.validate(sut);
            assertThat(violations.size(), is(2));

        }

        @Test
        public void _規定外() throws Exception {

            sut = new IdentificationDocumentAcceptanceMeansForm("郵送");

            Set<ConstraintViolation<IdentificationDocumentAcceptanceMeansForm>> violations = validator.validate(sut);
            assertThat(violations.size(), is(1));

        }
    }

}