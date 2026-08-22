package com.hongzy.novelarchive.exception;

/**
 * 対象の小説が存在しない場合の例外
 */
public class NovelNotFoundException extends RuntimeException {

    public NovelNotFoundException(Long novelId) {
        super("対象の小説が存在しません。id=" + novelId);
    }
}