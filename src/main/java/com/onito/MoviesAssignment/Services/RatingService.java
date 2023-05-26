package com.onito.MoviesAssignment.Services;

import com.onito.MoviesAssignment.Model.Movie;
import com.onito.MoviesAssignment.Model.Rating;
import com.onito.MoviesAssignment.Repository.MovieRepository;
import com.onito.MoviesAssignment.Repository.RatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

@Service
public class RatingService
{
    @Autowired
    RatingRepository ratingRepository;
    @Autowired
    MovieRepository movieRepository;

    String line = "";
    public String readData() throws FileNotFoundException {
        try
        {
            BufferedReader br = new BufferedReader(new FileReader("src/main/resources/ratings.csv"));

            boolean flag = false;
            while((line=br.readLine()) != null)
            {
                String data[] = line.split(",");

                if(flag)
                {
                    Rating rating = new Rating();
                    rating.setId(data[0]);
                    Movie movie = movieRepository.findById(data[0]).get();
                    rating.setAverageRating(Float.parseFloat(data[1]));
                    rating.setNumVotes(Integer.parseInt(data[2]));

                    rating.setMovie(movie);
                    movie.setRating(rating);
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
}
