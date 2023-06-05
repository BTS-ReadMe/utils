package com.readme.utils.starrating.responseObject;

import com.readme.utils.starrating.dto.ResponseStarRatingDto;
import lombok.Getter;

@Getter
public class ResponseStarRating {
    private boolean rated;
    private double starRating;
    private double myRating;

    public ResponseStarRating(ResponseStarRatingDto responseStarRatingDto) {
        this.rated = responseStarRatingDto.isRated();
        this.starRating = responseStarRatingDto.getStarRating();
        this.myRating = responseStarRatingDto.getMyRating();
    }
}
