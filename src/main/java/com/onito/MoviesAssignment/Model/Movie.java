package com.onito.MoviesAssignment.Model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Movie
{
    @Id
    String id;

    String titleType;

    String primaryTitle;

    int runtimeMinutes;

    String genres;

    @OneToOne(mappedBy = "movie", cascade = CascadeType.ALL)
    Rating rating;
}
