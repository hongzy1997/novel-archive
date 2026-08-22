package com.hongzy.novelarchive.exception;

/**
 * 小説タイトル重複時の例外
 */
public class NovelAlreadyExistsException extends RuntimeException {

    public NovelAlreadyExistsException(String title) {
        super("同じタイトルの小説が既に登録されています。title=" + title);
    }
}