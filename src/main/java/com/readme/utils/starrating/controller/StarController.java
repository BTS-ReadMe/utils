package com.readme.utils.starrating.controller;

import com.readme.utils.commonResponseObject.CommonResponseData;
import com.readme.utils.starrating.dto.ResponseStarRatingDto;
import com.readme.utils.starrating.dto.StarRatingDto;
import com.readme.utils.starrating.requestObject.RequestAddStar;
import com.readme.utils.starrating.responseObject.ResponseStarRating;
import com.readme.utils.starrating.service.StarService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/starRating")
@RequiredArgsConstructor
@Slf4j
public class StarController {

    private final StarService starService;

    @PostMapping
    public void addStarRating(@RequestHeader("uuid") String uuid,
        @RequestBody RequestAddStar requestAddStar) {

        starService.addStarRating(new StarRatingDto(uuid, requestAddStar));

    }

    @GetMapping("/episode/{episodeId}")
    public ResponseEntity<CommonResponseData<ResponseStarRating>> getStarRatingByEpisodeId(
        @RequestHeader("uuid") String uuid, @PathVariable long episodeId) {
        ResponseStarRatingDto responseStarRatingDto = starService.getStarRatingByEpisodeId(uuid,
            episodeId);

        return ResponseEntity.ok(new CommonResponseData<>(
            new ResponseStarRating(responseStarRatingDto)
        ));
    }

//    @GetMapping("/novel/{novelId}")
//    public void getStarRatingByNovelId() {
//
//    }
}
