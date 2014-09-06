package jp.co.biglobe.isp.mobile.extension.validation;

import javax.validation.Constraint;
import java.lang.annotation.*;

@Target({ElementType.FIELD}) // アノテーションを付ける場所の指定 フィールドに付与できるアノテーション
@Retention(RetentionPolicy.RUNTIME) // アノテーションの有効範囲 実行する際にもJavaVMにそのアノテーションの情報が読み込まれる
@Documented // javadocの文書化ツールで、その注釈情報が出力される。
@Constraint(validatedBy = {FuriganaValidator.class}) // 実際にバリデーションを実施するクラスを指定
public @interface Furigana {

    Class<?>[] groups() default {};

    String message() default "不正な文字列が指定されています。";

    Class<? extends Furigana>[] payload() default {};
}
