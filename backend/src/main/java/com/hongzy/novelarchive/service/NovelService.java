package com.hongzy.novelarchive.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hongzy.novelarchive.dao.NovelDao;
import com.hongzy.novelarchive.dto.NovelRequest;
import com.hongzy.novelarchive.dto.NovelResponse;
import com.hongzy.novelarchive.entity.Novel;
import com.hongzy.novelarchive.exception.NovelAlreadyExistsException;
import com.hongzy.novelarchive.exception.NovelNotFoundException;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class NovelService {

    private final NovelDao novelDao;

    /**
     * 小説一覧取得
     */
    public List<NovelResponse> findAll() {

        List<Novel> novels = novelDao.selectAll();

        List<NovelResponse> responses = new ArrayList<>();

        for (Novel novel : novels) {
            responses.add(convertToResponse(novel));
        }

        return responses;
    }

    /**
     * 小説詳細取得
     */
    public NovelResponse findById(Long novelId) {

        Novel novel = findNovelById(novelId);

        return convertToResponse(novel);
    }

    /**
     * 小説登録
     */
    @Transactional
    public NovelResponse create(NovelRequest request) {

        String title = request.getTitle().trim();

        log.info("小説登録開始。title={}", title);

        // 同一タイトルの小説が既に存在する場合は登録不可
        if (novelDao.existsByTitle(title)) {
            throw new NovelAlreadyExistsException(title);
        }

        Novel novel = new Novel();

        novel.setTitle(title);
        novel.setAuthor(trimToNull(request.getAuthor()));
        novel.setReadingStatus(request.getReadingStatus());
        novel.setRating(request.getRating());
        novel.setMemo(trimToNull(request.getMemo()));

        novelDao.insert(novel);

        log.info("小説登録完了。id={}", novel.getNovelId());

        return convertToResponse(novel);
    }

    /**
     * 小説更新
     */
    @Transactional
    public NovelResponse update(
            Long novelId,
            NovelRequest request) {

        log.info("小説更新開始。id={}", novelId);

        Novel novel = findNovelById(novelId);

        String title = request.getTitle().trim();

        // 他の小説とタイトルが重複する場合は更新不可
        if (novelDao.existsByTitleAndNovelIdNot(title, novelId)) {
            throw new NovelAlreadyExistsException(title);
        }

        novel.setTitle(title);
        novel.setAuthor(trimToNull(request.getAuthor()));
        novel.setReadingStatus(request.getReadingStatus());
        novel.setRating(request.getRating());
        novel.setMemo(trimToNull(request.getMemo()));

        novelDao.update(novel);

        log.info("小説更新完了。id={}", novelId);

        return convertToResponse(novel);
    }

    /**
     * 小説削除
     */
    @Transactional
    public void delete(Long novelId) {

        log.info("小説削除開始。id={}", novelId);

        Novel novel = findNovelById(novelId);

        novelDao.delete(novel);

        log.info("小説削除完了。id={}", novelId);
    }

    /**
     * EntityをレスポンスDTOへ変換
     */
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

    /**
     * 小説IDから小説情報を取得
     */
    private Novel findNovelById(Long novelId) {

        return novelDao.selectById(novelId)
                .orElseThrow(() -> new NovelNotFoundException(novelId));
    }

    /**
     * 文字列の前後空白を除去する。
     * nullまたは空文字の場合はnullを返す。
     */
    private String trimToNull(String value) {

        if (value == null) {
            return null;
        }

        String trimmed = value.trim();

        return trimmed.isEmpty() ? null : trimmed;
    }
}