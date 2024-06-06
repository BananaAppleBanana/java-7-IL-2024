package org.example.week3.homework1.service;

import org.example.week3.homework1.domain.University;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public interface UniversityService {

    University[] getAll();
    List<University> getByCountries(List<String> countries);
}
