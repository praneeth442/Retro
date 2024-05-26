package com.sis.retro.repository;

import com.sis.retro.model.FeedBack;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FeedBackRepository extends CrudRepository<FeedBack, String> {
}



