package com.sis.retro.controller;


import com.sis.retro.request.RetroRequest;
import com.sis.retro.response.RetroResponse;
import com.sis.retro.service.RetroService;
import com.sis.retro.validation.DateValidatorConstraint;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@Slf4j
public class RetrospectiveController {

    private RetroService retroService;

    @PostMapping(value = "retro/create", consumes = MediaType.APPLICATION_JSON_VALUE,produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity createRetro(@Valid @RequestBody RetroRequest retro) {
        log.info("received retro request with id {}",retro.getRetroName());
        retroService.create(retro);
        return new ResponseEntity<String>(String.format("created retro record with name %s", retro.getRetroName()), HttpStatus.CREATED);
    }

    @GetMapping(value = "/get/retros", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    @ResponseBody
    public List<RetroResponse> getRetros(@RequestParam(required = false) Integer currentPage, @RequestParam(required = false) Integer pageSize) {
        log.info("retrieving all retros");
        return retroService.getAllRetroData(currentPage, pageSize);
    }

    @GetMapping(value = "/get/retros/date", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    @ResponseBody
    public List<RetroResponse> getRetros(@RequestParam(required = false) Integer currentPage, @RequestParam(required = false)  Integer pageSize, @RequestParam @NotNull(message = "date cannot be null")@DateValidatorConstraint(message="invalid date or date format expected date format is dd/mm/yyyy") String date) {
        log.info("retrieving all retros with date {}",date);
        return retroService.getAllRetroDataByDate(currentPage, pageSize, date);
    }
}
