package com.readme.utils.comments.service;

import com.readme.utils.comments.dto.CommentsDto;
import com.readme.utils.comments.requestObject.RequestUpdateComments;

public interface CommentsService {

    void addComment(CommentsDto commentsDto);

    void updateComment(CommentsDto commentsDto);

    void deleteComments(String uuid, Long commentsId);
}
