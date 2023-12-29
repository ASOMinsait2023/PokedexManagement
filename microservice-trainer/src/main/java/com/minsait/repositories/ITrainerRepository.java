package com.minsait.repositories;

import com.minsait.models.Trainer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ITrainerRepository extends JpaRepository<Trainer,Long> {

}
