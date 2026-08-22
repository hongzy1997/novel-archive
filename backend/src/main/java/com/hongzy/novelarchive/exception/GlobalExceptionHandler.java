package com.hongzy.novelarchive.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.hongzy.novelarchive.dto.ErrorResponse;

/**
 * APIで発生した例外を共通で処理するExceptionHandler
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 対象の小説が存在しない場合
     * HTTP 404 Not Foundを返却
     */
    @ExceptionHandler(NovelNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleNovelNotFoundException(
            NovelNotFoundException ex) {

        ErrorResponse response = new ErrorResponse(ex.getMessage());

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(response);
    }

    /**
     * 同じタイトルの小説が既に存在する場合
     * HTTP 409 Conflictを返却
     */
    @ExceptionHandler(NovelAlreadyExistsException.class)
    public ResponseEntity<ErrorResponse> handleNovelAlreadyExistsException(
            NovelAlreadyExistsException ex) {

        ErrorResponse response = new ErrorResponse(ex.getMessage());

        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(response);
    }

    /**
     * リクエストのバリデーションエラーが発生した場合
     * フィールド名とエラーメッセージをHTTP 400 Bad Requestで返却
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationException(
            MethodArgumentNotValidException ex) {

        Map<String, String> errors = new HashMap<>();

        // バリデーションエラーを「フィールド名：エラーメッセージ」の形式に変換
        ex.getBindingResult()
                .getFieldErrors()
                .forEach(error -> errors.put(
                        error.getField(),
                        error.getDefaultMessage()));

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(errors);
    }
}