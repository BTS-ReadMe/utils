package com.readme.utils.comments.service;

import com.readme.utils.comments.dto.CommentsDto;
import com.readme.utils.comments.model.Comments;
import com.readme.utils.comments.repository.CommentsRepository;
import javax.xml.stream.events.Comment;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
@Slf4j
public class CommentsServiceImpl implements CommentsService {

    private final CommentsRepository commentsRepository;

    @Override
    public void addComment(CommentsDto commentsDto) {
        Comments comments = Comments.builder()
            .uuid(commentsDto.getUuid())
            .episodesId(commentsDto.getEpisodesId())
            .novelsId(commentsDto.getNovelsId())
            .content(commentsDto.getContent())
            .writer(commentsDto.getWriter())
            .build();

        commentsRepository.save(comments);
    }

    @Override
    public void updateComment(CommentsDto commentsDto) {

        log.info(commentsDto.toString());

        Comments comments = commentsRepository.findByIdAndUuid(commentsDto.getId(),
                commentsDto.getUuid())
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST));

        Comments updateComments = Comments.builder()
            .id(comments.getId())
            .content(commentsDto.getContent())
            .episodesId(comments.getEpisodesId())
            .novelsId(comments.getNovelsId())
            .uuid(comments.getUuid())
            .writer(comments.getWriter())
            .build();

        commentsRepository.save(updateComments);

    }
}
