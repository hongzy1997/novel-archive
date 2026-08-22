package com.hongzy.novelarchive.validation;

import com.hongzy.novelarchive.dto.NovelRequest;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

/**
 * 読書状況と評価の整合性をチェックするValidator
 */
public class RatingValidator
        implements ConstraintValidator<ValidRating, NovelRequest> {

    /** ValidRatingで定義されたエラーメッセージ */
    private String message;

    /**
     * ValidRatingの設定値を初期化
     */
    @Override
    public void initialize(ValidRating constraintAnnotation) {
        this.message = constraintAnnotation.message();
    }

    /**
     * 読書状況と評価の組み合わせをチェック
     *
     * 未読の場合 ：評価なし
     * 未読以外の場合 ：評価あり
     */
    @Override
    public boolean isValid(
            NovelRequest request,
            ConstraintValidatorContext context) {

        // readingStatus自体の必須チェックは@NotNullに任せる
        if (request == null || request.getReadingStatus() == null) {
            return true;
        }

        Integer readingStatus = request.getReadingStatus();
        Integer rating = request.getRating();

        boolean valid = readingStatus == 0
                ? rating == null
                : rating != null;

        if (!valid) {
            // クラス単位ではなく、ratingフィールドのエラーとして設定
            context.disableDefaultConstraintViolation();

            context.buildConstraintViolationWithTemplate(message)
                    .addPropertyNode("rating")
                    .addConstraintViolation();
        }

        return valid;
    }
}