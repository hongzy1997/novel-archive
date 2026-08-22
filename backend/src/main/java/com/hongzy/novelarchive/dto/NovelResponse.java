package com.hongzy.novelarchive.dto;

import lombok.Data;

/**
 * 小説情報APIのレスポンスDTO
 */
@Data
public class NovelResponse {

    /** 小説ID */
    private Long novelId;

    /** タイトル */
    private String title;

    /** 作者 */
    private String author;

    /** 読書状況 */
    private Integer readingStatus;

    /** 評価 */
    private Integer rating;

    /** メモ */
    private String memo;
}