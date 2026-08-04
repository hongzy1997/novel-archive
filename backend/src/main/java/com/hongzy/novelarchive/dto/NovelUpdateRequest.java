package com.hongzy.novelarchive.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * 小説更新APIのリクエストDTO
 */
@Data
public class NovelUpdateRequest {

    /** タイトル */
    @NotBlank(message = "タイトルは必須です。")
    private String title;

    /** 著者 */
    @NotBlank(message = "著者は必須です。")
    private String author;

    /** 読書状況 */
    @Min(value = 0, message = "読書状況が不正です。")
    @Max(value = 3, message = "読書状況が不正です。")
    private Integer readingStatus;

    /** 評価 */
    @Min(value = 1, message = "評価は1～10で入力してください。")
    @Max(value = 10, message = "評価は1～10で入力してください。")
    private Integer rating;

    /** メモ */
    private String memo;
}