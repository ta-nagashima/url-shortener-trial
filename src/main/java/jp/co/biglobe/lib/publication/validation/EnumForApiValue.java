package jp.co.biglobe.lib.publication.validation;

import jp.co.biglobe.lib.danger.validation.enumforapivalue.EnumForApiValueValidator;

import javax.validation.Constraint;
import java.lang.annotation.*;

@Target({ElementType.FIELD}) // アノテーションを付ける場所の指定 フィールドに付与できるアノテーション
@Retention(RetentionPolicy.RUNTIME) // アノテーションの有効範囲 実行する際にもJavaVMにそのアノテーションの情報が読み込まれる
@Documented // javadocの文書化ツールで、その注釈情報が出力される。
@Constraint(validatedBy = {EnumForApiValueValidator.class}) // 実際にバリデーションを実施するクラスを指定
public @interface EnumForApiValue {

    Class<? extends Enum<?>> value();

    Class<?>[] groups() default {};

    String message() default "指定された文字列以外は変換できません。EnumConvertForApiを実装したEnumの値を指定してください。";

    Class<? extends EnumForApiValue>[] payload() default {};
}
