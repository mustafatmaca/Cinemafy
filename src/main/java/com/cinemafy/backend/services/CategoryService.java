package com.cinemafy.backend.services;

import com.cinemafy.backend.models.Category;

import java.util.List;

public interface CategoryService {
    List<Category> findAll();

    Long count();

    void delete(Category category);

    void save(Category category);
}
