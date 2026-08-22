package com.hongzy.novelarchive.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import com.hongzy.novelarchive.dto.NovelRequest;
import com.hongzy.novelarchive.dto.NovelResponse;
import com.hongzy.novelarchive.exception.NovelNotFoundException;
import com.hongzy.novelarchive.service.NovelService;

import tools.jackson.databind.ObjectMapper;

@WebMvcTest(NovelController.class)
class NovelControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private NovelService novelService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void shouldReturnNovelWhenNovelExists() throws Exception {

        // Arrange
        NovelResponse response = new NovelResponse();
        response.setNovelId(1L);
        response.setTitle("诡秘之主");
        response.setAuthor("爱潜水的乌贼");
        response.setReadingStatus(2);
        response.setRating(8);
        response.setMemo("很好看");

        when(novelService.findById(1L))
                .thenReturn(response);

        // Act & Assert
        mockMvc.perform(get("/api/novels/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.novelId").value(1))
                .andExpect(jsonPath("$.title").value("诡秘之主"))
                .andExpect(jsonPath("$.author").value("爱潜水的乌贼"))
                .andExpect(jsonPath("$.readingStatus").value(2))
                .andExpect(jsonPath("$.rating").value(8))
                .andExpect(jsonPath("$.memo").value("很好看"));

        verify(novelService).findById(1L);
    }

    @Test
    void shouldCreateNovel() throws Exception {

        // Arrange
        NovelRequest request = new NovelRequest();
        request.setTitle("诡秘之主");
        request.setAuthor("爱潜水的乌贼");
        request.setReadingStatus(2);
        request.setRating(8);
        request.setMemo("很好看");

        NovelResponse response = new NovelResponse();
        response.setNovelId(1L);
        response.setTitle("诡秘之主");
        response.setAuthor("爱潜水的乌贼");
        response.setReadingStatus(2);
        response.setRating(8);
        response.setMemo("很好看");

        when(novelService.create(any(NovelRequest.class)))
                .thenReturn(response);

        // Act & Assert
        mockMvc.perform(post("/api/novels")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.novelId").value(1))
                .andExpect(jsonPath("$.title").value("诡秘之主"))
                .andExpect(jsonPath("$.author").value("爱潜水的乌贼"))
                .andExpect(jsonPath("$.readingStatus").value(2))
                .andExpect(jsonPath("$.rating").value(8))
                .andExpect(jsonPath("$.memo").value("很好看"));

        verify(novelService).create(any(NovelRequest.class));
    }

    @Test
    void shouldReturn400WhenTitleIsBlank() throws Exception {

        // Arrange
        NovelRequest request = new NovelRequest();
        request.setTitle(""); // 故意错误
        request.setAuthor("乌贼");
        request.setReadingStatus(2);
        request.setRating(8);

        // Act & Assert
        mockMvc.perform(post("/api/novels")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest());

        verify(novelService, never()).create(any());
    }

    @Test
    void shouldReturn404WhenNovelNotFound() throws Exception {

        // Arrange
        when(novelService.findById(1L))
                .thenThrow(new NovelNotFoundException(1L));

        // Act & Assert
        mockMvc.perform(get("/api/novels/1"))
                .andExpect(status().isNotFound());

        verify(novelService).findById(1L);
    }
}