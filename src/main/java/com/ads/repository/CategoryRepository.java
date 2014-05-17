package com.ads.repository;

import com.ads.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Integer> {

    public Category findByCategoryName(String categoryName);
}
