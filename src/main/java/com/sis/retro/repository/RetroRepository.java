package com.sis.retro.repository;

import com.sis.retro.model.Retro;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface RetroRepository extends PagingAndSortingRepository<Retro, String> {
    List<Retro> findByDate(LocalDate date, Pageable sortedByName);
}



