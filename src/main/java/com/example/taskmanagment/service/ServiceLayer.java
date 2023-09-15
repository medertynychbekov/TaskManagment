package com.example.taskmanagment.service;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ServiceLayer<RQST, RSNPS> {

    RSNPS save(RQST rqst);

    RSNPS findById(Long id);

    List<RSNPS> findAll();

    RSNPS update(Long id, RQST rqst);

    RSNPS delete(Long id);
}
