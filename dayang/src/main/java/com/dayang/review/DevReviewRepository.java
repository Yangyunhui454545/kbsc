package com.dayang.review;

import com.dayang.domain.Item;
import com.dayang.domain.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DevReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findByItem(Item item);
}
