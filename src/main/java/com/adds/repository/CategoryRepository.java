package com.adds.repository;

import com.adds.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Integer> {

    public Category findByCategoryName(String categoryName);
}
