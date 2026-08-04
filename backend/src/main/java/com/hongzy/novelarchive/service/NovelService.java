package com.hongzy.novelarchive.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hongzy.novelarchive.dao.NovelDao;
import com.hongzy.novelarchive.dto.NovelCreateRequest;
import com.hongzy.novelarchive.dto.NovelResponse;
import com.hongzy.novelarchive.dto.NovelUpdateRequest;
import com.hongzy.novelarchive.entity.Novel;
import com.hongzy.novelarchive.exception.NovelNotFoundException;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class NovelService {

    private final NovelDao novelDao;

    public List<NovelResponse> findAll() {

        List<Novel> novels = novelDao.selectAll();

        List<NovelResponse> responses = new ArrayList<>();

        for (Novel novel : novels) {
            responses.add(convertToResponse(novel));
        }

        return responses;
    }

    public NovelResponse findById(Long novelId) {

        Novel novel = novelDao.selectById(novelId)
                .orElseThrow(() -> new NovelNotFoundException(novelId));

        return convertToResponse(novel);
    }

    @Transactional
    public NovelResponse create(NovelCreateRequest request) {

        log.info("小説登録開始。title={}", request.getTitle());

        Novel novel = new Novel();

        novel.setTitle(request.getTitle());
        novel.setAuthor(request.getAuthor());
        novel.setReadingStatus(request.getReadingStatus());
        novel.setRating(request.getRating());
        novel.setMemo(request.getMemo());

        LocalDateTime now = LocalDateTime.now();

        novel.setCreatedAt(now);
        novel.setUpdatedAt(now);

        novelDao.insert(novel);

        log.info("小説登録完了。id={}", novel.getNovelId());

        return convertToResponse(novel);
    }

    @Transactional
    public NovelResponse update(
            Long novelId,
            NovelUpdateRequest request) {

        log.info("小説更新開始。id={}", novelId);

        Novel novel = novelDao.selectById(novelId)
                .orElseThrow(() -> new NovelNotFoundException(novelId));

        novel.setTitle(request.getTitle());
        novel.setAuthor(request.getAuthor());
        novel.setReadingStatus(request.getReadingStatus());
        novel.setRating(request.getRating());
        novel.setMemo(request.getMemo());
        novel.setUpdatedAt(LocalDateTime.now());

        novelDao.update(novel);

        log.info("小説更新完了。id={}", novelId);

        return convertToResponse(novel);
    }

    @Transactional
    public void delete(Long novelId) {

        log.info("小説削除開始。id={}", novelId);

        Novel novel = novelDao.selectById(novelId)
                .orElseThrow(() -> new NovelNotFoundException(novelId));

        novelDao.delete(novel);

        log.info("小説削除完了。id={}", novelId);
    }

    private NovelResponse convertToResponse(Novel novel) {

        NovelResponse response = new NovelResponse();

        response.setNovelId(novel.getNovelId());
        response.setTitle(novel.getTitle());
        response.setAuthor(novel.getAuthor());
        response.setReadingStatus(novel.getReadingStatus());
        response.setRating(novel.getRating());
        response.setMemo(novel.getMemo());

        return response;
    }
}