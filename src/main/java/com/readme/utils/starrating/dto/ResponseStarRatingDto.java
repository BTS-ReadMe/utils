package com.readme.utils.starrating.dto;

import lombok.Getter;

@Getter
public class ResponseStarRatingDto {
    private boolean rated;
    private double starRating;
    private double myRating;

    public ResponseStarRatingDto(boolean rated, double starRating, double myRating) {
        this.rated = rated;
        this.starRating = starRating;
        this.myRating = myRating;
    }
}
