package com.hongzy.novelarchive.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hongzy.novelarchive.dto.NovelRequest;
import com.hongzy.novelarchive.dto.NovelResponse;
import com.hongzy.novelarchive.service.NovelService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

/**
 * 小説情報を操作するREST API
 */
@RequiredArgsConstructor
@RequestMapping("/api/novels")
@RestController
public class NovelController {

    private final NovelService novelService;

    /**
     * 小説一覧を取得
     *
     * @return 小説一覧
     */
    @GetMapping
    public List<NovelResponse> findAll() {
        return novelService.findAll();
    }

    /**
     * 指定した小説を取得
     *
     * @param novelId 小説ID
     * @return 小説情報
     */
    @GetMapping("/{novelId}")
    public NovelResponse findById(
            @PathVariable Long novelId) {

        return novelService.findById(novelId);
    }

    /**
     * 小説を登録
     *
     * @param request 登録内容
     * @return 登録した小説情報
     */
    @PostMapping
    public NovelResponse create(
            @Valid @RequestBody NovelRequest request) {

        return novelService.create(request);
    }

    /**
     * 小説を更新
     *
     * @param novelId 小説ID
     * @param request 更新内容
     * @return 更新した小説情報
     */
    @PutMapping("/{novelId}")
    public NovelResponse update(
            @PathVariable Long novelId,
            @Valid @RequestBody NovelRequest request) {

        return novelService.update(novelId, request);
    }

    /**
     * 小説を削除
     *
     * @param novelId 小説ID
     */
    @DeleteMapping("/{novelId}")
    public void delete(
            @PathVariable Long novelId) {

        novelService.delete(novelId);
    }
}