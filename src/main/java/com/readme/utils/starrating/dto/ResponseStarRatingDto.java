package com.readme.utils.starrating.dto;

import lombok.Getter;

@Getter
public class ResponseStarRatingDto {
    private boolean rated;
    private double starRating;

    public ResponseStarRatingDto(boolean rated, double starRating) {
        this.rated = rated;
        this.starRating = starRating;
    }
}
