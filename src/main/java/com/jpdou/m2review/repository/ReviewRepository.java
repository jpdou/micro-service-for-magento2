package com.jpdou.m2review.repository;

import com.jpdou.m2review.model.Review;
import org.springframework.data.repository.CrudRepository;

public interface ReviewRepository extends CrudRepository<Review, Integer> {
}
