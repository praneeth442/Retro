package com.sis.retro.service;


import com.sis.retro.model.Retro;
import com.sis.retro.repository.RetroCrudRepository;
import com.sis.retro.repository.RetroRepository;
import com.sis.retro.request.RetroRequest;
import com.sis.retro.response.FeedBackResponse;
import com.sis.retro.response.RetroResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;


@AllArgsConstructor
@Service
@Slf4j
public class RetroService {

    private RetroRepository retroRepository;

    private RetroCrudRepository retroCrudRepository;

    public List<RetroResponse> getAllRetroData(Integer pageNumber, Integer numberOfElements) {
        Iterable<Retro> retros = null;
        if (pageNumber == null) {
            pageNumber = 0;
            log.info("setting default value of page number as 0");
        }
        if (numberOfElements == null) {
            numberOfElements = 10;
            log.info("setting default value of number of elements as 10");
        }
        Pageable sortedByName =
                PageRequest.of(pageNumber, numberOfElements, Sort.by("date"));
        retros = retroRepository.findAll(sortedByName);
        if (!retros.iterator().hasNext()) {
            log.debug("no entries in db");
            return List.of();
        }
        log.debug("returning set with pageNumber {} And numberOfElements {}", pageNumber, numberOfElements);
        return Streamable.of(retros).map(eachRetro -> RetroResponse.builder().name(eachRetro.getName())
                .date(eachRetro.getDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))).participants(
                        eachRetro.getParticipants()
                ).summary(eachRetro.getSummary()).feedBacks(eachRetro.getFeedBacks().stream().map(eachFeedBack -> FeedBackResponse.builder()
                        .body(eachFeedBack.getBody()).name(eachFeedBack.getName()).type(eachFeedBack
                                .getType()).build()).toList()).build()).toList();
    }

    public List<RetroResponse> getAllRetroDataByDate(Integer pageNumber, Integer numberOfElements, String date) {
        Iterable<Retro> retros = null;
        if (pageNumber == null) {
            pageNumber = 0;
            log.info("setting default value of page number as 0");
        }
        if (numberOfElements == null) {
            numberOfElements = 10;
            log.info("setting default value of number of elements as 10");
        }
        Pageable sortedByName;
        sortedByName = PageRequest.of(pageNumber, numberOfElements, Sort.by("name"));
        retros = retroRepository.findByDate(LocalDate.parse(date, DateTimeFormatter.ofPattern("dd/MM/yyyy")), sortedByName);
        if (!retros.iterator().hasNext()) {
            log.debug("no entries in db for the date {}", date);
            return List.of();
        }
        log.debug("returning set based on date with pageNumber {} And numberOfElements {}", pageNumber, numberOfElements);
        return Streamable.of(retros).map(eachRetro -> RetroResponse.builder().name(eachRetro.getName())
                .date(eachRetro.getDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))).participants(
                        eachRetro.getParticipants()
                ).summary(eachRetro.getSummary()).feedBacks(eachRetro.getFeedBacks().stream().map(eachf -> FeedBackResponse.builder()
                        .body(eachf.getBody()).name(eachf.getName()).type(eachf
                                .getType()).build()).toList()).build()).toList();
    }

    public void create(RetroRequest retro) {
        retroCrudRepository.
                save(Retro.builder().name(retro.getRetroName()).date(LocalDate.parse(retro.getDate(), DateTimeFormatter.ofPattern("dd/MM/yyyy")))
                        .participants(retro.getParticipants()).summary(retro.getSummary()).build());
        log.debug("created retro record with name {}", retro
                .getRetroName());
    }
}
