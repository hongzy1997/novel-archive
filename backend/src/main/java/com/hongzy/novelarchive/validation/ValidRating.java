package com.hongzy.novelarchive.validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

/**
 * 読書状況と評価の整合性をチェックするカスタムバリデーション
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = RatingValidator.class)
public @interface ValidRating {

    /** バリデーションエラーメッセージ */
    String message() default "未読の場合は評価できません。未読以外の場合は評価が必須です。";

    /** バリデーショングループ */
    Class<?>[] groups() default {};

    /** バリデーションに付加情報を設定するためのPayload */
    Class<? extends Payload>[] payload() default {};
}