package com.dayang.repository;

import com.dayang.domain.Brand;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BrandRepository extends JpaRepository<Brand, Long> {
    Brand findByNameContaining(String name);
}
