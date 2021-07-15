package com.cinemafy.backend.services;
/**
 * @author Mustafa Atmaca
 */

import com.cinemafy.backend.models.Category;

import java.util.List;
import java.util.Set;

public interface CategoryService {
    List<Category> findAll();

    Set<Category> findByGenreFilter(String filter);

    Long count();

    void delete(Category category);

    void save(Category category);
}
