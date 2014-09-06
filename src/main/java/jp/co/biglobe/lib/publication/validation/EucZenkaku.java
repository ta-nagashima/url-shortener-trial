package jp.co.biglobe.lib.publication.validation;

import jp.co.biglobe.lib.danger.validation.euczenkaku.EucZenkakuValidator;

import javax.validation.Constraint;
import java.lang.annotation.*;

@Target({ElementType.FIELD}) // アノテーションを付ける場所の指定 フィールドに付与できるアノテーション
@Retention(RetentionPolicy.RUNTIME) // アノテーションの有効範囲 実行する際にもJavaVMにそのアノテーションの情報が読み込まれる
@Documented // javadocの文書化ツールで、その注釈情報が出力される。
@Constraint(validatedBy = {EucZenkakuValidator.class}) // 実際にバリデーションを実施するクラスを指定
public @interface EucZenkaku {

    Class<?>[] groups() default {};

    String message() default "半角文字が含まれているか、EUCに含まれない、不正な文字列が指定されています。";

    Class<? extends EucZenkaku>[] payload() default {};
}
