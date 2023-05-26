package com.onito.MoviesAssignment.Services;

import com.onito.MoviesAssignment.DTO.TopRatedMovieResponseDTO;
import com.onito.MoviesAssignment.DTO.TotalVotesResponseDTO;
import com.onito.MoviesAssignment.Model.Movie;
import com.onito.MoviesAssignment.Repository.MovieRepository;
import com.onito.MoviesAssignment.Repository.MovieUpdater;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

@Service
public class MovieService
{
    @Autowired
    MovieRepository movieRepository;

    String line = "";
    public String readData() throws FileNotFoundException
    {
        try
        {
            BufferedReader br = new BufferedReader(new FileReader("src/main/resources/movies.csv"));

            boolean flag = false;
            while((line=br.readLine()) != null)
            {
                String data[] = line.split(",");
                if (flag)
                {
                    Movie movie = new Movie();
                    movie.setId(data[0]);
                    movie.setTitleType(data[1]);
                    movie.setPrimaryTitle(data[2]);
                    movie.setRuntimeMinutes(Integer.parseInt(data[3]));
                    movie.setGenres(data[4]);
                    movieRepository.save(movie);
                }
                flag = true;
            }
        }
        catch (FileNotFoundException e)
        {
            throw new FileNotFoundException("File Not Found");
        } catch (IOException e)
        {
            throw new RuntimeException(e);
        }
        return "Data read";
    }

    public List<Movie> longestDurationMovies()
    {
        return movieRepository.longestDurationMovies();
    }

    public String addMovie(Movie movie)
    {
        movieRepository.save(movie);
        return "Success";
    }

    public List<TopRatedMovieResponseDTO> topRatedMovies()
    {
        return movieRepository.topRatedMovies();
    }

    public List<TotalVotesResponseDTO> genreSubtotals()
    {
        return movieRepository.genreSubtotals();
    }

    public void updateRuntime()
    {
        MovieUpdater.update();
    }
}
