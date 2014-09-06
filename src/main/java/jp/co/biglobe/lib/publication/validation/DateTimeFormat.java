package jp.co.biglobe.lib.publication.validation;

import jp.co.biglobe.lib.danger.validation.datetimeformat.DateTimeFormatValidator;

import javax.validation.Constraint;
import java.lang.annotation.*;

@Target({ElementType.FIELD}) // アノテーションを付ける場所の指定 フィールドに付与できるアノテーション
@Retention(RetentionPolicy.RUNTIME) // アノテーションの有効範囲 実行する際にもJavaVMにそのアノテーションの情報が読み込まれる
@Documented // javadocの文書化ツールで、その注釈情報が出力される。
@Constraint(validatedBy = {DateTimeFormatValidator.class}) // 実際にバリデーションを実施するクラスを指定
public @interface DateTimeFormat {

    Class<?>[] groups() default {};

    String message() default "日付時刻文字列（yyyyMMddHHmmss）を指定してください。";

    Class<? extends DateTimeFormat>[] payload() default {};
}
