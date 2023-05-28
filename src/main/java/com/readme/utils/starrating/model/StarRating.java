package com.readme.utils.starrating.model;

import com.readme.utils.starrating.dto.StarRatingDto;
import com.readme.utils.utils.BaseTimeEntity;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
public class StarRating extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String uuid;
    private Long episodeId;
    private Long novelId;
    private Double starRating;

    public StarRating(StarRatingDto starRatingDto) {
        this.uuid = starRatingDto.getUuid();
        this.episodeId = starRatingDto.getEpisodeId();
        this.novelId = starRatingDto.getNovelId();
        this.starRating = starRatingDto.getStarRating();
    }
}
