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

    @PostMapping
    public void addComments(@RequestHeader("uuid") String uuid,
        @RequestBody RequestAddComments requestAddComments) {

        commentService.addComment(new CommentsDto(requestAddComments, uuid));
    }

    @PutMapping("/{commentsId}")
    public void updateComments(@RequestHeader("uuid") String uuid, @PathVariable Long commentsId,
        @RequestBody RequestUpdateComments requestUpdateComments) {

        commentService.updateComment(new CommentsDto(requestUpdateComments, commentsId, uuid));
    }

    @DeleteMapping("/{commentsId}")
    public void deleteComments(@RequestHeader("uuid") String uuid, @PathVariable Long commentsId) {

        commentService.deleteComments(uuid, commentsId);
    }

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
