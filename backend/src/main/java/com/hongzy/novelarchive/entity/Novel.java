package com.hongzy.novelarchive.entity;

import java.time.LocalDateTime;

import org.seasar.doma.Column;
import org.seasar.doma.Entity;
import org.seasar.doma.GeneratedValue;
import org.seasar.doma.GenerationType;
import org.seasar.doma.Id;
import org.seasar.doma.SequenceGenerator;
import org.seasar.doma.Table;

import lombok.Getter;
import lombok.Setter;

/**
 * 小説情報Entity
 */
@Entity
@Table(name = "NOVELS")
@Getter
@Setter
public class Novel {

    /** 小説ID */
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(sequence = "NOVEL_SEQ")
    @Column(name = "NOVEL_ID")
    private Long novelId;

    /** タイトル */
    @Column(name = "TITLE")
    private String title;

    /** 作者 */
    @Column(name = "AUTHOR")
    private String author;

    /** 読書状況 */
    @Column(name = "READING_STATUS")
    private Integer readingStatus;

    /** 評価 */
    @Column(name = "RATING")
    private Integer rating;

    /** メモ */
    @Column(name = "MEMO")
    private String memo;

    /** 登録日時 */
    @Column(name = "CREATED_AT")
    private LocalDateTime createdAt;

    /** 更新日時 */
    @Column(name = "UPDATED_AT")
    private LocalDateTime updatedAt;
}