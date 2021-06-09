package com.cinemafy.backend.services;

import com.cinemafy.backend.models.Category;

import java.util.Set;

public interface CategoryService {
    Set<Category> getCategories();
}
