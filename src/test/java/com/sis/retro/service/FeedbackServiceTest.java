package com.sis.retro.service;

import com.sis.retro.repository.FeedBackRepository;
import com.sis.retro.request.FeedbackRequest;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;


@SpringBootTest
class FeedbackServiceTest {

    @InjectMocks
    FeedbackService feedbackService;

    @Mock
    FeedBackRepository feedBackRepository;

    @Test
    void create() {
        FeedbackRequest feedbackRequest = FeedbackRequest.builder().feedBackName("test").retroName("123").type("negative").build();
        feedbackService.create(feedbackRequest);
        verify(feedBackRepository, times(1)).save(Mockito.any());
    }

    @Test
    void createThrowsException() {
        FeedbackRequest feedbackRequest = FeedbackRequest.builder().feedBackName("test").retroName("123").type("negative").build();
        when(feedBackRepository.save(Mockito.any())).thenThrow(RuntimeException.class);
        assertThrows(RuntimeException.class, () -> feedbackService.create(feedbackRequest));
        verify(feedBackRepository, times(1)).save(Mockito.any());
    }

    @Test
    void update() {
        FeedbackRequest feedbackRequest = FeedbackRequest.builder().feedBackName("test").retroName("123").type("negative").build();
        feedbackService.update(feedbackRequest);
        verify(feedBackRepository, times(1)).save(Mockito.any());
    }
}