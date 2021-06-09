package com.cinemafy.backend.services;

import com.cinemafy.backend.models.Category;
import com.cinemafy.backend.repositories.CategoryRepository;

import java.util.HashSet;
import java.util.Set;

public class CategoryServiceImpl implements CategoryService{

    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Set<Category> getCategories() {
        Set<Category> categorySet = new HashSet<>();
        categoryRepository.findAll().iterator().forEachRemaining(categorySet::add);
        return categorySet;
    }
}
