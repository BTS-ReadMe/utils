package com.readme.utils.comments.controller;

import com.readme.utils.comments.dto.CommentsDto;
import com.readme.utils.comments.dto.CommentsPaginationDto;
import com.readme.utils.comments.dto.ResponseCommentsDto;
import com.readme.utils.comments.requestObject.RequestAddComments;
import com.readme.utils.comments.requestObject.RequestUpdateComments;
import com.readme.utils.comments.responsepObject.ResponseComments;
import com.readme.utils.comments.responsepObject.ResponseCommentsPagination;
import com.readme.utils.comments.responsepObject.ResponseCommentsPagination.Pagination;
import com.readme.utils.comments.service.CommentsService;
import com.readme.utils.commonResponseObject.CommonResponseData;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/comments")
@RequiredArgsConstructor
public class CommentsController {

    private final CommentsService commentService;

    @Operation(summary = "댓글 등록", description = "댓글 등록", tags = {"댓글"})
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "OK"),
        @ApiResponse(responseCode = "400", description = "BAD REQUEST"),
        @ApiResponse(responseCode = "404", description = "NOT FOUND"),
        @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR")
    })
    @PostMapping
    public void addComments(@RequestHeader("uuid") String uuid,
        @RequestBody RequestAddComments requestAddComments) {

        commentService.addComment(new CommentsDto(requestAddComments, uuid));
    }

    @Operation(summary = "댓글 수정", description = "댓글 수정", tags = {"댓글"})
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "OK"),
        @ApiResponse(responseCode = "400", description = "BAD REQUEST"),
        @ApiResponse(responseCode = "404", description = "NOT FOUND"),
        @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR")
    })
    @PutMapping("/{commentsId}")
    public void updateComments(@RequestHeader("uuid") String uuid, @PathVariable Long commentsId,
        @RequestBody RequestUpdateComments requestUpdateComments) {

        commentService.updateComment(new CommentsDto(requestUpdateComments, commentsId, uuid));
    }

    @Operation(summary = "댓글 삭제", description = "댓글 삭제", tags = {"댓글"})
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "OK"),
        @ApiResponse(responseCode = "400", description = "BAD REQUEST"),
        @ApiResponse(responseCode = "404", description = "NOT FOUND"),
        @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR")
    })
    @DeleteMapping("/{commentsId}")
    public void deleteComments(@RequestHeader("uuid") String uuid, @PathVariable Long commentsId) {

        commentService.deleteComments(uuid, commentsId);
    }

    @Operation(summary = "에피소드별 댓글 조회", description = "소설별 댓글 조회", tags = {"댓글"})
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "OK"),
        @ApiResponse(responseCode = "400", description = "BAD REQUEST"),
        @ApiResponse(responseCode = "404", description = "NOT FOUND"),
        @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR")
    })
    @GetMapping("/episodes/{episodesId}")
    public ResponseEntity<CommonResponseData<ResponseCommentsPagination>> getCommentsByEpisodes(
        @RequestHeader(value = "uuid", required = false, defaultValue = "") String uuid,
        @PathVariable Long episodesId, @PageableDefault(size = 10) Pageable pageable) {

        CommentsPaginationDto commentsPaginationDto = commentService.getCommentsByEpisodesId(
            uuid, episodesId, pageable);

        return ResponseEntity.ok(new CommonResponseData<>(
            new ResponseCommentsPagination(
                commentsPaginationDto.getResponseCommentsDtoList().stream()
                    .map(ResponseComments::new),
                new Pagination(commentsPaginationDto.getPaginationDto())
            )));
    }

    @Operation(summary = "소설별 댓글 조회", description = "소설별 댓글 조회", tags = {"댓글"})
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "OK"),
        @ApiResponse(responseCode = "400", description = "BAD REQUEST"),
        @ApiResponse(responseCode = "404", description = "NOT FOUND"),
        @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR")
    })
    @GetMapping("novels/{novelsId}")
    public ResponseEntity<CommonResponseData<ResponseCommentsPagination>> getCommentsByNovels(
        @RequestHeader(value = "uuid", required = false, defaultValue = "") String uuid,
        @PathVariable Long novelsId, @PageableDefault(size = 10) Pageable pageable) {

        CommentsPaginationDto commentsPaginationDto = commentService.getCommentsByNovelsId(uuid,
            novelsId, pageable);

        return ResponseEntity.ok(new CommonResponseData<>(
            new ResponseCommentsPagination(
                commentsPaginationDto.getResponseCommentsDtoList().stream().map(
                    ResponseComments::new),
                new Pagination(commentsPaginationDto.getPaginationDto())
            )));
    }


}
