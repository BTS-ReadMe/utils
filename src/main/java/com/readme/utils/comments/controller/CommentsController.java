package com.readme.utils.comments.controller;

import com.readme.utils.comments.dto.CommentsDto;
import com.readme.utils.comments.requestObject.RequestAddComments;
import com.readme.utils.comments.requestObject.RequestUpdateComments;
import com.readme.utils.comments.service.CommentsService;
import javax.ws.rs.DELETE;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
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




}
