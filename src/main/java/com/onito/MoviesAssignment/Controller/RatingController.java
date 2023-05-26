package com.onito.MoviesAssignment.Controller;

import com.onito.MoviesAssignment.Services.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileNotFoundException;

@RestController
@RequestMapping("/rating")
public class RatingController
{
    @Autowired
    RatingService ratingService;

    @GetMapping("/read-data")
    public String readData() throws FileNotFoundException
    {
        return ratingService.readData();
    }
}
