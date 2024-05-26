package com.sis.retro.repository;

import com.sis.retro.model.Retro;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RetroCrudRepository extends CrudRepository<Retro, String> {

}



