package jp.co.biglobe.lib.publication.validation;

import jp.co.biglobe.lib.danger.validation.valueobjectnotempty.ValueObjectNotEmptyValidator;

import javax.validation.Constraint;
import java.lang.annotation.*;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = {ValueObjectNotEmptyValidator.class})
/**
 * ValueObjectのvalueフィールドに対する必須チェックを行うアノテーション。
 * ValueObjectを保持するクラス（INパラをまとめるクラス）のフィールド値にアノテーションを付与する。
 * Stringクラスは空文字とnull、それ以外のクラスはnullをfalseと判定する。
 *
 * プリミティブ型の場合は、デフォルト値が自動設定されるので、そもそも必須チェックはできない。
 * そのため、ValueObjectのvalueフィールドがプリミティブ型の場合は例外とする。
 */
public @interface ValueObjectNotEmpty {
            
    Class<?>[] groups() default {};

    String message() default "必須項目です";

    Class<? extends ValueObjectNotEmpty>[] payload() default {};
}
