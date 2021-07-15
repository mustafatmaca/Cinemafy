package com.cinemafy.backend.services;
/**
 * @author Mustafa Atmaca
 */

import com.cinemafy.backend.models.Category;
import com.cinemafy.backend.repositories.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class CategoryServiceImpl implements CategoryService{

    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }


    @Override
    public List<Category> findAll() {
        return StreamSupport.stream(categoryRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    @Override
    public Set<Category> findByGenreFilter(String filter) {
        Set<Category> categorySet = new HashSet<>();
        categoryRepository.findByGenreContainingIgnoreCase(filter).iterator().forEachRemaining(categorySet::add);
        return categorySet;
    }

    @Override
    public Long count() {
        return categoryRepository.count();
    }

    @Override
    public void delete(Category category) {
        categoryRepository.delete(category);
    }

    @Override
    public void save(Category category) {
        categoryRepository.save(category);
    }

}
