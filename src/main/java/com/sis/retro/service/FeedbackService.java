package com.sis.retro.service;


import com.sis.retro.model.FeedBack;
import com.sis.retro.model.FeedBackType;
import com.sis.retro.model.Retro;
import com.sis.retro.repository.FeedBackRepository;
import com.sis.retro.request.FeedbackRequest;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


@AllArgsConstructor
@Service
@Slf4j
public class FeedbackService {

    private FeedBackRepository feedBackRepository;

    public void create(FeedbackRequest feedbackRequest) {
        feedBackRepository.
                save(FeedBack.builder().name(feedbackRequest.getFeedBackName()).body(feedbackRequest.getBody())
                        .type(FeedBackType.valueOf(feedbackRequest.getType())).retro(Retro.builder().name(feedbackRequest.getRetroName()).build()).build());
        log.debug("created feedback request with id {} with retro name {}",feedbackRequest.getFeedBackName(),feedbackRequest.getRetroName());
    }

    public ResponseEntity<String> update(FeedbackRequest feedbackRequest) {

        ResponseEntity<String> responseEntity = null;
        if (!feedBackRepository.existsById(feedbackRequest.getFeedBackName())) {
            responseEntity = new ResponseEntity<String>(String.format("created new feedback record with name %s", feedbackRequest.getFeedBackName()), HttpStatus.CREATED);
            log.info("record feedback request with id {} already exists updating record",feedbackRequest.getRetroName());
        } else {
            responseEntity = new ResponseEntity<String>(String.format("updated feedback record with name %s", feedbackRequest.getFeedBackName()), HttpStatus.NO_CONTENT);
            log.info("record feedback request with id {} created",feedbackRequest.getRetroName());
        }
        feedBackRepository.
                save(FeedBack.builder().name(feedbackRequest.getFeedBackName()).body(feedbackRequest.getBody())
                        .type(FeedBackType.valueOf(feedbackRequest.getType())).retro(Retro.builder().name(feedbackRequest.getRetroName()).build()).build());

        return responseEntity;
    }
}
