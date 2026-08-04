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

import com.hongzy.novelarchive.dto.NovelCreateRequest;
import com.hongzy.novelarchive.dto.NovelResponse;
import com.hongzy.novelarchive.dto.NovelUpdateRequest;
import com.hongzy.novelarchive.service.NovelService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RequestMapping("/api/novels")
@RestController
public class NovelController {

    private final NovelService novelService;

    @GetMapping
    public List<NovelResponse> findAll() {

        return novelService.findAll();

    }

    @GetMapping("/{novelId}")
    public NovelResponse findById(
            @PathVariable Long novelId) {

        return novelService.findById(novelId);

    }

    @PostMapping
    public NovelResponse create(
            @Valid @RequestBody NovelCreateRequest request) {

        return novelService.create(request);
    }

    @PutMapping("/{novelId}")
    public NovelResponse update(
            @PathVariable Long novelId,
            @Valid @RequestBody NovelUpdateRequest request) {

        return novelService.update(novelId, request);
    }

    @DeleteMapping("/{novelId}")
    public void delete(
            @Valid @PathVariable Long novelId) {

        novelService.delete(novelId);
    }
}