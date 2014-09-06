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
public class MnpReservationNumberFormTest {

    public static class _正常系のテスト {

        ValidatorFactory validatorFactory;

        Validator validator;

        MnpReservationNumberForm sut;

        @Before
        public void setup() throws IOException, DatabaseUnitException, SQLException {
            validatorFactory = Validation.buildDefaultValidatorFactory();
            validator = validatorFactory.getValidator();
        }

        @Test
        public void _完全にパターン一致() throws Exception {

            sut = new MnpReservationNumberForm("01-234-56789");

            Set<ConstraintViolation<MnpReservationNumberForm>> violations = validator.validate(sut);
            assertThat(violations.size(), is(0));

        }
    }

    public static class _エラー系のテスト {

        ValidatorFactory validatorFactory;

        Validator validator;

        EquipmentNumberForm sut;

        @Before
        public void setup() throws IOException, DatabaseUnitException, SQLException {
            validatorFactory = Validation.buildDefaultValidatorFactory();
            validator = validatorFactory.getValidator();
        }

        @Test
        public void _空白のみ() throws Exception {

            sut = new EquipmentNumberForm(" ");

            Set<ConstraintViolation<EquipmentNumberForm>> violations = validator.validate(sut);
            assertThat(violations.size(), is(2));

        }


        @Test
        public void _パターン不一致_ハイフンなし() throws Exception {

            sut = new EquipmentNumberForm("0123456789");

            Set<ConstraintViolation<EquipmentNumberForm>> violations = validator.validate(sut);
            assertThat(violations.size(), is(1));

        }

        @Test
        public void _パターン不一致_英字が存在() throws Exception {

            sut = new EquipmentNumberForm("01-234-56a89");

            Set<ConstraintViolation<EquipmentNumberForm>> violations = validator.validate(sut);
            assertThat(violations.size(), is(1));

        }

    }

}
