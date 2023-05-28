package com.readme.utils.starrating.controller;

import com.readme.utils.commonResponseObject.CommonResponseData;
import com.readme.utils.starrating.dto.ResponseStarRatingDto;
import com.readme.utils.starrating.dto.StarRatingDto;
import com.readme.utils.starrating.requestObject.RequestAddStar;
import com.readme.utils.starrating.responseObject.ResponseStarRating;
import com.readme.utils.starrating.service.StarService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    @Operation(summary = "별점 등록/수정", description = "별점 없으면 등록, 있으면 수정", tags = {"별점"})
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "OK"),
        @ApiResponse(responseCode = "400", description = "BAD REQUEST"),
        @ApiResponse(responseCode = "404", description = "NOT FOUND"),
        @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR")
    })
    @PostMapping
    public void addStarRating(@RequestHeader("uuid") String uuid,
        @RequestBody RequestAddStar requestAddStar) {

        starService.addStarRating(new StarRatingDto(uuid, requestAddStar));

    }

    @Operation(summary = "에피소드별 별점 조회", description = "에피소드별 별점 조회", tags = {"별점"})
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "OK"),
        @ApiResponse(responseCode = "400", description = "BAD REQUEST"),
        @ApiResponse(responseCode = "404", description = "NOT FOUND"),
        @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR")
    })
    @GetMapping("/episode/{episodeId}")
    public ResponseEntity<CommonResponseData<ResponseStarRating>> getStarRatingByEpisodeId(
        @RequestHeader(value = "uuid", required = false, defaultValue = "") String uuid, @PathVariable long episodeId) {
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
