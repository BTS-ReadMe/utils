package com.readme.utils.starrating.dto;

import com.readme.utils.starrating.requestObject.RequestAddStar;
import lombok.Getter;

@Getter
public class StarRatingDto {
    private String uuid;
    private long episodeId;
    private long novelId;
    private double starRating;

    public StarRatingDto(String uuid, RequestAddStar requestAddStar) {
        this.uuid = uuid;
        this.episodeId = requestAddStar.getEpisodeId();
        this.novelId = requestAddStar.getNovelId();
        this.starRating = requestAddStar.getStarRating();
    }
}
