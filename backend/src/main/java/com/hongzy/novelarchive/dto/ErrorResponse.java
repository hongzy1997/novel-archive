package com.hongzy.novelarchive.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * APIエラーレスポンスDTO
 */
@Data
@AllArgsConstructor
public class ErrorResponse {

    /** エラーメッセージ */
    private String message;
}