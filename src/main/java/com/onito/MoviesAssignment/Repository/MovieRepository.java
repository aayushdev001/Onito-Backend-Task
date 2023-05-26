package com.onito.MoviesAssignment.Repository;

import com.onito.MoviesAssignment.DTO.TopRatedMovieResponseDTO;
import com.onito.MoviesAssignment.DTO.TotalVotesResponseDTO;
import com.onito.MoviesAssignment.Model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MovieRepository extends JpaRepository<Movie, String>
{
    @Query(value = "SELECT * FROM Movie where runtime_minutes > 6 order by runtime_minutes desc limit 10", nativeQuery = true)
    List<Movie> longestDurationMovies();

    @Query(value = "select m.id as id, m.primary_title as primaryTitle, m.genres as genre, r.average_rating as averageRating from movie m join rating r on m.id = r.movie_id where r.average_rating>6 order by r.average_rating asc", nativeQuery = true)
    List<TopRatedMovieResponseDTO> topRatedMovies();

    @Query(value = "select m.genres as genre, m.primary_title as primaryTitle, r.num_votes numVotes, sum(r.num_votes) AS subtotal from movie m join rating r on m.id=r.movie_id group by m.genres , m.primary_title with rollup", nativeQuery = true)
    List<TotalVotesResponseDTO> genreSubtotals();

    @Query(value = "UPDATE Movie SET runtime_minutes = CASE WHEN genres = 'Documentary' THEN runtime_minutes + 15 WHEN genres = 'Animation' THEN runtime_minutes + 30 ELSE runtime_minutes + 45 END", nativeQuery = true)
    void updateRuntime();
}
