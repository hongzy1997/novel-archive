package com.hongzy.novelarchive.dto;

import com.hongzy.novelarchive.validation.ValidRating;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

/**
 * 小説登録・更新APIのリクエストDTO
 */
@Data
@ValidRating
public class NovelRequest {

    /** タイトル */
    @NotBlank(message = "タイトルは必須です。")
    @Size(max = 200, message = "タイトルは200文字以内で入力してください。")
    private String title;

    /** 作者 */
    @Size(max = 100, message = "作者は100文字以内で入力してください。")
    private String author;

    /** 読書状態 */
    @NotNull(message = "読書状態は必須です。")
    @Min(value = 0, message = "読書状態が不正です。")
    @Max(value = 3, message = "読書状態が不正です。")
    private Integer readingStatus;

    /** 評価 */
    @Min(value = 1, message = "評価は1～10で入力してください。")
    @Max(value = 10, message = "評価は1～10で入力してください。")
    private Integer rating;

    /** メモ */
    @Size(max = 1000, message = "メモは1000文字以内で入力してください。")
    private String memo;
}