package com.sis.retro.controller;


import com.sis.retro.request.FeedbackRequest;
import com.sis.retro.service.FeedbackService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@Slf4j
public class FeedbackController {

    private FeedbackService feedbackService;

    @PostMapping(value = "/feedback/create", consumes = MediaType.APPLICATION_JSON_VALUE,produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity createFeedBack(@Valid @RequestBody FeedbackRequest feedbackRequest) {
        log.info("received feedback request with id {}",feedbackRequest.getFeedBackName());
        feedbackService.create(feedbackRequest);
        return new ResponseEntity<String>(String.format("created feedback record with name %s", feedbackRequest.getFeedBackName()), HttpStatus.CREATED);
    }

    @PutMapping(value = "/feedback/update", consumes = MediaType.APPLICATION_JSON_VALUE,produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity updateFeedBack(@Valid @RequestBody FeedbackRequest feedbackRequest) {
        return feedbackService.update(feedbackRequest);
    }
}
