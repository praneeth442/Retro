package com.sis.retro.service;

import com.sis.retro.model.Retro;
import com.sis.retro.repository.RetroCrudRepository;
import com.sis.retro.repository.RetroRepository;
import com.sis.retro.request.RetroRequest;
import com.sis.retro.response.RetroResponse;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;


@SpringBootTest
class RetroServiceTest {

    @InjectMocks
    RetroService retroService;

    @Mock
    RetroCrudRepository retroCrudRepository;

    @Mock
    RetroRepository retroRepository;

    @Test
    void getAllRetroData() {
        Retro retro = Retro.builder().summary("").name("name").date(LocalDate.parse("23/03/2003", DateTimeFormatter
                .ofPattern("dd/MM/yyyy"))).feedBacks(List.of()).build();
        List<Retro> iterable  = Arrays.asList(retro);
        Page<Retro> page  = new PageImpl(iterable);
        when(retroRepository.findAll(Mockito.any(Pageable.class))).thenReturn(page);
        List<RetroResponse> responses =retroService.getAllRetroData(null,null);
        assertEquals(1, responses.size());
    }

    @Test
    void getAllRetroDataEmptyData() {
        Page<Retro> page  = new PageImpl(List.of());
        when(retroRepository.findAll(Mockito.any(Pageable.class))).thenReturn(page);
        List<RetroResponse> responses =retroService.getAllRetroData(null,null);
        assertEquals(0, responses.size());
    }

    @Test
    void getAllRetroDataPageable() {
        Retro retro = Retro.builder().summary("").name("name").date(LocalDate.parse("23/03/2003", DateTimeFormatter
                .ofPattern("dd/MM/yyyy"))).feedBacks(List.of()).build();
        List<Retro> iterable  = Arrays.asList(retro,retro,retro,retro);
        Page<Retro> page  = new PageImpl(iterable);
        when(retroRepository.findAll(Mockito.any(Pageable.class))).thenReturn(page);
        List<RetroResponse> responses =retroService.getAllRetroData(1,4);
        assertEquals(4, responses.size());
    }
    @Test
    void getAllRetroDataByDate() {
        String date= "23/03/1991";
        Retro retro = Retro.builder().summary("").name("name").date(LocalDate.parse("23/03/2003", DateTimeFormatter
                .ofPattern("dd/MM/yyyy"))).feedBacks(List.of()).build();
        List<Retro> iterable  = Arrays.asList(retro);
        Page<Retro> page  = new PageImpl(iterable);
        when(retroRepository.findByDate(Mockito.any(LocalDate.class),Mockito.any(Pageable.class))).thenReturn(iterable);
        List<RetroResponse> responses =retroService.getAllRetroDataByDate(null,null,date);
        assertEquals(1, responses.size());
    }

    @Test
    void getAllRetroDataByDateEmptyData() {
        String date= "23/03/1991";
        when(retroRepository.findByDate(Mockito.any(LocalDate.class),Mockito.any(Pageable.class))).thenReturn(List.of());
        List<RetroResponse> responses =retroService.getAllRetroDataByDate(null,null,date);
        assertEquals(0, responses.size());
    }

    @Test
    void getAllRetroDataByDateWithPageNumber() {
        String date= "23/03/1991";
        Retro retro = Retro.builder().summary("").name("name").date(LocalDate.parse("23/03/2003", DateTimeFormatter
                .ofPattern("dd/MM/yyyy"))).feedBacks(List.of()).build();
        List<Retro> iterable  = Arrays.asList(retro,retro,retro,retro);
        Page<Retro> page  = new PageImpl(iterable);
        when(retroRepository.findByDate(Mockito.any(LocalDate.class),Mockito.any(Pageable.class))).thenReturn(iterable);
        List<RetroResponse> responses =retroService.getAllRetroDataByDate(1,4,date);
        assertEquals(4, responses.size());
    }

    @Test
    void create() {
        RetroRequest retroRequest = RetroRequest.builder().retroName("test").participants(List.of("123")).date("23/03/1991").build();
        retroService.create(retroRequest);
        verify(retroCrudRepository,times(1)).save(Mockito.any());
        verifyNoMoreInteractions(retroCrudRepository);

    }
}