package com.hongzy.novelarchive.exception;

public class NovelNotFoundException extends RuntimeException {

    public NovelNotFoundException(Long novelId) {
        super("対象の小説が存在しません。id=" + novelId);
    }

    public NovelNotFoundException(String string) {
        // TODO Auto-generated constructor stub
    }
}