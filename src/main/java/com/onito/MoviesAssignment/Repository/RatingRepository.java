package com.onito.MoviesAssignment.Repository;

import com.onito.MoviesAssignment.Model.Rating;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RatingRepository extends JpaRepository<Rating, String>
{
}
