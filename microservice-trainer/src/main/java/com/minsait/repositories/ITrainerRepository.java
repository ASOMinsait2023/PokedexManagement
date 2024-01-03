package com.minsait.repositories;

import com.minsait.models.Trainer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ITrainerRepository extends JpaRepository<Trainer,Long> {

}
