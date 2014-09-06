package jp.co.biglobe.lib.publication.validation;

import jp.co.biglobe.lib.danger.validation.dateformat.DateFormatValidator;

import javax.validation.Constraint;
import java.lang.annotation.*;

@Target({ElementType.FIELD}) // アノテーションを付ける場所の指定 フィールドに付与できるアノテーション
@Retention(RetentionPolicy.RUNTIME) // アノテーションの有効範囲 実行する際にもJavaVMにそのアノテーションの情報が読み込まれる
@Documented // javadocの文書化ツールで、その注釈情報が出力される。
@Constraint(validatedBy = {DateFormatValidator.class}) // 実際にバリデーションを実施するクラスを指定
public @interface DateFormat {

    Class<?>[] groups() default {};

    String message() default "日付文字列（yyyyMMdd）を指定してください。";

    Class<? extends DateFormat>[] payload() default {};
}
