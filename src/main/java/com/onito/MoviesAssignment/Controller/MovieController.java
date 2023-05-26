package com.onito.MoviesAssignment.Controller;

import com.onito.MoviesAssignment.DTO.TopRatedMovieResponseDTO;
import com.onito.MoviesAssignment.DTO.TotalVotesResponseDTO;
import com.onito.MoviesAssignment.Model.Movie;
import com.onito.MoviesAssignment.Repository.MovieRepository;
import com.onito.MoviesAssignment.Services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.FileNotFoundException;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class MovieController
{
    @Autowired
    MovieService movieService;

    @GetMapping("/read-data")
    public String readData() throws FileNotFoundException
    {
        return movieService.readData();
    }

    @GetMapping("/longest-duration-movies")
    public List<Movie> longestDurationMovies()
    {
        return movieService.longestDurationMovies();
    }

    @PostMapping("/new-movie")
    public String addMovie(@RequestBody Movie movie)
    {
        return movieService.addMovie(movie);
    }

    @GetMapping("/top-rated-movies")
    public List<TopRatedMovieResponseDTO> topRatedMovies()
    {
        return movieService.topRatedMovies();
    }

    @GetMapping("/genre-movies-with-subtotals")
    public List<TotalVotesResponseDTO> genreSubtotals()
    {
        return movieService.genreSubtotals();
    }

    @PostMapping("/update-runtime-minutes")
    public void updateRuntime()
    {
        movieService.updateRuntime();
    }
}
