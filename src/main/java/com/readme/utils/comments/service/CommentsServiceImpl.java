package com.readme.utils.comments.service;

import com.readme.utils.comments.dto.CommentsDto;
import com.readme.utils.comments.dto.PaginationDto;
import com.readme.utils.comments.dto.ResponseCommentsDto;
import com.readme.utils.comments.dto.CommentsPaginationDto;
import com.readme.utils.comments.model.Comments;
import com.readme.utils.comments.repository.CommentsRepository;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    @Override
    public void deleteComments(String uuid, Long commentsId) {

        commentsRepository.findByIdAndUuid(commentsId, uuid)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST));

        commentsRepository.deleteById(commentsId);
    }

    @Override
    public CommentsPaginationDto getCommentsByEpisodesId(String uuid, Long episodesId,
        Pageable pageable) {

        Page<Comments> commentsPage = commentsRepository.findAllByEpisodesIdOrderByCreateDateDesc(
            episodesId, pageable);
        List<ResponseCommentsDto> responseCommentsDtoList = new ArrayList<>();

        ZoneId seoulZone = ZoneId.of("Asia/Seoul");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        commentsPage.forEach(comments -> {
            boolean myComment = uuid.equals(comments.getUuid());
            boolean recent = comments.getCreateDate().isAfter(LocalDateTime.now().minusDays(1));

            ResponseCommentsDto responseCommentsDto = new ResponseCommentsDto(comments, myComment,
                recent);

            responseCommentsDto.setFormattedDate(
                comments.getCreateDate().atZone(seoulZone).format(formatter));

            responseCommentsDtoList.add(responseCommentsDto);
        });

        PaginationDto paginationDto = PaginationDto.builder()
            .page(commentsPage.getNumber())
            .size(commentsPage.getSize())
            .totalElements(commentsPage.getTotalElements())
            .totalPage(commentsPage.getTotalPages())
            .build();

        return new CommentsPaginationDto(responseCommentsDtoList, paginationDto);
    }

    @Override
    public CommentsPaginationDto getCommentsByNovelsId(String uuid, Long novelsId,
        Pageable pageable) {

        Page<Comments> commentsPage = commentsRepository.findAllByNovelsIdOrderByCreateDateDesc(
            novelsId, pageable);
        List<ResponseCommentsDto> responseCommentsDtoList = new ArrayList<>();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        commentsPage.forEach(comments -> {
            boolean myComment = uuid.equals(comments.getUuid());
            boolean recent = comments.getCreateDate().isAfter(LocalDateTime.now().minusDays(1));
            ResponseCommentsDto responseCommentsDto = new ResponseCommentsDto(comments, myComment,
                recent);

            responseCommentsDto.setFormattedDate(comments.getCreateDate().format(formatter));

            responseCommentsDtoList.add(responseCommentsDto);
        });

        PaginationDto paginationDto = PaginationDto.builder()
            .page(commentsPage.getNumber())
            .size(commentsPage.getSize())
            .totalElements(commentsPage.getTotalElements())
            .totalPage(commentsPage.getTotalPages())
            .build();

        return new CommentsPaginationDto(responseCommentsDtoList, paginationDto);
    }


}
