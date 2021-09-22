package com.dayang.category;

import com.dayang.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DevCategoryRepository extends JpaRepository<Category, Long> {


    List<Category> findByParent(Category category);

    Optional<Category> findById(Long id);
}
