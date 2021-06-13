package com.cinemafy.backend.repositories;
/**
 * @author Mustafa Atmaca
 */

import com.cinemafy.backend.models.Category;
import org.springframework.data.repository.CrudRepository;

public interface CategoryRepository extends CrudRepository<Category, Long> {
}
