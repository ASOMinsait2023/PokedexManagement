package com.minsait.services;

import com.minsait.models.Trainer;

import java.util.List;

public interface ITrainerService {
    List<Trainer> findAll();
    Trainer findById(Long id);
    Trainer save(Trainer trainer);

    void  setTrainerNotes(Long id, String notes);


}
