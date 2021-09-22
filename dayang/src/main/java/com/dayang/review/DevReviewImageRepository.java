package com.dayang.review;

import com.dayang.domain.Review;
import com.dayang.domain.Review_img;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DevReviewImageRepository extends JpaRepository<Review_img, Long> {
    Review_img findByReview(Review review);
}
