package com.readme.utils.starrating.service;

import com.readme.utils.starrating.dto.ResponseStarRatingDto;
import com.readme.utils.starrating.dto.StarRatingDto;
import com.readme.utils.starrating.model.StarRating;
import com.readme.utils.starrating.repository.StarRepository;
import com.readme.utils.starrating.requestObject.RequestAddStar;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;

@Service
@RequiredArgsConstructor
public class StarServiceImpl implements StarService {

    private final StarRepository starRepository;

    @Transactional
    @Override
    public void addStarRating(StarRatingDto starRatingDto) {

        if (starRepository.existsByUuidAndEpisodeId(starRatingDto.getUuid(),
            starRatingDto.getEpisodeId())) {
            StarRating starRating = starRepository.findByUuidAndEpisodeId(starRatingDto.getUuid(),
                starRatingDto.getEpisodeId());
            starRating.setStarRating(starRatingDto.getStarRating());
        } else {
            StarRating starRating = new StarRating(starRatingDto);
            starRepository.save(starRating);
        }

    }

    @Override
    public ResponseStarRatingDto getStarRatingByEpisodeId(String uuid, long episodeId) {
        Double starRating = 0.0;
        Double myRating = 0.0;
        if (starRepository.existsByEpisodeId(episodeId)) {
            starRating = starRepository.findAvgEpisodeIdByEpisodeId(episodeId);
            starRating = Math.round(starRating * 10.0) / 10.0;
        }

        boolean rated = false;

        if (starRepository.existsByUuidAndEpisodeId(uuid, episodeId)) {
            StarRating myStarRating = starRepository.findByUuidAndEpisodeId(uuid, episodeId);
            rated = true;
            myRating = Math.round(myStarRating.getStarRating()*10.0)/10.0;
        }

        ResponseStarRatingDto responseStarRatingDto = new ResponseStarRatingDto(rated, starRating, myRating);

        return responseStarRatingDto;
    }
}
