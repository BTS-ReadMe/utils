package com.readme.utils.starrating.service;

import com.readme.utils.starrating.dto.ResponseStarRatingDto;
import com.readme.utils.starrating.dto.StarRatingDto;
import com.readme.utils.starrating.dto.StarRatingKafkaDto;
import com.readme.utils.starrating.requestObject.RequestAddStar;

public interface StarService {
    void addStarRating(StarRatingDto starRatingDto);

    ResponseStarRatingDto getStarRatingByEpisodeId(String uuid, long episodeId);

    StarRatingKafkaDto getStarRatingByNovelId(long novelId);
}
