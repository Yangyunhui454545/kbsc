package com.dayang.repository;

import com.dayang.domain.Item;
import com.dayang.domain.TestColor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TestRepository extends JpaRepository<TestColor, Long> {
    List<TestColor> findByItem(Item item);
}
