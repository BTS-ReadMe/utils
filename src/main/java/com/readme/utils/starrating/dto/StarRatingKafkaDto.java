package com.readme.utils.starrating.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@NoArgsConstructor
@ToString
public class StarRatingKafkaDto {
    private long novelId;
    private Double starRating;

    public StarRatingKafkaDto(long novelId, Double starRating) {
        this.novelId = novelId;
        this.starRating = starRating;
    }
}
