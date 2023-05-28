package com.readme.utils.picks.controller;

import com.readme.utils.commonResponseObject.CommonResponseData;
import com.readme.utils.picks.dto.PickDto;
import com.readme.utils.picks.dto.PickPaginationDto;
import com.readme.utils.picks.dto.ResponsePickDto;
import com.readme.utils.picks.repository.PickRepository;
import com.readme.utils.picks.requestObject.RequestPick;
import com.readme.utils.picks.responseObject.ResponsePicks;
import com.readme.utils.picks.responseObject.ResponsePicksPagination;
import com.readme.utils.picks.responseObject.ResponsePicksPagination.Pagination;
import com.readme.utils.picks.service.PickService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/pick")
@RequiredArgsConstructor
public class picksController {

    private final PickService pickService;

    @Operation(summary = "찜(좋아요) 등록/취소", description = "찜(좋아요) 기능 등록, 등록되있으면 취소", tags = {"찜(좋아요)"})
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "OK"),
        @ApiResponse(responseCode = "400", description = "BAD REQUEST"),
        @ApiResponse(responseCode = "404", description = "NOT FOUND"),
        @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR")
    })
    @PostMapping
    public void pickNovels(@RequestHeader("uuid") String uuid,
        @RequestBody RequestPick requestPick) {

        pickService.pickNovels(new PickDto(uuid, requestPick));

    }

    @Operation(summary = "찜(좋아요) 목록 조회", description = "찜(좋아요) 목록 조회", tags = {"찜(좋아요)"})
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "OK"),
        @ApiResponse(responseCode = "400", description = "BAD REQUEST"),
        @ApiResponse(responseCode = "404", description = "NOT FOUND"),
        @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR")
    })
    @GetMapping
    public ResponseEntity<CommonResponseData<ResponsePicksPagination>> getPicks(
        @RequestHeader("uuid") String uuid, @PageableDefault(size = 12) Pageable pageable) {

        PickPaginationDto responsePickDtoList = pickService.getPicks(uuid, pageable);

        return ResponseEntity.ok(new CommonResponseData<>(
           new ResponsePicksPagination(
               responsePickDtoList.getContents().stream().map(ResponsePicks::new),
               new Pagination(responsePickDtoList.getPaginationDto())
           )
        ));
    }

}
