package jp.co.biglobe.isp.mobile.voiceoption.api.form;

import org.dbunit.DatabaseUnitException;
import org.joda.time.DateTime;
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
import java.util.Date;
import java.util.Set;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

@RunWith(Enclosed.class)
public class VoiceEngagementEndDateTimeFormTest {

    public static class _正常系のテスト {

        ValidatorFactory validatorFactory;

        Validator validator;

        VoiceEngagementEndDateTimeForm sut;

        @Before
        public void setup() throws IOException, DatabaseUnitException, SQLException {
            validatorFactory = Validation.buildDefaultValidatorFactory();
            validator = validatorFactory.getValidator();
        }

        @Test
        public void _数字のみ14桁_AM() throws Exception {

            sut = new VoiceEngagementEndDateTimeForm("20140401015959");

            Set<ConstraintViolation<VoiceEngagementEndDateTimeForm>> violations = validator.validate(sut);
            assertThat(violations.size(), is(0));


            Date date = sut.getValueObject().getValue();
            DateTime dt = new DateTime(date);

            assertThat(dt.toString("yyyyMMddHHmmss"), is("20140401015959"));
        }

        @Test
        public void _数字のみ14桁_PM() throws Exception {

            sut = new VoiceEngagementEndDateTimeForm("20140401145959");

            Set<ConstraintViolation<VoiceEngagementEndDateTimeForm>> violations = validator.validate(sut);
            assertThat(violations.size(), is(0));

            Date date = sut.getValueObject().getValue();
            DateTime dt = new DateTime(date);

            assertThat(dt.toString("yyyyMMddHHmmss"), is("20140401145959"));

        }
    }

    public static class _エラー系のテスト {

        ValidatorFactory validatorFactory;

        Validator validator;

        VoiceEngagementEndDateTimeForm sut;

        @Before
        public void setup() throws IOException, DatabaseUnitException, SQLException {
            validatorFactory = Validation.buildDefaultValidatorFactory();
            validator = validatorFactory.getValidator();
        }

        @Test
        public void _空文字() throws Exception {

            sut = new VoiceEngagementEndDateTimeForm("");

            Set<ConstraintViolation<VoiceEngagementEndDateTimeForm>> violations = validator.validate(sut);
            assertThat(violations.size(), is(1));
        }

        @Test
        public void _null() throws Exception {

            sut = new VoiceEngagementEndDateTimeForm(null);

            Set<ConstraintViolation<VoiceEngagementEndDateTimeForm>> violations = validator.validate(sut);
            assertThat(violations.size(), is(1));
        }

        @Test
        public void _空白のみ() throws Exception {

            sut = new VoiceEngagementEndDateTimeForm(" ");

            Set<ConstraintViolation<VoiceEngagementEndDateTimeForm>> violations = validator.validate(sut);
            assertThat(violations.size(), is(2));

        }

        @Test
        public void _英字() throws Exception {

            sut = new VoiceEngagementEndDateTimeForm("aaaaaaaa");

            Set<ConstraintViolation<VoiceEngagementEndDateTimeForm>> violations = validator.validate(sut);
            assertThat(violations.size(), is(1));

        }

        @Test
        public void _数字のみで13桁() throws Exception {

            sut = new VoiceEngagementEndDateTimeForm("2014040114595");

            Set<ConstraintViolation<VoiceEngagementEndDateTimeForm>> violations = validator.validate(sut);
            assertThat(violations.size(), is(1));

        }

        @Test
        public void _数字のみで15桁() throws Exception {

            sut = new VoiceEngagementEndDateTimeForm("201404011459599");

            Set<ConstraintViolation<VoiceEngagementEndDateTimeForm>> violations = validator.validate(sut);
            assertThat(violations.size(), is(1));

        }

        @Test
        public void 時刻に変換できない文字列() throws Exception {

            sut = new VoiceEngagementEndDateTimeForm("20140401285959");

            Set<ConstraintViolation<VoiceEngagementEndDateTimeForm>> violations = validator.validate(sut);
            assertThat(violations.size(), is(1));

        }
    }
}