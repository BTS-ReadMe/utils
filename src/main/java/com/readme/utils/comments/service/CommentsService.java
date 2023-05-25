package com.readme.utils.comments.service;

import com.readme.utils.comments.dto.CommentsDto;
import com.readme.utils.comments.dto.CommentsPaginationDto;
import org.springframework.data.domain.Pageable;

public interface CommentsService {

    void addComment(CommentsDto commentsDto);

    void updateComment(CommentsDto commentsDto);

    void deleteComments(String uuid, Long commentsId);

    CommentsPaginationDto getCommentsByEpisodesId(String uuid, Long episodesId, Pageable pageable);

    CommentsPaginationDto getCommentsByNovelsId(String uuid, Long novelsId, Pageable pageable);

}
