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

@Entity
@Table(name = "NOVELS")
@Getter
@Setter
public class Novel {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(sequence = "NOVEL_SEQ")

    @Column(name = "NOVEL_ID")
    private Long novelId;

    @Column(name = "TITLE")
    private String title;

    @Column(name = "AUTHOR")
    private String author;

    @Column(name = "READING_STATUS")
    private Integer readingStatus;

    @Column(name = "RATING")
    private Integer rating;

    @Column(name = "MEMO")
    private String memo;

    @Column(name = "CREATED_AT")
    private LocalDateTime createdAt;

    @Column(name = "UPDATED_AT")
    private LocalDateTime updatedAt;

}