package com.dayang.repository;

import com.dayang.domain.Item;
import com.dayang.domain.Review;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepo extends JpaRepository<Review, Long> {

    List<Review> findByItem(Item item);
}
