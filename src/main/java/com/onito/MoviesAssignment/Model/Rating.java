package com.onito.MoviesAssignment.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Rating
{
    @Id
    String id;

    double averageRating;

    int numVotes;

    @JsonIgnore
    @OneToOne
    @JoinColumn
    Movie movie;
}
