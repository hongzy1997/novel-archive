package com.hongzy.novelarchive.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Data;

/**
 * 小説情報取得APIのレスポンスDTO
 */
@Data
@JsonPropertyOrder({
        "novelId",
        "title",
        "author",
        "readingStatus",
        "rating",
        "memo"
})
public class NovelResponse {

    /** 小説ID */
    private Long novelId;

    /** タイトル */
    private String title;

    /** 著者 */
    private String author;

    /** 読書状況 */
    private Integer readingStatus;

    /** 評価 */
    private Integer rating;

    /** メモ */
    private String memo;

}