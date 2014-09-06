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
public class VoiceEngagementCancelReasonFormTest {

    public static class _正常系のテスト {

        ValidatorFactory validatorFactory;

        Validator validator;

        VoiceEngagementCancelReasonForm sut;

        @Before
        public void setup() throws IOException, DatabaseUnitException, SQLException {
            validatorFactory = Validation.buildDefaultValidatorFactory();
            validator = validatorFactory.getValidator();
        }

        @Test
        public void _キャンセル理由_ユーザ要求() throws Exception {

            sut = new VoiceEngagementCancelReasonForm("user_request");

            Set<ConstraintViolation<VoiceEngagementCancelReasonForm>> violations = validator.validate(sut);
            assertThat(violations.size(), is(0));

        }

        @Test
        public void _キャンセル理由_配送拒否() throws Exception {

            sut = new VoiceEngagementCancelReasonForm("delivery_receipt_refusal");

            Set<ConstraintViolation<VoiceEngagementCancelReasonForm>> violations = validator.validate(sut);
            assertThat(violations.size(), is(0));

        }

        @Test
        public void _キャンセル理由_登録保留() throws Exception {

            sut = new VoiceEngagementCancelReasonForm("withdraw_or_de_register");

            Set<ConstraintViolation<VoiceEngagementCancelReasonForm>> violations = validator.validate(sut);
            assertThat(violations.size(), is(0));

        }

        @Test
        public void _キャンセル理由_コース変更() throws Exception {

            sut = new VoiceEngagementCancelReasonForm("course_change");

            Set<ConstraintViolation<VoiceEngagementCancelReasonForm>> violations = validator.validate(sut);
            assertThat(violations.size(), is(0));

        }

        @Test
        public void _キャンセル理由_サービス停止() throws Exception {

            sut = new VoiceEngagementCancelReasonForm("adjourn_or_service_stop");

            Set<ConstraintViolation<VoiceEngagementCancelReasonForm>> violations = validator.validate(sut);
            assertThat(violations.size(), is(0));

        }

    }

    public static class _エラー系のテスト {

        ValidatorFactory validatorFactory;

        Validator validator;

        VoiceEngagementCancelReasonForm sut;

        @Before
        public void setup() throws IOException, DatabaseUnitException, SQLException {
            validatorFactory = Validation.buildDefaultValidatorFactory();
            validator = validatorFactory.getValidator();
        }

        @Test
        public void _空白のみ() throws Exception {

            sut = new VoiceEngagementCancelReasonForm(" ");

            Set<ConstraintViolation<VoiceEngagementCancelReasonForm>> violations = validator.validate(sut);
            assertThat(violations.size(), is(2));

        }

        @Test
        public void _規格外() throws Exception {

            sut = new VoiceEngagementCancelReasonForm("キャンセル理由");

            Set<ConstraintViolation<VoiceEngagementCancelReasonForm>> violations = validator.validate(sut);
            assertThat(violations.size(), is(1));

        }
    }

}
