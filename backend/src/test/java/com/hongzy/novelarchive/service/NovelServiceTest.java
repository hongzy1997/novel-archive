package com.hongzy.novelarchive.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.hongzy.novelarchive.dao.NovelDao;
import com.hongzy.novelarchive.dto.NovelRequest;
import com.hongzy.novelarchive.dto.NovelResponse;
import com.hongzy.novelarchive.entity.Novel;
import com.hongzy.novelarchive.exception.NovelNotFoundException;

@ExtendWith(MockitoExtension.class)
class NovelServiceTest {

    @Mock
    private NovelDao novelDao;

    @InjectMocks
    private NovelService novelService;

    @Test
    void shouldReturnNovelWhenNovelExists() {

        // Arrange
        Novel novel = new Novel();
        novel.setNovelId(1L);
        novel.setTitle("诡秘之主");
        novel.setAuthor("爱潜水的乌贼");
        novel.setReadingStatus(1);
        novel.setRating(5);
        novel.setMemo("已读");

        when(novelDao.selectById(1L))
                .thenReturn(Optional.of(novel));

        // Act
        NovelResponse response = novelService.findById(1L);

        // Assert
        assertEquals(1L, response.getNovelId());
        assertEquals("诡秘之主", response.getTitle());
        assertEquals("爱潜水的乌贼", response.getAuthor());
        assertEquals(1, response.getReadingStatus());
        assertEquals(5, response.getRating());
        assertEquals("已读", response.getMemo());

        verify(novelDao).selectById(1L);
    }

    @Test
    void shouldThrowExceptionWhenNovelNotFound() {

        when(novelDao.selectById(999L))
                .thenReturn(Optional.empty());

        assertThrows(
                NovelNotFoundException.class,
                () -> novelService.findById(999L));

        verify(novelDao).selectById(999L);
    }

    @Test
    void shouldCreateNovel() {

        // Arrange
        NovelRequest request = new NovelRequest();
        request.setTitle("诡秘之主");
        request.setAuthor("爱潜水的乌贼");
        request.setReadingStatus(1);
        request.setRating(5);
        request.setMemo("已读");

        // Act
        NovelResponse response = novelService.create(request);

        // Assert
        assertEquals("诡秘之主", response.getTitle());
        assertEquals("爱潜水的乌贼", response.getAuthor());
        assertEquals(1, response.getReadingStatus());
        assertEquals(5, response.getRating());
        assertEquals("已读", response.getMemo());

        verify(novelDao).insert(any(Novel.class));
    }
}